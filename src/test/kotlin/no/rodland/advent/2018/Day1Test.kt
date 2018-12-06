package no.rodland.advent.`2018`

import Day1
import org.junit.jupiter.api.Test
import readFileAsInt

internal class Day1Test {
    private val data1 = "input_1.txt".readFileAsInt()
    private val test1 = listOf(+1, -2, +3, +1)
    private val test2 = listOf(+1, +1, +1)
    private val test3 = listOf(+1, +1, -2)
    private val test4 = listOf(-1, -2, -3)

    @Test
    fun `1,1,test,remake`() {
        report {
            Day1.day1_1_remake(test1) to 3
        }
    }

    @Test
    fun `1,1,test2,remake`() {
        report {
            Day1.day1_1_remake(test2) to 3
        }
    }

    @Test
    fun `1,1,test3,remake`() {
        report {
            Day1.day1_1_remake(test3) to 0
        }
    }

    @Test
    fun `1,1,test4,remake`() {
        report {
            Day1.day1_1_remake(test4) to -6
        }
    }

    @Test
    fun `1,1,live,remake`() {
        report {
            Day1.day1_1_remake(data1) to 587
        }
    }

    @Test
    fun `1,2,test,3`() {
        report {
            Day1.day1_2_take3(test1) to 2
        }
    }

    @Test
    fun `1,2,live,3`() {
        report {
            Day1.day1_2_take3(data1) to 83130
        }
    }

    @Test
    fun `1,2,test,4`() {
        report {
            Day1.day1_2_take4(test1) to 2
        }
    }

    @Test
    fun `1,2,live,4`() {
        report {
            Day1.day1_2_take4(data1) to 83130
        }
    }
}