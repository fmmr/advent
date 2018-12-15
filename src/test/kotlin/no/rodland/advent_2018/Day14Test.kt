package no.rodland.advent_2018

import get
import getString
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@Suppress("ClassName")
@DisableSlow
internal class Day14Test {

    @ParameterizedTest(name = "(value:{0},currentPos:{1},size:{2}) => {3}")
    @CsvSource("3, 0, 4, 0",
            "3, 0, 6, 4",
            "7, 1, 6, 3",
            "7, 1, 13, 9",
            "1, 5, 20, 7")
    fun `14,1,get next index`(value: Int, currentPos: Int, listSize: Int, expected: Int) {
        report {
            Day14.newPos(value, currentPos, listSize) to expected
        }
    }

    @ParameterizedTest(name = "(value:{0},currentPos:{1},size:{2}) => {3}")
    @CsvSource("8, 8", "18, 18", "0,0")
    fun `14,1,list digits`(value: Int, expected: String) {
        report {
            Day14.toListOfDigits(value).joinToString("") to expected
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `14,1,test`() {
            report {
                Day14.partOne(9) to "5158916779"
            }
        }

        @Test
        fun `14,1,test,2`() {
            report {
                Day14.partOne(5) to "0124515891"
            }
        }

        @Test
        fun `14,1,test,3`() {
            report {
                Day14.partOne(18) to "9251071085"
            }
        }

        @Test
        fun `14,1,test,4`() {
            report {
                Day14.partOne(2018) to "5941429882"
            }
        }

        @Test
        fun `14,1,live`() {
            report {
                Day14.partOne(320851) to "7116398711"
            }
        }
    }

    @Nested
    inner class `Part 2` {

        val re = """(\d+) first appears after (\d+) recipes\.""".toRegex()
        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["51589 first appears after 9 recipes.",
            "01245 first appears after 5 recipes.",
            "92510 first appears after 18 recipes.",
            "59414 first appears after 2018 recipes."]
        )
        @Slow(300)
        fun `9,2,paramterizedtest`(candidate: String) {
            val num = re.getString(candidate, 1)!!
            val answer = re.get(candidate, 2)
            report {
                Day14.partTwo(num) to answer
            }
        }

        @Test
        @Slow(5000)
        fun `9,2,live`() {
            report {
                Day14.partTwo("320851") to 20316365
            }
        }
    }
}


