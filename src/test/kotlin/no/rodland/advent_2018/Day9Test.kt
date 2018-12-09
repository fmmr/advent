package no.rodland.advent_2018

import Day9
import get
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


@DisableSlow
internal class Day9Test {
    @Nested
    inner class `Part 1` {
        val re = """(\d+) pla.*worth (\d+) point.*is (\d+)""".toRegex()

        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["10 players; last marble is worth 1618 points: high score is 8317",
            "13 players; last marble is worth 7999 points: high score is 146373",
            "17 players; last marble is worth 1104 points: high score is 2764",
            "21 players; last marble is worth 6111 points: high score is 54718",
            "30 players; last marble is worth 5807 points: high score is 37305",
            "405 players; last marble is worth 71700 points: high score is 1"]
        )
        fun `8,1,paramterized`(candidate: String) {
            val players = re.get(candidate, 1)
            val worth = re.get(candidate, 2)
            val answer = re.get(candidate, 3)
            report {
                println(candidate)
                Day9.partOne(players, worth) to 2
            }
        }


    }

}


