package no.rodland.advent_2019

class Util

fun String.readFile(): List<String> {
    val resource = Util::class.java.getResource("/$this")
    return resource.readText().split("\n").filter { it.isNotEmpty() }
}

fun String.readFirstLineInts(): List<Int> {
    return this.readFile()[0].split(",").map { it.toInt() }
}
