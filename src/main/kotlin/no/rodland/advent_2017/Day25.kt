package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day25 {


    val testData = mapOf(
            "A" to State("A", Action(1, Dir.RIGHT, "B"), Action(0, Dir.LEFT, "B")),
            "B" to State("B", Action(1, Dir.LEFT, "A"), Action(1, Dir.RIGHT, "A")),
    )
    val liveData = mapOf(
            "A" to State("A", Action(1, Dir.RIGHT, "B"), Action(0, Dir.LEFT, "D")),
            "B" to State("B", Action(1, Dir.RIGHT, "C"), Action(0, Dir.RIGHT, "F")),
            "C" to State("C", Action(1, Dir.LEFT, "C"), Action(1, Dir.LEFT, "A")),
            "D" to State("D", Action(0, Dir.LEFT, "E"), Action(1, Dir.RIGHT, "A")),
            "E" to State("E", Action(1, Dir.LEFT, "A"), Action(0, Dir.RIGHT, "B")),
            "F" to State("F", Action(0, Dir.RIGHT, "C"), Action(0, Dir.RIGHT, "E")),
    )


    fun partOneTest(startState: String, steps: Long): Int {
        return run(startState, steps, testData)
    }

    fun partOne(startState: String, steps: Long): Int {
        return run(startState, steps, liveData)
    }

    private fun run(startState: String, steps: Long, states: Map<String, State>): Int {
        val memory = mutableMapOf<Long, Int>()
        var currentState = startState
        var idx = 0L
        repeat(steps.toInt()) {
            val state = states[currentState]!!
            val currentValue = memory.getOrPut(idx, { 0 })
            val action = state.action(currentValue)
            memory[idx] = action.valueToWrite
            idx += if (action.move == Dir.RIGHT) 1 else -1
            currentState = action.nextState
        }
        return memory.values.sum()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    class State(val name: String, val actionZero: Action, val actionOne: Action) {
        fun action(currentValue: Int) = if (currentValue == 0) actionZero else actionOne
    }

    data class Action(val valueToWrite: Int, val move: Dir, val nextState: String)

    enum class Dir {
        LEFT, RIGHT
    }


}
