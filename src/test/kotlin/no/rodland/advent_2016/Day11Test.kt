package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import no.rodland.advent_2016.Day11.duplicate
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day11Test {
    val data11 = listOf(
            "The first floor contains a strontium generator, a strontium-compatible microchip, a plutonium generator, and a plutonium-compatible microchip.",
            "The second floor contains a thulium generator, a ruthenium generator, a ruthenium-compatible microchip, a curium generator, and a curium-compatible microchip.",
            "The third floor contains a thulium-compatible microchip.",
            "The fourth floor contains nothing relevant.",

            )
//    val test11 = listOf(
//            "The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.",
//            "The second floor contains a hydrogen generator.",
//            "The third floor contains a lithium generator.",
//            "The fourth floor contains nothing relevant.",
//    )

    @Test
    fun `11,1,duplicates_same`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            val set = mutableSetOf(state.toString())
            set.duplicate(state) to true
        }
    }

    @Test
    fun `11,1,duplicates_similar`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            val ar2 = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar2[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar2[1][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar2[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar2[0][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar2[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state2 = Day11.State(ar2, listOf("PG", "PM", "CG", "CM", "EE"))
            val set = mutableSetOf(state.toString())
            set.duplicate(state2) to true
        }
    }

    @Test
    fun `11,1,endstate`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            state.isEndState() to false
        }
    }

    @Test
    fun `11,1,endstate_true`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[3][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[3][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[3][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[3][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[3][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            state.isEndState() to true
        }
    }

    @Test
    fun `11,1,duplicates_equals`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            val ar2 = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar2[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar2[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar2[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar2[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar2[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state2 = Day11.State(ar2, listOf("PG", "PM", "CG", "CM", "EE"))
            val set = mutableSetOf(state.toString())
            set.duplicate(state2) to true
        }
    }

    @Test
    fun `11,1,nextstates`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            val next = state.nextStates()
            next.size to 4
        }
    }

    @Test
    fun `11,1,nextstates,2`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[0][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            val next = state.nextStates()
            next.size to 1
        }
    }

    @Test
    fun `11,1,state_valid`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[1][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[0][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[1][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            state.isValid() to true
        }
    }

    @Test
    fun `11,1,state_invalid`() {
        report {
            val ar = Array(4) {
                Array<Day11.Stuff?>(5) { null }
            }
            ar[0][0] = Day11.Stuff("plutonion", Day11.Type.GENERATOR)
            ar[1][1] = Day11.Stuff("plutonion", Day11.Type.MICROCHIP)
            ar[1][2] = Day11.Stuff("cerium", Day11.Type.GENERATOR)
            ar[1][3] = Day11.Stuff("cerium", Day11.Type.MICROCHIP)
            ar[0][4] = Day11.Stuff("elevator", Day11.Type.ELEVATOR)
            val state = Day11.State(ar, listOf("PG", "PM", "CG", "CM", "EE"))
            state.isValid() to false
        }
    }

//    @Test
//    fun `11,1,test`() {
//        report {
//            Day11.partOne(test11) to 11
//        }
//    }

    @Test
    fun `11,1,part1`() {
        report {
            Day11.partOne(data11) to 37
        }
    }

    @Test
    @Slow(800)
    fun `11,2,part2`() {
        report {
            Day11.partTwo(data11) to 61
        }
    }
}


