package no.rodland.advent

interface Day<T, S, U> {
    fun List<String>.parse(): U
    fun partOne(): T
    fun partTwo(): S

    val day: Int
}