package no.rodland.advent_2019

import no.rodland.advent.Pos

object Day10 {
    fun partOne(list: List<String>): Int {
        val asteroids = getAsteroids(list)
        val max = asteroids
            .map { a -> a to asteroids.filter { it != a }.distinctBy { it.pos.angle(a.pos) }.count() }
            .maxByOrNull { it.second }
        println("Found ${max!!.second} in ${max.first}")
        return max.second
    }

    fun partTwo(list: List<String>): Int {
        val asteroids = getAsteroids(list)
        // found in part 1
        val laser = asteroids.filter { it.pos.x == 11 && it.pos.y == 11 }.first()

        // could be written smoother.  also we know that the 200th asteroid is hit in the first rotation since the laser
        // can see 221 other asteroids (found in part 1)
        val willVaporize = asteroids
                .filter { it != laser }
                .map { it to (laser.directDistance(it) to (laser.angle(it) + Math.PI)) }
                .sortedBy { it.second.first }
                .groupBy { it.second.second }
                .toSortedMap()
        val angles = willVaporize.keys.toList().reversed()
        val place = willVaporize[angles[199]]!![0]
        println("Found num 200: ${place.first} with dist: ${place.second.first} and angle: ${place.second.second} ")
        return place.first.x * 100 + place.first.y
    }

    private fun getAsteroids(list: List<String>): List<Asteroid> {
        val map = list.map { it.toCharArray() }.toTypedArray()
                .withIndex()
                .flatMap { (row, arr) -> arr.mapIndexed { col, value -> Asteroid(Pos(col, row), value) } }
        val asteroids = map.filter { it.c == '#' }
        return asteroids
    }

    data class Asteroid(val pos: Pos, val c: Char) {
        val x = pos.x
        val y = pos.y
        fun angle(asteroid: Asteroid): Double = pos.angle(asteroid.pos)
        fun directDistance(asteroid: Asteroid): Double = pos.directDistance(asteroid.pos)
    }
}

