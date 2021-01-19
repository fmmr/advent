package no.rodland.advent_2016

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day21Test {
    val data21_list = "2016/input_21.txt".readFile()
    val test21_list = "2016/input_21_test.txt".readFile()

    @Nested
    inner class Init {
        @Test
        fun `21,1,live,init`() {
            report {
                Day21.partOne("abcdefgh", data21_list) to "ghfacdbe"
            }
        }

        @Test
        fun `21,2,live,init`() {
            report {
                Day21.partTwo("fbgdceah", data21_list) to "fhgcdaeb"
            }
        }
    }

    @Nested
    inner class Units {
        @Test
        fun `21,unit,swap pos 4 0`() {
            report {
                Day21.swapPos("abcde", 4, 0) to "ebcda"
            }
        }

        @Test
        fun `21,unit,swap pos 0 4`() {
            report {
                Day21.swapPos("abcde", 0, 4) to "ebcda"
            }
        }

        @Test
        fun `21,unit,swap pos 1 3`() {
            report {
                Day21.swapPos("abcde", 1, 3) to "adcbe"
            }
        }

        @Test
        fun `21,unit,swap letter`() {
            report {
                Day21.swapLetter("ebcda", 'd', 'b') to "edcba"
            }
        }

        @Test
        fun `21,unit,rotate right 1`() {
            report {
                Day21.rotate("abcd", "right", 1) to "dabc"
            }
        }

        @Test
        fun `21,unit,rotate right 4`() {
            report {
                Day21.rotate("abcd", "right", 4) to "abcd"
            }
        }

        @Test
        fun `21,unit,rotate left`() {
            report {
                Day21.rotate("abcde", "left", 1) to "bcdea"
            }
        }

        @Test
        fun `21,unit,rotate pos b`() {
            report {
                Day21.rotatePos("abdec", 'b') to "ecabd"
            }
        }

        @Test
        fun `21,unit,rotate pos reversed`() {
            report {
                val rotatePos = Day21.rotatePos("abcdefghij", 'b')
                val back = Day21.rotatePosReversed(rotatePos, 'b')
                back to "abcdefghij"
            }
        }

        @Test
        fun `21,unit,rotate reversed`() {
            report {
                val rotatePos = Day21.rotate("abcdefghij", "right", 3)
                val back = Day21.rotateReversed(rotatePos, "right", 3)
                back to "abcdefghij"
            }
        }

        @Test
        fun `21,unit,rotate pos d`() {
            report {
                Day21.rotatePos("ecabd", 'd') to "decab"
            }
        }

        @Test
        fun `21,unit,reverse pos 0 4`() {
            report {
                Day21.reversePos("edcba", 0, 4) to "abcde"
            }
        }

        @Test
        fun `21,unit,reverse pos 1 3`() {
            report {
                Day21.reversePos("edcba", 1, 3) to "ebcda"
            }
        }

        @Test
        fun `21,unit,move pos 1`() {
            report {
                Day21.move("bcdea", 1, 4) to "bdeac"
            }
        }

        @Test
        fun `21,unit,move pos 2`() {
            report {
                Day21.move("bdeac", 3, 0) to "abdec"
            }
        }

        @Test
        fun `21,unit,move reversed`() {
            report {
                val rotatePos = Day21.move("bcdea", 1, 4)
                val back = Day21.moveReversed(rotatePos, 1, 4)
                back to "bcdea"
            }
        }

        @Test
        fun `21,unit,move reversed, 2`() {
            report {
                val rotatePos = Day21.move("bdeac", 3, 0)
                val back = Day21.moveReversed(rotatePos, 3, 0)
                back to "bdeac"
            }
        }


    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test`() {
            report {
                Day21.partOne("abcde", test21_list) to "decab"
            }
        }

        @Test
        fun `21,1,live,1`() {
            report {
                Day21.partOne("abcdefgh", data21_list) to "ghfacdbe"
            }
        }

        @Test
        fun `21,1,live,2`() {
            report {
                Day21.partOne("abcdefgh", data21_list) to "ghfacdbe"
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,live,1`() {
            report {
                Day21.partTwo("fbgdceah", data21_list) to "fhgcdaeb"
            }
        }

        @Test
        fun `21,2,live,2`() {
            report {
                Day21.partTwo("fbgdceah", data21_list) to "fhgcdaeb"
            }
        }
    }
}
