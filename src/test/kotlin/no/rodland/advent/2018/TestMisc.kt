package no.rodland.advent.`2018`

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import kotlin.system.measureTimeMillis

fun <T> report(function: () -> Pair<T, T>) {
    println(getHeader())
    println("=".repeat(getHeader().length))
    val millis = measureTimeMillis {
        val value = function()
        println("Result: ${value.first}, Excpected: ${value.second}")
        Assertions.assertEquals(value.second, value.first)
    }
    println("took: ${millis}ms")
    println()
}

private fun getHeader(): String {
    val method = Thread.currentThread().stackTrace[3].methodName
    val name = method.split(",")
    val take = if (name.size == 4) {
        name[3]
    } else {
        "1"
    }
    val header = "DAY: ${name[0]}, PART: ${name[1]}, DATA: ${name[2]}, TAKE: $take"
    return header
}

val OK_TO_WAIT_PR_TEST = 600

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



