import no.rodland.advent.Pos


fun String.readFile(): List<String> {
    return readFileAsString().split("\n")
}

fun String.readFileAsString(): String {
    return try {
        val resource = Pos::class.java.getResource("/$this")!!
        val str = resource.readText()
        if (str.last() == '\n') {
            str.dropLast(1)
        } else {
            str
        }
    } catch (e: Exception) {
        println("Unable to read file: $this, ${e.message}")
        ""
    }
}

fun String.readFileAsInt(): List<Int> {
    return readFile().map { it.toInt() }
}

fun List<String>.chunckedInts(): List<List<Int>> {
    return map { it.toIntOrNull() }
        .joinToString("\n")
        .split("null")
        .map {
            it.split("\n")
                .filterNot { it.isEmpty() }
                .map { it.replace("\n", "").toInt() }
        }
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

fun <K> MutableMap<K, Int>.increment(key: K) {
    val value = if (containsKey(key)) this[key] else 0
    this[key] = value!! + 1
}

fun factorial(n: Int) = (1..n).map { it.toLong() }.reduce { acc, l -> acc * l }

fun getCharForTyping(on: String = "⚪️", off: String = "⚫️", predicate: () -> Boolean): String {
    return if (predicate()) on else off
}


@Suppress("unused")
inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> = this.asSequence().takeUntil(predicate)
inline fun <T> Sequence<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}
