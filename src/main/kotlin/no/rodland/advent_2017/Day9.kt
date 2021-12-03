package no.rodland.advent_2017

object Day9 {
    fun partOne(input: String): Int {
        val onlyGroups = cleanGroups(input)
        var value = 0
        var sum = 0
        onlyGroups.first.forEach {
            if (it == '{') {
                value += 1
                sum += value
            } else {
                value -= 1
            }
        }

//        println("input:         $input")
//        println("only groups:   ${onlyGroups.first}")
        println("current value: $value, sum: $sum")
        return sum
    }

    fun partTwo(input: String): Int {
        val onlyGroups = cleanGroups(input)
        println("removed ${onlyGroups.second} garbage")
        return onlyGroups.second
    }

    private fun cleanGroups(input: String): Pair<String, Int> {
        var isGarbage = false
        var cancelNext = false
        var countGarbage = 0
        val onlyGroups = input.mapNotNull {
            if (cancelNext) {
                cancelNext = false
                null
            } else if (it == '!') {
                cancelNext = true
                null
            } else if (isGarbage) {
                if (it == '>') {
                    isGarbage = false
                } else {
                    countGarbage++
                }
                null
            } else if (it == '<') {
                isGarbage = true
                null
            } else if (!"{}".contains(it)) {
                null

            } else {
                it
            }
        }.joinToString("")
        return onlyGroups to countGarbage
    }

}