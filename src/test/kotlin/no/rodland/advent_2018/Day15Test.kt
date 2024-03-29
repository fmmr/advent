package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.Pos
import no.rodland.advent.report
import no.rodland.advent_2018.Day15.Creature
import no.rodland.advent_2018.Day15.Team.ELF
import no.rodland.advent_2018.Day15.Team.GOBLIN
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day15Test {
    val data15 = "2018/input_15.txt".readFile()

    val test15_small = listOf(
            "#########",
            "#G..G..G#",
            "#.......#",
            "#.......#",
            "#G..E..G#",
            "#.......#",
            "#.......#",
            "#G..G..G#",
            "#########"
    )

    @Nested
    inner class Neighbours {
        @Test
        fun `15,1,neighbours`() {
            report {
                Pos(2, 3).neighbourCellsReadingOrder() to listOf(Pos(x = 2, y = 2), Pos(x = 1, y = 3), Pos(x = 3, y = 3), Pos(x = 2, y = 4))
            }
        }
    }

    @Nested
    inner class Creatures {
        @Test
        fun `15,1,creatures,sort`() {
            report {
                val creatures = mutableListOf<Creature>()
                creatures.add(Creature(ELF, "5", Pos(5, 2)))
                creatures.add(Creature(ELF, "3", Pos(1, 2)))
                creatures.add(Creature(ELF, "2", Pos(4, 1)))
                creatures.add(Creature(ELF, "1", Pos(2, 1)))
                creatures.add(Creature(ELF, "6", Pos(2, 3)))
                creatures.add(Creature(ELF, "7", Pos(4, 3)))
                creatures.add(Creature(ELF, "4", Pos(3, 2)))

                val sorted = creatures.sorted()
                val names = sorted.map { it.name }
                names to listOf("1", "2", "3", "4", "5", "6", "7")
            }
        }

        @Test
        fun `15,1,creatures,hit`() {
            report {
                val elf = Creature(ELF, "test", Pos(0, 0))
                val goblin = Creature(ELF, "test", Pos(0, 0))
                goblin.hit(elf)
                repeat(10) { elf.hit(goblin) }
                assertEquals(goblin.dead(), false)
                goblin.hitPoints to (200 - 30)
            }
        }

        @Test
        fun `15,1,creatures,hit_until_dead`() {
            report {
                val elf = Creature(ELF, "test", Pos(0, 0))
                val goblin = Creature(ELF, "test", Pos(0, 0))
                goblin.hit(elf)
                repeat(200 / 3) { elf.hit(goblin) }
                assertEquals(goblin.dead(), false)
                elf.hit(goblin)
                assertEquals(goblin.dead(), true)
                goblin.hitPoints to -1
            }
        }

        @Test
        fun `15,1,creatures,g`() {
            report {
                'G'.toTeam() to GOBLIN
            }
        }

        @Test
        fun `15,1,creatures,e`() {
            report {
                'E'.toTeam() to ELF
            }
        }

        @Test
        fun `15,1,creatures,1`() {
            report {
                '.'.toTeam() to null
            }
        }
    }


    @Nested
    inner class Init {
        @Test
        fun `15,1,init,small`() {
            report {
                val caves = Day15.init(test15_small)
                val creatures: List<Creature> = Creature.findCreatures(caves)

                assertEquals(caves[0].size, 9) // left and right walls
                assertEquals(creatures.count { it.team == GOBLIN }, 8)
                assertEquals(creatures.count { it.team == ELF }, 1)
                creatures.size to 9
            }
        }

        @Test
        fun `15,1,init,mini`() {
            report {
                val caves = Day15.init(test15_small)
                val creatures: List<Creature> = Creature.findCreatures(caves)
                creatures.size to 9
            }
        }
    }

    @Nested
    inner class `Part 1` {

        @Test
        fun `15,1,test,ex1`() {
            report {
                Day15.partOne(listOf(
                        "#######",
                        "#.G...#",
                        "#...EG#",
                        "#.#.#G#",
                        "#..G#E#",
                        "#.....#",
                        "#######")) to 27730

            }
        }

        @Test
        fun `15,1,test,ex2`() {
            report {
                Day15.partOne(listOf(
                        "#######",
                        "#G..#E#",
                        "#E#E.E#",
                        "#G.##.#",
                        "#...#E#",
                        "#...E.#",
                        "#######")) to 36334
            }
        }

        @Test
        fun `15,1,live`() {
            report {
                Day15.partOne(data15) to 245280

            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,1,test,ex2`() {
            report {
                Day15.partTwo(listOf(
                        "#######",
                        "#G..#E#",
                        "#E#E.E#",
                        "#G.##.#",
                        "#...#E#",
                        "#...E.#",
                        "#######")) to 9240
            }
        }

        @Test
        fun `15,2,live`() {
            report {
                Day15.partTwo(data15) to 74984
            }
        }
    }

    @Nested
    inner class Stats {
        @Test
        fun `15,1,stats`() {
            report {
                val creatures = mutableListOf<Creature>()
                creatures.add(Creature(ELF, "5", Pos(5, 2), hitPoints = 200))
                creatures.add(Creature(ELF, "3", Pos(1, 2), hitPoints = 131))
                creatures.add(Creature(ELF, "2", Pos(4, 1), hitPoints = 59))
                creatures.add(Creature(ELF, "1", Pos(2, 1), hitPoints = 200))
                creatures.filterNot { it.dead() }.sumOf { it.hitPoints } * 47 to 27730
            }
        }
    }
}


