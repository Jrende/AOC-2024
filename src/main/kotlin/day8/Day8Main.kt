package day8

import util.FileUtil
import util.FileUtil.Companion.toGrid
import util.Vec2Int
import util.printGrid

fun main() {
    class Antenna(val pos: Vec2Int, val frequency: Char) {
        override fun toString(): String {
            return "Antenna(pos=$pos, frequency=$frequency)"
        }
    }
//    val antennas =
    val input = FileUtil.getInput(8, sample = false).toGrid()

    val antennas = input.foldIndexed(emptyList<Antenna>()) { i, acc, row ->
        row.foldIndexed(acc) { j, acc2, char ->
            if (char != ".") {
                acc2 + Antenna(Vec2Int(i, j), char[0])
            } else acc2
        }
    }
    input.printGrid()
    val antennasByGroup = antennas.groupBy { it.frequency }
    var count = 0
    antennasByGroup.forEach { entry ->
        entry.value.forEach { from ->
            entry.value.filter { it !== from }.forEach { to ->
                val p1 = from.pos - to.pos;
//4,3
                val ps = listOf(
                    from.pos + p1,
                    from.pos - p1,
                    to.pos + p1,
                    to.pos - p1
                )


                val positions = ps
                    .filter { it.withinGrid(input) }
                    .filter { input[it.x][it.y] in listOf(".", "#")  }
                    positions.forEach { input[it.x][it.y] = "#" }
                count += positions.size
            }
        }
    }
    input.printGrid()
//    val num = input.flatten().count { it == "#" }
    println(count / 2)
}