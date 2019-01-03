package no.rodland.advent_2018

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@DisableSlow
internal class Day24Test {
    val data24 = "2018/input_24.txt".readFile()
    val test24 = listOf("1", "2")

    @Nested
    inner class Parsing {
        @Test
        fun `24,1,parse`() {
            report {
                Day24.Group(Day24.Team.IMMUNE, "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2") to
                        Day24.Group(Day24.Team.IMMUNE, 17, 5390, 4507, "fire", 2, "weak to radiation, bludgeoning")
            }
        }

        @Test
        fun `24,1,parse, 2`() {
            report {
                Day24.Group(Day24.Team.INFECTION, "17 units each with 5390 hit points with an attack that does 4507 fire damage at initiative 2") to
                        Day24.Group(Day24.Team.INFECTION, 17, 5390, 4507, "fire", 2, "")
            }
        }

        @Test
        fun `24,1,parse, 3`() {
            report {
                Day24.Group(Day24.Team.INFECTION, "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3") to
                        Day24.Group(Day24.Team.INFECTION, 989, 1274, 25, "slashing", 3, "immune to fire; weak to bludgeoning, slashing")
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
                )) to 2
            }
        }

//        @Test
//        fun `24,1,live`() {
//            report {
//                Day24.partOne(data24, emptyList()) to 2
//            }
//        }
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
}


