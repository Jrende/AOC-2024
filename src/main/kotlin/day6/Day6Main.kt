package day6

import util.*
import util.FileUtil.Companion.toGrid

fun step(grid: Grid<String>, guard: Guard): Boolean {
    grid[guard.pos[0]][guard.pos[1]] = "X"

    val newPos = listOf(guard.pos[0] + guard.direction.dir[0], guard.pos[1] + guard.direction.dir[1])
    if (newPos[0] < 0 || newPos[0] > grid[0].size - 1 || newPos[1] < 0 || newPos[1] > grid.size - 1) {
        return true
    }

    val inFront = grid[newPos[0]][newPos[1]]
    if (inFront == "#") {
        guard.turnRight();
        return false
    }

    guard.pos = newPos
    return false;
}


fun main() {
    val input = FileUtil.getInput(6, sample = true).toGrid()
    val guardPos = input.findGuard() ?: return
    val guard = Guard(guardPos)
    input.printGrid()
    input[guardPos[0]][guardPos[1]] = "."
    do {
        val done = step(input, guard)
    } while (!done)
    input.printGrid()
    println("sum: ${input.sum()}")
}
