package org.example.day1

import util.FileUtil
import kotlin.math.abs

fun main() {
    val input = FileUtil.getInput(4)
        .map {
            it.split("").filter(String::isNotBlank)
        }
        .filter { it.isNotEmpty() }
    val a = true;


    val targetChars = listOf("X", "M", "A", "S")
    fun checkChar(letter: Int, pos: Pair<Int, Int>, dir: Pair<Int, Int>): Boolean {
        if (pos.first < 0 || pos.second < 0 || pos.first >= input.size || pos.second >= input.size) {
            return false
        }
        if (input[pos.first][pos.second] == targetChars[letter]) {
            if (letter == 3) {
                return true
            }
            return checkChar(letter + 1, Pair(pos.first + dir.first, pos.second + dir.second), dir)
        } else {
            return false
        }
    }

    fun checkForXmas(pos: Pair<Int, Int>): Int {
        return listOf(
            Pair(1, 0),
            Pair(0, 1),
            Pair(0, -1),
            Pair(-1, 0),
            Pair(1, 1),
            Pair(1, -1),
            Pair(-1, 1),
            Pair(-1, -1)
        )
            .map { dir -> checkChar(0, pos, dir) }
            .map { if (it) 1 else 0 }
            .sum()
    }


    var matches = 0;
    input.forEachIndexed { i, line ->
        line.forEachIndexed { j, chr ->
            if (chr == "X") {
                matches += checkForXmas(Pair(i, j))
            }
        }
    }
    println(matches)
}