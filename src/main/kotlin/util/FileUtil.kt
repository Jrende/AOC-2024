package util

import com.sun.org.apache.xerces.internal.impl.dv.xs.IntegerDV
import java.io.File
import java.math.BigInteger
import kotlin.math.abs

class FileUtil {
    companion object {
        private val relatedCharacters = Regex("a|\\-|\\.|E|e")
        private val numberRegex = Regex("\\d")

        fun getInput(day: Int, sample: Boolean = false): MutableList<String> =
            File("input/day${day}${if (sample) "sample" else ""}")
                .readLines()
                .toMutableList()


        fun List<String>.toGrid(): MutableList<MutableList<String>> =
            this.filter(String::isNotEmpty)
                .map {
                    it
                        .split("")
                        .filter(String::isNotEmpty)
                        .toMutableList()
                }
                .toMutableList()

        fun List<String>.toIntGrid(): MutableList<MutableList<Int>> =
            this.filter(String::isNotEmpty)
                .map {
                    it
                        .split("")
                        .filter(String::isNotEmpty)
                        .map { digit -> if (digit != ".") digit.toInt() else -1 }
                        .toMutableList()
                }

                .toMutableList()

        fun List<List<String>>.printGrid() {
            this.forEach { line ->
                line.forEach { char ->
                    print("$char ")
                }
                println()
            }
            println()
        }


        fun stringToDoubles(input: String): List<Double> {
            val numbers = ArrayList<Double>()
            splitToNumbers(input) { str ->
                numbers.add(str.toDouble())
            }
            return numbers;
        }

        fun stringToBigIntegers(input: String): List<BigInteger> {
            val numbers = ArrayList<BigInteger>()
            splitToNumbers(input) { str ->
                numbers.add(BigInteger.valueOf(str.toLong()))
            }
            return numbers;
        }

        fun stringToIntegers(input: String): List<Int> {
            val numbers = ArrayList<Int>()
            splitToNumbers(input) { str ->
                numbers.add(str.toInt())
            }
            return numbers;
        }

        private fun splitToNumbers(input: String, getNumber: (String) -> Unit) {
            var currentString = input;
            var currentNumber = "";

            while (currentString.isNotEmpty()) {
                val char = currentString.take(1)
                currentString = currentString.drop(1)
                if (char.matches(numberRegex)) {
                    currentNumber += char;
                } else {
                    if (char.matches(relatedCharacters) && currentString.take(1)
                            .matches(numberRegex) && (currentNumber.isNotEmpty() || char === "-")
                    ) {
                        currentNumber += char;
                        continue
                    }
                    if (currentNumber.isNotEmpty()) {
                        getNumber(currentNumber)
                        currentNumber = "";
                    }
                }

            }
            if (currentNumber.isNotEmpty()) {
                getNumber(currentNumber)
            }
        }

        fun bresenham(
            from: Pair<Int, Int>,
            to: Pair<Int, Int>,
            keepGoing: Boolean = false,
            bounds: Pair<Int, Int>
        ): List<Pair<Int, Int>> {
            val points = mutableListOf<Pair<Int, Int>>()
            var x0 = from.first
            var y0 = from.second
            var x1 = to.first
            var y1 = to.second
            val dx = abs(x1 - x0);
            val sx = if (x0 < x1) 1 else -1;
            val dy = -abs(y1 - y0);
            val sy = if (y0 < y1) 1 else -1;
            var err = dx + dy;
            while (true) {
                points.add(Pair(x0, y0));
                if (!keepGoing) {
                    if (x0 == x1 && y0 == y1) {
                        break;
                    }
                } else {
                    if (x0 <= 0 || y0 <= 0 || x0 > bounds.first - 1 || y0 > bounds.second) {
                        break
                    }
                }
                val e2 = 2 * err;
                if (e2 >= dy) {
                    err += dy;
                    x0 += sx;
                }
                if (e2 <= dx) {
                    err += dx;
                    y0 += sy;
                }
            }
            return points;
        }
    }
//    export function line(x0: number, y0: number, x1: number, y1: number): [number, number][] {


}