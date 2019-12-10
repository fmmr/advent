package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class Util

fun String.readFile(): List<String> {
    val resource = Util::class.java.getResource("/$this")
    return resource.readText().split("\n").filter { it.isNotEmpty() }
}

fun String.readFirstLineInts(): List<Int> {
    return this.readFile()[0].split(",").map { it.toInt() }
}

fun String.readFirstLineStrings(): List<String> {
    return this.readFile()[0].split(",")
}

fun String.readFirstLineConvertToInts(): List<Int> {
    return this.readFile()[0].map { it.toString().toInt() }
}

fun permute(range: IntRange): List<List<Long>> {
    return range.flatMap { p0 ->
        range.flatMap { p1 ->
            range.flatMap { p2 ->
                range.flatMap { p3 ->
                    range.map { p4 ->
                        listOf(p0.toLong(), p1.toLong(), p2.toLong(), p3.toLong(), p4.toLong())
                    }
                }
            }
        }
    }
            .distinct()
            .filterNot { it.toSet().size != it.size }
}

// hacky - i guess
fun getValueFromDeferred(deferred: Deferred<Int>): Int {
    var value: Int = NO_OUTPUT_VALUE
    runBlocking { value = deferred.await() }
    return value
}

fun getValueFromDeferredList(deferred: Deferred<List<Long>>): List<Long> {
    var value: List<Long> = emptyList()
    runBlocking { value = deferred.await() }
    return value
}
