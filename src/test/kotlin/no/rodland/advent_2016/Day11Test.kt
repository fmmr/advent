package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
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

    @Nested
    inner class Init {
        @Test
        fun `11,1,live,init`() {
            report {
                Day11.partOne(data11) to 37
            }
        }

        @Test
        fun `11,2,live,init`() {
            report {
                Day11.partTwo(data11) to 61
            }
        }
    }

}
