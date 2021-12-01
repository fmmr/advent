object Day4 {
    private val BEGIN = """.*Guard #(\d+) begins.*""".toRegex()
    private val SLEEP = """.* 00:(\d\d).*""".toRegex()

    fun partOne(data: List<String>): Int {
        val guards = readGuards(data)
        val id = guards.mapValues { it.value.sumMinutes() }.maxByOrNull { it.value }!!.key
        val guardWithMostSleep = guards[id]!!
        val maxSleepMinute = guardWithMostSleep.maxSleepMinute()
        val maxMinute = guardWithMostSleep.maxMinute()
        val sumMinutes = guardWithMostSleep.sumMinutes()

        debug("guardWithMostSleep = $guardWithMostSleep")
        debug("guard slept = $sumMinutes")
        debug("maxsleepinaminute = $maxSleepMinute")
        println("slept guard $id slept $maxSleepMinute in minute $maxMinute")
        println("multiply: $id * $maxMinute = ${maxMinute * id}")
        return id * maxMinute
    }

    fun partTwo(data: List<String>): Int {
        val guards = readGuards(data)

        val hei = (0..59).map { min ->
            val maxGuard = guards.maxByOrNull { e ->
                e.value.numTimesSleptOnMinute(min)
            }
            min to maxGuard!!.value
        }

        hei.forEach {
            debug("min: ${it.first}, guard: ${it.second}, num: ${it.second.numTimesSleptOnMinute(it.first)}")
        }
        val (min, guard) = hei.maxByOrNull { it.second.numTimesSleptOnMinute(it.first) }!!
        println("min = $min, guard: $guard")
        println("mult min * guard.id = ${min * guard.id}")
        return min * guard.id
    }


    fun find_best_guard_to_trick(data: List<String>): Guard? {
        val guard = readGuards(data).maxByOrNull { it.value.sleepPrOnCall() }?.value
        println("guard = $guard")
        return guard
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
                        else -> error("Added when converting to 1.6")
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
        data class Begin(val str: String, val guard: Int = BEGIN.get(str)) : Msg()
        data class Sleep(val str: String, val min: Int = SLEEP.get(str)) : Msg()
        data class Wake(val str: String, val min: Int = SLEEP.get(str)) : Msg()
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

        fun sleepPrOnCall() = if (onCall != 0) {
            sumMinutes() / onCall
        } else {
            -1
        }

        fun wake(min: Int) {
            if (start == -1) {
                error("should not call wake without having slept: $this")
            }
            (start until (min)).forEach { arr[it]++ }
            start = -1
        }

        fun sumMinutes() = arr.sum()

        fun maxSleepMinute() = arr.maxOrNull()!!

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