package no.rodland.advent_2018

import Day9
import get
import getLong
import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


@DisableSlow
internal class Day9Test {

    @Nested
    inner class `Part 2` {
        val re = """(\d+) pla.*worth (\d+) point.*is (\d+)""".toRegex()

        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["405 players; last marble is worth 71700 points: high score is 428690",
            "405 players; last marble is worth 7170 points: high score is 10245",
            "405 players; last marble is worth 717 points: high score is 954"]
        )
        fun `9,2,paramterized`(candidate: String) {
            val players = re.get(candidate, 1)
            val worth = re.get(candidate, 2)
            val answer = re.getLong(candidate, 3)
            report {
                Day9.partTwo(players, worth) to answer
            }
        }

        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["405 players; last marble is worth 717000 points: high score is 36911578"])
        fun `9,2,paramterized_10`(candidate: String) {
            val players = re.get(candidate, 1)
            val worth = re.get(candidate, 2)
            val answer = re.getLong(candidate, 3)
            report {
                Day9.partTwo(players, worth) to answer
            }
        }

        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["405 players; last marble is worth 7170000 points: high score is 3628143500"])
        fun `9,2,paramterized_100`(candidate: String) {
            val players = re.get(candidate, 1)
            val worth = re.get(candidate, 2)
            val answer = re.getLong(candidate, 3)
            report {
                Day9.partTwo(players, worth) to answer
            }
        }
    }

    @Nested
    inner class `Part 1` {
        val re = """(\d+) pla.*worth (\d+) point.*is (\d+)""".toRegex()

        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["17 players; last marble is worth 1104 points: high score is 2764",
            "21 players; last marble is worth 6111 points: high score is 54718",
            "30 players; last marble is worth 5807 points: high score is 37305",
            "405 players; last marble is worth 71700 points: high score is 428690"]  // actual!
        )
        fun `9,1,paramterized`(candidate: String) {
            val players = re.get(candidate, 1)
            val worth = re.get(candidate, 2)
            val answer = re.getLong(candidate, 3)
            report {
                Day9.partOne(players, worth) to answer
            }
        }

        @ParameterizedTest(name = "run #{index} with [{arguments}]")
        @ValueSource(strings = ["9 players; last marble is worth 25 points: high score is 32",
            "10 players; last marble is worth 1618 points: high score is 8317",
            "13 players; last marble is worth 7999 points: high score is 146373"]
        )
        fun `9,1,paramterized,2`(candidate: String) {
            val players = re.get(candidate, 1)
            val worth = re.get(candidate, 2)
            val answer = re.getLong(candidate, 3)
            report {
                Day9.partOne(players, worth) to answer
            }
        }

        @Nested
        inner class `get index add` {
            val game = Day9.Game(3, 4)
            @BeforeAll
            fun playSomeRounds() {
                (1..9).forEach { game.placeMarble(it.toLong()) }
            }

            @Test
            fun `9,1,getindexforadd,1`() {
                report {
                    game.getIndexForAdd(8) to 10
                }
            }   

            @Test
            fun `9,1,getindexforadd,2`() {
                report {
                    game.getIndexForAdd(3) to 5
                }
            }

            @Test
            fun `9,1,getindexforadd,3`() {
                report {
                    game.getIndexForAdd(9) to 1
                }
            }
        }

        @Nested
        inner class `get index` {
            val game = Day9.Game(3, 4)

            @BeforeAll
            fun playSomeRounds() {
                (1..9).forEach { game.placeMarble(it.toLong()) }
            }

            @Test
            fun `9,1,getindex,1`() {
                report {
                    game.getIndex(4, 2) to 6
                }

            }

            @Test
            fun `9,1,getindex,2`() {
                report {
                    game.getIndex(8, 1) to 9
                }
            }

            @Test
            fun `9,1,getindex,3`() {
                report {
                    game.getIndex(8, 2) to 0
                }
            }

            @Test
            fun `9,1,getindex,4`() {
                report {
                    game.getIndex(8, 4) to 2
                }
            }

            @Test
            fun `9,1,getindex,7`() {
                report {
                    game.getIndex(8, 13) to 1
                }
            }

            @Test
            fun `9,1,getindex,5`() {
                report {
                    game.getIndex(8, -2) to 6
                }
            }

            @Test
            fun `9,1,getindex,6`() {
                report {
                    game.getIndex(0, -2) to 8
                }
            }
        }
    }


}


