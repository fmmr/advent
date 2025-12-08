package no.rodland.advent_2025

import cartesianPairs
import no.rodland.advent.Day
import no.rodland.advent.Pos3D

// template generated: 08/12/2025
// Fredrik Rødland 2025

@Suppress("unused")
class Day08(val input: List<String>) : Day<Long, Long, Pair<List<Pos3D>, List<Pair<Pos3D, Pos3D>>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        // test: 10, live: 1000
        val (points, pairs) = parsed
        val iterations = if (points.size == 20) 10 else 1000
        val product = dsu(points, pairs.take(iterations))
        return product.sortedDescending().take(3).reduce { acc, i -> acc * i }.toLong()
    }

    // Union–Find/Disjoint Set Union
    private fun dsu(points: List<Pos3D>, pairs: List<Pair<Pos3D, Pos3D>>): Collection<Int> {
        val indexOf = points.withIndex().associate { (i, p) -> p to i }
        val dsu = DSU(points.size)
        // Union the chosen pairs
        for ((p, q) in pairs) {
            val ip = indexOf.getValue(p)
            val iq = indexOf.getValue(q)
            dsu.union(ip, iq)
        }
        // Count component sizes: group by root
        val rootToSize = HashMap<Int, Int>()
        for (i in points.indices) {
            val r = dsu.find(i)
            rootToSize[r] = (rootToSize[r] ?: 0) + 1
        }
        return rootToSize.values
    }

    private fun naiveFmr(pairs: List<Pair<Pos3D, Pos3D>>): Collection<Int> {
        val result = pairs
            .fold(mutableListOf<MutableSet<Pos3D>>()) { setOfSets, (p, q) ->
                val both = setOfSets.firstOrNull { p in it && q in it }
                val pSet = setOfSets.firstOrNull { p in it }
                val qSet = setOfSets.firstOrNull { q in it }

                if (both != null) {
                    // do nothing
                } else if (pSet != null && qSet != null) {
                    setOfSets.remove(pSet)
                    setOfSets.remove(qSet)
                    setOfSets.add((pSet union qSet).toMutableSet())
                } else if (pSet != null) {
                    pSet.add(q)
                } else if (qSet != null) {
                    qSet.add(p)
                } else {
                    setOfSets.add(mutableSetOf(p, q))
                }
                setOfSets
            }
        return result.map { it.size }
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<List<Pos3D>, List<Pair<Pos3D, Pos3D>>> {
        val map = map { line ->
            val (x, y, z) = line.split(",").map { it.toInt() }
            Pos3D(x, y, z)
        }
        val pairs = map
            .cartesianPairs()
            .filterNot { it.first == it.second }
            .sortedBy { (p, q) ->
                p.squaredDistance(q)
            }
        return map to pairs
    }

    class DSU(n: Int) {
        private val parent = IntArray(n) { it }
        private val size = IntArray(n) { 1 }

        fun find(x: Int): Int {
            var v = x
            while (v != parent[v]) {
                parent[v] = parent[parent[v]] // path halving
                v = parent[v]
            }
            return v
        }

        fun union(a: Int, b: Int): Boolean {
            var ra = find(a)
            var rb = find(b)
            if (ra == rb) return false
            // union by size: attach smaller tree under larger
            if (size[ra] < size[rb]) {
                val tmp = ra; ra = rb; rb = tmp
            }
            parent[rb] = ra
            size[ra] += size[rb]
            return true
        }

        fun componentSize(x: Int): Int = size[find(x)]
    }

    override val day = "08".toInt()
}
