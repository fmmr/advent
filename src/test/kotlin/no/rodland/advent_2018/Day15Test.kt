package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2018.Day15.Creature
import no.rodland.advent_2018.Day15.Type.ELF
import no.rodland.advent_2018.Day15.Type.GOBLIN
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day15Test {
    val data15 = "2018/input_15.txt".readFile()
    val test15_mini = listOf(
            "#######",
            "#.E...#",
            "#.....#",
            "#...G.#",
            "#######"
    )
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
    inner class Creatures {
        @Test
        fun `15,1,creatures,sort`() {
            report {
                val creatures = mutableListOf<Creature>()
                creatures.add(Creature(ELF, "5", 5, 2))
                creatures.add(Creature(ELF, "3", 1, 2))
                creatures.add(Creature(ELF, "2", 4, 1))
                creatures.add(Creature(ELF, "1", 2, 1))
                creatures.add(Creature(ELF, "6", 2, 3))
                creatures.add(Creature(ELF, "7", 4, 3))
                creatures.add(Creature(ELF, "4", 3, 2))

                val sorted = creatures.sorted()
                val names = sorted.map { it.name }
                names to listOf("1", "2", "3", "4", "5", "6", "7")
            }
        }

        @Test
        fun `15,1,creatures,hit`() {
            report {
                val elf = Creature(ELF, "test", 0, 0)
                val goblin = Creature(ELF, "test", 0, 0)
                goblin.hit(elf)
                repeat(10) { elf.hit(goblin) }
                assertEquals(goblin.dead(), false)
                goblin.hitPoints to (200 - 30)
            }
        }

        @Test
        fun `15,1,creatures,hit_until_dead`() {
            report {
                val elf = Creature(ELF, "test", 0, 0)
                val goblin = Creature(ELF, "test", 0, 0)
                goblin.hit(elf)
                repeat(200 / 3) { elf.hit(goblin) }
                assertEquals(goblin.dead(), false)
                elf.hit(goblin)
                assertEquals(goblin.dead(), true)
                goblin.hitPoints to -1
            }
        }

        @Test
        fun `15,1,creatures,elf_other`() {
            report {
                ELF.other() to GOBLIN
            }
        }

        @Test
        fun `15,1,creatures,goblin_other`() {
            report {
                GOBLIN.other() to ELF
            }
        }

        @Test
        fun `15,1,creatures,g`() {
            report {
                'G'.toType() to GOBLIN
            }
        }

        @Test
        fun `15,1,creatures,e`() {
            report {
                'E'.toType() to ELF
            }
        }

        @Test
        fun `15,1,creatures,1`() {
            report {
                assertThrows(IllegalStateException::class.java) { '.'.toType() }
                "exeption ok" to "exeption ok"
            }
        }
    }


    @Nested
    inner class Init {
        @Test
        fun `15,1,init,small`() {
            report {
                val (creatures, map) = Day15.init(test15_small)
                (2..7).forEach { assertEquals(map[1], map[it]) }
                assertEquals(map[0], map[8]) // left and right walls
                assertEquals(map[0].size, 9) // left and right walls
                assertEquals(creatures.count { it.type == GOBLIN }, 8)
                assertEquals(creatures.count { it.type == ELF }, 1)
                assertEquals(creatures.map { it.type.other() }.count { it == ELF }, 8)
                creatures.size to 9
            }
        }

        @Test
        fun `15,1,init,mini`() {
            report {
                val (creatures, map) = Day15.init(test15_mini)
                assertEquals(map[1].size, 7)
                assertEquals(map[1], map[2])
                assertEquals(map[1], map[3])
                assertEquals(map[0], map[4]) // left and right walls
                creatures.size to 2
            }
        }
    }

    @Nested
    inner class `Part 1` {

        @Test
        fun `15,1,live`() {
            report {
                Day15.partOne(data15) to 2
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `15,2,test`() {
            report {
                Day15.partTwo(test15_mini) to 2
            }
        }

        @Test
        fun `15,2,live`() {
            report {
                Day15.partTwo(data15) to 2
            }
        }
    }

    @Nested
    inner class Stats {
        @Test
        fun `15,1,stats`() {
            report {
                val creatures = mutableListOf<Creature>()
                creatures.add(Creature(ELF, "5", 5, 2, hitPoints = 200))
                creatures.add(Creature(ELF, "3", 1, 2, hitPoints = 131))
                creatures.add(Creature(ELF, "2", 4, 1, hitPoints = 59))
                creatures.add(Creature(ELF, "1", 2, 1, hitPoints = 200))
                Day15.stats(creatures, 47) to 27730
            }
        }
    }
}


