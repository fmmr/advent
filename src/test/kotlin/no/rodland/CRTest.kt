package no.rodland

import kotlinx.coroutines.*
import no.rodland.advent.DisableSlow
import no.rodland.advent.Slow
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

// https://www.baeldung.com/kotlin-coroutines
@DisableSlow
internal class CRTest {
    @Test
    fun fibonacci_with_yield() {
        val fibonacciSeq = sequence {
            var a = 0
            var b = 1
            yield(1)
            while (true) {
                yield(a + b)

                val tmp = a + b
                a = b
                b = tmp
            }
        }
        assertEquals(listOf(1, 1, 2, 3, 5), fibonacciSeq.take(5).toList())
    }


    @Test
    @Slow(1000)
    fun givenAsyncCoroutine_whenStartIt_thenShouldExecuteItInTheAsyncWay() {
        suspend fun expensiveComputation(res: MutableList<String>) {
            delay(1000L)
            res.add("word!")
        }

        // given
        val res = mutableListOf<String>()

        // when
        runBlocking<Unit> {
            val promise = launch {
                expensiveComputation(res)
            }
            res.add("Hello,")
            promise.join()
        }

        // then
        assertEquals(res, listOf("Hello,", "word!"))
    }

    @Test
    @Slow(1400)
    fun givenHugeAmountOfCoroutines_whenStartIt_thenShouldExecuteItWithoutOutOfMemory() {
        runBlocking<Unit> {
            // given
            val counter = AtomicInteger(0)
            val numberOfCoroutines = 100_000

            // when
            val jobs = List(numberOfCoroutines) {
                launch(Dispatchers.Default) {
                    delay(1000L)
                    counter.incrementAndGet()
                }
            }
            jobs.forEach { it.join() }

            // then
            assertEquals(counter.get(), numberOfCoroutines)
        }
    }

    @Test
    @Slow(1300)
    fun givenCancellableJob_whenRequestForCancel_thenShouldQuit() {
        runBlocking<Unit> {
            // given
            val job = launch(Dispatchers.Default) {
                while (isActive) {
                    println("is working")
                }
                println("Ending...")
            }

            delay(1300L)
            // when
            job.cancel()
        }
    }

    @Test()
    @Slow(1300)
    fun givenAsyncAction_whenDeclareTimeout_thenShouldFinishWhenTimedOut() {
        Assertions.assertThrows(CancellationException::class.java) {
            runBlocking<Unit> {
                withTimeout(1300L) {
                    repeat(1000) { i ->
                        println("Some expensive computation $i ...")
                        delay(500L)
                    }
                }
            }
        }

    }

    @Test
    @Slow(2000)
    fun givenHaveTwoExpensiveAction_whenExecuteThemAsync_thenTheyShouldRunConcurrently() {
        runBlocking {
            val delay = 1000L
            val time = measureTimeMillis {
                // given
                val one = async {
                    someExpensiveComputation("1", delay)
                }
                val two = async(start = CoroutineStart.LAZY) {
                    someExpensiveComputation("2", delay)
                }

                // when
                runBlocking {
                    val hei = one.await()
                    println("${Thread.currentThread().name} 1 $hei")
                    val hei2 = two.await()
                    println("${Thread.currentThread().name} 2 $hei2")
                }
            }

            // then
            assertTrue(time > delay * 2)
        }
    }

    private suspend fun someExpensiveComputation(caller: String, delay: Long): Any {
        println("${Thread.currentThread().name} $caller will delay $delay")
        delay(delay)
        println("${Thread.currentThread().name} $caller done delay $delay")
        return "slept $delay"
    }
}