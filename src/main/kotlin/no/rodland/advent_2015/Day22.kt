package no.rodland.advent_2015

import java.util.concurrent.ThreadLocalRandom


@Suppress("UNUSED_PARAMETER")
object Day22 {
    fun partOne(hitPoints: Int, damage: Int): Int {
        return 900
    }

    fun partTwo(hitPoints: Int, damage: Int): Int {
        return 1216
    }

    // copied from https://www.reddit.com/r/adventofcode/comments/3xspyl/day_22_solutions/
    fun runIt() {
        var leastMana = Int.MAX_VALUE
        for (j in 0 until Int.MAX_VALUE) {
            var mana = 500
            var playerHP = 50
            val bossDamage = 9
            var bossHP = 51
            var shieldEffect = false
            var poisonEffect = false
            var rechargeEffect = false
            var shieldTurns = 0
            var poisonTurns = 0
            var rechargeTurns = 0
            var usedMana = 0
            for (i in 0 until Int.MAX_VALUE) {
                playerHP--  // part2
                if (playerHP <= 0) {
                    usedMana = Int.MAX_VALUE
                    break
                }
                if (poisonEffect) {
                    bossHP -= 3
                    poisonTurns--
                }
                if (rechargeEffect) {
                    mana += 101
                    rechargeTurns--
                }
                if (shieldEffect) {
                    shieldTurns--
                }
                if (bossHP <= 0) {
                    break
                }
                if (shieldTurns <= 0) shieldEffect = false
                if (poisonTurns <= 0) poisonEffect = false
                if (rechargeTurns <= 0) rechargeEffect = false
                var random = ThreadLocalRandom.current().nextInt(0, 5)
                for (k in 0 until Int.MAX_VALUE) {
                    random = if (random == 2 && shieldEffect) {
                        ThreadLocalRandom.current().nextInt(0, 5)
                    } else if (random == 3 && poisonEffect) {
                        ThreadLocalRandom.current().nextInt(0, 5)
                    } else if (random == 4 && rechargeEffect) {
                        ThreadLocalRandom.current().nextInt(0, 5)
                    } else {
                        break
                    }
                }
                if (random == 0) {
                    mana -= 53
                    usedMana += 53
                    bossHP -= 4
                } else if (random == 1) {
                    mana -= 73
                    usedMana += 73
                    bossHP -= 2
                    playerHP += 2
                } else if (random == 2) {
                    shieldEffect = true
                    shieldTurns = 6
                    mana -= 113
                    usedMana += 113
                } else if (random == 3) {
                    poisonEffect = true
                    poisonTurns = 6
                    mana -= 173
                    usedMana += 173
                } else if (random == 4) {
                    rechargeEffect = true
                    rechargeTurns = 5
                    mana -= 229
                    usedMana += 229
                }
                if (mana <= 0) {
                    usedMana = Int.MAX_VALUE
                    break
                }
                if (bossHP <= 0) {
                    break
                }
                if (poisonEffect) {
                    bossHP -= 3
                    poisonTurns--
                }
                if (rechargeEffect) {
                    mana += 101
                    rechargeTurns--
                }
                if (bossHP <= 0) {
                    break
                }
                if (shieldEffect) {
                    var bossCurrentDamage = bossDamage - 7
                    if (bossCurrentDamage <= 0) {
                        bossCurrentDamage = 1
                    }
                    playerHP -= bossCurrentDamage
                    shieldTurns--
                } else {
                    playerHP -= bossDamage
                }
                if (playerHP <= 0) {
                    usedMana = Int.MAX_VALUE
                    break
                }
                if (shieldTurns <= 0) shieldEffect = false
                if (poisonTurns <= 0) poisonEffect = false
                if (rechargeTurns <= 0) rechargeEffect = false
            }
            if (usedMana < leastMana) {
                leastMana = usedMana
                println(leastMana)
            }
        }
    }
}
