object Day7 {
    fun partOne(data: List<String>): String {
        return buildOrderOfWork(data.fix()).joinToString("")
    }

    fun partTwo(data: List<String>, secondsPrTask: Int, numWorkers: Int): Int {
        val fix = data.fix()
        val wp = WorkPlace(secondsPrTask, numWorkers)
        val tasksWithDeps = buildOrderOfWorkKeepAllDependecies(fix).toMutableList()
        val todo = buildOrderOfWork(fix).toMutableList()
        val done = mutableListOf<String>()

        while (tasksWithDeps.isNotEmpty() || todo.isNotEmpty() || !wp.idle()) {
            val allWork = getAllNext(tasksWithDeps).map { it.first }

            // we might have stuff laying around that didn't have any dependencies
            val readyWork = if (allWork.isNotEmpty()) {
                allWork
            } else {
                todo.toList()
            }
            readyWork.forEach { work ->
                if (todo.contains(work) && wp.addWork(work)) {
                    todo.remove(work)
                    debug(wp, "Added   ", work, todo, done)
                }
            }
            val finished = wp.timeGoesBy()
            done.addAll(finished)
            finished.forEach { fin ->
                debug(wp, "Finished", fin, todo, done)
                tasksWithDeps.removeIf { it.first == fin }
            }
        }
        return wp.secondsTicking
    }


    class WorkPlace(val secPrTask: Int, val numWorkers: Int) {

        var secondsTicking = 0
        var inProgress: MutableList<Pair<String, Int>> = mutableListOf()
        var started = false
        fun addWork(w: String): Boolean {
            started = true
            return if (okToAddWork()) {
                val secondsForWork = getSeconds(w)
                inProgress.add(Pair(w, secondsForWork))
                true
            } else {
                false
            }
        }

        fun idle() = inProgress.size == 0

        fun timeGoesBy(): List<String> {
            secondsTicking++
            inProgress = inProgress.map { it.first to (it.second - 1) }.toMutableList()
            val isFinished = inProgress.filter { it.second == 0 }.map { it.first }
            isFinished.forEach { removeStr -> inProgress.removeIf { it.first == removeStr } }
            return isFinished
        }

        private fun okToAddWork(): Boolean = inProgress.size < numWorkers

        fun getSeconds(w: String): Int {
            return secPrTask + (w.toCharArray()[0].toInt() - 64)
        }
    }

    private fun buildOrderOfWorkKeepAllDependecies(fix: List<Pair<String, String>>): List<Pair<String, String>> {
        if (fix.isEmpty()) {
            return emptyList()
        }
        val next: List<Pair<String, String>> = getAllNext(fix)
        return listOf(*next.toTypedArray()) + listOf(*buildOrderOfWorkKeepAllDependecies(fix.filter { !next.contains(it) }).toTypedArray())
    }

    private fun buildOrderOfWork(fix: List<Pair<String, String>>): List<String> {
        if (fix.size == 1) {
            return listOf(fix[0].first, fix[0].second)
        }
        val next: Pair<String, String> = getNext(fix)
        return listOf(next.first, *buildOrderOfWork(fix.filter { it.first != next.first }).toTypedArray())
    }

    private fun getAllNext(fix: List<Pair<String, String>>): List<Pair<String, String>> {
        val endLetters = fix.map { it.second }.toSet()
        return fix.filter { !endLetters.contains(it.first) }.sortedBy { it.first }
    }

    private fun getNext(fix: List<Pair<String, String>>, exclude: List<String> = emptyList()): Pair<String, String> {
        if (fix.size == 1) {
            return fix[0]
        }
        val endLetters = fix.map { it.second }.toSet()
        return fix.filter { !endLetters.contains(it.first) }.filter { !exclude.contains(it.first) }.sortedBy { it.first }.first()
    }

    private fun List<String>.fix(): List<Pair<String, String>> {
        return this.map { it.replace("Step ", "").replace(" must be finished before step ", " ").replace(" can begin", "").replace(".", "") }
                .map { it.split(" ") }
                .map { it[0] to it[1] }
    }

    private fun debug(wp: WorkPlace, werb: String, w: String, todo: MutableList<String>, done: MutableList<String>) {
        println("${wp.secondsTicking.pad()}  $werb  $w      workers: ${wp.inProgress.size}  todo: ${todo.joinToString("").pad(25)}  done: ${done.joinToString("")}")
    }
}

private fun String.pad(i: Int): String {
    return this + (" ".repeat(i - this.length))
}

private fun Int.pad(): String {
    return when {
        this < 10 -> "   $this"
        this < 100 -> "  $this"
        this < 1000 -> " $this"
        else -> this.toString()
    }
}
