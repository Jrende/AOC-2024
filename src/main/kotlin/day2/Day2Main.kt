package org.example.day1

import util.FileUtil
import kotlin.math.abs

//mellan 560 och 506
fun main() {
    fun <T> getDiff(list: List<T>, func: (left: T, right: T) -> T): List<T> {
        val ret = ArrayList<T>();
//        ret.add(list[0])
        list.subList(0, list.size - 1).forEachIndexed { index, value ->
            ret.add(func(value, list[index + 1]))
        }
        return ret;
    }

    fun isSafe(report: List<Int>): Boolean {
        val asc = (report[0] - report[1]) < 0
        fun check(l: List<Int>): Int {
            var firstMistakeIndex = -1;
            l.subList(0, l.size - 1).forEachIndexed() { index, num ->
                val diff = l[index + 1] - num
                if (asc && diff < 0 || (!asc && diff > 0) || abs(diff) > 3 || diff == 0) {
                    firstMistakeIndex = index + 1;
                    return@forEachIndexed
                }
            }
            return firstMistakeIndex
        }

        var ret = check(report)
        if (ret != -1) {
            val minusMistake = report.filterIndexed { index, _ -> index != ret }
            ret = check(minusMistake)
            return ret == -1
        }
        return ret == -1
    }
var count = 0;
    fun isSafeAndUgly(report: List<Int>): Boolean {

        fun check(l: List<Int>): Int {
            val asc = (l[0] - l[1]) < 0
            var firstMistakeIndex = -1;
            l.subList(0, l.size - 1).forEachIndexed() { index, num ->
                val diff = l[index + 1] - num
                if (asc && diff < 0 || (!asc && diff > 0) || abs(diff) > 3 || diff == 0) {
                    firstMistakeIndex = index + 1;
                    return@forEachIndexed
                }
            }
            return firstMistakeIndex
        }

        var ret = check(report)
        if (ret != -1) {
            report.forEachIndexed { index, value ->
                val minusMistake = report.filterIndexed { i, _ -> i != index }
                ret = check(minusMistake)
                if (ret == -1) {
                    var d = minusMistake.map { num -> num - minusMistake[0] }
                       d = getDiff(d) { l, r -> r - l }
                    println(minusMistake.joinToString())
                    return true
                }
            }
            return false
        }
        return true
    }

//    fun removeFirstMistake(report: List<Int>): List<Int> {
//        val asc = (report[0] - report[1]) < 0
//        val mistake = report.find { num -> abs(num) > 3 || (asc && num < 0) || (!asc && num > 0) }
//        return if (mistake != null)
//            report.minusElement(mistake)
//        else report;
////    }

    val sample =
        "7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9"
//    val input = sample.split("\n")
    val input = FileUtil.getInput(2)
        .map(FileUtil::stringToIntegers)
    val gropu = input.partition { report -> isSafeAndUgly(report) }
    val safe = gropu.first.map { f -> f.joinToString() }
    val unsafe = gropu.second.map { f -> f.joinToString() }
    val v = input.filter(::isSafeAndUgly)


        //        val input = FileUtil.getInput(2)
//        .map(FileUtil::stringToIntegers)
//    input.forEach { report ->
//        var direction: Boolean? = null;
//        report.subList(0, report.size - 1).forEachIndexed { index, num ->
//            val next = report[index + 1];
//            val diff = num - next;
//            if(direction == null) {
//                direction = diff > 0;
//            } else {
//                if(direction == true && diff < 0) {
//
//                }
//            }
//
//        }
//
//    }
//    val input = FileUtil.getInput(2)
//    fun removeFirstMistake(report: List<Int>): List<Int> {
//        val asc = report[0] > 0;
//        val mistake = report.find { num -> abs(num) > 3 || (asc && num < 0) || (!asc && num > 0) }
//        return if (mistake != null)
//            report.minusElement(mistake)
//        else report;
//    }

//    val input = sample.split("\n")
//        .map(FileUtil::stringToIntegers)
//        .map { list -> list.map { num -> num - list[0] } }
//        .map { list -> getDiff(list) { l, r -> r - l } }
//        .map { list -> removeFirstMistake(list) }
//        .filter { list -> list.all { num -> abs(num) <= 3 } }
//        .filter { list -> list.all { num -> num > 0 } || list.all { num -> num < 0 } }

//    input.forEach { report ->
//        var prevMistake = false;
//        var asc = report[0] > 0;
//        report.forEach { num ->
//            var mistake = false
//            if (abs(num) <= 3 || (asc && num < 0) || (!asc && num > 0)) {
//                mistake = true
//            }
//            if (prevMistake && mistake) false
//        }
//    }
//        .filter { list -> list.all { num -> abs(num) <= 3 }}
//            .filter{ list -> list.filter { num -> num > 0 }.size < list.size - 1}
//        .filter { list -> list.all { num -> num > 0 } || list.all { num -> num  < 0 } }
        .map { list -> list.joinToString() }
    println(safe.size)
}