fun main(args: Array<String>) {
//    (3 to 1).report {
//        part_one_take_one()
//    }
    (3 to 1).report(2) {
        part_one_take_two()
    }
    (3 to 2).report {
        part_two_take_one()  //noOverlap = #806 @ 736,434: 22x21
    }
}
//    val list = listOf("#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2", "#1 @ 1,3: 4x4").map { Claim(it) }

private fun part_two_take_one() {
    var i = 0


    val list = "/Users/frerodla/projects/advent/src/input_3.txt".readFile().map { Claim(it) }

    // asSequence shaves of approx 20 sec.
    val noOverlap = list.asSequence().mapIndexed { index, claim ->
        val overLapsForClaim = list
                .filter { it != claim }
                .map { claim.overlap(it) }
                .flatten()
                .toList()
                .size
        Pair(claim, overLapsForClaim)
    }.first { it.second == 0 }.first


    println("noOverlap = ${noOverlap}")

}


private fun part_one_take_two() {
    val list = "/Users/frerodla/projects/advent/src/input_3.txt".readFile()
            .asSequence()
            .map { Claim(it) }
            .map { it.toPos() }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .count()

    println("list.size = ${list}")
}

private fun part_one_take_one() {
    var i = 0
    val list = "/Users/frerodla/projects/advent/src/input_3.txt".readFile().map { Claim(it) }
    val overlaps = list.mapIndexed { index, claim ->
        list.subList(index + 1, list.size).map { claim.overlap(it) }.flatten()
    }.flatten().toSet()

//    println("overlaps = ${overlaps}")
    println("overlaps.size = ${overlaps.size}")
    println("list.size = ${list.size}")

//    println(Claim("#1 @ 1,3: 4x4").toPos())
//    println(Claim("#1 @ 1,3: 4x4").toPos().size)
}

data class Claim(val str: String) {
    val id: Int
    val x: Int
    val y: Int
    val w: Int
    val h: Int
    val RE = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

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
