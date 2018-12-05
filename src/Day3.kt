class Day3 {

    private val testList = listOf("#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2", "#1 @ 1,3: 4x4").map { Claim(it) }
    private val list3 = "src/input_3.txt".readFile().map { Claim(it) }

    fun main(args: Array<String>) {
        (3 to 1).drop {
            part_one_take_one()
        }
        (3 to 1).report(2) {
            part_one_take_two()
        }
        (3 to 2).drop {
            part_two_take_one()  //took: 32116ms noOverlap = #806 @ 736,434: 22x21
        }
        (3 to 2).report(2) {
            part_two_take_two()  // took: 160ms
        }
    }

    // had a look @ https://github.com/tginsberg/advent-2018-kotlin/
    // who solved part1 similar to my take 2.
    private fun part_two_take_two() {
        val map: MutableMap<Pair<Int, Int>, Claim> = mutableMapOf()
        val candidates = list3.toMutableSet()
        list3.forEach { claim ->
            claim.toPos().forEach { p ->
                val previous = map.getOrPut(p) { claim }
                if (previous != claim) {
                    // if there was another claim at this spot, both the previous and current claim
                    // is not a candidate anymore
                    candidates.remove(claim)
                    candidates.remove(previous)
                }
            }
        }
        println("list3.size = ${list3.size}")
        println("candidates.size = ${candidates.size}")
        println("candidates = ${candidates}")
    }


    private fun part_two_take_one() {
        // asSequence shaves of approx 20 sec.
        val noOverlap = list3.asSequence().mapIndexed { index, claim ->
            val overLapsForClaim = list3
                    .filter { it != claim }
                    .flatMap { claim.overlap(it) }
                    .toList()
                    .size
            Pair(claim, overLapsForClaim)
        }.first { it.second == 0 }.first
        println("noOverlap = $noOverlap")
    }

    private fun part_one_take_two() {
        val list = list3
                .flatMap { it.toPos() }
                .groupingBy { it }
                .eachCount()
                .count { it.value > 1 }

        println("list = $list")
    }

    private fun part_one_take_one() {
        val list = "src/input_3.txt".readFile().map { Claim(it) }
        val overlaps = list.mapIndexed { index, claim ->
            list.subList(index + 1, list.size).map { claim.overlap(it) }.flatten()
        }.flatten().toSet()

        debug("overlaps = $overlaps")
        println("overlaps.size = ${overlaps.size}")
        println("list.size = ${list.size}")

    }

    data class Claim(val str: String) {
        val id: Int
        private val x: Int
        private val y: Int
        private val w: Int
        private val h: Int
        private val RE = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

        init {
            val find = RE.find(str)
            val (id, x, y, w, h) = find!!.destructured
            this.id = id.toInt()
            this.x = x.toInt()
            this.y = y.toInt()
            this.w = w.toInt()
            this.h = h.toInt()
        }

        fun toPos(): List<Pair<Int, Int>> {
            return (x until (x + w)).map { xCoord ->
                (y until (y + h)).map { yCoord ->
                    (xCoord to yCoord)
                }
            }.flatten()
        }

        override fun toString(): String {
            return str
        }

        fun overlap(other: Claim): Set<Pair<Int, Int>> {
            return toPos().intersect(other.toPos())
        }
    }
}
