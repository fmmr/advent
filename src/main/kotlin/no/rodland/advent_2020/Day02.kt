package no.rodland.advent_2020


object Day02 {
    fun partOne(input: List<String>): Int = input.map { it.toPolicyAndPassword() }.count { it.first.valid(it.second) }

    fun partTwo(input: List<String>): Int = 2
}

private fun String.toPolicyAndPassword(): Pair<PasswordPolicy, String> {
    val splitted = this.split(": ")
    val password = splitted[1]
    val splittedPolicy = splitted[0].split(" ")
    val char = splittedPolicy[1]
    val (from, to) = splittedPolicy[0].split("-")
    return (PasswordPolicy(from.toInt(), to.toInt(), char[0]) to password)

}

class PasswordPolicy(val from: Int, val to: Int, val char: Char) {
    fun valid(password: String): Boolean {
        return password.count { it == char } in from..to
    }
}