package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day12Test {
    val data12 = "2015/input_12.txt".readFileAsOneString()

    @Nested
    inner class Init {
        @Test
        fun `12,1,live,init`() {
            report {
                Day12.partOne(data12) to 111754
            }
        }

        @Test
        fun `12,2,live,init`() {
            report {
                Day12.partTwo(data12) to 65402
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `12,1,test,1`() {
            report {
                Day12.partOne("{\"a\":{\"b\":4},\"c\":-1}") to 3
            }
        }

        @Test
        fun `12,1,test,2`() {
            report {
                Day12.partOne("[1,2,3]") to 6
            }
        }

        @Test
        fun `12,1,test,3`() {
            report {
                Day12.partOne("[[[3]]]") to 3
            }
        }

        @Test
        fun `12,1,test,4`() {
            report {
                Day12.partOne("{\"a\":2,\"b\":4}") to 6
            }
        }

        @Test
        fun `12,1,test,5`() {
            report {
                Day12.partOne("{\"a\":[-1,1]}") to 0
            }
        }

        @Test
        fun `12,1,test,6`() {
            report {
                Day12.partOne("[]") to 0
            }
        }

        @Test
        fun `12,1,test,7`() {
            report {
                Day12.partOne("{}") to 0
            }
        }

        @Test
        fun `12,1,test,8`() {
            report {
                Day12.partOne("[-1,{\"a\":1}]") to 0
            }
        }

        @Test
        fun `12,1,live,1`() {
            report {
                Day12.partOne(data12) to 111754
            }
        }

        @Test
        fun `12,1,live,2`() {
            report {
                Day12.partOne(data12) to 111754
            }
        }
    }

    @Nested
    inner class `Part 2` {

        @Test
        fun `12,2,test,3_a`() {
            report {
                Day12.partTwo("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}") to 0
            }
        }

        @Test
        fun `12,2,test,3_b`() {
            report {
                Day12.partTwo("{\"e\":[1,2,3,4],\"d\":\"red\",\"f\":5}") to 0
            }
        }

        @Test
        fun `12,2,live,1`() {
            report {
                Day12.partTwo(data12) to 65402
            }
        }

        @Test
        fun `12,2,live,2`() {
            report {
                Day12.partTwo(data12) to 65402
            }
        }
    }
}
