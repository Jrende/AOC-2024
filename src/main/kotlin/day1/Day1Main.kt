package org.example.day1

import util.FileUtil
import kotlin.math.abs

fun main() {
    val input = FileUtil.getInput(1)
    val leftList = ArrayList<Int>()
    val rightList = ArrayList<Int>()

    input.forEach { line ->
        val ints = FileUtil.stringToIntegers(line)
        leftList.add(ints[0])
        rightList.add(ints[1])
    }

    leftList.sort()
    rightList.sort()

    val firstAnswer = (leftList zip rightList)
        .fold(0) { acc, cur ->
            acc + abs(cur.first - cur.second)
        }
    println("First answer: $firstAnswer")

    val secondAnswer = leftList
        .fold(0) { acc, cur ->
            acc + cur * rightList.filter { f -> cur == f }.size
        }
    println("Second answer: $secondAnswer")
}