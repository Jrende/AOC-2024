package util

import com.sun.org.apache.xerces.internal.impl.dv.xs.IntegerDV
import java.io.File

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


        fun stringToDoubles(input: String): List<Double> {
            val numbers = ArrayList<Double>()
            splitToNumbers(input) { str ->
                numbers.add(str.toDouble())
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
    }
}