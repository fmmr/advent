package no.rodland.advent_2018

import get

private val re = """.*?(\d+),? (\d+),? (\d+),? (\d+).*""".toRegex()

data class Instruction(val i: Int, val a: Int, val b: Int, val c: Int) {
    constructor(str: String) : this(re.get(str), re.get(str, 2), re.get(str, 3), re.get(str, 4))
}