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

fun <A, B> Pair<A, B>.drop(take: Int = 1, function: () -> Unit) {
    val header = "DAY: ${this.first}, PART: ${this.second}, TAKE: $take"
    println(header)
    println("=".repeat(header.length))
    println("Not run function: ${function.javaClass} - this function is very timeconuming")
    println()
}

fun String.readFile(): List<String> {
    return File(this).readLines().toMutableList()
}

@Suppress("ConstantConditionIf")
fun debug(str: String){
    if (false){
        println(str)
    }
}

fun Regex.first(str: String): Int = find(str)!!.groupValues[1].toInt()
