package no.rodland.advent_2021

import no.rodland.advent.AOCTest
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import no.rodland.advent.defaultTestSuite
import no.rodland.advent.report
import no.rodland.advent_2021.Day18.FishNumber
import no.rodland.advent_2021.Day18.FishPair
import no.rodland.advent_2021.Day18.parse
import org.junit.jupiter.api.Test
import readFile
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Suppress("ClassName")
@DisableSlow
internal class Day18Test {
    private val liveData = "2021/input_18.txt".readFile()
    private val testData2 = listOf(
        "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
        "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
        "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
        "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
        "[7,[5,[[3,8],[1,4]]]]",
        "[[2,[2,2]],[8,[8,1]]]",
        "[2,9]",
        "[1,[[[9,3],9],[[9,0],[0,7]]]]",
        "[[[5,[7,4]],7],1]",
        "[[[[4,2],2],6],[8,7]]",
    )
    private val testData = listOf(
        "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
        "[[[5,[2,8]],4],[5,[[9,9],0]]]",
        "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
        "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
        "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
        "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
        "[[[[5,4],[7,7]],8],[[8,3],8]]",
        "[[9,3],[[9,9],[6,[4,9]]]]",
        "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
        "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]",
    )
    private val testDataSimple = listOf(
        "[1,1]",
        "[2,2]",
        "[3,3]",
        "[4,4]",
        "[5,5]",
        "[6,6]",
    )
    private val testDataVerySimple = listOf(
        "[1,1]",
        "[2,2]",
        "[3,3]",
        "[4,4]",
    )
    val test = defaultTestSuite(
        18, Day18::partOne, Day18::partTwo, liveData, testData,
        testPart1 = 4140,
        livePart1 = 4072,
        testPart2 = 3993,
        livePart2 = 4483,
        numTestPart1 = 1,
        numTestPart2 = 1
    )

//    @BeforeAll
//    fun `0_init`() {
//        test.livePart1.run { function(data) }
//    }

    @Test
    fun `1_test`() {
        report(test.testPart1)
    }

    @Test
    @Slow(100)
    fun `1_live`() {
        report(test.livePart1)
    }

    @Test
    fun `2_test`() {
        report(test.testPart2)
    }

    @Test
    @Slow(700)
    fun `2_live`() {
        report(test.livePart2)
    }

    @Test
    fun `18,1,test_very_simple`() {
        report(test.testPart1.copy(data = testDataVerySimple, expected = 445))
    }

    @Test
    fun `18,1,test_simple`() {
        report(test.testPart1.copy(data = testDataSimple, expected = 1137))
    }

    @Test
    fun `18,1,add_1`() {
        report(AOCTest("18.demo", { ("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]".parse() + "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]".parse()).toString() }, 1, "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"))
    }

    @Test
    fun `18,1,add_3`() {
        report(AOCTest("18.demo", { ("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]".parse() + "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]".parse()).toString() }, 1, "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]"))
    }

    @Test
    fun `18,1,add_4`() {
        report(AOCTest("18.demo", { ("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]".parse() + "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]".parse()).toString() }, 1, "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]"))
    }

    @Test
    fun `18,1,testdata_reduce`() {
        report(AOCTest("18.demo", { testData2.map { it.parse() }.reduce { acc, fish -> acc + fish }.toString() }, 1, "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"))
    }

    @Test
    fun `18,1,add_2`() {
        report(AOCTest("18.demo", { ("[1,1]".parse() + "[2,2]".parse()).toString() }, 1, "[[1,1],[2,2]]"))
    }

    @Test
    fun `18,1,reduce_1`() {
        report(AOCTest("18.demo", { "[[[[[9,8],1],2],3],4]".parse().reduce().toString() }, 1, "[[[[0,9],2],3],4]"))
    }

    @Test
    fun `18,1,magnitude_1`() {
        report(AOCTest("18.demo", { "[[[[5,0],[7,4]],[5,5]],[6,6]]".parse().reduce().magnitude() }, 1, 1137))
    }

    @Test
    fun `18,1,magnitude_2`() {
        report(AOCTest("18.demo", { "[[1,2],[[3,4],5]]".parse().reduce().magnitude() }, 1, 143))
    }

    @Test
    fun `18,1,magnitude_3`() {
        report(AOCTest("18.demo", { "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]".parse().reduce().magnitude() }, 1, 1384))
    }

    @Test
    fun `18,1,magnitude_4`() {
        report(AOCTest("18.demo", { "[[[[1,1],[2,2]],[3,3]],[4,4]]".parse().reduce().magnitude() }, 1, 445))
    }

    @Test
    fun `18,1,magnitude_5`() {
        report(AOCTest("18.demo", { "[[[[3,0],[5,3]],[4,4]],[5,5]]".parse().reduce().magnitude() }, 1, 791))
    }

    @Test
    fun `18,1,magnitude_7`() {
        report(AOCTest("18.demo", { "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]".parse().reduce().magnitude() }, 1, 3488))
    }

    @Test
    fun `18,1,magnitude_8`() {
        report(AOCTest("18.demo", { "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]".parse().magnitude() }, 1, 3488))
    }

    @Test
    fun `18,1,magnitude_9`() {
        report(AOCTest("18.demo", { "[[[[1,1],[2,2]],[3,3]],[4,4]]".parse().magnitude() }, 1, 445))
    }

    @Test
    fun `18,1,reduce_2`() {
        report(AOCTest("18.demo", { "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".parse().reduce().toString() }, 1, "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"))
    }

    @Test
    fun `18,1,split_10`() {
        report(AOCTest("18.demo", { it.split().toString() }, FishNumber(10), "[5,5]"))
    }

    @Test
    fun `18,1,split_11`() {
        report(AOCTest("18.demo", { it.split().toString() }, FishNumber(11), "[5,6]"))
    }

    @Test
    fun `18,1,magnitude_9,1`() {
        report(AOCTest("18.demo", { it.magnitude() }, FishPair(FishNumber(9), FishNumber(1)), 29))
    }

    @Test
    fun `18,1,magnitude_1,9`() {
        report(AOCTest("18.demo", { it.magnitude() }, FishPair(FishNumber(1), FishNumber(9)), 21))
    }
}

