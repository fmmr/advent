import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day5Test {

    val data5 = "input/input_5.txt".readFile()[0]
    val test5 = "dabAcCaCBAcCcaDA"

    @Nested
    inner class `Part 1` {
        @Test
        fun `5,1,test`() {
            report {
                Day5.partOne(test5) to 10
            }
        }

        @Test
        @Slow(500)
        fun `5,1,live`() {
            report {
                Day5.partOne(data5) to 10450
            }
        }
    }

    @Nested
    inner class `Part 2` {

        @Test
        @Slow(2000)
        fun `5,2,live`() {
            report {
                Day5.partTwo(data5) to 4624
            }
        }

        @Test
        fun `5,2,test`() {
            report {
                Day5.partTwo(test5) to 4
            }
        }

        @Test
        fun `5,2,test,2`() {
            report {
                Day5.partTwo_take2(test5) to 4
            }
        }

        @Test
        @Slow(550)
        fun `5,2,live,2`() {
            report {
                Day5.partTwo_take2(data5) to 4624
            }
        }

        @Test
        fun `5,2,test,chriswk`() {
            report {
                Day5.partTwo_chriswk(test5) to 4
            }
        }

        @Test
        @Slow(550)
        fun `5,2,live,chriswk`() {
            report {
                Day5.partTwo_chriswk(data5) to 4624
            }
        }
    }
}

