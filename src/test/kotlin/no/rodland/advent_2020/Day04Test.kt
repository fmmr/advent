package no.rodland.advent_2020

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisableSlow
internal class Day04Test {
    val data04 = "2020/input_04.txt".readFileAsOneString()
    val test04 =
        """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm
            
            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929
            
            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm
            
            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

    val invalidPart2 =
        """
        eyr:1972 cid:100
        hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

        iyr:2019
        hcl:#602927 eyr:1967 hgt:170cm
        ecl:grn pid:012533040 byr:1946

        hcl:dab227 iyr:2012
        ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

        hgt:59cm ecl:zzz
        eyr:2038 hcl:74454a iyr:2023
        pid:3556412378 byr:2007
    """.trimIndent()

    val validPart2 =
        """
            pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
            hcl:#623a2f

            eyr:2029 ecl:blu cid:129 byr:1989
            iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

            hcl:#888785
            hgt:164cm byr:2001 iyr:2015 cid:88
            pid:545766238 ecl:hzl
            eyr:2022

            iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
        """.trimIndent()

    @Nested
    inner class `Part 1` {
        @Test
        fun `04,1,test`() {
            report {
                Day04.partOne(test04) to 2
            }
        }

        @Test
        fun `04,1,live`() {
            report {
                Day04.partOne(data04) to 245
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `04,2,test,1`() {
            report {
                Day04.partTwo(test04) to 2
            }
        }

        @Test
        fun `04,2,test,INVALID`() {
            report {
                Day04.partTwo(invalidPart2) to 0
            }
        }

        @Test
        fun `04,2,test,VALID`() {
            report {
                Day04.partTwo(validPart2) to 4
            }
        }

        @Test
        fun `04,2,live`() {
            report {
                Day04.partTwo(data04) to 133
            }
        }
    }
}


