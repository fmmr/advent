package no.rodland.advent_2020

@Suppress("UNUSED_PARAMETER")
object Day23 {
    fun partOne(list: String, iterations: Int = 100): String {
        val buffer = play(list, iterations)
        return buffer.result()
    }

    fun partTwo(list: String, iterations: Int = 10000000): Int {
//        val buffer = play(list, iterations)
//        println("result: ${buffer.result()}")
        return 2
    }

    private fun play(list: String, iterations: Int): RingBuffer {
        val buffer = RingBuffer(list)
        var idx = 0
        for (i in 1..iterations) {
            val num = buffer[idx]
            buffer.blacklist(idx)
            val destination = buffer.destination(num)
            //  debug(i, buffer, num, destination, idx)
            buffer.moveStuff(num, destination, i)
            idx = buffer.indexAfter(num)
        }
        return buffer
    }

    @Suppress("unused")
    private fun debug(i: Int, buffer: RingBuffer, num: Int, destination: Int, idx: Int) {
        println("-- move $i --")
        println("cups:  ${buffer.toString(num)}")
        println("pick up: ${buffer.getBlackList()}")
        println("destination: $destination")
        println("idx_current: $idx")
        println()
    }


    class RingBuffer(data: List<Int>) : Iterable<Int> {
        constructor(str: String) : this(str.map { c -> c.toString().toInt() })

        private var dataArray = IntArray(data.size) { data[it] }
        private var blackList = emptyList<Int>()

        operator fun get(i: Int): Int = dataArray[i % dataArray.size]

        fun destination(num: Int) = dataArray.filterNot { it in blackList }.filter { it < num }.maxOrNull() ?: dataArray.filterNot { it in blackList }.filterNot { it == num }.maxOrNull()!!

        fun moveStuff(num: Int, destination: Int, iteration: Int) {
            val idxNum = indexOf(num)

            val blackListIndices = blackList.map { indexOf(it) }
            val indices = (idxNum..(3 * dataArray.size)).filterNot { (it % dataArray.size) in blackListIndices }
            val valuesWithOutBlackList = indices.map { get(it) }
            val idxDest = valuesWithOutBlackList.indexOf(destination)

            val data = (valuesWithOutBlackList.subList(0, idxDest + 1) + blackList + valuesWithOutBlackList.subList(idxDest + 1, valuesWithOutBlackList.size)).take(9)

            dataArray = IntArray(dataArray.size) { data[it] }
        }

        fun getBlackList() = blackList

        fun blacklist(idx: Int) {
            blackList = get3After(idx)
        }

        fun get3After(i: Int): List<Int> = (1..3).map { dataArray[(it + i) % dataArray.size] }

        fun indexOf(num: Int) = dataArray.indexOf(num)
        fun indexAfter(num: Int) = dataArray.indexOf(num) + 1

        fun sequence(start: Int = 0): Sequence<Int> {
            var i = start
            return sequence {
                while (true) {
                    yield(get(i++))
                }
            }
        }

        fun size(): Int = dataArray.size
        fun result(): String {
            val from = indexOf(1) + 1
            return sequence(from).take(dataArray.size - 1).joinToString("")
        }

        override fun iterator(): Iterator<Int> = object : Iterator<Int> {
            private var index: Int = 0
            override fun hasNext(): Boolean = true
            override fun next(): Int = get(index++)
        }

        fun toString(num: Int): String = toString().replace(num.toString(), "($num)")

        override fun toString(): String {
            return dataArray.joinToString(" ")
        }
    }
}
