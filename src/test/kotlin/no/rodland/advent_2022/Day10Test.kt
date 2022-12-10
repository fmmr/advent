package no.rodland.advent_2022

import no.rodland.advent.DisableSlow
import no.rodland.advent.report
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import readFile

@Suppress("ClassName")
@DisableSlow
internal class Day10Test {
    val data10 = "2022/input_10.txt".readFile()
    val test10 = "2022/input_10_test.txt".readFile()
    val test10Mini = listOf(
        "noop",
        "addx 3",
        "addx -5",
    )

    val resultTestOne = 13140
    val resultOne = 12980
    val resultTestTwo = """
##  ##  ##  ##  ##  ##  ##  ##  ##  ##  
###   ###   ###   ###   ###   ###   ### 
####    ####    ####    ####    ####    
#####     #####     #####     #####     
######      ######      ######      ####
#######       #######       #######     
    """.trimIndent()
    val resultTwo = """
###  ###    ## #    #### #  # #    ###  
#  # #  #    # #    #    #  # #    #  # 
###  #  #    # #    ###  #  # #    #  # 
#  # ###     # #    #    #  # #    ###  
#  # # #  #  # #    #    #  # #    #    
###  #  #  ##  #### #     ##  #### #    
""".trimIndent()  // BRJLFULP

    @Nested
    inner class Init {
        @Test
        fun `10,1,live,init`() {
            report {
                Day10.partOne(data10) to resultOne
            }
        }

        @Test
        fun `10,2,live,init`() {
            report {
                Day10.partTwo(data10) to resultTwo
            }
        }
    }

    @Nested
    inner class `Part 1` {
        @Test
        fun `10,1,test`() {
            report {
                Day10.partOne(test10) to resultTestOne
            }
        }

        @Test
        fun `10,1,test,mini`() {
            report {
                Day10.partOne(test10Mini) to 0
            }
        }

        @Test
        fun `10,1,live,1`() {
            report {
                Day10.partOne(data10) to resultOne
            }
        }
    }

    @Nested
    inner class `Part 2` {
        @Test
        fun `10,2,test`() {
            report {
                Day10.partTwo(test10) to resultTestTwo
            }
        }

        @Test
        fun `10,2,live,1`() {
            report {
                Day10.partTwo(data10) to resultTwo
            }
        }
    }
}
