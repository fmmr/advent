/*
 * Copyright (c) 2019 Michael Krane
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
*/

package no.rodland.advent

/**
 * Splits this string into chunks of [size]. The last element might be shorter if the string can't evenly divided
 *
 * @param size The size of the chunks
 * @return [Sequence] of [size] big chunks
 */
fun String.chunksOfSize(size: Int): Sequence<String> = sequence {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val window = StringBuilder()
        repeat((1..size).count()) { if (iterator.hasNext()) window.append(iterator.next()) }
        yield(window.toString())
    }
}

/**
 * Creates a string from a [Char] by repeating it n times
 *
 * @param n How often the char is repeated
 * @return A string which is the char repated n times
 */
operator fun Char.times(n: Int): String {
    val sb = StringBuilder()
    repeat((1..n).count()) { sb.append(this) }
    return sb.toString()
}

/**
 * Groups elements in fixed size blocks by passing a *sliding window* over them, as opposed to partitioning them, as is done in [[chunksOfSize()]].
 * The last and the only element will be truncated if there are fewer characters than *size*
 *
 * @param size the number of characters per group
 */
fun String.sliding(size: Int): Sequence<String> = sequence {
    val iterator = iterator()
    val window = StringBuilder()
    repeat((1..size).count()) { if (iterator.hasNext()) window.append(iterator.next()) }
    yield(window.toString())
    while (iterator.hasNext()) {
        window.deleteCharAt(0).append(iterator.next())
        yield(window.toString())
    }
}

/**
 * Cartesian product for CharRanges.
 * The input is the range itself repeated n times
 *
 * @param n How often the Range is repeated
 * @return The cartesian prodcut of the range
 */
fun CharRange.cartProd(n: Int): Sequence<List<Char>> {
    val ranges = repeat(n).toList().toTypedArray()
    return cartProd(*(ranges))
}

/**
 * Cartesian product for CharRanges.
 * The input is the range itself repeated n times
 *
 * @param n How often the Range is repeated
 * @return The cartesian prodcut of the range
 */
fun IntRange.cartProd(n: Int): Sequence<List<Int>> {
    val ranges = repeat(n).toList().toTypedArray()
    return cartProd(*(ranges))
}

/**
 * Cartesian product for CharRanges.
 * The input is the range itself repeated n times
 *
 * @param n How often the Range is repeated
 * @return The cartesian prodcut of the range
 */
fun LongRange.cartProd(n: Int): Sequence<List<Long>> {
    val ranges = repeat(n).toList().toTypedArray()
    return cartProd(*(ranges))
}

/**
 * Make a [Sequence] that returns object over and over again.
 * Runs indefinitely unless the [times] argument is specified.
 *
 * @param times How often the object is repeated. null means its repeated indefinitely
 */
fun <T : Any> T.repeat(times: Int? = null): Sequence<T> = sequence {
    var count = 0
    while (times == null || count++ < times) yield(this@repeat)
}

/**
 * Creates a [Sequence] which contains the cartesian product of the two Iterable
 *
 * @param other The second iterable
 *
 * @return [Sequence] which contains the cartesian product as Pairs
 */
operator fun <T : Any> Iterable<T>.times(other: Iterable<T>): Sequence<Pair<T, T>> = sequence {
    for (e1 in this@times) for (e2 in other) yield(Pair(e1, e2))
}

/**
 * Splits this iterable into pairs. The last element might be have the the second element set to null, if the length in uneven
 *
 * @return [Sequence] of pairs
 */
fun <T : Any> Iterable<T>.intoPairs(): Sequence<Pair<T, T?>> = sequence {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val fst = iterator.next()
        val snd = if (iterator.hasNext()) iterator.next() else null
        yield(Pair(fst, snd))
    }
}

/**
 * Make an iterator that returns elements from the first iterable until it is exhausted,
 * then proceeds to the next iterable, until all of the iterables are exhausted.
 * Used for treating consecutive sequences as a single sequence.
 *
 */
fun <T : Any> Iterable<T>.chain(vararg other: Iterable<T>): Sequence<T> = sequence {
    for (elem in this@chain) yield(elem)
    other.flatMap { it }.forEach { yield(it) }
}

// Functions for finite Collections without get(index)
fun String.combinations(r: Int, replace: Boolean = false): Sequence<String> =
        toList().combinations(r, replace).map { it.joinToString(separator = "") }

fun <T : Any> Set<T>.combinations(r: Int, replace: Boolean = false): Sequence<List<T>> =
        toList().combinations(r, replace)

fun <T : Any> Array<T>.combinations(r: Int, replace: Boolean = false): Sequence<List<T>> =
        toList().combinations(r, replace)

/**
 * Return r length [List]s of T from this List which are emitted in lexicographic sort order.
 * So, if the input iterable is sorted, the combination tuples will be produced in sorted order.
 * Elements are treated as unique based on their position, not on their value.
 * So if the input elements are unique, there will be no repeat values in each combination.
 *
 * @param r How many elements to pick
 * @param replace elements are replaced after being chosen
 *
 * @return [Sequence] of all possible combinations of length r
 */
fun <T : Any> List<T>.combinations(r: Int, replace: Boolean = false): Sequence<List<T>> {
    val n = count()
    if (r > n) return sequenceOf()
    return sequence {
        var indices = if (replace) 0.repeat(r).toMutableList() else (0 until r).toMutableList()
        while (true) {
            yield(indices.map { this@combinations[it] })
            var i = r - 1
            loop@ while (i >= 0) {
                when (replace) {
                    true -> if (indices[i] != n - 1) break@loop
                    false -> if (indices[i] != i + n - r) break@loop
                }
                i--
            }
            if (i < 0) break
            when (replace) {
                true -> indices = (indices.take(i) + (indices[i] + 1).repeat(r - i)).toMutableList()
                false -> {
                    indices[i] += 1
                    (i + 1 until r).forEach { indices[it] = indices[it - 1] + 1 }
                }
            }
        }
    }
}

/**
 * Make an [Sequence] that returns evenly spaced values starting with n
 *
 * @param start The value at which the sequence starts
 * @param step The step size
 */
fun count(start: Int = 0, step: Int = 1): Sequence<Int> = generateSequence(start) { it + step }

/**
 * Make an [Sequence] that returns evenly spaced values starting with n
 *
 * @param start The value at which the sequence starts
 * @param step The step size
 */
fun count(start: Long = 0, step: Long = 1): Sequence<Long> = generateSequence(start) { it + step }

/**
 * Make an [Sequence] that returns evenly spaced values starting with n
 *
 * @param start The value at which the sequence starts
 * @param step The step size
 */
fun count(start: Float = 0f, step: Float = 1f): Sequence<Float> = generateSequence(start) { it + step }

/**
 * Make an [Sequence] that returns evenly spaced values starting with n
 *
 * @param start The value at which the sequence starts
 * @param step The step size
 */
fun count(start: Double = 0.0, step: Double = 1.0): Sequence<Double> = generateSequence(start) { it + step }

/**
 * Make an [Sequence] returning elements from the iterable and saving a copy of each.
 * When the iterable is exhausted, return elements from the saved copy. Repeats indefinitely.
 *
 */
fun <T : Any> Iterable<T>.cycle(): Sequence<T> = sequence {
    val saved = mutableListOf<T>()
    for (elem in this@cycle) {
        saved.add(elem)
        yield(elem)
    }
    while (true) {
        for (elem in saved) yield(elem)
    }
}

// Functions for finite Collections without get(index)
fun String.permutations(): Sequence<String> = toList().permutations().map { it.joinToString(separator = "") }

fun <T : Comparable<T>> Set<T>.permutations(): Sequence<List<T>> = toList().permutations()

fun <T : Comparable<T>> Array<T>.permutations(): Sequence<List<T>> = toList().permutations()

/**
 * Return successive r length permutations of elements in the [Iterable].
 * If r is not specified, then r defaults to the length of the iterable and all possible
 * full-length permutations are generated. Permutations are emitted in lexicographic sort order.
 * So, if the input iterable is sorted, the permutation tuples will be produced in sorted order.
 * Elements are treated as unique based on their position, not on their value.
 * So if the input elements are unique, there will be no repeat values in each permutation.
 *
 *
 * @param k The length of the permutation
 *
 * @return [Sequence] of all k-length possible permutations
 */
fun <T : Comparable<T>> Iterable<T>.permutations(k: Int = this.count()): Sequence<List<T>> {
    val elements = this@permutations.toMutableList()
    val n = elements.count()
    return if (k == n) sequence {
        // https://en.wikipedia.org/wiki/Heap%27s_algorithm
        val indicies = 0.repeat(n).toMutableList()
        yield(elements)
        var i = 0
        while (i < n) {
            if (indicies[i] < i) {
                if (i.rem(2) == 0) elements.swapByIndex(0, i)
                else elements.swapByIndex(indicies[i], i)
                yield(elements)
                indicies[i] += 1
                i = 0
            } else {
                indicies[i] = 0
                i += 1
            }
        }
    } else {
        sequence {
            // https://alistairisrael.wordpress.com/2009/09/22/simple-efficient-pnk-algorithm/
            while (true) {
                yield(elements.take(k))
                var edge = k - 1
                // find j in (k…n-1) where a_j > a_edge
                var j = (k until n).firstOrNull { elements[it] > elements[edge] } ?: n
                if (j < n) {
                    elements.swapByIndex(edge, j)
                } else {
                    elements.reverseRightOf(edge)
                    // find rightmost ascent to left of edge
                    edge = (edge - 1 downTo 0).firstOrNull { elements[it] < elements[it + 1] } ?: break
                    // find j in (n-1 … i+1) where a_j > a_i
                    j = (j - 1 downTo edge).firstOrNull { elements[edge] < elements[it] } ?: edge
                    elements.swapByIndex(edge, j)
                    elements.reverseRightOf(edge)
                }
            }
        }
    }
}

fun <T : Any> MutableList<T>.swapByIndex(from: Int, to: Int) {
    this[from] = this[to].also { this[to] = this[from] }
}

fun <T : Any> MutableList<T>.reverseRightOf(start: Int) {
    val end = start + Math.floorDiv(size - start, 2)
    ((start + 1)..end).forEach { swapByIndex(it, size - it + start) }
}

/**
 * Cartesian product of input [Iterable]. (https://en.wikipedia.org/wiki/Cartesian_product)
 * Roughly equivalent to nested for-loops in a generator expression.
 * For example, product(A, B) returns the same as ((x,y) for x in A for y in B).
 * The nested loops cycle like an odometer with the rightmost element advancing on every iteration.
 * This pattern creates a lexicographic ordering so that if the input’s iterables are sorted,
 * the product tuples are emitted in sorted order.
 *
 * @param items The input for which the cartesian product is calucalted
 *
 * @return A [Sequence] of [List] which contains the cartesian product
 *
 */
fun <T : Any> cartProd(vararg items: Iterable<T>): Sequence<List<T>> = sequence {
    if (items.all { it.iterator().hasNext() }) {
        val itemsIter = items.map { it.iterator() }.filter { it.hasNext() }.toMutableList()
        val currElement: MutableList<T> = itemsIter.map { it.next() }.toMutableList()
        loop@ while (true) {
            yield(currElement.toList())
            for (pos in itemsIter.count() - 1 downTo 0) {
                if (!itemsIter[pos].hasNext()) {
                    if (pos == 0) break@loop
                    itemsIter[pos] = items[pos].iterator()
                    currElement[pos] = itemsIter[pos].next()
                } else {
                    currElement[pos] = itemsIter[pos].next()
                    break
                }
            }
        }
    }
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val width = first().size
    val height = size
    return (0..<width).map { j ->
        (0..<height).map { i ->
            get(i)[j]
        }.toList()
    }
}