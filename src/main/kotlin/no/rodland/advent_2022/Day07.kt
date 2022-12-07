package no.rodland.advent_2022

import java.util.Stack

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day07 {
    fun partOne(list: List<String>): Int {
        val sizes = sizes(list)
        return sizes.filter { it.second < 100000 }.sumOf { it.second }
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Int {
        val sizes = sizes(list)
        val used = sizes.first { it.first.name == "/" }.second
        val required = 30000000 - (70000000 - used)
        return sizes.filter { it.second >= required }.minOf { it.second }
    }

    private fun sizes(list: List<String>): List<Pair<Node, Int>> {
        val stack = Stack<String>()
        val (directories, files) = list.mapNotNull { line ->
            val path = stack.toList().joinToString("/").replace("//", "/")
            when {
                line == "\$ cd .." -> {
                    stack.pop()
                    null
                }
                line.startsWith("\$ cd") -> {
                    val name = line.substringAfter("cd ").appendSlashIfMissing()
                    stack.push(name)
                    Dir(name, path)
                }
                line.matches("^\\d+ .*".toRegex()) -> line.toNode(path)
                else -> null // dir are already created by cd-command.
            }
        }.partition { it is Dir }

        return directories.map { dir ->
            dir to files.filter { it.path.startsWith(dir.pathWithSelf()) }.sumOf { it.size() }
        }
    }

    private fun String.appendSlashIfMissing(): String = if (endsWith("/")) this else "$this/"

    private fun String.toNode(path: String): Node {
        val (first, second) = split(" ")
        return if (first == "dir") {
            Dir(second, path)
        } else {
            File(second, path, first.toInt())
        }
    }

    sealed class Node(open val name: String, open val path: String) {
        fun pathWithSelf() = path + name
        abstract fun size(): Int
    }

    data class Dir(override val name: String, override val path: String) : Node(name, path) {
        override fun size(): Int = 0
    }

    data class File(override val name: String, override val path: String, val size: Int) : Node(name, path) {
        override fun size() = size
    }

}

