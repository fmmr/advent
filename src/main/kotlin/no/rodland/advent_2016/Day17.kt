package no.rodland.advent_2016

import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER", "UnstableApiUsage", "DEPRECATION")
object Day17 {
    private val md5: HashFunction = Hashing.md5()
    private val OPEN_DOORS = setOf('b', 'c', 'd', 'e', 'f')

    fun partOne(str: String): String {
        val endState = bfs(str)
        return endState!!.pathAsString
    }

    private fun bfs(str: String, destination: Pos = Pos(3, 3)): State? {
        val initial = Pos(0, 0)
        val queue = ArrayDeque(listOf(State(emptyList(), initial, str)))

        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            if (state.pos == destination) {
                return state
            }
            queue.addAll(
                    "UDLR"
                            .filterIndexed { index, _ -> state.isOpen(index) }
                            .map { it to state.pos.next(it) }
                            .filter { it.second.isPositiveAndWithin(destination.x, destination.y) }
                            .map {
                                State(state.path + it.first, it.second, str)
                            }
            )
        }
        return null
    }

    fun partTwo(str: String): Int {
        return 2
    }

    class State(val path: List<Char>, val pos: Pos, password: String) {
        val pathAsString = path.joinToString("")
        private val md5 = md5(password + pathAsString).take(4)
        fun isOpen(idx: Int) = md5[idx].isOpen()
    }


    private fun md5(acc: String) = md5.hashString(acc, Charsets.UTF_8).toString()
    private fun Char.isOpen(): Boolean = this in OPEN_DOORS
}

