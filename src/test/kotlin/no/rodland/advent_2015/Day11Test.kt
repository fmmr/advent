package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import no.rodland.advent_2015.Day11.hasIncreasingLetters
import no.rodland.advent_2015.Day11.pairs
import no.rodland.advent_2015.Day11.valid
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day11Test {
    val data11 = "vzbxkghb"
    val data12 = "vzbxxyzz"

    @Nested
    inner class Init {
        @Test
        fun `11,1,live,init`() {
            report {
                Day11.partOne(data11) to "vzbxxyzz"
            }
        }

        @Test
        fun `11,2,live,init`() {
            report {
                Day11.partOne(data12) to "vzcaabcc"
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `11,1,test,1`() {
            report {
                Day11.partOne("abcdefgh") to "abcdffaa"
            }
        }

        @Test
        @Slow(2400)
        fun `11,1,test,2`() {
            report {
                Day11.partOne("ghijklmn") to "ghjaabcc"
            }
        }


        @Test
        fun `11,1,increasing,yes`() {
            report {
                "dssjkdsdghidsw".hasIncreasingLetters() to true
            }
        }

        @Test
        fun `11,1,increasing,no`() {
            report {
                "sdwoenfwos".hasIncreasingLetters() to false
            }
        }

        @Test
        fun `11,1,pairs,yes_1`() {
            report {
                "dsbbkdaaghidsw".pairs() to true
            }
        }

        @Test
        fun `11,1,pairs,no_1`() {
            report {
                "sdwoenfbbwos".pairs() to false
            }
        }

        @Test
        fun `11,1,pairs,no_2`() {
            report {
                "sdwoenfbbbwos".pairs() to false
            }
        }

        @Test
        fun `11,1,pairs,yes_2`() {
            report {
                "abcdffaa".pairs() to true
            }
        }

        @Test
        fun `11,1,valid,no_1`() {
            report {
                "hijklmmn".valid() to false
            }
        }

        @Test
        fun `11,1,valid,no_2`() {
            report {
                "abbceffg".valid() to false
            }
        }

        @Test
        fun `11,1,valid,no_3`() {
            report {
                "abbcegjk".valid() to false
            }
        }

        @Test
        fun `11,1,valid,yes_4`() {
            report {
                "abcdffaa".valid() to true
            }
        }

        @Test
        fun `11,1,valid,yes_5`() {
            report {
                "ghjaabcc".valid() to true
            }
        }


        @Test
        fun `11,1,live,1`() {
            report {
                Day11.partOne(data11) to "vzbxxyzz"
            }
        }

        @Test
        fun `11,1,live,2`() {
            report {
                Day11.partOne(data11) to "vzbxxyzz"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `11,2,live,1`() {
            report {
                Day11.partOne(data12) to "vzcaabcc"
            }
        }

        @Test
        fun `11,2,live,2`() {
            report {
                Day11.partOne(data12) to "vzcaabcc"
            }
        }
    }
}
