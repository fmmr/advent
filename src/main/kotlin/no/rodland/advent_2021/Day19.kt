package no.rodland.advent_2021

import no.rodland.advent.Pos3D

@Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
object Day19 {
    fun partOne(list: List<String>): Int {
        val scanners = list.parse()

        var start = scanners[0]
        val joins = scanners.drop(1).toMutableList()
        while (joins.isNotEmpty()) {
            val foundIndices = mutableListOf<Int>()
            joins.indices.forEach { index ->
                val potentialJoin = joins[index]
                val joinResult = start.join(potentialJoin)
                if (joinResult != null) {
                    start = joinResult
                    foundIndices.add(index)
                }
            }
            foundIndices.sortedDescending().forEach {
                joins.removeAt(it)
            }
        }
        return start.pos.size
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }

    private data class Scanner(val pos: Set<Pos3D>) : Set<Pos3D> by pos {
        constructor(str: String) : this(str.split("\n").map {
            it.split(",").let { Pos3D(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        }.toSet())

        fun transform(t: (Pos3D) -> Pos3D): Scanner = Scanner(pos.map { t(it) }.toSet())

        fun join(other: Scanner): Scanner? {
            transformations().forEach { trsf ->
                val test = other.transform(trsf)
                pos.forEach { sourcePos ->
                    test.pos.forEach { otherPos ->
                        val diff = sourcePos - otherPos
                        val count = test.pos.map { it + diff }.count {
                            pos.contains(it)
                        }
                        if (count >= 12) {
                            return Scanner(pos + test.pos.map { it + diff })
                        }
                    }
                }
            }
            return null
        }
    }

    fun transformations(): List<(Pos3D) -> Pos3D> = listOf(
        { p: Pos3D -> Pos3D(p.x, p.y, p.z) },
        { p: Pos3D -> Pos3D(p.x, p.z, -p.y) },
        { p: Pos3D -> Pos3D(p.x, -p.y, -p.z) },
        { p: Pos3D -> Pos3D(p.x, -p.z, p.y) },
        { p: Pos3D -> Pos3D(p.y, -p.x, p.z) },
        { p: Pos3D -> Pos3D(p.y, p.z, p.x) },
        { p: Pos3D -> Pos3D(p.y, p.x, -p.z) },
        { p: Pos3D -> Pos3D(p.y, -p.z, -p.x) },
        { p: Pos3D -> Pos3D(-p.x, -p.y, p.z) },
        { p: Pos3D -> Pos3D(-p.x, -p.z, -p.y) },
        { p: Pos3D -> Pos3D(-p.x, p.y, -p.z) },
        { p: Pos3D -> Pos3D(-p.x, p.z, p.y) },
        { p: Pos3D -> Pos3D(-p.y, p.x, p.z) },
        { p: Pos3D -> Pos3D(-p.y, -p.z, p.x) },
        { p: Pos3D -> Pos3D(-p.y, -p.x, -p.z) },
        { p: Pos3D -> Pos3D(-p.y, p.z, -p.x) },
        { p: Pos3D -> Pos3D(p.z, p.y, -p.x) },
        { p: Pos3D -> Pos3D(p.z, p.x, p.y) },
        { p: Pos3D -> Pos3D(p.z, -p.y, p.x) },
        { p: Pos3D -> Pos3D(p.z, -p.x, -p.y) },
        { p: Pos3D -> Pos3D(-p.z, -p.y, -p.x) },
        { p: Pos3D -> Pos3D(-p.z, -p.x, p.y) },
        { p: Pos3D -> Pos3D(-p.z, p.y, p.x) },
        { p: Pos3D -> Pos3D(-p.z, p.x, -p.y) },
    )

    private fun List<String>.parse() = filterNot { it.contains("scanner") }.joinToString("\n").split("\n\n").map { s: String -> Scanner(s) }
}
