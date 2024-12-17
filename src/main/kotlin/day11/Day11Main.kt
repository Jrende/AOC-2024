package day11

import util.FileUtil
import kotlin.math.ceil
import kotlin.math.log10

// Number per stone id
val stones = mutableMapOf<Long, Int>()
typealias StoneTransformation = Pair<Long?, List<Long>?>
fun processStone(stone: Long): StoneTransformation {
    return if (stone == 0L) Pair(1, null)
    else if ((ceil(log10((stone + 1L).toDouble())) % 2) == 0.0) {
        val str = stone.toString();
        val len = str.length / 2
        val left = str.take(len)
        val right = str.takeLast(len)
        Pair(null, listOf(left.toLong(), right.toLong()))
    } else {
        Pair(stone * 2024, null)
    }
}


val stoneTransformations = mutableMapOf<Long, StoneTransformation>()
fun getOrProcessStone(stone: Long): StoneTransformation {
    return stoneTransformations.getOrPut(stone) { processStone(stone) }
}

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

    var input = FileUtil.stringToIntegers(
        FileUtil.getInput(11, sample = true).first()
    ).map { it.toLong() }.toMutableList()
    input.forEach {
        stones[it] = 1;
    }
val changes =
    var iteration = 0;
    repeat(6) {

    }
    println(input.size)
}