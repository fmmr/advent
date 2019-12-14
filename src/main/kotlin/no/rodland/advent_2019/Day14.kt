package no.rodland.advent_2019

object Day14 {
    fun partOne(list: List<String>): Int {
        val process = toProcess(list)
        val alreadyMade = mutableMapOf<String, Int>()
        val result = process(process, Step("FUEL", 1), alreadyMade)
        return result.filter { it.name == "ORE" }.map { it.quantity }.sum()
    }

    private fun toProcess(list: List<String>): Map<Step, List<Step>> {
        return list.map { it.split("=>") }
                .map { it[0].split(",") to it[1].trim() }
                .map { Step(it.second) to it.first.map { c -> Step(c.trim()) } }
                .toMap()
    }

    fun process(process: Map<Step, List<Step>>, step: Step, alreadyMade: MutableMap<String, Int>): List<Step> {
        val name = step.name
        println("need: $step  - have $alreadyMade")

        if (name == "ORE") {
            return listOf(step)  // always need ore...
        }

        val already = alreadyMade[name] ?: 0
        return if (already >= step.quantity) {
            val tooMuch = already - step.quantity
            alreadyMade[name] = tooMuch
            emptyList()  // yeah - no need for more
        } else if (already > 0) {
            alreadyMade.remove(name)
            process(process, Step(step.name, step.quantity - already), alreadyMade)
        } else {
            val def = process.keys.first { it.name == name }
            val needed = process[def] ?: error("we just fetched key $def in previous statement")
            if (step.quantity > def.quantity) {
                //  må ha 13 , def sier 3
                val mustMake = Math.ceil(step.quantity.toDouble() / def.quantity).toInt()
                // 5 - har da (5*3)-13 == 2 til overs
                alreadyMade[name] = (mustMake * def.quantity) - step.quantity
                needed.map { Step(it.name, it.quantity * mustMake) }.flatMap { process(process, it, alreadyMade) }
            } else {
                //  må ha 3 , def sier 8
                alreadyMade[name] = def.quantity - step.quantity
                needed.flatMap { process(process, it, alreadyMade) }
            }
        }
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Step(val name: String, val quantity: Int) {
        constructor(str1: String, str2: String) : this(str2.trim(), str1.trim().toInt())
        constructor(str: String) : this(str.split(" ")[0], str.split(" ")[1])
    }
}