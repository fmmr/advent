object Day1 {
    fun day1_2_take3(ints: List<Int>): Int {
        val seenAcc = mutableSetOf<Int>()
        var acc = 0
        sequence(ints).takeWhile {
            acc += it
            seenAcc.add(acc)
        }.toList()
        return acc
    }


    fun day1_2_take4(ints: List<Int>): Int {
        val seenAcc = mutableSetOf<Int>()
        var acc = 0
        sequence(ints).first {
            acc += it
            !seenAcc.add(acc)
        }
        return acc

    }

    private fun sequence(ints: List<Int>): Sequence<Int> {
        var index = 0
        return generateSequence {
            if (index == ints.size) {
                index = 0
            }
            ints[index++]
        }
    }


}