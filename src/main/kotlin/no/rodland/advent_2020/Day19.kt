package no.rodland.advent_2020

object Day19 {
    fun partOne(list: List<String>): Int {
        val (ruleList, messages) = list.filterNot { it.isEmpty() }.partition { it.contains(": ") }
        val rules = ruleList.map { it.split(": ") }.map { it.first().toInt() to it.last().replace("\"", "").trim() }.toMap()
//        val shouldBeTrue = validate("ababbb", listOf(0), rules)
//        val shouldBeFalse = validate("bababa", listOf(0), rules)
        return messages.count {
            val validated = validate(it, listOf(0), rules)
//            println("validated $it $validated")
            validated
        }
    }

    fun partTwo(list: List<String>): Int {
        val (ruleList, messages) = list.filterNot { it.isEmpty() }.partition { it.contains(": ") }
        val rules = ruleList.map { it.split(": ") }.map { it.first().toInt() to it.last().replace("\"", "").trim() }.toMap().toMutableMap()
        rules[8] = "42 | 42 8"
        rules[11] = "42 31 | 42 11 31"
        return messages.count {
            validate(it, listOf(0), rules)
        }

    }

    private fun validate(s: String, rulesToMatch: List<Int>, rules: Map<Int, String>): Boolean {
        if (s.isEmpty()) {  // finished matching string - should be no more rules
            return rulesToMatch.isEmpty()
        }
        if (rulesToMatch.isEmpty()) { // no more rules, but still got string to match - false
            return false
        }
        val nextRuleToMatch = rules.getValue(rulesToMatch[0])
        val potentialChar = nextRuleToMatch[0]
//        println("matching $nextRuleToMatch (char: $potentialChar) on $s (rulesToMatch: $rulesToMatch)")
        if (potentialChar == 'a' || potentialChar == 'b') {
            return if ((s[0] == potentialChar)) { // next char matches char in rule
                validate(s.drop(1), rulesToMatch.drop(1), rules)  // match rest if string to rest of rules
            } else {
                false  // nope - next char does not match
            }
        }
        return nextRuleToMatch.split(" | ").any { rulePart ->
            val newRules = rulePart.split(" ").map { splittedRule -> splittedRule.toInt() } + rulesToMatch.drop(1)
            validate(s, newRules, rules)
        }
    }
}
