package no.rodland.advent_2019

object Day08 {
    fun partOne(list: List<Int>, width: Int, height: Int): Int {
        val layers = list.chunked(width * height)
        val minZerosLayer = layers.minByOrNull { it.count { it == 0 } }!!
        return minZerosLayer.count { it == 1 } * minZerosLayer.count { it == 2 }
    }

    fun partTwo(list: List<Int>, width: Int, height: Int): List<List<Int>> {
        val layers = list.chunked(width * height).map { it.chunked(width) }
        val finalImage = (0 until height).map { h ->
            (0 until width).map { w ->
                getPixel(layers, w, h)
            }
        }
        finalImage.map { line ->
            line.forEach { pixel -> print(makeReadable(pixel)) }
            println()
        }
        return finalImage
    }

    private fun makeReadable(pixel: Int): Char = when (pixel) {
        1 -> '*'
        0 -> ' '
        else -> error("unknown pixel value: $pixel")
    }

    private fun getPixel(layers: List<List<List<Int>>>, w: Int, h: Int): Int {
        return layers.map { it[h][w] }.first { it != 2 }
    }
}