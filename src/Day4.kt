private val list4 = "src/input_4.txt".readFile().sorted()
private val testList = listOf("[1518-11-01 00:00] Guard #10 begins shift", "[1518-11-01 00:05] falls asleep", "[1518-11-01 00:25] wakes up", "[1518-11-01 00:30] falls asleep", "[1518-11-01 00:55] wakes up", "[1518-11-01 23:58] Guard #99 begins shift", "[1518-11-02 00:40] falls asleep", "[1518-11-02 00:50] wakes up", "[1518-11-03 00:05] Guard #10 begins shift", "[1518-11-03 00:24] falls asleep", "[1518-11-03 00:29] wakes up", "[1518-11-04 00:02] Guard #99 begins shift", "[1518-11-04 00:36] falls asleep", "[1518-11-04 00:46] wakes up", "[1518-11-05 00:03] Guard #99 begins shift", "[1518-11-05 00:45] falls asleep", "[1518-11-05 00:55] wakes up")
private val BEGIN = """.*Guard #(\d+) begins.*""".toRegex()
private val SLEEP = """.* 00:(\d\d).*""".toRegex()

class Day4 {

    fun main(args: Array<String>) {
        (4 to 1).report {
            partOne()
        }
        (4 to 2).report {
            partTwo()
        }
        (4 to "NA").report {
            find_best_guard_to_trick()
        }
    }


    private fun find_best_guard_to_trick() {
        val guard = readGuards(list4).maxBy { it.value.sleepPrOnCall() }?.value
        println("guard = $guard")
    }

    private fun partTwo() {
        val guards = readGuards(list4)

        val hei = (0..59).map { min ->
            val maxGuard = guards.maxBy { e ->
                e.value.numTimesSleptOnMinute(min)
            }
            min to maxGuard!!.value
        }

        hei.forEach {
            debug("min: ${it.first}, guard: ${it.second}, num: ${it.second.numTimesSleptOnMinute(it.first)}")
        }
        val (min, guard) = hei.maxBy { it.second.numTimesSleptOnMinute(it.first) }!!
        println("min = $min, guard: $guard")
        println("mult min * guard.id = ${min * guard.id}")
    }


    private fun partOne() {
        val guards = readGuards(list4)
        val id = guards.mapValues { it.value.sumMinutes() }.maxBy { it.value }!!.key
        val guardWithMostSleep = guards[id]!!
        val maxSleepMinute = guardWithMostSleep.maxSleepMinute()
        val maxMinute = guardWithMostSleep.maxMinute()
        val sumMinutes = guardWithMostSleep.sumMinutes()

        debug("guardWithMostSleep = $guardWithMostSleep")
        debug("guard slept = $sumMinutes")
        debug("maxsleepinaminute = $maxSleepMinute")
        println("slept guard $id slept $maxSleepMinute in minute $maxMinute")
        println("multiply: $id * $maxMinute = ${maxMinute * id}")
    }

    private fun readGuards(list: List<String>): MutableMap<Int, Guard> {
        var guard = Guard(-1)
        val guards = mutableMapOf<Int, Guard>()

        list.map { getMsg(it) }
                .forEach { msg ->
                    @Suppress("UNUSED_VARIABLE")  // needed to get exhaustivness
                    val hei = when (msg) {
                        is Msg.Begin -> {
                            guard = guards.getOrPut(msg.guard) { Guard(msg.guard) }
                            guard.newOnCall()
                            debug("BEGIN $guard $msg")
                        }
                        is Msg.Sleep -> {
                            guard.sleep(msg.min)
                            debug("sleeping $guard $msg")
                        }
                        is Msg.Wake -> {
                            guard.wake(msg.min)
                            debug("wake $guard $msg")
                        }
                    }
                }
        return guards
    }

    private fun getMsg(str: String): Msg {
        return when {
            BEGIN.matches(str) -> Msg.Begin(str)
            str.contains("asleep") -> Msg.Sleep(str)
            str.contains("wakes") -> Msg.Wake(str)
            else -> error("$str not valid - not able to continue")
        }
    }

    sealed class Msg {
        data class Begin(val str: String, val guard: Int = BEGIN.first(str)) : Msg()
        data class Sleep(val str: String, val min: Int = SLEEP.first(str)) : Msg()
        data class Wake(val str: String, val min: Int = SLEEP.first(str)) : Msg()
    }

    data class Guard(val id: Int) {
        private val arr = IntArray(60)
        private var start = -1
        private var onCall = 0

        fun newOnCall() {
            onCall++
        }

        fun sleep(min: Int) {
            start = min
        }

        fun sleepPrOnCall() = sumMinutes() / onCall

        fun wake(min: Int) {
            if (start == -1) {
                error("should not call wake without having slept: $this")
            }
            (start until (min)).forEach { arr[it]++ }
            start = -1
        }

        fun sumMinutes() = arr.sum()

        fun maxSleepMinute() = arr.max()!!

        fun numTimesSleptOnMinute(min: Int) = arr[min]

        fun maxMinute(): Int {
            val maxSleepMinute = maxSleepMinute()
            return arr.mapIndexed { index, i ->
                if (i == maxSleepMinute) {
                    index
                } else {
                    null
                }

            }.filterNotNull().first()
        }

        override fun toString(): String {
            return "Guard(id=$id, oncall: $onCall, slept=${sumMinutes()}, sleptPrOnCall=${sleepPrOnCall()})"
        }
    }
}