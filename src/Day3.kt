object Day3 {

    fun part_one_take_one(list: List<Claim>): Int {
        val overlaps = list.mapIndexed { index, claim ->
            list.subList(index + 1, list.size).map { claim.overlap(it) }.flatten()
        }.flatten().toSet()

        debug("overlaps = $overlaps")
        println("overlaps.size = ${overlaps.size}")
        println("list.size = ${list.size}")
        return overlaps.size
    }

    fun part_one_take_two(data: List<Claim>): Int {
        return data
                .flatMap { it.toPos() }
                .groupingBy { it }
                .eachCount()
                .count { it.value > 1 }
    }

    fun part_two_take_one(list: List<Claim>): Claim {
        // asSequence shaves of approx 20 sec.
        val noOverlap = list.asSequence().mapIndexed { index, claim ->
            val overLapsForClaim = list
                    .filter { it != claim }
                    .flatMap { claim.overlap(it) }
                    .toList()
                    .size
            Pair(claim, overLapsForClaim)
        }.first { it.second == 0 }.first
        println("noOverlap = $noOverlap")
        return noOverlap
    }

    // had a look @ https://github.com/tginsberg/advent-2018-kotlin/
    // who solved part1 similar to my take 2.
    fun part_two_take_two(list: List<Claim>): Claim {
        val map: MutableMap<Pair<Int, Int>, Claim> = mutableMapOf()
        val candidates = list.toMutableSet()
        list.forEach { claim ->
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
        val claim = candidates.toList()[0]
        println("candidates.size = ${candidates.size}")
        println("claim = ${claim}")
        return claim
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
