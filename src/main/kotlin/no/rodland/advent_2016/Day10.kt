package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day10 {
    fun partOne(list: List<String>): Int {
        val bots = Array(210) { Bot(it) }
        val outputs = IntArray(21) { 0 }
        doYourThing(list, bots, outputs)
        return bots.filter { it.isVictim }.first().number
    }

    fun partTwo(list: List<String>): Int {
        val bots = Array(210) { Bot(it) }
        val outputs = IntArray(21) { 0 }
        doYourThing(list, bots, outputs)
        return outputs[0] * outputs[1] * outputs[2]
    }

    private fun doYourThing(list: List<String>, bots: Array<Bot>, outputs: IntArray): Array<Bot> {
        val inputRE = "value (\\d+) goes to bot (\\d+)".toRegex()
        val giveRE = "bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)".toRegex()

        val (assigning, dist) = list.partition { it.contains("goes to") }
        assigning.forEach { str ->
            val destructured = inputRE.find(str)!!.destructured
            val value = destructured.component1().toInt()
            val botNum = destructured.component2().toInt()
            bots[botNum].put(value)
        }

        dist.forEach { str ->
            val destructured = giveRE.find(str)!!.destructured
            val botNum = destructured.component1().toInt()
            val lowGiveType = destructured.component2()
            val lowGiveIdx = destructured.component3().toInt()
            val highGiveType = destructured.component4()
            val highGiveIdx = destructured.component5().toInt()
            bots[botNum].cmd = Cmd(lowGiveType, lowGiveIdx, highGiveType, highGiveIdx)
        }

        while (bots.count { it.hasBoth() } > 0) {
            bots.filter { it.hasBoth() }.forEach { it.doWork(outputs, bots) }
        }
        return bots
    }

    data class Cmd(val lowGiveType: String, val lowGiveIdx: Int, val highGiveType: String, val highGiveIdx: Int)

    class Bot(val number: Int, private var low: Int = -1, private var high: Int = -1, var cmd: Cmd? = null) {
        fun hasBoth() = low != -1 && high != -1

        var isVictim = false

        fun put(value: Int) {
            if (hasBoth()) {
                error("bot already has two values")
            }
            if (low == -1) {
                low = value
            } else {
                if (value < low) {
                    high = low
                    low = value
                } else {
                    high = value
                }
            }
            if (low == 17 && high == 61) {
                isVictim = true
            }
        }

        private fun clear() {
            high = -1
            low = -1
        }

        override fun toString(): String {
            return "Bot(number=$number, low=$low, high=$high)"
        }

        fun doWork(outputs: IntArray, bots: Array<Bot>) {
            if (cmd!!.lowGiveType == "output") {
                outputs[cmd!!.lowGiveIdx] = low
            } else {
                bots[cmd!!.lowGiveIdx].put(low)
            }
            if (cmd!!.highGiveType == "output") {
                outputs[cmd!!.highGiveIdx] = high
            } else {
                bots[cmd!!.highGiveIdx].put(high)
            }
            clear()
        }

    }
}
