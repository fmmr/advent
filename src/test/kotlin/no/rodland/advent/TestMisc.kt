package no.rodland.advent

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun <T> report(function: () -> Pair<T, T>) {
    getHeader().run {
        println(this)
        println("=".repeat(this.length).joinToString(""))
    }
    val millis = measureTimeMillis {
        val value = function()
        println("Result: ${value.first}, Excpected: ${value.second}")
        Assertions.assertEquals(value.second, value.first)
    }
    println("took: ${millis}ms")
    println()
}

fun <T, U> report(test: AOCTest<T, U>) {
    test.name.run {
        println(this)
        println("=".repeat(this.length).joinToString(""))
    }

    val init = test.function(test.data)
    if (init == test.expected) {
        val nanos = measureNanoTime {
            repeat(test.numTests) {
                val start = System.nanoTime()
                val value = test.function(test.data) to test.expected
                (System.nanoTime() - start) to (value.first to value.second)
            }
        }
        val avg = nanos.toDuration(DurationUnit.NANOSECONDS) / test.numTests
        println("Result: $init (OK)")
        println("Ran ${test.numTests}, time_pr_test: $avg")

    } else {
        println("Result: ${init}, Expected: ${test.expected}")
    }
    println()
    Assertions.assertEquals(test.expected, init)
}


private fun getHeader(): String {
    val method = Thread.currentThread().stackTrace[3].methodName
    val name = method.split(",")
    return if (name.size == 4) {
        "DAY: ${name[0]}, PART: ${name[1]}, DATA: ${name[2]}, TAKE: ${name[3]}"
    } else {
        "DAY: ${name[0]}, PART: ${name[1]}, DATA: ${name[2]}"
    }
}

const val OK_TO_WAIT_PR_TEST = 600

class DisableSlowCond : ExecutionCondition {
    override fun evaluateExecutionCondition(context: ExtensionContext): ConditionEvaluationResult {
        val isSlow = context.element.get().getAnnotation(Slow::class.java)
        return if (isSlow != null && isSlow.approximateRunTimeInMillis > OK_TO_WAIT_PR_TEST) {
            ConditionEvaluationResult.disabled("Disabled ${context.displayName}. Marked as slow: ${isSlow.approximateRunTimeInMillis} ms > $OK_TO_WAIT_PR_TEST ms")
        } else {
            ConditionEvaluationResult.enabled("Enabled")
        }
    }
}

//@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION)
@Retention
@ExtendWith(DisableSlowCond::class)
annotation class DisableSlow

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION)
@Retention
annotation class Slow(val approximateRunTimeInMillis: Int)

data class AOCTest<I, T>(
    val name: String,
    val function: (I) -> T,
    val data: I,
    val expected: T,
    val numTests: Int = 1
) {
    constructor(
        function: (I) -> T,
        data: I,
        expected: T,
        numTests: Int,
        day: Int,
        part: Part,
        live: Boolean,
    ) : this(day.padDate() + "." + part.toString() + if (live) ".LIVE" else ".TEST", function, data, expected, numTests)
}

fun Int.padDate(): String = if (this < 10) "0$this" else this.toString()

enum class Part { ONE, TWO }

class AOCTestSuite<I, T, S>(
    val livePart1: AOCTest<T, I>,
    val livePart2: AOCTest<S, I>,
    val testPart1: AOCTest<T, I>,
    val testPart2: AOCTest<S, I>,
    val initPart1: AOCTest<T, I> = livePart1.copy(numTests = 1),
    val initPart2: AOCTest<S, I> = livePart2.copy(numTests = 1),
)

fun <T, S, U> defaultTestSuiteParseOnCall(
    day: Int,
    part1: (U) -> T,
    part2: (U) -> S,
    liveData: U,
    testData: U,
    testPart1: T,
    livePart1: T,
    testPart2: S,
    livePart2: S,
    numTestPart1: Int = 10,
    numTestPart2: Int = 10,
) = AOCTestSuite(
    AOCTest(part1, liveData, livePart1, numTestPart1, day = day, part = Part.ONE, live = true),
    AOCTest(part2, liveData, livePart2, numTestPart2, day = day, part = Part.TWO, live = true),
    AOCTest(part1, testData, testPart1, 1, day, part = Part.ONE, live = false),
    AOCTest(part2, testData, testPart2, 1, day, part = Part.TWO, live = false),
)


fun <T, S> defaultTestSuiteParseOnInit(
    liveDay: Day<T, S, *>,
    testDay: Day<T, S, *>,
    testPart1: T,
    livePart1: T,
    testPart2: S,
    livePart2: S,
    numTestPart1: Int = 1000,
    numTestPart2: Int = 1000,
) = AOCTestSuite(
    AOCTest({ liveDay.partOne() }, Unit, livePart1, numTestPart1, liveDay.day, Part.ONE, true),
    AOCTest({ liveDay.partTwo() }, Unit, livePart2, numTestPart2, liveDay.day, Part.TWO, true),
    AOCTest({ testDay.partOne() }, Unit, testPart1, 2, liveDay.day, Part.ONE, false),
    AOCTest({ testDay.partTwo() }, Unit, testPart2, 2, liveDay.day, Part.TWO, false),
)


