import java.io.File
import kotlin.system.measureTimeMillis

fun <A, B> Pair<A, B>.report(take: Int = 1, function: () -> Unit) {
    val header = "DAY: ${this.first}, PART: ${this.second}, TAKE: $take"
    println(header)
    println("=".repeat(header.length))
    val millis = measureTimeMillis {
        function()
    }
    println("took: ${millis}ms")
    println()
}

fun String.readFileMutable(): MutableList<String> {
    return File(this).readLines().toMutableList()
}

fun String.readFile(): List<String> {
    return File(this).readLines().toMutableList()
}
