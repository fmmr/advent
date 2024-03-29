package no.rodland.advent_2016

import no.rodland.advent.combinations

typealias Structure = Array<Array<Day11.Stuff?>>


@Suppress("UNUSED_PARAMETER")
object Day11 {
    // optimzations work for live data, but not for tests for some reason.
    const val optimization1 = true
    const val optimization2 = true
    const val optimization3 = true

    var count = 0
    private var duplicatecount = 0
    private val seen = mutableSetOf<String>()

    // https://blog.jverkamp.com/2016/12/11/aoc-2016-day-11-radiation-avoider/
    // https://www.reddit.com/r/adventofcode/comments/5hoia9/2016_day_11_solutions/#s
    fun partOne(list: List<String>): Int {
        val str1 = list.first()
        return doIt(str1, list)
    }

    fun partTwo(list: List<String>): Int {
        val str1 = "The first floor contains a elerium generator, a elerium-compatible microchip, a dilithium generator, a dilithium-compatible microchip, a strontium generator, a strontium-compatible microchip, a plutonium generator, and a plutonium-compatible microchip."
        return doIt(str1, list)
    }

    private fun doIt(str1: String, list: List<String>): Int {
        val (ar, columns) = getInitialState(listOf(str1) + list.drop(1))
        val endState = bfs(ar, columns)
        println("count: $count")
        println("dups: $duplicatecount")
        println("seen: ${seen.size}")
        return endState!!.steps.size
    }

    private fun bfs(ar: Array<Array<Stuff?>>, columns: List<String>): State? {
        val queue = ArrayDeque(listOf(State(ar, columns)))
        while (queue.isNotEmpty()) {
            val s = queue.removeFirst()
            count++
            if (s.isEndState()) {
                return s
            }
            s.nextStates().filterNot { seen.duplicate(it) }.forEach {
                queue.addLast(it)
                queue.addLast(it)
                seen.addState(it)
            }
        }
        return null
    }


    data class Stuff(val element: String, val type: Type) : Comparable<Stuff> {
        constructor(str: String) : this(str.split("_")[0], Type.fromStr(str.split("_")[1]))

        override fun toString(): String {
            return "${element.first().uppercaseChar()}${type.toString().first()}"
        }

        fun sameElement(other: Stuff?) = element == other?.element

        override fun compareTo(other: Stuff) = if (element.compareTo(other.element) == 0) {
            if (type == Type.GENERATOR) -1 else 1
        } else element.compareTo(other.element)
    }

    @Suppress("ArrayInDataClass")
    data class State(private val structure: Structure, private val columns: List<String>, val steps: List<State> = emptyList()) {
        private val elevatorCol = columns.indexOf("EE")

        fun isEndState() = structure.last().none { it == null }

        override fun toString() = structure.joinToString(";") { row ->
            val nulls = row.count { it == null }
            val chips = row.getMicrochips()
            val gens = row.getGenerators()
            val pairs = chips.count { chip -> (gens.any { it?.sameElement(chip) ?: false }) }
            listOf(nulls, pairs, chips.size - pairs, gens.size - pairs).joinToString(",")
        }

        fun nextStates(): List<State> {
            return when (val floor = getElevatorRow()) {
                0 -> getNextStates(floor, floor + 1)
                structure.size - 1 -> getNextStates(floor, floor - 1)
                else -> getNextStates(floor, floor - 1) + getNextStates(floor, floor + 1)
            }
        }

        private fun getElevatorRow() = structure.map { it[elevatorCol] }.indexOfFirst { it?.type == Type.ELEVATOR }

        private fun getElevator() = structure.map { it[elevatorCol] }.first { it?.type == Type.ELEVATOR }

        private fun getNextStates(currentFloor: Int, newFloor: Int): List<State> {
            val optimized = getMoves(newFloor, currentFloor)

            return optimized
                    .map { comps ->
                        val structureCopy = structure.makeDeepCopy()
                        comps.forEach { comp ->
                            val idx = structureCopy[currentFloor].indexOf(comp)
                            structureCopy[currentFloor][idx] = null
                            structureCopy[newFloor][idx] = comp
                        }
                        State(structureCopy, columns, steps + this)
                    }
                    .filter { it.isValid() }
        }

        private fun getMoves(newFloor: Int, currentFloor: Int, isUp: Boolean = newFloor > currentFloor): List<List<Stuff?>> {
            val components = structure[currentFloor].mapNotNull { it }.filterNot { it.type == Type.ELEVATOR }

            // all combos, including elevator on each step
            val moves = (components.map { listOf(it) } + components.combinations(2)).map { it + getElevator() }

            if (optimization1) {  // if the floor below is empty don't bother moving stuff down  (should maybe be all floors below current)
                if (!isUp && structure[newFloor].all { it == null }) {
                    return emptyList()
                }
            }
            if (optimization2 && isUp) {  // do not move 1 elements up if youcan move 2
                if (moves.any { it.size == 3 }) {
                    return moves.filterNot { it.size == 2 }
                }
            }
            if (optimization3 && !isUp) { // do not move 2 elements up if youcan move 1
                if (moves.any { it.size == 2 }) {
                    return moves.filterNot { it.size == 3 }
                }
            }
            return moves
        }

        fun isValid(): Boolean {
            // if chip does not have it's gen AND there are other generators
            val mapIndexed = structure.map { row ->
                val chips = row.getMicrochips()
                val gens = row.getGenerators()
                chips.any { chip -> (gens.none { it?.sameElement(chip) ?: false } && gens.isNotEmpty()) }
            }
            return mapIndexed.none { it }
        }
    }


    enum class Type {
        MICROCHIP, GENERATOR, ELEVATOR;

        companion object {
            fun fromStr(str: String): Type {
                return when (str) {
                    "M" -> MICROCHIP
                    "G" -> GENERATOR
                    else -> error("unable to get type from $str")
                }
            }
        }
    }

    private fun getInitialState(list: List<String>): Pair<Array<Array<Stuff?>>, List<String>> {
        val codes = list
                .map { it.replace("The (.*) floor contains ".toRegex(), "") }
                .map { it.replace(".", "") }
                .map { it.replace(",", " ") }
                .map { it.replace("a?[ ^]([^ ]*) generator".toRegex(), "$1_G ") }
                .map { it.replace("a?[ ^]([^ ]*)-compatible microchip".toRegex(), "$1_M ") }
                .map { it.replace(" and ", ",") }
                .map { it.replace(" +".toRegex(), ",") }
                .map { it.replace(",+".toRegex(), ",") }
                .map { it.replace(",$".toRegex(), "") }
                .dropLast(1)
                .map { str ->
                    str.split(",").map { Stuff(it) }
                } + listOf(emptyList())
        val elements = codes.flatten().sorted()
        val columns = elements.map { it.toString() } + "EE"

        val ar = Array(4) { Array<Stuff?>(columns.size) { null } }
        codes.forEachIndexed { floor, stuffList ->
            stuffList.forEach { stuff ->
                val idx = columns.indexOf(stuff.toString())
                ar[floor][idx] = stuff
            }
        }
        ar[0][columns.size - 1] = Stuff("Elevator", Type.ELEVATOR)
        return ar to (columns)
    }

    private fun Structure.makeDeepCopy(): Structure {
        return Array(size) { i ->
            Array(this[i].size) { j -> this[i][j] }
        }
    }

    fun Set<String>.duplicate(state: State): Boolean {
        val contains = contains(state.toString())
        duplicatecount++
        return contains
    }

    private fun MutableSet<String>.addState(state: State): Boolean {
        return add(state.toString())
    }

    private fun Array<Stuff?>.getGenerators() = filter { it?.type == Type.GENERATOR }

    private fun Array<Stuff?>.getMicrochips() = filter { it?.type == Type.MICROCHIP }

}



