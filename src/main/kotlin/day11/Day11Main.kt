package day11

import util.FileUtil
import kotlin.math.ceil
import kotlin.math.log10

fun processStone(stone: Long): Pair<Long?, Long?> {
    return if (stone == 0L) Pair(1, null)
    else if ((ceil(log10((stone + 1L).toDouble())) % 2) == 0.0) {
        split(stone.toString())
    } else {
        Pair(stone * 2024, null)
    }
}

val stoneTransformations = mutableMapOf<Long, Pair<Long?, Long?>>()
fun getOrProcessStone(stone: Long): Pair<Long?, Long?> {
    return stoneTransformations.getOrPut(stone) { processStone(stone) }
}

fun split(str: String): Pair<Long, Long> {
    val len = str.length / 2
    val left = str.take(len)
    val right = str.takeLast(len)
    return Pair(left.toLong(), right.toLong())
}

fun main() {

    val stones = mutableMapOf<Long, Long>()
    var input = FileUtil.stringToIntegers(
        FileUtil.getInput(11, sample = true).first()
    ).map { it.toLong() }.toMutableList()
    input.forEach {
        stones[it] = 1;
    }
    val changes = mutableMapOf<Long, Long>().withDefault { 0L };
    repeat(25) {
        stones.keys.forEach { stone ->
            changes[stone] = -1
            val change = getOrProcessStone(stone)
            if(change.first != null) {
                val id: Long = change.first!!
                changes[id] = changes.getValue(id) + 1;
            }
            if(change.second !== null) {
                val id: Long = change.second!!
                changes[id] = changes.getValue(id) + 1;
            }
        }
        changes.forEach {entry ->
            val num = stones.getOrDefault(entry.key, 0L)
            val value = num + entry.value
            if(value == 0L) {
                stones.remove(entry.key)
            } else {
                stones[entry.key] = value
            }
        }
        changes.clear()
    }
    println(stones.values.sum())
}