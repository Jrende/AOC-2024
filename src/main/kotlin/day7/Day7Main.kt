package day7

import util.FileUtil
import java.math.BigInteger
import kotlin.math.pow;

fun main() {

    fun calculate(permutation: String, numbers: List<BigInteger>): BigInteger {
        var sum = BigInteger.valueOf(numbers[0].toLong())
        numbers.drop(1).forEachIndexed { index, value ->
            if (permutation[index] == '0') {
                sum = sum.plus(BigInteger.valueOf(value.toLong()))
            } else if (permutation[index] == '1') {
                sum = sum.multiply(BigInteger.valueOf(value.toLong()))
            } else {
                sum = BigInteger.valueOf((sum.toString() + value.toString()).toLong())
            }
        }
        return sum;
    }

    fun toString(permutation: String, numbers: List<BigInteger>): String {
        var equation = "${numbers[0]}"
        numbers.drop(1).forEachIndexed { index, value ->
            equation += if (permutation[index] == '0') {
                " + "
            } else if (permutation[index] == '1') {
                " * "
            } else {
                " || "
            }
            equation += value.toString()
        }
        return equation;
    }
    var testValueSum = BigInteger.valueOf(0)
    val input = FileUtil.getInput(7, sample = false)
        .map(FileUtil.Companion::stringToBigIntegers)
    input.map { values -> Pair(values.first(), values.drop(1)) }
        .forEach { entry ->
            val numPermutations = BigInteger.valueOf(3).pow(entry.second.size - 1)
            for (i in 0 until numPermutations.intValueExact()) {
                val permutation = i.toString(3).padStart(entry.second.size - 1, '0')
                val result = calculate(permutation, entry.second)
                if (result == entry.first) {
                    testValueSum = testValueSum.add(BigInteger.valueOf(entry.first.toLong()))
                    println("${toString(permutation, entry.second)} = ${result.toBigDecimal().toPlainString()}")
                    return@forEach
                }

            }
        }

    println(testValueSum.toString())
}