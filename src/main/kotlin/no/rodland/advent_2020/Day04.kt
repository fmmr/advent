package no.rodland.advent_2020

object Day04 {
    fun partOne(list: String): Int {
        return list.split("\n\n").count { it.validPassportPart1() }
    }

    fun partTwo(list: String): Int {
        return list.split("\n\n").count { it.validPassportPart2() }
    }

    private fun String.validPassportPart1(): Boolean {
        val fields = this.split("\n", " ").map { it.split(":")[0] }.map { it.toUpperCase() }
        return ValidationType.values().filterNot { it == ValidationType.CID }.map { it.toString() }.all { fields.contains(it) }
    }

    private fun String.validPassportPart2(): Boolean {
        val fields = this
            .split("\n", " ")
            .map { it.split(":") }
            .map { it[0].toUpperCase() to it[1] }
            .toMap()
        return ValidationType.values().all { it.isValid(fields[it.toString()]) }
    }

    enum class ValidationType(val description: String, val isValid: (String?) -> Boolean) {
        BYR("Birth Year", String?::byr),
        IYR("Issue Year", String?::iyr),
        EYR("Expiration Year", String?::eyr),
        HGT("Height", String?::hgt),
        HCL("Hair Color", String?::hcl),
        ECL("Eye Color", String?::ecl),
        PID("Passport ID", String?::pid),
        CID("Country ID", { true });
    }
}

// byr (Birth Year) - four digits; at least 1920 and at most 2002.
// iyr (Issue Year) - four digits; at least 2010 and at most 2020.
// eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
// hgt (Height) - a number followed by either cm or in:
// If cm, the number must be at least 150 and at most 193.
// If in, the number must be at least 59 and at most 76.
// hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
// ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
// pid (Passport ID) - a nine-digit number, including leading zeroes.
// cid (Country ID) - ignored, missing or not.

private val cmRegex = "^[\\d]+cm$".toRegex()
private val inRegex = "^[\\d]+in$".toRegex()
private val pidRegex = "^[\\d]{9}$".toRegex()
private val hclRegex = "^#[0-9a-f]{6}$".toRegex()

private fun String?.byr() = this?.toIntOrNull() in (1920..2002)
private fun String?.iyr() = this?.toIntOrNull() in (2010..2020)
private fun String?.eyr() = this?.toIntOrNull() in (2020..2030)
private fun String?.ecl() = this in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
private fun String?.pid() = this != null && pidRegex.matches(this)
private fun String?.hcl() = this != null && hclRegex.matches(this)

private fun String?.hgt(): Boolean {
    return when {
        this == null -> false
        cmRegex.matches(this) -> this.replace("cm", "").toIntOrNull() in (150..193)
        inRegex.matches(this) -> this.replace("in", "").toIntOrNull() in (59..76)
        else -> false
    }
}

