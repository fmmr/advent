package no.rodland.advent_2016

import no.rodland.advent.combinations

typealias Structure = Array<Array<Day11.Stuff?>>


@Suppress("UNUSED_PARAMETER")
object Day11 {
    // optimzations work for live data, but not for tests for some reason.
    val optimization1 = true
    val optimization2 = true

    var count = 0
    var duplicatecount = 0
    val seen = mutableSetOf<List<String>>()

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
                seen.addState(it)
            }
        }
        return null
    }

    fun Set<List<String>>.duplicate(state: State): Boolean {
        // XXX need to check that we have analogue states
        val contains = contains(state.generic())
        duplicatecount++
        return contains
    }

    fun MutableSet<List<String>>.addState(state: State): Boolean {
        return add(state.generic())
    }


    data class Stuff(val element: String, val type: Type) : Comparable<Stuff> {
        constructor(str: String) : this(str.split("_")[0], typeFromStr(str.split("_")[1]))

        override fun toString(): String {
            return "${element.first().toUpperCase()}${type.toString().first()}"
        }

        fun sameElement(other: Stuff?) = element == other?.element

        override fun compareTo(other: Stuff) = if (element.compareTo(other.element) == 0) {
            if (type == Type.GENERATOR) -1 else 1
        } else element.compareTo(other.element)
    }

    private fun Array<Stuff?>.generic(): String {
        val nulls = count { it == null }
        val chips = filter { it?.type == Type.MICROCHIP }
        val gens = filter { it?.type == Type.GENERATOR }
        val pairs = chips.count { chip -> (gens.any { it?.sameElement(chip) ?: false }) }
        return listOf(nulls, pairs, chips.size - pairs, gens.size - pairs).joinToString(",")
    }

    data class State(private val structure: Structure, private val columns: List<String>, val steps: List<State> = emptyList()) {
        private val elevatorCol = columns.indexOf("EE")

        fun isEndState() = structure.last().none { it == null }


        fun generic() = structure.map { it.generic() }


        fun nextStates(): List<State> {
            val el = getElevatorRow()
            return if (el == 0) {
                getNextStates(el, 1)
            } else if (el == structure.size - 1) {
                getNextStates(el, structure.size - 2)
            } else {
                getNextStates(el, el - 1) + getNextStates(el, el + 1)
            }
        }

        private fun getElevatorRow() = structure.map { it[elevatorCol] }.indexOfFirst { it?.type == Type.ELEVATOR }
        private fun getElevator() = structure.map { it[elevatorCol] }.first { it?.type == Type.ELEVATOR }


        private fun getNextStates(currentFloor: Int, newFloor: Int): List<State> {
            val isUp = newFloor > currentFloor
            val components = structure[currentFloor].mapNotNull { it }.filterNot { it.type == Type.ELEVATOR }
            val moves = ((components.map { listOf(it) } + components.combinations(2))).map { it + getElevator() }

            if (optimization1) {  // if the floor below is empty don't bother moving stuff down
                if (!isUp && structure[newFloor].all { it == null }) {
                    return emptyList()
                }
            }

            val notOneUp = if (optimization2) {
                if (isUp) {
                    if (moves.any { it.size == 3 }) {
                        moves.filterNot { it.size == 2 }
                    } else {
                        moves
                    }
                } else {
                    if (moves.any { it.size == 2 }) {
                        moves.filterNot { it.size == 3 }
                    } else {
                        moves
                    }
                }
            } else {
                moves
            }

            return notOneUp
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

        fun isValid(): Boolean {
            // if chip does not have it's gen AND there are other generators
            val mapIndexed = structure.mapIndexed { floor, row ->
                val chips = row.filter { it?.type == Type.MICROCHIP }
                val gens = row.filter { it?.type == Type.GENERATOR }
                chips.any { chip -> (gens.none { it?.sameElement(chip) ?: false } && gens.isNotEmpty()) }
            }
            return mapIndexed.none { it }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as State

            if (!structure.contentDeepEquals(other.structure)) return false
            if (columns != other.columns) return false
            if (steps != other.steps) return false
            if (elevatorCol != other.elevatorCol) return false

            return true
        }

        override fun hashCode(): Int {
            var result = structure.contentDeepHashCode()
            result = 31 * result + columns.hashCode()
            result = 31 * result + steps.hashCode()
            result = 31 * result + elevatorCol
            return result
        }

    }

    enum class Type {
        MICROCHIP, GENERATOR, ELEVATOR;
    }

    fun typeFromStr(str: String): Type {
        return when (str) {
            "M" -> Type.MICROCHIP
            "G" -> Type.GENERATOR
            else -> error("unable to get type from $str")
        }
    }

    private fun getInitialState(list: List<String>): Pair<Array<Array<Stuff?>>, List<String>> {
        val dropLast = list
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
        val codes1 = dropLast
                .map {
                    it.split(",").map { Stuff(it) }
                } + listOf(emptyList())
        val codes = codes1
        val elements = codes.flatten().sorted()
        val columns = elements.map { it.toString() } + "EE"

        val ar = Array(4) { i ->
            Array<Stuff?>(columns.size) { null }
        }
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
}



