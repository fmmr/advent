package no.rodland.advent_2020

@Suppress("UNUSED_PARAMETER")
object Day23 {
    fun partOne(list: String, iterations: Int = 100): String {
        val original = list.map { c -> c.toString().toInt() }.map { LinkNext(it) }.toList()
        val buffer = RingBuffer1(original)
        repeat(iterations) { buffer.play() }
        return buffer.resultPart1()
    }

    fun partTwo(list: String, iterations: Int = 10000000): Long {
        val original = list.map { c -> c.toString().toInt() }
        val intList = (original + ((original.maxOrNull()!! + 1)..1000000)).map { LinkNext(it) }.toList()
        val buffer = RingBuffer1(intList)
        repeat(iterations) { buffer.play() }
        val res = buffer.resultPart2()
        return res.second.num.toLong() * res.third.num.toLong()
    }

    class RingBuffer1(data: List<LinkNext>) {
        private var dataArray = Array(data.size) { data[it] }
        var current = dataArray[0]
        val size = dataArray.size
        private var dataArrayFirst = Array(10) { idx -> dataArray.indexOfFirst { arrayElement -> arrayElement.num == idx } }

        init {
            // TODO we can arrange the array (i.e by changing pointers, and skipping idx 0) so elements with num = i always reside in idx i - will make code in play simpler.
            dataArray.indices.take(dataArray.size - 1).forEach { idx -> data[idx].next = data[idx + 1] }
            dataArray.last().next = dataArray[0]
        }

        operator fun get(i: Int): LinkNext = dataArray.get(i)

        fun resultPart2(): Triple<LinkNext, LinkNext, LinkNext> {
            val indexOfOne = dataArray[dataArrayFirst[1]]
            return Triple(indexOfOne, indexOfOne.next, indexOfOne.next.next)
        }

        fun resultPart1(): String {
            var current = dataArray[dataArrayFirst[1]].next
            return (1..8).fold("") { acc, _ ->
                val ret = acc + current.num.toString()
                current = current.next
                ret
            }
        }

        fun play() {
            val blackList1 = current.next
            val blackList2 = blackList1.next
            val blackList3 = blackList2.next
            val blackList = setOf(blackList1.num, blackList2.num, blackList3.num)

            val next = blackList3.next

            val destinationValue = setOf(current.num - 1, current.num - 2, current.num - 3, current.num - 4, size, size - 1, size - 2, size - 3).first { it > 0 && it !in blackList }
            val destination = when {
                destinationValue >= 10 -> dataArray[destinationValue - 1]
                else -> dataArray[dataArrayFirst[destinationValue]]
            }

            val destinationNext = destination.next

            // ok - now the flipping
            destination.next = blackList1
            blackList3.next = destinationNext
            current.next = next
            current = next
        }
    }

    class LinkNext(val num: Int) {
        lateinit var next: LinkNext
        override fun toString() = "link: $num, next: ${next.num}"
    }
}
