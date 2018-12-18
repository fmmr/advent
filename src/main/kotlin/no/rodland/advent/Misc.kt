import no.rodland.advent.Pos

typealias Caves = Array<CharArray>

fun String.readFile(): List<String> {
    val resource = Any::class.java.getResource("/$this")
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
