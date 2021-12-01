package no.rodland.advent_2015

import combinations
import kotlin.math.max

object Day21 {
    fun partOne(bossHitPoints: Int, bossDamage: Int, bossArmor: Int): Int {
        return items()
            .sortedBy { tmpItems -> tmpItems.sumOf { it.cost } }
                .map { playGame(it, bossHitPoints, bossDamage, bossArmor) }
                .first { it.first }
                .second
    }


    fun partTwo(bossHitPoints: Int, bossDamage: Int, bossArmor: Int): Int {
        return items()
            .sortedByDescending { tmpItems -> tmpItems.sumOf { it.cost } }
                .map { playGame(it, bossHitPoints, bossDamage, bossArmor) }
                .first { !it.first }
                .second
    }


    private fun playGame(items: List<Item>, bossHitPoints: Int, bossDamage: Int, bossArmor: Int): Pair<Boolean, Int> {
        val boss = Player(bossHitPoints, bossDamage, bossArmor)
        val player = Player(100, 0, 0, items)
        while (boss.hitPoints > 0 && player.hitPoints > 0) {
            player.hit(boss)
            boss.hit(player)
        }
        return (boss.hitPoints <= 0) to items.sumOf { it.cost }
    }

    data class Item(val name: String, val cost: Int, val damage: Int, val armor: Int)

    class Player(var hitPoints: Int, private var damage: Int, private var armor: Int, items: List<Item> = emptyList()) {
        init {
            apply(items)
        }

        private fun apply(items: List<Item>) {
            items.forEach {
                damage += it.damage
                armor += it.armor
            }
        }

        fun hit(other: Player) {
            other.hitPoints -= (max(damage - other.armor, 1))
        }

        override fun toString(): String {
            return "hp: $hitPoints"
        }
    }

    private fun items(): Sequence<List<Item>> {
        return weapons.asSequence().flatMap { weapon ->
            armor.flatMap { armor ->
                ringCombos.map { ringsSelected ->
                    ringsSelected + armor + weapon
                }
            }
        }
    }

    private val weapons = listOf(
            Item("Dagger", 8, 4, 0),
            Item("Shortsword", 10, 5, 0),
            Item("Warhammer", 25, 6, 0),
            Item("Longsword", 40, 7, 0),
            Item("Greataxe", 74, 8, 0),
    )

    private val armor = listOf(
            Item("NO_ARMOUR", 0, 0, 0),
            Item("Leather", 13, 0, 1),
            Item("Chainmail", 31, 0, 2),
            Item("Splintmail", 53, 0, 3),
            Item("Bandedmail", 75, 0, 4),
            Item("Platemail", 102, 0, 5),
    )

    val rings = listOf(
            Item("Damage +1", 25, 1, 0),
            Item("Damage +2", 50, 2, 0),
            Item("Damage +3", 100, 3, 0),
            Item("Defense +1", 20, 0, 1),
            Item("Defense +2", 40, 0, 2),
            Item("Defense +3", 80, 0, 3),
    )

    private val ringCombos = rings.combinations().filter { it.size <= 2 }
}
