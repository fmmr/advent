package no.rodland.advent_2018

import Day3
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day3Test {
    private val test3 = listOf("#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2", "#1 @ 1,3: 4x4").map { Day3.Claim(it) }
    private val data3 = "2018/input_3.txt".readFile().map { Day3.Claim(it) }

    @Test
    fun `3,1,test`() {
        report {
            Day3.part_one_take_one(test3) to 4
        }
    }

    @Test
    @Slow(48000)
    fun `3,1,live`() {
        report {
            Day3.part_one_take_one(data3) to 104241
        }
    }

    @Test
    fun `3,1,test,2`() {
        report {
            Day3.part_one_take_two(test3) to 4
        }
    }

    @Test
    @Slow(500)
    fun `3,1,live,2`() {
        report {
            Day3.part_one_take_two(data3) to 104241
        }
    }

    @Test
    fun `3,2,test`() {
        report {
            Day3.part_two_take_one(test3) to Day3.Claim("#3 @ 5,5: 2x2")
        }
    }

    @Test
    @Slow(33000)
    fun `3,2,live`() {
        report {
            Day3.part_two_take_one(data3) to Day3.Claim("#806 @ 736,434: 22x21")
        }
    }

    @Test
    fun `3,2,test,2`() {
        report {
            Day3.part_two_take_two(test3) to Day3.Claim("#3 @ 5,5: 2x2")
        }
    }

    @Test
    @Slow(200)
    fun `3,2,live,2`() {
        report {
            Day3.part_two_take_two(data3) to Day3.Claim("#806 @ 736,434: 22x21")
        }
    }
}
