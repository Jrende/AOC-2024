package day11

import util.FileUtil
import kotlin.math.ceil
import kotlin.math.log10


fun split(str: String): Pair<Long, Long> {
    val len = str.length
    val left = str.take(len / 2).toLong()
    val right = str.takeLast(len / 2).toLong()
    return Pair(left, right)
}

fun main() {
    fun blink(input: List<Long>): List<Long> {
        return input.flatMap { stone ->
            if (stone == 0L) listOf(1)
            else if (stone.toString().length % 2 == 0) {
                val str = stone.toString();
                val len = stone.toString().length
                val left = str.take(len / 2)
                val right = str.takeLast(len / 2)
                listOf(left.toLong(), right.toLong())
            } else {
                listOf(stone * 2024)
            }
        }
    }

    var input =
        FileUtil.stringToIntegers(
            FileUtil.getInput(11, sample = true)
                .first()
        )
            .map { it.toLong() }
            .toMutableList()

    var iteration = 0;
    var prevLen = input.size
    repeat(75) {
        for (i in input.size - 1 downTo 0) {
            val v = input[i]
            if (v == 0L) {
                input[i] = 1L
            } else if ((ceil(log10((v + 1L).toDouble())) % 2) == 0.0) {
                val pair = split(input[i].toString())
                input[i] = pair.first
                input.add(i + 1, pair.second)
            } else {
                input[i] = input[i] * 2024
            }
        }
        var len = input.size;
        println(len - prevLen)
        prevLen = len;
//        println("Iteration ${iteration++}")
    }
    println(input.size)
}