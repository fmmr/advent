package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day19Test {

    @Nested
    inner class Init {
        @Test
        fun `19,1,live,init`() {
            report {
                Day19.partOne(3017957) to 1841611
            }
        }

        @Test
        fun `19,1,test`() {
            report {
                Day19.partOne(5) to 3
            }
        }

        @Test
        fun `19,2,live,test`() {
            report {
                Day19.partTwo(5) to 2
            }
        }

        @Test
        fun `19,2,live,init`() {
            report {
                Day19.partTwo(3017957) to 2
            }
        }
    }
}
