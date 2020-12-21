package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day21Test {
    val data21 = "2020/input_21.txt".readFile()
    val test21 = listOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)",
    )

    @Nested
    inner class Init {
        @Test
        fun `21,1,live,init`() {
            report {
                Day21.partOne(data21) to 2569
            }
        }

        @Test
        fun `21,2,live,init`() {
            report {
                Day21.partTwo(data21) to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test`() {
            report {
                Day21.partOne(test21) to 5
            }
        }

        @Test
        fun `21,1,live,1`() {
            report {
                Day21.partOne(data21) to 2569
            }
        }

        @Test
        fun `21,1,live,2`() {
            report {
                Day21.partOne(data21) to 2569
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,test`() {
            report {
                Day21.partTwo(test21) to 2
            }
        }

        @Test
        fun `21,2,live,1`() {
            report {
                Day21.partTwo(data21) to 2
            }
        }

        @Test
        fun `21,2,live,2`() {
            report {
                Day21.partTwo(data21) to 2
            }
        }
    }
}
