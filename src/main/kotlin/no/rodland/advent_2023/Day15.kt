package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 15/12/2023
// Fredrik Rødland 2023

class Day15(val input: List<String>) : Day<Int, Int, List<Day15.Step>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.sumOf { it.hash }
    }

    override fun partTwo(): Int {
        val ar = Array(256) { mutableListOf<Lens>() }
        parsed.forEach { step ->
            ar[step.label.hash()].also { box ->
                if (step.isMinus) {
                    box.remove(step.label)
                } else {
                    box.replace(step.label, step)
                }
            }
        }
        return ar.flatMapIndexed { box, list ->
            list.mapIndexed { slot, lens -> focusingPower(box, slot + 1, lens) }
        }.sum()
    }

    private fun focusingPower(box: Int, slot: Int, lens: Lens) = (box + 1) * slot * lens.focal

    private fun MutableList<Lens>.remove(label: String) {
        removeIf { it.label == label }
    }

    private fun MutableList<Lens>.replace(label: String, step: Step) {
        Lens(label, step.num!!).also { lens ->
            indexOfFirst { it.label == label }.also { idx -> if (idx >= 0) this[idx] = lens else add(lens) }
        }
    }

    data class Lens(val label: String, val focal: Int)

    data class Step(val str: String) {
        val label = str.split("=", "-")[0]
        val num: Int? = str.split("=").let {
            if (it.size == 2) {
                it[1].toInt()
            } else {
                null
            }
        }
        val isMinus = num == null
        val hash = str.hash()
    }

    override fun List<String>.parse(): List<Step> {
        return first().split(",").map { Step(it) }
    }

    override val day = "15".toInt()
}

fun String.hash() = fold(0) { acc, c -> ((c.code + acc) * 17).mod(256) }

