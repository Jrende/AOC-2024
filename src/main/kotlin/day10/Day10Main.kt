package day10

import util.FileUtil
import util.Grid
import util.FileUtil.Companion.toIntGrid
import util.Vec2Int
import util.at
import util.forEachGrid

val directions = listOf(
    Vec2Int(0, 1),
    Vec2Int(0, -1),
    Vec2Int(1, 0),
    Vec2Int(-1, 0),
)

fun Grid<Int>.traverse(pos: Vec2Int, visited: MutableSet<Vec2Int> = mutableSetOf()): Int {
    val height = this.at(pos)
    if (height == 9) {
//        visited.add(pos)
        return 1
    }
    val next = directions
        .map { pos + it }
        .filter { it.withinGrid(this) }
        .filter { this.at(it) == height + 1 }
    return if (next.isEmpty()) 0
    else next.sumOf{traverse(it, visited)}
}

fun main() {
    val input = FileUtil.getInput(10, sample = false).toIntGrid()
    val trailheads = mutableListOf<Vec2Int>()
    input.forEachGrid { i: Int, j: Int, value: Int ->
        if (value == 0) {
            trailheads.add(Vec2Int(i, j))
        }
    }
    val scores = trailheads.map { input.traverse(it) }
    println(scores.sum())
}

