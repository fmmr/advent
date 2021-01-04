package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day13Test {
    val data13 = "2015/input_13.txt".readFile()
    val test13 = listOf(
        "Alice would gain 54 happiness units by sitting next to Bob.",
        "Alice would lose 79 happiness units by sitting next to Carol.",
        "Alice would lose 2 happiness units by sitting next to David.",
        "Bob would gain 83 happiness units by sitting next to Alice.",
        "Bob would lose 7 happiness units by sitting next to Carol.",
        "Bob would lose 63 happiness units by sitting next to David.",
        "Carol would lose 62 happiness units by sitting next to Alice.",
        "Carol would gain 60 happiness units by sitting next to Bob.",
        "Carol would gain 55 happiness units by sitting next to David.",
        "David would gain 46 happiness units by sitting next to Alice.",
        "David would lose 7 happiness units by sitting next to Bob.",
        "David would gain 41 happiness units by sitting next to Carol.",
    )

    @Nested
    inner class Init {
        @Test
        fun `13,1,live,init`() {
            report {
                Day13.partOne(data13) to 733
            }
        }

        @Test
        @Slow(500)
        fun `13,2,live,init`() {
            report {
                Day13.partTwo(data13) to 725
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `13,1,test`() {
            report {
                Day13.partOne(test13) to 330
            }
        }

        @Test
        fun `13,1,live,1`() {
            report {
                Day13.partOne(data13) to 733
            }
        }

        @Test
        fun `13,1,live,2`() {
            report {
                Day13.partOne(data13) to 733
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `13,2,test`() {
            report {
                Day13.partTwo(test13) to 286
            }
        }

        @Test
        @Slow(500)
        fun `13,2,live,1`() {
            report {
                Day13.partTwo(data13) to 725
            }
        }

        @Test
        @Slow(500)
        fun `13,2,live,2`() {
            report {
                Day13.partTwo(data13) to 725
            }
        }
    }
}
