package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022
// Copied: https://github.com/forketyfork/aoc-2022/blob/main/src/main/kotlin/year2022/Day19.kt
object Day19 {
    fun partOne(list: List<String>): Int {
        return solution(list, 24).reduceIndexed { idx, acc, value -> acc + (idx + 1) * value }
    }

    fun partTwo(list: List<String>): Int {
        val reduce = solution(list.take(3), 32).reduce(Int::times)
        println(count)
        println(visited.size)
        return reduce
    }


    data class Blueprint(
        val id: Int,
        val oreRobotOreCost: Int,
        val clayRobotOreCost: Int,
        val obsidianRobotOreCost: Int,
        val obsidianRobotClayCost: Int,
        val geodeRobotOreCost: Int,
        val geodeRobotObsidianCost: Int
    ) {
        val maxOreCost = maxOf(oreRobotOreCost, obsidianRobotOreCost, geodeRobotOreCost, clayRobotOreCost)
    }

    data class State(
        val blueprint: Blueprint,
        val stepsLeft: Int,
        val ore: Int,
        val clay: Int,
        val obsidian: Int,
        val geode: Int,
        val oreRobots: Int,
        val clayRobots: Int,
        val obsidianRobots: Int,
        val geodeRobots: Int
    ) {
        val isConsistent
            get() = stepsLeft > 0 && ore >= 0 && clay >= 0 && obsidian >= 0

        fun canBuyOreRobot() = ore >= blueprint.oreRobotOreCost
        fun canBuyClayRobot() = ore >= blueprint.clayRobotOreCost
        fun canBuyObsidianRobot() =
            ore >= blueprint.obsidianRobotOreCost && clay >= blueprint.obsidianRobotClayCost

        fun canBuyGeodeRobot() =
            ore >= blueprint.geodeRobotOreCost && obsidian >= blueprint.geodeRobotObsidianCost

        fun sameNumberOfRobots(other: State) = oreRobots == other.oreRobots
            && clayRobots == other.clayRobots
            && obsidianRobots == other.obsidianRobots
            && geodeRobots == other.geodeRobots

        fun buyGeodeRobot() = copy(
            ore = ore - blueprint.geodeRobotOreCost,
            obsidian = obsidian - blueprint.geodeRobotObsidianCost,
            geodeRobots = geodeRobots + 1
        )

        fun buyOreRobot() = copy(
            ore = ore - blueprint.oreRobotOreCost,
            oreRobots = oreRobots + 1,
        )

        fun buyClayRobot() = copy(
            ore = ore - blueprint.clayRobotOreCost,
            clayRobots = clayRobots + 1,
        )

        fun buyObsidianRobot() = copy(
            ore = ore - blueprint.obsidianRobotOreCost,
            clay = clay - blueprint.obsidianRobotClayCost,
            obsidianRobots = obsidianRobots + 1,
        )

        fun increaseStep(prevState: State) = copy(
            stepsLeft = stepsLeft - 1,
            ore = ore + prevState.oreRobots,
            clay = clay + prevState.clayRobots,
            obsidian = obsidian + prevState.obsidianRobots,
            geode = geode + prevState.geodeRobots
        )
    }

    private val regex =
        """Blueprint (\d*): Each ore robot costs (\d*) ore. Each clay robot costs (\d*) ore. Each obsidian robot costs (\d*) ore and (\d*) clay. Each geode robot costs (\d*) ore and (\d*) obsidian.""".toRegex()


    private fun solution(input: List<String>, steps: Int) = input.map(regex::find)
        .map { result -> result!!.groupValues.drop(1).map(String::toInt) }
        .map { list -> Blueprint(list[0], list[1], list[2], list[3], list[4], list[5], list[6]) }
        .map { blueprint -> solution(null, State(blueprint, steps, 0, 0, 0, 0, 1, 0, 0, 0)) }

    var count = 0
    val visited = mutableMapOf<State, Int>()
    private fun solution(prevState: State?, state: State): Int {
        if (state in visited){
            return visited[state]!!
        }
        if (state.stepsLeft == 0) {
            return state.geode
        }
        return buildList {
            if (state.canBuyGeodeRobot()) {
                add(state.buyGeodeRobot())
            } else {
                add(state)
                if (!(prevState?.canBuyOreRobot() == true && prevState.sameNumberOfRobots(state))
                    && state.oreRobots < state.blueprint.maxOreCost
                ) {
                    add(state.buyOreRobot())
                }
                if (!(prevState?.canBuyClayRobot() == true && prevState.sameNumberOfRobots(state))
                    && state.clayRobots < state.blueprint.obsidianRobotClayCost
                ) {
                    add(state.buyClayRobot())
                }
                if (!(prevState?.canBuyObsidianRobot() == true && prevState.sameNumberOfRobots(state))
                    && state.obsidianRobots < state.blueprint.geodeRobotObsidianCost
                ) {
                    add(state.buyObsidianRobot())
                }
            }
        }.asSequence().filter { it.isConsistent }
            .map { it.increaseStep(state) }
            .map { it to solution(state, it) }
            .onEach { visited[it.first] = it.second}
            .onEach { count++ }
            .maxOf { it.second }
    }

}
