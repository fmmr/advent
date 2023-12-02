
import java.util.*

private const val STARTING_ROOM = "AA"

// https://github.com/NoMoor/aoc2022/blob/main/src/aoc2022/Day16.kt
private class Day16(lines: List<String>) {

    var roomMap: Map<String, Room>
    val sparseEdges: Map<String, List<Edge>>
    val cache = mutableMapOf<State, Long>()

    init {
        val denseEdges = mutableMapOf<String, List<Edge>>()
        val denseRooms = lines.map {
            val (a, b) = it.split(";")
            val name = a.split(" ")[1]
            val flow = a.split("=")[1].toLong()
            val edges = b.split("valve")[1]
                .removePrefix("s")
                .removePrefix(" ")
                .split(", ")
                .map { Edge(it, 1) }

            denseEdges.put(name, edges)

            Room(name, flow)
        }

        roomMap = denseRooms.filter { it.rate >= 0 || it.name == STARTING_ROOM }.associateBy { it.name }

        // Make paths from every room to every other room
        sparseEdges = denseRooms.associate { r ->
            val shortestPath = computeSparseEdges(r, denseEdges)

            r.name to shortestPath
                .filter { r.name != it.key }
                .filter { roomMap[it.key]!!.rate > 0 }
                .map { Edge(it.key, it.value) }
        }
    }

    private fun computeSparseEdges(r: Room, denseEdges: Map<String, List<Edge>>): Map<String, Int> {
        // Create spanning tree for this room
        val shortestPath = mutableMapOf<String, Int>()
        val stack = PriorityQueue<Pair<String, Int>>(compareBy { it.second }) // PQ
        stack.add(r.name to 0)

        while (stack.isNotEmpty()) {
            val (name, distance) = stack.poll()

            // check
            if (shortestPath.containsKey(name) && shortestPath[name]!! <= distance) {
                continue
            }
            shortestPath[name] = distance

            stack.addAll(denseEdges[name]!!.map { it.dest to distance + 1 })
        }
        return shortestPath
    }

    data class Room(val name: String, val rate: Long)
    data class Edge(val dest: String, val weight: Int)

    data class State(
        val minute: Int,
        val location: Room,
        val openValves: Set<String>,
        val isEle: Boolean = false,
        val isPt2: Boolean = false
    )

    @Suppress("unused")
    fun part1(): Long {
        cache.clear()
        val state = State(0, roomMap.get(STARTING_ROOM)!!, setOf())
        return sparseEdges[state.location.name]!!.maxOfOrNull { goto(it, state, 30) }!!
    }

    fun part2(): Long {
        cache.clear()
        val state = State(0, roomMap[STARTING_ROOM]!!, setOf(), isPt2 = true)
        return sparseEdges[state.location.name]!!.maxOfOrNull { goto(it, state, 26) }!!
    }

    /**
     * Traverse the edge taking n minutes. If there is time, turn the valve and score those points.
     *
     * Return the max pressure you can release after this state. This only includes valves opened after this state.
     * The total pressure released for a volve is counted on the round it is open.
     */
    private fun goto(edge: Edge, prevState: State, totalMinutes: Int): Long {
        // Move to the new location
        val loc = roomMap.get(edge.dest)!!
        // Put us on turn + traversal time + 1 turn to turn on the valve.
        // We can check the turn to change the valve now because if we don't have time to turn it, we can't score
        // any more points.
        val currentMinute = prevState.minute + edge.weight + 1

        if (currentMinute >= totalMinutes && prevState.isPt2 && !prevState.isEle) {
            // Once we've taken all our turns, Let the elephant take all its turns.
            val eleState = prevState.copy(minute = 0, location = roomMap[STARTING_ROOM]!!, isEle = true)
            val max = sparseEdges[eleState.location.name]!!.filter { it.dest !in prevState.openValves }
                .map { goto(it, eleState, totalMinutes) }
                .max()

            return max
        } else if (currentMinute >= totalMinutes) {
            return 0L
        }

        // Turn it on
        val openValves = buildSet { addAll(prevState.openValves); add(loc.name) }
        val pressureReleasedThisTurn = loc.rate * (totalMinutes - currentMinute)

        val newState = prevState.copy(minute = currentMinute, location = loc, openValves=openValves)

        if (cache.containsKey(newState)) {
            return cache[newState]!!
        }

        val maxPressureReleasedAfterThisTurn = sparseEdges[newState.location.name]!!
            .filter { !newState.openValves.contains(it.dest) }
            .map { goto(it, newState, totalMinutes) }
            .maxOrNull() ?: 0L

        val maxPressureReleased = maxPressureReleasedAfterThisTurn + pressureReleasedThisTurn
        cache[newState] = maxPressureReleased

        return maxPressureReleased
    }
}

fun main() {
    val todayTest = Day16( listOf(
        "Valve ZN has flow rate=0; tunnels lead to valves SD, ZV",
        "Valve HO has flow rate=17; tunnel leads to valve LT",
        "Valve FT has flow rate=6; tunnels lead to valves DW, BV, JA, FB, TV",
        "Valve AD has flow rate=0; tunnels lead to valves AA, JG",
        "Valve GE has flow rate=0; tunnels lead to valves JG, RD",
        "Valve GI has flow rate=0; tunnels lead to valves WJ, RD",
        "Valve RM has flow rate=0; tunnels lead to valves BU, WJ",
        "Valve GV has flow rate=0; tunnels lead to valves WB, HS",
        "Valve VA has flow rate=0; tunnels lead to valves AA, HS",
        "Valve TJ has flow rate=21; tunnel leads to valve CK",
        "Valve WB has flow rate=0; tunnels lead to valves GV, EV",
        "Valve DV has flow rate=19; tunnels lead to valves OI, NK",
        "Valve EL has flow rate=0; tunnels lead to valves HS, YC",
        "Valve KU has flow rate=0; tunnels lead to valves WJ, OI",
        "Valve WI has flow rate=16; tunnels lead to valves SD, AN, GS, JV",
        "Valve JG has flow rate=3; tunnels lead to valves SV, BU, GC, GE, AD",
        "Valve TC has flow rate=0; tunnels lead to valves TV, WJ",
        "Valve GC has flow rate=0; tunnels lead to valves JG, JA",
        "Valve LS has flow rate=0; tunnels lead to valves JH, YP",
        "Valve OI has flow rate=0; tunnels lead to valves KU, DV",
        "Valve ZH has flow rate=0; tunnels lead to valves YZ, RD",
        "Valve YZ has flow rate=0; tunnels lead to valves ZH, AA",
        "Valve YP has flow rate=0; tunnels lead to valves KS, LS",
        "Valve CK has flow rate=0; tunnels lead to valves EG, TJ",
        "Valve NY has flow rate=0; tunnels lead to valves HS, UU",
        "Valve IQ has flow rate=18; tunnel leads to valve YC",
        "Valve HI has flow rate=0; tunnels lead to valves SS, RD",
        "Valve DW has flow rate=0; tunnels lead to valves FT, JH",
        "Valve EV has flow rate=7; tunnels lead to valves SV, WB, SS, GS",
        "Valve SV has flow rate=0; tunnels lead to valves JG, EV",
        "Valve BU has flow rate=0; tunnels lead to valves JG, RM",
        "Valve GS has flow rate=0; tunnels lead to valves EV, WI",
        "Valve UY has flow rate=0; tunnels lead to valves WJ, FE",
        "Valve AA has flow rate=0; tunnels lead to valves VA, YZ, AD, FB",
        "Valve SD has flow rate=0; tunnels lead to valves WI, ZN",
        "Valve KS has flow rate=23; tunnel leads to valve YP",
        "Valve RD has flow rate=4; tunnels lead to valves GI, HI, BV, ZH, GE",
        "Valve ZV has flow rate=15; tunnel leads to valve ZN",
        "Valve HB has flow rate=0; tunnels lead to valves HS, AN",
        "Valve UU has flow rate=0; tunnels lead to valves EG, NY",
        "Valve SS has flow rate=0; tunnels lead to valves HI, EV",
        "Valve HS has flow rate=12; tunnels lead to valves HB, EL, VA, GV, NY",
        "Valve LT has flow rate=0; tunnels lead to valves DS, HO",
        "Valve JH has flow rate=5; tunnels lead to valves LS, FE, QU, NK, DW",
        "Valve AN has flow rate=0; tunnels lead to valves HB, WI",
        "Valve NK has flow rate=0; tunnels lead to valves DV, JH",
        "Valve JA has flow rate=0; tunnels lead to valves GC, FT",
        "Valve EG has flow rate=14; tunnels lead to valves CK, UU, DS",
        "Valve JV has flow rate=0; tunnels lead to valves QU, WI",
        "Valve WJ has flow rate=8; tunnels lead to valves GI, RM, KU, UY, TC",
        "Valve FE has flow rate=0; tunnels lead to valves JH, UY",
        "Valve TV has flow rate=0; tunnels lead to valves FT, TC",
        "Valve YC has flow rate=0; tunnels lead to valves IQ, EL",
        "Valve QU has flow rate=0; tunnels lead to valves JV, JH",
        "Valve DS has flow rate=0; tunnels lead to valves LT, EG",
        "Valve BV has flow rate=0; tunnels lead to valves FT, RD",
        "Valve FB has flow rate=0; tunnels lead to valves AA, FT",
    ))
    val hei = todayTest.part2()
    println(hei)
//    execute(todayTest::part1, "Day[Test] $day: pt 1", 1651L)
//
//    val today = Day16(readInput(day, 2022))
//    execute(today::part1, "Day $day: pt 1")
//
//   execute(todayTest::part2, "Day[Test] $day: pt 2")
//    execute(today::part2, "Day $day: pt 2", 2838L)
}