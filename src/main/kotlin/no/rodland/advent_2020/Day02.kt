package no.rodland.advent_2020


object Day02 {
    fun partOne(input: List<String>): Int = input.map { it.toPolicyAndPassword() }.count { (policy, password) -> policy.validPart1(password) }

    fun partTwo(input: List<String>): Int = input.map { it.toPolicyAndPassword() }.count { (policy, password) -> policy.validPart2(password) }
}

private fun String.toPolicyAndPassword(): Pair<PasswordPolicy, String> {
    val splitted = this.split(": ")
    val password = splitted[1]
    val splittedPolicy = splitted[0].split(" ")
    val char = splittedPolicy[1]
    val (from, to) = splittedPolicy[0].split("-")
    return (PasswordPolicy(from.toInt(), to.toInt(), char[0]) to password)

}

data class PasswordPolicy(val from: Int, val to: Int, val char: Char) {
    fun validPart1(password: String): Boolean = password.count { it == char } in from..to

    fun validPart2(password: String): Boolean = (password[from - 1] == char) xor (password[to - 1] == char)
}