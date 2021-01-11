import no.rodland.advent.Pos

typealias Caves = Array<CharArray>

fun String.readFile(): List<String> {
    val resource = Pos::class.java.getResource("/$this")
    return resource.readText().split("\n")
}

fun String.readFileAsInt(): List<Int> {
    return this.readFile().map { it.toInt() }
}

@Suppress("ConstantConditionIf")
fun debug(str: String) {
    if (false) {
        println(str)
    }
}

fun Regex.getString(str: String, i: Int = 1): String? = find(str)?.groupValues?.get(i)
fun Regex.get(str: String, i: Int = 1): Int = find(str)!!.groupValues[i].toInt()
fun Regex.getLong(str: String, i: Int = 1): Long = find(str)!!.groupValues[i].toLong()

fun Int.isEven() = this % 2 == 0

fun Any.println() = println(this)

fun String.pad(i: Int): String {
    return this + (" ".repeat(i - this.length))
}

fun Int.pad(): String {
    return when {
        this < 10 -> "   $this"
        this < 100 -> "  $this"
        this < 1000 -> " $this"
        else -> this.toString()
    }
}

fun Char.asInt(): Int = toString().toInt()

operator fun Caves.set(pos: Pos, value: Char) {
    this[pos.y][pos.x] = value
}

operator fun Caves.contains(pos: Pos): Boolean =
        pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

operator fun Caves.get(pos: Pos): Char {
    return this[pos.y][pos.x]
}

fun <T> Sequence<T>.takeWhileInclusive(pred: (T) -> Boolean): Sequence<T> {
    var shouldContinue = true
    return takeWhile {
        val result = shouldContinue
        shouldContinue = pred(it)
        result
    }
}

fun List<Number>.product(): Long = fold(1L) { acc, n -> acc * n.toLong() }

// https://stackoverflow.com/a/59877740/13131627
fun <T> List<T>.combinations(): List<List<T>> {
    val retList = mutableListOf<List<T>>()
    val count = (1 shl size)
    (0 until count).forEach { i ->
        val working = mutableListOf<T>()
        (indices).forEach { j ->
            if (i and (1 shl j) > 0) {
                working.add(this[j])
            }
        }
        retList.add(working)
    }
    return retList
}

fun <T : Any> List<T>.combinations(r: Int, replace: Boolean = false): Sequence<List<T>> {
    val n = count()
    if (r > n) return sequenceOf()
    return sequence {
        var indices = if (replace) 0.repeat(r).toMutableList() else (0 until r).toMutableList()
        while (true) {
            yield(indices.map { this@combinations[it] })
            var i = r - 1
            loop@ while (i >= 0) {
                when (replace) {
                    true -> if (indices[i] != n - 1) break@loop
                    false -> if (indices[i] != i + n - r) break@loop
                }
                i--
            }
            if (i < 0) break
            when (replace) {
                true -> indices = (indices.take(i) + (indices[i] + 1).repeat(r - i)).toMutableList()
                false -> {
                    indices[i] += 1
                    (i + 1 until r).forEach { indices[it] = indices[it - 1] + 1 }
                }
            }
        }
    }
}

fun <T : Any> T.repeat(times: Int? = null): Sequence<T> = sequence {
    var count = 0
    while (times == null || count++ < times) yield(this@repeat)
}