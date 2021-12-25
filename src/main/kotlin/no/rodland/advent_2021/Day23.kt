package no.rodland.advent_2021

import no.rodland.advent.Pos
import no.rodland.advent_2021.Day23.Amphipod.Amber
import no.rodland.advent_2021.Day23.Amphipod.Bronze
import no.rodland.advent_2021.Day23.Amphipod.Copper
import no.rodland.advent_2021.Day23.Amphipod.Desert
import no.rodland.advent_2021.Day23.Amphipod.Empty
import kotlin.math.absoluteValue


@Suppress("UNUSED_PARAMETER")
object Day23 {

    val topRow = charArrayOf('0', '1', 'A', '3', 'B', '5', 'C', '7', 'D', '9', 'X')

    fun partOne(list: List<String>): Int {
        val rooms = Rooms.toRooms(list)
        println(rooms.pretty())
        println(rooms.amphipodsNotInCorrectSpace())

        return 2
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }


    class Rooms(val list: List<List<Amphipod>>) : List<List<Amphipod>> by list {

        fun solve(): Pair<Rooms, Int> {
            if (solved()) {
                return this to 0
            }
            val (canMoveDirectly, tryOut) = amphipodsNotInCorrectSpace().partition { available(it.amphipod) }
            if (canMoveDirectly.isNotEmpty()) {
                return canMoveDirectly.fold(this to 0) { acc, posAmphipod ->
                    val move = moveHome(posAmphipod)
                    move.first to (acc.second + move.second)
                }
            }

            val (topRow, otherPlaces) = tryOut.partition { it.pos.y == TOP }




            return this to 0
        }

        private fun moveHome(posAmphipod: PosAmphipod): Pair<Rooms, Int> {
            val (amphipod, pos) = posAmphipod
            val destX = get(amphipod.room).mapIndexed { index, a -> index to a }.filter { it.second == Empty }.minOf { it.first }
            val cost = cost(pos, Pos(destX, amphipod.room))
            return Rooms(mapIndexed { y, list: List<Amphipod> ->
                list.mapIndexed { x, a ->
                    if (Pos(x, y) == posAmphipod.pos) {
                        Empty
                    } else if (Pos(x, y) == Pos(destX, amphipod.room)) {
                        posAmphipod.amphipod
                    } else {
                        a
                    }
                }
            }) to cost
        }

        fun top(): List<Amphipod> = get(TOP)

        fun emptySpaces(): List<Pos> {
            return flatMapIndexed { y: Int, amphipods: List<Amphipod> ->
                amphipods.mapIndexed { x: Int, amphipod: Amphipod ->
                    amphipod to Pos(x, y)
                }.filter { it.first == Empty }
                    .map { it.second }
            }
        }


        fun amphipodsNotInCorrectSpace(): List<PosAmphipod> {
            val inRooms = dropLast(1).flatMapIndexed { y: Int, list: List<Amphipod> ->
                val (rightRoom, wrongRoom) = list.mapIndexed { x, a -> PosAmphipod(a, Pos(x, y)) }.partition { it.amphipod.room == y }
                val rightRoomButBlocksOthers = rightRoom.filter { posAmphipod ->
                    val room = posAmphipod.pos.y
                    val x = posAmphipod.pos.x
                    wrongRoom.filter { it.pos.y == room }.any { it.pos.x > x }
                }
                rightRoomButBlocksOthers + wrongRoom
            }
            val fromTop = top().mapIndexed { x, a -> PosAmphipod(a, Pos(x, TOP)) }.filter { it.amphipod != Empty }
            return inRooms + fromTop
        }

        private fun cost(source: Pos, destination: Pos): Int {
            var c = 0
            if (source.y == TOP && destination.y == TOP) {
                error("Illegal move from top to top")
            }

            // get up
            if (source.y != TOP) {
                c += (1 + source.x)
            }

            // move along top
            val sourcePosTopRow = roomIndexInTopRow(source)
            val destPosTopRow = roomIndexInTopRow(destination)
            c += (sourcePosTopRow - destPosTopRow).absoluteValue

            // get down
            if (destination.y != TOP) {
                c += (1 + destination.y)
            }
            return c
        }

        private fun roomIndexInTopRow(pos: Pos) = when (pos.y) {
            0 -> 2
            1 -> 4
            2 -> 6
            3 -> 8
            4 -> pos.x
            else -> error("wrong room ${pos.y}")
        }

        private fun solved(): Boolean {
            return get(0).all { it == Amber } && get(1).all { it == Bronze } && get(2).all { it == Copper } && get(3).all { it == Desert }
        }


        fun available(amphipod: Amphipod): Boolean {
            val occupants = get(amphipod.room)
            return occupants.all { it == amphipod || it == Empty } && occupants.any { it == Empty }
        }

        companion object {
            const val TOP = 4
            fun toRooms(list: List<String>, top: List<Amphipod> = (1..11).map { Empty }): Rooms {
                val rooms = list.map { it.toCharArray().map { Amphipod.fromChar(it) } }
                return Rooms(rooms + listOf(top))
            }
        }

        fun pretty(): String {
            val top = get(4).map { it.code }.joinToString("")
            return """
                 #############
                 #$top#
                 ###${get(0)[0].code}#${get(1)[0].code}#${get(2)[0].code}#${get(3)[0].code}###
                   #${get(0)[1].code}#${get(1)[1].code}#${get(2)[1].code}#${get(3)[1].code}#
                   #########
               """.trimIndent()
        }
    }


    data class PosAmphipod(val amphipod: Amphipod, val pos: Pos) {
        override fun toString(): String {
            return "[${amphipod.code} (${pos.x},${pos.y})]"
        }
    }

    enum class Amphipod(val code: Char, val energy: Int, val room: Int) {
        Amber('A', 1, 0),
        Bronze('B', 10, 1),
        Copper('C', 100, 2),
        Desert('D', 1000, 3),
        Empty('.', 0, 4);

        fun steps(i: Int): List<Amphipod> = (1..i).map { this }

        companion object {
            fun fromChar(char: Char): Amphipod = when (char) {
                'A' -> Amber
                'B' -> Bronze
                'C' -> Copper
                'D' -> Desert
                else -> error("unknown $char")
            }
        }

    }
}


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