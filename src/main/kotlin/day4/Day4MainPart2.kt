package org.example.day1

import util.FileUtil
import kotlin.math.abs

fun main() {
   val sample = """
    MMMSXXMASM
    MSAMXMSMSA
    AMXSXMAAMM
    MSAMASMSMX
    XMASAMXAMM
    XXAMMXXAMA
    SMSMSASXSS
    SAXAMASAAA
    MAMMMXMMMM
    MXMXAXMASX
    """
    val input = FileUtil.getInput(4)
        .map {
            it.split("").filter(String::isNotBlank)
        }
        .filter { it.isNotEmpty() }

    val targetChars = listOf("M", "A", "S")
    fun checkChar(letter: Int, pos: Pair<Int, Int>, dir: Pair<Int, Int>): Boolean {
        if (pos.first < 0 || pos.second < 0 || pos.first >= input.size || pos.second >= input.size) {
            return false
        }
        if (input[pos.first][pos.second] == targetChars[letter]) {
            if (letter == targetChars.size - 1) {
                return true
            }
            return checkChar(letter + 1, Pair(pos.first + dir.first, pos.second + dir.second), dir)
        } else {
            return false
        }
    }

    fun checkForCross(pos: Pair<Int, Int>, dir: Pair<Int, Int>): Boolean {
        when (dir) {
            Pair(1, 1) -> {
                return input[pos.first + 2][pos.second] == "S" &&
                        input[pos.first][pos.second + 2] == "M" ||
                        input[pos.first + 2][pos.second] == "M" &&
                        input[pos.first][pos.second + 2] == "S"
            }
            Pair(-1, 1) -> {
                return input[pos.first - 2][pos.second] == "S" &&
                        input[pos.first][pos.second + 2] == "M" ||
                        input[pos.first - 2][pos.second] == "M" &&
                        input[pos.first][pos.second + 2] == "S"
            }
            Pair(1, -1) -> {
                return input[pos.first + 2][pos.second] == "S" &&
                        input[pos.first][pos.second - 2] == "M" ||
                        input[pos.first + 2][pos.second] == "M" &&
                        input[pos.first][pos.second - 2] == "S"
            }
            Pair(-1, -1) -> {
                return input[pos.first - 2][pos.second] == "S" &&
                        input[pos.first][pos.second - 2] == "M" ||
                        input[pos.first - 2][pos.second] == "M" &&
                        input[pos.first][pos.second - 2] == "S"
            }
            else -> return false
        }
    }

    fun checkForMas(pos: Pair<Int, Int>): Int {

        return listOf(
            Pair(1, 1),
            Pair(1, -1),
            Pair(-1, 1),
            Pair(-1, -1)
        )
            .filter { dir -> checkChar(0, pos, dir) }
            .map { dir -> checkForCross(pos, dir) }
            .map { if (it) 1 else 0 }
            .sum()
    }


    var matches = 0;
    input.forEachIndexed { i, line ->
        line.forEachIndexed { j, chr ->
            if (chr == targetChars.first()) {
                matches += checkForMas(Pair(i, j))
            }
        }
    }
    println(matches / 2)
}