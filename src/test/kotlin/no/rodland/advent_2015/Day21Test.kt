package no.rodland.advent_2015

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2015.Day21.Player
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("ClassName")
@DisableSlow
internal class Day21Test {

    @Nested
    inner class Init {
        @Test
        fun `21,1,live,init`() {
            report {
                Day21.partOne(103, 9, 2) to 121
            }
        }

        @Test
        fun `21,2,live,init`() {
            report {
                Day21.partTwo(103, 9, 2) to 201
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `21,1,test,playgame`() {
            report {

                run {
                    val boss = Player(12, 7, 2)
                    val player = Player(8, 0, 0, listOf(Day21.Item("test", 0, 5, 5)))
                    while (boss.hitPoints > 0 && player.hitPoints > 0) {
                        player.hit(boss)
                        boss.hit(player)
                    }
                    boss.hitPoints <= 0
                } to true
            }
        }

        @Test
        fun `21,1,live,1`() {
            report {
                Day21.partOne(103, 9, 2) to 121
            }
        }

        @Test
        fun `21,1,live,2`() {
            report {
                Day21.partOne(103, 9, 2) to 121
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `21,2,live,1`() {
            report {
                Day21.partTwo(103, 9, 2) to 201
            }
        }

        @Test
        fun `21,2,live,2`() {
            report {
                Day21.partTwo(103, 9, 2) to 201
            }
        }
    }
}
