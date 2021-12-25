package no.rodland.advent_2021

import kotlin.math.abs

// copied from https://github.com/dfings/advent-of-code/blob/main/src/2021/problem_23.main.kts
// TODO - see comments below code and continue fixing my own.
object Day23 {

    val topRow = charArrayOf('0', '1', 'A', '3', 'B', '5', 'C', '7', 'D', '9', 'X')
    val regex = Regex(".*([ABCD]).*([ABCD]).*([ABCD]).*([ABCD])")

    fun partOne(list: List<String>): Int {
        val amphipods = list.parse()
        return solve(State(amphipods, 0)).let {
            println(it)
            it.totalEnergyCost
        }
    }

    fun partTwo(list: List<String>): Int {
        val input = listOf(list[0], list[1], list[2], "  #D#C#B#A#", "  #D#B#A#C#", list[3], list[4])
        val amphipods = input.parse()
        return solve(State(amphipods, 0)).let {
            println(it)
            it.totalEnergyCost
        }
    }

    private fun List<String>.parse() = drop(2).dropLast(1).flatMapIndexed { index, value ->
        println(value)
        val (a, b, c, d) = checkNotNull(regex.find(value)).destructured
        listOf(Amphipod(a.toType(), Point(2, 1 + index)), Amphipod(b.toType(), Point(4, 1 + index)),
            Amphipod(c.toType(), Point(6, 1 + index)), Amphipod(d.toType(), Point(8, 1 + index)))
    }

    enum class Type(val code: String, val roomX: Int, val cost: Int) {
        AMBER("A", 2, 1),
        BRONZE("B", 4, 10),
        COPPER("C", 6, 100),
        DESERT("D", 8, 1000),
    }

    data class Point(val x: Int, val y: Int)
    data class Amphipod(val type: Type, val p: Point)
    data class State(val amphipods: List<Amphipod>, val totalEnergyCost: Int)

    fun Point.manhattanDistance(p: Point): Int = abs(x - p.x) + abs(y - p.y)

    val HALLWAY = listOf(Point(0, 0), Point(1, 0), Point(3, 0), Point(5, 0), Point(7, 0), Point(9, 0), Point(10, 0))

    fun State.move(a: Amphipod, to: Point) = State(
        amphipods.toMutableList().apply {
            this[indexOf(a)] = a.copy(p = to)
            sortBy { it.hashCode() } // Need some consistent order for dedupe purposes.
        },
        totalEnergyCost + a.type.cost * a.p.manhattanDistance(to)
    )

    fun State.successors(slotsPerRoom: Int) = sequence {
        fun Amphipod.roomOnlyHasCorrectTypes() =
            amphipods.none { it.p.x == type.roomX && it.type != type }

        fun Amphipod.shouldStayPut() =
            p.x == type.roomX && roomOnlyHasCorrectTypes()

        fun Amphipod.canMoveThroughHall(x: Int) =
            (p.x < x && amphipods.none { it.p.y == 0 && it.p.x > p.x && it.p.x <= x } ||
                (p.x > x && amphipods.none { it.p.y == 0 && it.p.x < p.x && it.p.x >= x }))

        fun Amphipod.canMoveToHall() =
            p.y > 0 && amphipods.none { p.x == it.p.x && p.y > it.p.y }

        fun Amphipod.canMoveToRoom() =
            p.y == 0 && canMoveThroughHall(type.roomX) && roomOnlyHasCorrectTypes()

        fun Amphipod.firstOpenSlotInRoom(): Point {
            var minOccupiedY = slotsPerRoom + 1
            amphipods.forEach { if (it.p.x == type.roomX && it.p.y < minOccupiedY) minOccupiedY = it.p.y }
            return Point(type.roomX, minOccupiedY - 1)
        }

        amphipods.forEach { a ->
            when {
                a.shouldStayPut() -> {}
                a.canMoveToHall() -> HALLWAY.forEach { if (a.canMoveThroughHall(it.x)) yield(move(a, it)) }
                a.canMoveToRoom() -> yield(move(a, a.firstOpenSlotInRoom()))
                else -> {}
            }
        }
    }

    fun State.done() = amphipods.none { it.p.x != it.type.roomX }

    fun String.toType() = Type.values().single { this == it.code }

    data class Solution(val totalEnergyCost: Int, val statesExplored: Int, val maxFrontierSize: Int) {
        override fun toString(): String = """Total energy cost: ${totalEnergyCost}
            States explored: ${statesExplored}
            Max frontier size: ${maxFrontierSize}""".trimIndent()
    }

    fun solve(initialState: State): Solution {
        val slotsPerRoom = initialState.amphipods.size / 4
        val frontier = java.util.PriorityQueue<State>() { a, b ->
            a.totalEnergyCost.compareTo(b.totalEnergyCost)
        }
        frontier += initialState
        val seen = HashSet<List<Amphipod>>()
        var maxFrontierSize = 0
        while (!frontier.isEmpty()) {
            if (frontier.size > maxFrontierSize) maxFrontierSize = frontier.size
            val state = frontier.poll()
            when {
                state.amphipods in seen -> {}
                state.done() -> return Solution(state.totalEnergyCost, seen.size, maxFrontierSize)
                else -> {
                    seen += state.amphipods
                    state.successors(slotsPerRoom).forEach { frontier += it }
                }
            }
        }
        error("No solution!")
    }
}


//class Rooms(val list: List<List<Amphipod>>) : List<List<Amphipod>> by list {
//
//    fun solve(): Pair<Rooms, Int> {
//        if (solved()) {
//            return this to 0
//        }
//        val (canMoveDirectly, tryOut) = amphipodsNotInCorrectSpace().partition { available(it.amphipod) }
//        if (canMoveDirectly.isNotEmpty()) {
//            return canMoveDirectly.fold(this to 0) { acc, posAmphipod ->
//                val move = moveHome(posAmphipod)
//                move.first to (acc.second + move.second)
//            }
//        }
//
//        val (topRow, otherPlaces) = tryOut.partition { it.pos.y == TOP }
//
//
//
//
//        return this to 0
//    }
//
//    private fun moveHome(posAmphipod: PosAmphipod): Pair<Rooms, Int> {
//        val (amphipod, pos) = posAmphipod
//        val destX = get(amphipod.room).mapIndexed { index, a -> index to a }.filter { it.second == Empty }.minOf { it.first }
//        val cost = cost(pos, Pos(destX, amphipod.room))
//        return Rooms(mapIndexed { y, list: List<Amphipod> ->
//            list.mapIndexed { x, a ->
//                if (Pos(x, y) == posAmphipod.pos) {
//                    Empty
//                } else if (Pos(x, y) == Pos(destX, amphipod.room)) {
//                    posAmphipod.amphipod
//                } else {
//                    a
//                }
//            }
//        }) to cost
//    }
//
//    fun top(): List<Amphipod> = get(TOP)
//
//    fun emptySpaces(): List<Pos> {
//        return flatMapIndexed { y: Int, amphipods: List<Amphipod> ->
//            amphipods.mapIndexed { x: Int, amphipod: Amphipod ->
//                amphipod to Pos(x, y)
//            }.filter { it.first == Empty }
//                .map { it.second }
//        }
//    }
//
//
//    fun amphipodsNotInCorrectSpace(): List<PosAmphipod> {
//        val inRooms = dropLast(1).flatMapIndexed { y: Int, list: List<Amphipod> ->
//            val (rightRoom, wrongRoom) = list.mapIndexed { x, a -> PosAmphipod(a, Pos(x, y)) }.partition { it.amphipod.room == y }
//            val rightRoomButBlocksOthers = rightRoom.filter { posAmphipod ->
//                val room = posAmphipod.pos.y
//                val x = posAmphipod.pos.x
//                wrongRoom.filter { it.pos.y == room }.any { it.pos.x > x }
//            }
//            rightRoomButBlocksOthers + wrongRoom
//        }
//        val fromTop = top().mapIndexed { x, a -> PosAmphipod(a, Pos(x, TOP)) }.filter { it.amphipod != Empty }
//        return inRooms + fromTop
//    }
//
//    private fun cost(source: Pos, destination: Pos): Int {
//        var c = 0
//        if (source.y == TOP && destination.y == TOP) {
//            error("Illegal move from top to top")
//        }
//
//        // get up
//        if (source.y != TOP) {
//            c += (1 + source.x)
//        }
//
//        // move along top
//        val sourcePosTopRow = roomIndexInTopRow(source)
//        val destPosTopRow = roomIndexInTopRow(destination)
//        c += (sourcePosTopRow - destPosTopRow).absoluteValue
//
//        // get down
//        if (destination.y != TOP) {
//            c += (1 + destination.y)
//        }
//        return c
//    }
//
//    private fun roomIndexInTopRow(pos: Pos) = when (pos.y) {
//        0 -> 2
//        1 -> 4
//        2 -> 6
//        3 -> 8
//        4 -> pos.x
//        else -> error("wrong room ${pos.y}")
//    }
//
//    private fun solved(): Boolean {
//        return get(0).all { it == Amber } && get(1).all { it == Bronze } && get(2).all { it == Copper } && get(3).all { it == Desert }
//    }
//
//
//    fun available(amphipod: Amphipod): Boolean {
//        val occupants = get(amphipod.room)
//        return occupants.all { it == amphipod || it == Empty } && occupants.any { it == Empty }
//    }
//
//    companion object {
//        const val TOP = 4
//        fun toRooms(list: List<String>, top: List<Amphipod> = (1..11).map { Empty }): Rooms {
//            val rooms = list.map { it.toCharArray().map { Amphipod.fromChar(it) } }
//            return Rooms(rooms + listOf(top))
//        }
//    }
//
//    fun pretty(): String {
//        val top = get(4).map { it.code }.joinToString("")
//        return """
//                 #############
//                 #$top#
//                 ###${get(0)[0].code}#${get(1)[0].code}#${get(2)[0].code}#${get(3)[0].code}###
//                   #${get(0)[1].code}#${get(1)[1].code}#${get(2)[1].code}#${get(3)[1].code}#
//                   #########
//               """.trimIndent()
//    }
//}
//
//
//data class PosAmphipod(val amphipod: Amphipod, val pos: Pos) {
//    override fun toString(): String {
//        return "[${amphipod.code} (${pos.x},${pos.y})]"
//    }
//}
//
//enum class Amphipod(val code: Char, val energy: Int, val room: Int) {
//    Amber('A', 1, 0),
//    Bronze('B', 10, 1),
//    Copper('C', 100, 2),
//    Desert('D', 1000, 3),
//    Empty('.', 0, 4);
//
//    fun steps(i: Int): List<Amphipod> = (1..i).map { this }
//
//    companion object {
//        fun fromChar(char: Char): Amphipod = when (char) {
//            'A' -> Amber
//            'B' -> Bronze
//            'C' -> Copper
//            'D' -> Desert
//            else -> error("unknown $char")
//        }
//    }
//
//}
//}


//        return listOf(
//            Amber.steps(4), Amber.steps(3), Amber.steps(9), Amber.steps(2),
//            Bronze.steps(5), Bronze.steps(5),
//            Copper.steps(5), Copper.steps(6),
//            Desert.steps(3), Desert.steps(7), Desert.steps(9)
//        )
//        return listOf(
//            Copper.steps(3), Amber.steps(3), Copper.steps(3), Desert.steps(10), Amber.steps(5), Desert.steps(7),
//            Amber.steps(8), Bronze.steps(5), Bronze.steps(5),Copper.steps(5),Copper.steps(8),
//        )
//        return listOf(
//            Copper.steps(3), Amber.steps(3),Copper.steps(2),Desert.steps(10),Amber.steps(5),Desert.steps(7),
//            Amber.steps(8), Bronze.steps(5), Bronze.steps(5),Copper.steps(5),Copper.steps(7),
//        )
//        return listOf(
//            Amber.steps(5), Copper.steps(2), Amber.steps(9), Desert.steps(8), Bronze.steps(5), Bronze.steps(5),
//            Copper.steps(4), Copper.steps(7), Desert.steps(9),Amber.steps(3),Amber.steps(3)
//        )     
//        return listOf(
//            Amber.steps(14), Copper.steps(2), Copper.steps(11), Desert.steps(17),
//            Amber.steps(6)
//        )
//            .flatten()
//            .sumOf { it.energy }
//return 2
// 19016 too high
// 18816
// 18420
// 18320
// 18270