package no.rodland.advent

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

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

@ExperimentalTime
@Suppress("RemoveToStringInStringTemplate")  // runtime error without on duration.toString
fun <T> report(test: AOCTest<T>) {
    getHeader().run {
        println(this)
        println("=".repeat(this.length).joinToString(""))
    }
    val results = (1..test.numTests).map {
        val start = System.nanoTime()
        val value = test.function(test.data) to test.result
        (System.nanoTime() - start) to (value.first to value.second)
    }
    val (result, expected) = results.first().second
    val nanos = results.map { it.first }
    val avg = Duration.nanoseconds(nanos.average())
    if (result != expected) {
        println("Result: ${result}, Excpected: $expected, time: ${avg.toString()}")
    } else {
        println("Result: $result")
        println("Ran ${nanos.size}, time: ${avg.toString()}")
    }
    println()
    Assertions.assertEquals(expected, result)
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
@Retention()
@ExtendWith(DisableSlowCond::class)
annotation class DisableSlow

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION)
@Retention()
annotation class Slow(val approximateRunTimeInMillis: Int)


data class AOCTest<T>(
    val function: (List<String>) -> T,
    val data: List<String>,
    val result: T,
    val numTests: Int = 10
)

class AOCTestSuite<T, S>(
    val livePart1: AOCTest<T>,
    val livePart2: AOCTest<S>,
    val testPart1: AOCTest<T>,
    val testPart2: AOCTest<S>,
)

fun <T, S> defaultTestSuite(
    part1: (List<String>) -> T,
    part2: (List<String>) -> S,
    liveData: List<String>,
    testData: List<String>,
    testResultPart1: T,
    liveResultPart1: T,
    testResultPart2: S,
    liveResultPart2: S,
) = AOCTestSuite(
    AOCTest(part1, liveData, liveResultPart1),
    AOCTest(part2, liveData, liveResultPart2),
    AOCTest(part1, testData, testResultPart1, 1),
    AOCTest(part2, testData, testResultPart2, 1),
)



