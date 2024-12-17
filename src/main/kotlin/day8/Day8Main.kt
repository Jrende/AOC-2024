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

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Antenna

            if (pos != other.pos) return false
            if (frequency != other.frequency) return false

            return true
        }

        override fun hashCode(): Int {
            var result = pos.hashCode()
            result = 31 * result + frequency.hashCode()
            return result
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
    val antiPositions = mutableListOf<Antenna>()
    antennasByGroup.forEach { entry ->
        entry.value.forEach { from ->
            entry.value.filter { it !== from }.forEach { to ->
                val dist = from.pos - to.pos

                var start1 = from.pos
                if (from.pos + dist != to.pos) {
                    start1 -= dist
                }

                val positions = mutableListOf<Antenna>()
                var p1 = start1
                do {
                    positions.add(Antenna(p1, from.frequency))
                    p1 += dist
                } while (p1.withinGrid(input))

                var start2 = to.pos
                if (from.pos - dist != from.pos) {
                    start2 += dist
                }
                p1 = start2
                do {
                    positions.add(Antenna(p1, from.frequency))
                    p1 -= dist
                } while (p1.withinGrid(input))
//4,3
                /*
                val ps = listOf(
                    from.pos + p1,
                    from.pos - p1,
                    to.pos + p1,
                    to.pos - p1
                ).filter { it != from.pos && it != to.pos }
                */


//                val positions = ps
//                    .filter { it.withinGrid(input) }
//                    .filter { input[it.x][it.y] != entry.key.toString()}
//                    .map { pos -> Antenna(pos, to.frequency) }
                antiPositions.addAll(positions)
//                    .filter { input[it.x][it.y] in listOf(".", "#")  }
//                    positions.forEach { input[it.x][it.y] = "#" }
            }
        }
    }
    val distinctAntiPositions = antiPositions.distinctBy { it.pos }
    distinctAntiPositions.forEach { p ->
        input[p.pos.x][p.pos.y] = "#"
    }

    input.printGrid()
    // inte 355
    println(distinctAntiPositions.size)
}