package no.rodland.advent_2020

@Suppress("UNUSED_PARAMETER")
object Day23 {
    fun partOne(list: String, iterations: Int = 100): String {
        val buffer = playPart1(list, iterations)
        return buffer.result()
    }

    fun partTwo(list: String, iterations: Int = 10000000): Long {
        val original = list.map { c -> c.toString().toInt() }
        val intList = (original + ((original.maxOrNull()!! + 1)..1000000)).map { LinkNext(it) }.toList()
        val buffer = RingBuffer2(intList)
        repeat(10000000) { buffer.play() }
        val res = buffer.result()
        return res.second.num.toLong() * res.third.num.toLong()
    }

    class RingBuffer2(data: List<LinkNext>) {

        private var dataArray = Array(data.size) { data[it] }
        var current = dataArray[0]
        val size = dataArray.size
        private var dataArrayFirst = Array(10) { data[it] }

        init {
            dataArray.indices.take(dataArray.size - 1).forEach { idx -> data[idx].next = data[idx + 1] }
            dataArray.last().next = dataArray[0]
        }

        operator fun get(i: Int): LinkNext = dataArray.get(i)

        fun result(): Triple<LinkNext, LinkNext, LinkNext> {
            val indexOfOne = dataArray[dataArray.indexOfFirst { it.num == 1 }]
            return Triple(indexOfOne, indexOfOne.next, indexOfOne.next.next)
        }

        fun play() {
            val blackList1 = current.next
            val blackList2 = blackList1.next
            val blackList3 = blackList2.next
            val blackList = setOf(blackList1.num, blackList2.num, blackList3.num)
            val next = blackList3.next
            val nextValue = setOf(current.num - 1, current.num - 2, current.num - 3, current.num - 4, size, size - 1, size - 2, size - 3).first { it > 0 && it !in blackList }
            val nextPointer = if (nextValue >= 10) {
                dataArray[nextValue - 1]
            } else {
                dataArray[dataArrayFirst.indexOfFirst { it.num == nextValue }]
            }
            val nextNextPointer = nextPointer.next

            current.next = next
            nextPointer.next = blackList1
            blackList3.next = nextNextPointer
            current = next
        }
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

    class LinkNext(val num: Int) {
        lateinit var next: LinkNext
        override fun toString() = "link: $num, next: ${next.num}"
    }

    private fun playPart1(list: String, iterations: Int): RingBuffer {
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
