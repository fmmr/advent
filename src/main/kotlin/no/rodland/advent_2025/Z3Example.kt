package no.rodland.advent_2025

import com.microsoft.z3.Context
import com.microsoft.z3.Status

fun main() {
    // Create a Z3 context with some configuration
    val cfg = HashMap<String, String>()
    cfg["model"] = "true"
    val ctx = Context(cfg)

    try {
        // Create a solver
        val solver = ctx.mkSolver()

        // Declare integer variables
        val x = ctx.mkIntConst("x")
        val y = ctx.mkIntConst("y")

        // Add constraints: x > 10 and y < 5
        solver.add(ctx.mkGt(x, ctx.mkInt(10))) // x > 10
        solver.add(ctx.mkLe(x, ctx.mkInt(11))) // x > 10
        solver.add(ctx.mkLt(y, ctx.mkInt(5)))  // y < 5
        solver.add(ctx.mkEq(ctx.mkAdd(x, y), ctx.mkInt(12))) // x + y = 12

        // Check if the constraints are satisfiable
        if (solver.check() == Status.SATISFIABLE) {
            println("Satisfiable!")
            // Get the model (a possible solution)
            val model = solver.model
            println("Model: $model")
            println("x = ${model.eval(x, false)}")
            println("y = ${model.eval(y, false)}")
        } else {
            println("Unsatisfiable.")
        }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Dispose of the context to free native resources
        ctx.close()
    }
}
