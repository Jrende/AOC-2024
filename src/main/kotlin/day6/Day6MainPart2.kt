package day6

import util.FileUtil
import util.FileUtil.Companion.toGrid
import util.Grid
import util.findGuard

enum class State {
    OUT_OF_BOUNDS,
    LOOP,
    CONTINUE


}

fun main() {
    fun step(grid: Grid, guard: Guard, turns: MutableSet<Turn>): State {
        grid[guard.pos[0]][guard.pos[1]] = "X"

        val newPos = listOf(guard.pos[0] + guard.direction.dir[0], guard.pos[1] + guard.direction.dir[1])
        if (newPos[0] < 0 || newPos[0] > grid[0].size - 1 || newPos[1] < 0 || newPos[1] > grid.size - 1) {
            return State.OUT_OF_BOUNDS
        }

        val inFront = grid[newPos[0]][newPos[1]]
        if (inFront == "#") {
            guard.turnRight();
            if(turns.contains(Turn(guard))) {
                return State.LOOP
            }
            turns.add(Turn(guard))

            return State.CONTINUE
        }

        guard.pos = newPos
        return State.CONTINUE;
    }

    fun printGrid(grid: Grid) {
        grid.forEach { line ->
            line.forEach { char ->
                print("$char ")
            }
            println()
        }
        println()
    }

    fun Grid.copyGrid(): Grid {
        return this.map { it.toMutableList() }.toMutableList()

    }

    fun walkGrid(grid: Grid, guard: Guard): State {
        val turns = mutableSetOf<Turn>()
        var done: State;
        do {
            done = step(grid, guard, turns)
        } while (done === State.CONTINUE)
        return done
    }

    val input = FileUtil.getInput(6, sample = false).toGrid()
    val guardPos = input.findGuard() ?: return
    val guard = Guard(guardPos)


    input[guardPos[0]][guardPos[1]] = "."
    val walkedGrid = input.copyGrid()
    walkGrid(walkedGrid, guard.copy())
    printGrid(walkedGrid)
    var loops = 0;
    walkedGrid.forEachIndexed { x, line ->
        line.forEachIndexed { y, char ->
            if (char == "X") {
                val newGrid = input.copyGrid()
                newGrid[x][y] = "#"
                if(walkGrid(newGrid, guard.copy()) == State.LOOP) {
                    loops++;
                }
            }
        }
    }
    println("Number of loops: $loops")
}
