package no.rodland.advent_2021

// Found the period of 18, started to parse, but then just read up and more-or-less copied:
// https://todd.ginsberg.com/post/advent-of-code/2021/day24/
//inp w
//mul x 0
//add x z
//mod x 26   x = z % 26
//div z A    z = z / A
//add x B    x = (z % 26) / B
//eql x w    x = x == w?  (1/0)
//eql x 0    x = x != w   (1/0)
//mul y 0
//add y 25
//mul y x
//add y 1
//mul z y    z = z * (25 * x + 1)
//mul y 0
//add y w
//add y C
//mul y x
//add z y    z = z + (w + C) * x

object Day24 {
    private fun List<String>.parse(): List<Params> =
        chunked(18).map {
            Params(
                it[4].substringAfterLast(" ").toInt(),
                it[5].substringAfterLast(" ").toInt(),
                it[15].substringAfterLast(" ").toInt()
            )
        }

    fun partOne(list: List<String>): Long {
        val params = list.parse()


        return solve(params).second
    }

    fun partTwo(list: List<String>): Long {
        val params = list.parse()
        return solve(params).first
    }

    private fun solve(params: List<Params>): Pair<Long, Long> {
        var zValues = mutableMapOf(0L to (0L to 0L))
        params.forEach { parameters ->
            val zValuesThisRound = mutableMapOf<Long, Pair<Long, Long>>()
            zValues.forEach { (z, minMax) ->
                (1..9).forEach { digit ->
                    val newValueForZ = magicFunction(parameters, z, digit.toLong())
                    if (parameters.a == 1 || (parameters.a == 26 && newValueForZ < z)) {
                        zValuesThisRound[newValueForZ] =
                            minOf(zValuesThisRound[newValueForZ]?.first ?: Long.MAX_VALUE, minMax.first * 10 + digit) to
                                maxOf(zValuesThisRound[newValueForZ]?.second ?: Long.MIN_VALUE, minMax.second * 10 + digit)
                    }
                }
            }
            zValues = zValuesThisRound
        }
        return zValues.getValue(0)
    }

    private fun magicFunction(parameters: Params, z: Long, w: Long): Long =
        if (z % 26 + parameters.b != w) ((z / parameters.a) * 26) + w + parameters.c
        else z / parameters.a

    private class Params(val a: Int, val b: Int, val c: Int)
}



