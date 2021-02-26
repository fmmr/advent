package no.rodland.advent_2019

import com.ginsberg.advent2019.IntCodeComputerMk2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// copy of https://todd.ginsberg.com/post/advent-of-code/2019/day23/
object Day23 {
    fun partOne(input: List<String>): Int {
        val program = input.withIndex().associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }
        return runComputers(program) { _, nat, answerChannel ->
            answerChannel.send(nat.receive())
        }
    }

    @ExperimentalCoroutinesApi
    fun partTwo(input: List<String>): Int {
        val program = input.withIndex().associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }
        return runComputers(program) { computers, nat, answerChannel ->
            coroutineScope {
                var mostRecentNatValue: Pair<Long, Long> = nat.receive()
                launch {
                    while (true) {
                        mostRecentNatValue = nat.receive()
                    }
                }
                launch {
                    var latestDelivery: Long? = null
                    while (true) {
                        if (computers.all { it.input.isEmpty }) {
                            val toSend = mostRecentNatValue
                            computers[0].input.send(toSend.first)
                            computers[0].input.send(toSend.second)

                            if (latestDelivery == toSend.second) {
                                answerChannel.send(toSend)
                            }
                            latestDelivery = toSend.second
                        }
                        delay(30) // Give the computers a chance to work
                    }
                }
            }
        }
    }

    private fun runComputers(program: MutableMap<Long, Long>, natMessageHandler: suspend (Array<IntCodeComputerMk2>, ReceiveChannel<Pair<Long, Long>>, SendChannel<Pair<Long, Long>>) -> Unit): Int = runBlocking {
        val computers = Array(50) {
            IntCodeComputerMk2(program = program.copyOf(), output = Channel(UNLIMITED))
        }
        val answer = Channel<Pair<Long, Long>>(CONFLATED)
        val nat = Channel<Pair<Long, Long>>(UNLIMITED)
        val router = router(computers, nat)

        launch {
            natMessageHandler(computers, nat, answer)
        }

        computers.forEachIndexed { id, computer ->
            launch {
                computer.runProgram()
            }
            // Initialize with network id.
            computer.input.send(id.toLong())
            computer.input.send(-1)

            launch {
                while (true) {
                    router.send(
                            Triple(
                                    computer.output.receive(),
                                    computer.output.receive(),
                                    computer.output.receive()
                            )
                    )
                }
            }
        }

        answer.receive().second.toInt().also {
            this.coroutineContext.cancelChildren()
        }
    }

    private fun CoroutineScope.router(computers: Array<IntCodeComputerMk2>, nat: SendChannel<Pair<Long, Long>>): SendChannel<Triple<Long, Long, Long>> {
        val input: Channel<Triple<Long, Long, Long>> = Channel(UNLIMITED)
        launch {
            while (true) {
                val (to, x, y) = input.receive()
                if (to == 255L) {
                    nat.send(Pair(x, y))
                } else {
                    computers[to.toInt()].input.send(x)
                    computers[to.toInt()].input.send(y)
                }
            }
        }
        return input
    }

    private fun <T, R> MutableMap<T, R>.copyOf(): MutableMap<T, R> = mutableMapOf<T, R>().also { it.putAll(this) }
}