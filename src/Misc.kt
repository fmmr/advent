import java.io.File

fun String.readFile(): List<String> {
    return File(this).readLines()
}

fun String.readFileAsInt(): List<Int> {
    return File(this).readLines().map { it.toInt() }
}

@Suppress("ConstantConditionIf")
fun debug(str: String) {
    if (false) {
        println(str)
    }
}

fun Regex.get(str: String, i: Int = 1): Int = find(str)!!.groupValues[i].toInt()
