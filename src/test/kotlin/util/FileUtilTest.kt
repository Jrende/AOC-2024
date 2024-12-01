package util

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class FileUtilTest {

    @Test
    fun testStringToIntegers_shouldHandleListOfIntegers() {
        val input = "123, 2345, 45,2 ,3 | 12 &* 6000"
        val expected = listOf(123, 2345, 45, 2, 3, 12, 6000)
        val actual = FileUtil.stringToIntegers(input)

        assertIterableEquals(expected, actual)
    }

    @Test
    fun testStringToDoubles_shouldHandleListOfDoubles() {
        val input = "123.432, 2345.45, 45.23,2e10,3.234 | 12 &* 6000"
        val expected: List<Double> = listOf(123.432, 2345.45, 45.23, 2e10, 3.234, 12.0, 6000.0)
        val actual: List<Double> = FileUtil.stringToDoubles(input)
//        expected.forEachIndexed { index, value ->
//            assertEquals(value, actual[index]);
//        }
        assertIterableEquals(expected, actual)
    }

    @Test
    fun shouldHandleMultipleDots() {
        val input = ".............................742.............$...125...+......................696..............523.......@..166......496..............#462.."
        val expected = listOf(742, 125, 696, 523, 166, 496, 462)
        val actual = FileUtil.stringToIntegers(input)
        assertIterableEquals(expected, actual)
    }

    @Test
    fun shouldHandleNegativeNumbers() {
    val input = "7 0 -7 -17 -38 -83 -170 -322 -567 -938 -1473 -2215 -3212 -4517 -6188 -8288 -10885 -14052 -17867 -22413 -27778"
        val expected = listOf(7, 0, -7, -17, -38, -83, -170, -322, -567, -938, -1473, -2215, -3212, -4517, -6188, -8288, -10885, -14052, -17867, -22413, -27778)
        val actual = FileUtil.stringToIntegers(input)
        assertIterableEquals(expected, actual)
    }
}