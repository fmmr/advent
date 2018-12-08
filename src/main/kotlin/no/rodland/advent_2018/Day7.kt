object Day7 {
    fun partOne(data: List<String>): String {
        val fix = data.fix()
        return process(fix).joinToString("")
    }

    fun partTwo(data: List<String>, secondsPrTask: Int, numWorkers: Int): Int {
        val fix = data.fix()
        val wp = WorkPlace(secondsPrTask, numWorkers)
        val tasksWithDeps = processWithoutFiltering(fix).toMutableList()
        //[(C, A), (C, F), (A, B), (A, D), (F, E), (B, E), (D, E)]
        val addedWork = mutableListOf<String>()
        val todo = process(fix).toMutableList()
        val done = mutableListOf<String>()

        while (!wp.finished() || tasksWithDeps.isNotEmpty() || todo.isNotEmpty()) {
            val allWork = getAllNext(tasksWithDeps).map { it.first }

            // we might have stuff laying around that didn't have any dependencies
            val readyWork = if (allWork.isEmpty()) {
                listOf(*todo.toTypedArray())
            } else {
                allWork
            }
            readyWork.forEach { work ->
                if (!addedWork.contains(work) && wp.addWork(work)) {
                    println("${wp.secondsTicking.pad()}  Added     $work      workers: ${wp.inProgress.size}  started: ${done.joinToString("")}")
                    addedWork.add(work)
                    todo.remove(work)
                }
            }
            val finished = wp.timeGoesBy()
            done.addAll(finished)
            finished.forEach { fin ->
                println("${wp.secondsTicking.pad()}  Finished  $fin      workers: ${wp.inProgress.size}  started: ${done.joinToString("")}")
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
            val added = if (okToAddWork()) {
                val secondsForWork = getSeconds(w)
                inProgress.add(Pair(w, secondsForWork))
                true
            } else {
                false
            }
            return added
        }

        fun finished() = started && inProgress.size == 0

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

    private fun processWithoutFiltering(fix: List<Pair<String, String>>): List<Pair<String, String>> {
        if (fix.size == 0) {
            return emptyList()
        }
        if (fix.size == 1) {
            return listOf(Pair(fix[0].first, fix[0].second))
        }
        val next: List<Pair<String, String>> = getAllNext(fix)

        return listOf(*next.toTypedArray()) + listOf(*processWithoutFiltering(fix.filter { !next.contains(it) }).toTypedArray())
    }

    private fun process(fix: List<Pair<String, String>>): List<String> {
        if (fix.size == 1) {
            return listOf(fix[0].first, fix[0].second)
        }
        val next: Pair<String, String> = getNext(fix)
        return listOf(next.first, *process(fix.filter { it.first != next.first }).toTypedArray())
    }

    private fun getAllNext(fix: List<Pair<String, String>>): List<Pair<String, String>> {
//        if (fix.size == 1) {
//            return fix
//        }
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
}

private fun Int.pad(): String {
    return when {
        this < 10 -> "   $this"
        this < 100 -> "  $this"
        this < 1000 -> " $this"
        else -> this.toString()
    }
}
