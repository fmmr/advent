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

fun Regex.get(str: String, i: Int = 1): Int = find(str)!!.groupValues[i].toInt()
