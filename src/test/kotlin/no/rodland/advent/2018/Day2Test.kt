package no.rodland.advent.`2018`

import Day2
import org.junit.jupiter.api.Test
import readFile

internal class Day2Test {
    private val data2 = "input_2.txt".readFile()
    private val test2 = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
    private val test22 = listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")
    @Test
    fun `2,1,test`() {
        report {
            Day2.partOne(test2) to 12
        }
    }

    @Test
    fun `2,1,live`() {
        report {
            Day2.partOne(data2) to 7350
        }
    }

    @Test
    fun `2,1,test,2`() {
        report {
            Day2.partOne_take2(test2) to 12
        }
    }

    @Test
    fun `2,1,live,2`() {
        report {
            Day2.partOne_take2(data2) to 7350
        }
    }

    @Test
    fun `2,1,test,3`() {
        report {
            Day2.partOne_take3(test2) to 12
        }
    }

    @Test
    fun `2,1,live,3`() {
        report {
            Day2.partOne_take3(data2) to 7350
        }
    }

    @Test
    fun `2,2,test,1`() {
        report {
            Day2.partTwo(test22) to "fgij"
        }
    }

    @Test
    fun `2,2,live,1`() {
        report {
            Day2.partTwo(data2) to "wmlnjevbfodamyiqpucrhsukg"
        }
    }

    @Test
    fun `2,2,test,2`() {
        report {
            Day2.partTwo_take2(test22) to "fgij"
        }
    }

    @Test
    fun `2,2,live,2`() {
        report {
            Day2.partTwo_take2(data2) to "wmlnjevbfodamyiqpucrhsukg"
        }
    }
}