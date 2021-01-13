package no.rodland.advent_2016

typealias Maze = Array<CharArray>

object Day08 {

    fun partOne(list: List<String>, width: Int, height: Int): Int {
        val ops = list.map { Cmd(it) }
        val grid = Array(height) { CharArray(width) { '.' } }
        grid.apply(ops)
        grid.print()
        return grid.map { it.count { c -> c == '#' } }.sum()
    }

    private fun Maze.print() {
        map { it.joinToString("").replace(".", " ") }.forEach { println(it) }
    }

    private fun Maze.apply(cmds: List<Cmd>) {
        cmds.forEach { apply(it) }
    }

    private fun Maze.apply(cmd: Cmd) {
        when (cmd.op) {
            Op.RECT -> {
                (0 until cmd.arg1).forEach { x ->
                    (0 until cmd.arg2).forEach { y ->
                        this[y][x] = '#'
                    }
                }
            }
            Op.ROT_C -> {
                val col = (0 until size).map { this[it][cmd.arg1] }
                val size = col.size
                val newCol = CharArray(size) { i -> col[(size + i - cmd.arg2) % size] }
                (0 until size).forEach { this[it][cmd.arg1] = newCol[it] }

            }
            Op.ROT_R -> {
                val row = this[cmd.arg1]
                val size = row.size
                val charArray = CharArray(size) { i -> row[(size + i - cmd.arg2) % size] }
                this[cmd.arg1] = charArray
            }
        }
    }

    val regex = "(rotate column|rotate row|rect)( | y=| x=)(\\d+)( by |x)(\\d+)".toRegex()

    data class Cmd(val op: Op, val arg1: Int, val arg2: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(mr.component1().toOp(), mr.component3().toInt(), mr.component5().toInt())
    }

    fun String.toOp(): Op {
        return when (this) {
            "rotate column" -> Op.ROT_C
            "rect" -> Op.RECT
            "rotate row" -> Op.ROT_R
            else -> error("unable to parse $this")
        }
    }

    enum class Op { ROT_C, ROT_R, RECT }
}

