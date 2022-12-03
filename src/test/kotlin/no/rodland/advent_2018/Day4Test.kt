package no.rodland.advent_2018

import no.rodland.advent.report
import org.junit.jupiter.api.Test
import readFile

internal class Day4Test {
    private val test4 = listOf("[1518-11-01 00:00] Guard #10 begins shift", "[1518-11-01 00:05] falls asleep", "[1518-11-01 00:25] wakes up", "[1518-11-01 00:30] falls asleep", "[1518-11-01 00:55] wakes up", "[1518-11-01 23:58] Guard #99 begins shift", "[1518-11-02 00:40] falls asleep", "[1518-11-02 00:50] wakes up", "[1518-11-03 00:05] Guard #10 begins shift", "[1518-11-03 00:24] falls asleep", "[1518-11-03 00:29] wakes up", "[1518-11-04 00:02] Guard #99 begins shift", "[1518-11-04 00:36] falls asleep", "[1518-11-04 00:46] wakes up", "[1518-11-05 00:03] Guard #99 begins shift", "[1518-11-05 00:45] falls asleep", "[1518-11-05 00:55] wakes up")
    private val data4 = "2018/input_4.txt".readFile().sorted()

    @Test
    fun `4,1,test`() {
        report {
            Day4.partOne(test4) to 240
        }
    }

    @Test
    fun `4,1,live`() {
        report {
            Day4.partOne(data4) to 35184
        }
    }

    @Test
    fun `4,2,test`() {
        report {
            Day4.partTwo(test4) to 4455
        }
    }

    @Test
    fun `4,2,live`() {
        report {
            Day4.partTwo(data4) to 37886
        }
    }

    @Test
    fun `4,FMR_WORST_GUARD,test`() {
        report {
            Day4.findBestGuardToTrick(test4) to Day4.Guard(id = 10)
        }
    }

    @Test
    fun `4,FMR_WORST_GUARD,live`() {
        report {
            Day4.findBestGuardToTrick(data4) to Day4.Guard(id = 1663)
        }
    }
}