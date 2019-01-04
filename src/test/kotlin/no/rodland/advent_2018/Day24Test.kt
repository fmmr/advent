package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import no.rodland.advent_2018.Day24.Group
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile
import kotlin.test.assertEquals

@DisableSlow
internal class Day24Test {
    val data24 = "2018/input_24.txt".readFile()
    val test24 = listOf("1", "2")

    @Nested
    inner class Hit {
        @Test
        fun `24,1,test`() {
            report {
                val immune = Group.of(Day24.Team.IMMUNE, "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2")
                val infection = Group.of(Day24.Team.INFECTION, "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1")
                infection.hit(immune)

                immune.num to 0
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `24,1,test`() {
            report {
                Day24.partOne(listOf(
                        "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
                        "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3"
                ), listOf(
                        "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1",
                        "4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4"
                )) to 5216
            }
        }

        @Test
        fun `24,1,live`() {
            report {
                Day24.partOne(listOf(
                        "7056 units each with 8028 hit points (weak to radiation) with an attack that does 10 slashing damage at initiative 13",
                        "4459 units each with 10339 hit points (immune to fire, radiation, slashing) with an attack that does 22 cold damage at initiative 4",
                        "724 units each with 10689 hit points (immune to bludgeoning, cold, fire) with an attack that does 124 radiation damage at initiative 17",
                        "1889 units each with 3361 hit points (weak to cold) with an attack that does 17 fire damage at initiative 2",
                        "4655 units each with 1499 hit points (weak to fire) with an attack that does 2 fire damage at initiative 5",
                        "6799 units each with 3314 hit points with an attack that does 4 radiation damage at initiative 16",
                        "2407 units each with 4016 hit points (weak to slashing; immune to bludgeoning) with an attack that does 13 fire damage at initiative 20",
                        "5372 units each with 5729 hit points with an attack that does 9 fire damage at initiative 14",
                        "432 units each with 11056 hit points with an attack that does 220 cold damage at initiative 10",
                        "3192 units each with 8960 hit points (weak to slashing, radiation) with an attack that does 24 cold damage at initiative 15"
                ), listOf(
                        "4052 units each with 25687 hit points (weak to fire, radiation) with an attack that does 11 slashing damage at initiative 18",
                        "1038 units each with 13648 hit points (weak to slashing) with an attack that does 24 bludgeoning damage at initiative 9",
                        "6627 units each with 34156 hit points (weak to radiation) with an attack that does 10 slashing damage at initiative 6",
                        "2299 units each with 45224 hit points (weak to fire) with an attack that does 38 cold damage at initiative 19",
                        "2913 units each with 30594 hit points (weak to radiation; immune to cold) with an attack that does 20 fire damage at initiative 1",
                        "2153 units each with 14838 hit points (immune to fire, bludgeoning, radiation; weak to slashing) with an attack that does 11 radiation damage at initiative 3",
                        "2381 units each with 61130 hit points (weak to cold) with an attack that does 39 slashing damage at initiative 8",
                        "2729 units each with 33834 hit points (immune to slashing, cold) with an attack that does 23 fire damage at initiative 7",
                        "344 units each with 20830 hit points (immune to fire) with an attack that does 116 bludgeoning damage at initiative 12",
                        "6848 units each with 50757 hit points with an attack that does 12 slashing damage at initiative 11"
                )) to 26343
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `24,2,test`() {
            report {
                Day24.partTwo(test24) to 2
            }
        }

        @Test
        fun `24,2,live`() {
            report {
                Day24.partTwo(data24) to 2
            }
        }
    }

    @Nested
    inner class Counting {
        @Test
        fun `24,1,parse,1`() {
            report {
                val group = Day24.Group.of(Day24.Team.IMMUNE, "5 units each with 20 hit points (weak to radiation, bludgeoning) with an attack that does 30 fire damage at initiative 2")
                assertEquals(5, group.num)
                assertEquals(150, group.effective())
                group.num -= 1
                assertEquals(4, group.num)
                assertEquals(120, group.effective())
                2 to 2
            }
        }
    }

    @Nested
    inner class Parsing {
        @Test
        fun `24,1,parse,1`() {
            report {
                Day24.Group.of(Day24.Team.IMMUNE, "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2") to
                        Day24.Group(Day24.Team.IMMUNE, 17, 5390, 4507, "fire", 2, listOf("radiation", "bludgeoning"), emptyList())
            }
        }

        @Test
        fun `24,1,parse,2`() {
            report {
                Day24.Group.of(Day24.Team.INFECTION, "17 units each with 5390 hit points with an attack that does 4507 fire damage at initiative 2") to
                        Day24.Group(Day24.Team.INFECTION, 17, 5390, 4507, "fire", 2, emptyList(), emptyList())
            }
        }

        @Test
        fun `24,1,parse,3`() {
            report {
                Day24.Group.of(Day24.Team.INFECTION, "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3") to
                        Day24.Group(Day24.Team.INFECTION, 989, 1274, 25, "slashing", 3, listOf("bludgeoning", "slashing"), listOf("fire"))
            }
        }

        @Test
        fun `24,1,parse,4`() {
            report {
                Day24.Group.of(Day24.Team.INFECTION, "989 units each with 1274 hit points (weak to bludgeoning, slashing; immune to fire) with an attack that does 25 slashing damage at initiative 3") to
                        Day24.Group(Day24.Team.INFECTION, 989, 1274, 25, "slashing", 3, listOf("bludgeoning", "slashing"), listOf("fire"))
            }
        }

        @Test
        fun `24,1,parse,5`() {
            report {
                Day24.Group.of(Day24.Team.INFECTION, "989 units each with 1274 hit points (immune to fire, slashing) with an attack that does 25 slashing damage at initiative 3") to
                        Day24.Group(Day24.Team.INFECTION, 989, 1274, 25, "slashing", 3, emptyList(), listOf("fire", "slashing"))
            }
        }
    }
}


