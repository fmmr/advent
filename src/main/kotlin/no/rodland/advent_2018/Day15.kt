package no.rodland.advent_2018


import no.rodland.advent.*
import no.rodland.advent_2018.Day15.Team.ELF
import no.rodland.advent_2018.Day15.Team.GOBLIN
import java.util.*


// after struggling a really long time, I finally gave up (see last version of this file), and read
// through https://todd.ginsberg.com/post/advent-of-code/2018/day15/ excellent blogpost on Day 15 and adopted (i.e. copied)
// his solution to mine
object Day15 {
    fun partOne(list: List<String>): Int {
        val cave: Cave = init(list)
        val creatures: List<Creature> = Creature.findCreatures(cave)

        val rounds = fight(creatures, cave)
        return creatures.filterNot { it.dead() }.sumOf { it.hitPoints } * rounds
    }

    fun partTwo(list: List<String>): Int {
        return generateSequence(4, Int::inc).map { _ ->
            // Reset
            val cave: Cave = init(list)
            val creatures: List<Creature> = Creature.findCreatures(cave, 19)
            val elves = creatures.count { it.team == ELF }
            val rounds = fight(creatures, cave)
            val elvesSurvivors = creatures.filterNot { it.dead() }.count { it.team == ELF }
            val dead = elves - elvesSurvivors

            println("$dead DEAD elves after $rounds rounds.  org: $elves, surv: $elvesSurvivors")

            if (creatures.filter { it.team == ELF }.none { it.dead() }) {
                creatures.filterNot { it.dead() }.sumOf { it.hitPoints } * rounds
            } else {
                null
            }
        }.filterNotNull().first()
    }

    private fun fight(creatures: List<Creature>, cave: Cave): Int {
        var rounds = 0
        while (round(creatures, cave)) {
            rounds++
        }
        return rounds
    }


    private fun round(creatures: List<Creature>, cave: Cave): Boolean {
        // Fighters need to be in order at the start of the round.
        // This is a sequence because we can lazily remove dead fighers before their turn,
        // otherwise we have to manually check.
        creatures.sorted().asSequence().filterNot { it.dead() }.forEach { creature ->
            // Check for premature end of the round - nobody left to fight
            if (!keepFighting(creatures)) {
                return false
            }

            // If we are already in range, stop moving.
            var target: Creature? = creature.inRange(creatures).firstOrNull()
            if (target == null) {
                // Movement
                val path = creature.findPathToBestEnemyAdjacentSpot(creatures, cave)
                if (path.isNotEmpty()) {
                    creature.move(path.first(), cave)
                }
                // Find target
                target = creature.inRange(creatures).firstOrNull()
            }

            // Fight if we have a target
            target?.let {
                creature.attack(it, cave)
            }
        }
        return true // Round ended at its natural end
    }

    private fun keepFighting(creatures: List<Creature>): Boolean = creatures.filterNot { it.dead() }.distinctBy { it.team }.count() > 1

    fun init(list: List<String>): Cave = list.map { it.toCharArray() }.toTypedArray()

    data class Creature(val team: Team, val name: String, var pos: Pos, val power: Int = 3, var hitPoints: Int = 200) : Comparable<Creature> {
        override fun compareTo(other: Creature): Int = pos.compareTo(other.pos)

        fun dead() = hitPoints <= 0

        fun hit(other: Creature) {
            other.hitPoints -= power
        }

        // Enemies are in range if they are not me, neither of us is dead,
        // we are not on the same team, and only 1 square away
        fun inRange(others: List<Creature>): List<Creature> =
            others.filter {
                it != this &&
                    !this.dead() &&
                    !it.dead() &&
                    it.team != this.team &&
                    this.pos.distanceTo(it.pos) == 1
            }
                .sortedWith(compareBy({ it.hitPoints }, { it.pos }))

        fun findPathToBestEnemyAdjacentSpot(creatures: List<Creature>, cave: Cave): Path =
            pathToAnyEnemy(enemyAdjacentOpenSpots(creatures, cave), cave)

        private fun pathToAnyEnemy(enemies: Set<Pos>, cave: Cave): Path {
            val seen: MutableSet<Pos> = mutableSetOf(pos)
            val paths: Deque<Path> = ArrayDeque()

            // Seed the queue with each of our neighbors, in reading order (that's important)
            pos.neighbourCellsReadingOrder()
                .filter { cave[it] == '.' }
                .forEach { paths.add(listOf(it)) }

            // While we still have paths to examine, and haven't found the answer yet...
            while (paths.isNotEmpty()) {
                val path: Path = paths.removeFirst()
                val pathEnd: Pos = path.last()

                // If this is one of our destinations, return it
                if (pathEnd in enemies) {
                    return path
                }

                // If this is a new path, create a set of new paths from it for each of its
                // cardinal direction (again, in reader order), and add them all back
                // to the queue.
                if (pathEnd !in seen) {
                    seen.add(pathEnd)
                    pathEnd.neighbourCellsReadingOrder()
                        .filter { cave[it] == '.' }
                        .filterNot { it in seen }
                        .forEach { paths.add(path + it) }
                }
            }
            return emptyList()
        }


        private fun enemyAdjacentOpenSpots(creatures: List<Creature>, cave: Cave): Set<Pos> =
            creatures
                .filterNot { it.dead() }
                .filterNot { it.team == team }
                .flatMap { it.pos.neighbourCellsReadingOrder().filter { neighbor -> cave[neighbor.y][neighbor.x] == '.' } }
                .toSet()

        fun attack(target: Creature, cave: Cave) {
            hit(target)
            if (target.dead()) {
                cave[target.pos] = '.'
            }
        }

        fun move(newPos: Pos, cave: Cave) {
            cave[pos] = '.'
            cave[newPos] = team.char
            pos = newPos
        }

        companion object {
            fun findCreatures(cave: Cave, elvesAttackPower: Int = 3): List<Creature> =
                cave
                    .mapIndexed { y, row ->
                        row.mapIndexed { x, spot ->
                            spot.toTeam()?.let { team ->
                                if (team == ELF) {
                                    Creature(team, "${team}_$x,$y", Pos(x, y), power = elvesAttackPower)
                                } else {
                                    Creature(team, "${team}_$x,$y", Pos(x, y))
                                }
                            }
                        }
                    }
                    .flatten()
                    .filterNotNull()
        }
    }


    enum class Team(val char: Char) {
        ELF('E'), GOBLIN('G');
    }

}


fun Char.toTeam(): Day15.Team? {
    return when (this) {
        'G' -> GOBLIN
        'E' -> ELF
        else -> null
    }
}

