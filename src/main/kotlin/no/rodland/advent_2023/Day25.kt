package no.rodland.advent_2023


import no.rodland.advent.Day
import org.jgrapht.alg.flow.EdmondsKarpMFImpl
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph


// template generated: 31/12/2023
// Fredrik Rødland 2023

class Day25(val input: List<String>) : Day<Long, Long, SimpleGraph<String, DefaultEdge>> {

    private val parsed = input.parse()

    override fun partOne(): Long {

        parsed.vertexSet().forEach { source ->
            parsed.vertexSet().forEach { sink ->
                if (source != sink) {
                    val mc = EdmondsKarpMFImpl(parsed)
                    val minCut = mc.calculateMinCut(source, sink)
                    if (minCut == 3.0) {
//                        System.out.println("Minimum s-t cut weight: $minCut");
//                        System.out.println("Source partition S: " + mc.getSourcePartition());
//                        System.out.println("Sink partition T: " + mc.getSinkPartition());
//                        System.out.println("Cut edges (edges with their tail in S and their head in T): " + mc.getCutEdges());
                        return (mc.getSinkPartition().size * mc.getSourcePartition().size).toLong()
                    }
                }
            }


        }
        error("should have found something")
    }

    override fun partTwo(): Long {
        return 2
    }

    // frs: qnr lhk lsr
    override fun List<String>.parse(): SimpleGraph<String, DefaultEdge> {
        return SimpleGraph<String, DefaultEdge>(DefaultEdge::class.java).apply {
            map { line ->
                // frs: qnr lhk lsr
                val (from, to) = line.split(":").let {
                    it[0] to it[1].split(" ").filterNot { it.isEmpty() }
                }
                addVertex(from)
                to.forEach { addVertex(it); addEdge(from, it) }
            }
        }
    }

    override val day = "25".toInt()
}
