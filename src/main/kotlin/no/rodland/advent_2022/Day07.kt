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

    private fun sizes(list: List<String>): List<Pair<Dir, Int>> {
        val stack = Stack<String>()
        val all = list.mapNotNull { line ->
            val path = stack.path()
            when {
                line == "\$ cd .." -> {
                    stack.pop()
                    null
                }
                line.startsWith("\$ cd") -> {
                    val name = stack.push(line.toDirName())
                    Dir(name, path)
                }
                line.matches("^\\d+ .*".toRegex()) -> line.toFile(path)
                else -> null // dir are already created by cd-command.
            }
        }
        val files = all.filterIsInstance<File>()
        return all.filterIsInstance<Dir>().map { dir -> dir to files.filter { dir.isParent(it) }.sumOf { it.size() } }
    }

    private fun String.toDirName(): String {
        val substringAfter = substringAfter("cd ")
        return if (substringAfter.endsWith("/")) substringAfter else "${substringAfter}/"
    }

    private fun Stack<String>.path() = joinToString("/").replace("//", "/")

    private fun String.toFile(path: String): File = split(" ").let { (first, second) -> File(second, path, first.toInt()) }

    sealed class Node(open val name: String, open val path: String) {
        fun pathWithSelf() = path + name
        abstract fun size(): Int
    }

    data class Dir(override val name: String, override val path: String) : Node(name, path) {
        override fun size(): Int = 0
        fun isParent(file: Node): Boolean = file.path.startsWith(pathWithSelf())
    }

    data class File(override val name: String, override val path: String, val size: Int) : Node(name, path) {
        override fun size() = size
    }

}

