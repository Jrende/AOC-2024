package day5

import util.FileUtil

fun testRule(rule: Pair<Int, Int>, updates: List<Int>): Boolean {
    val firstIndex = updates.indexOf(rule.first)
    val secondIndex = updates.indexOf(rule.second)
    if (firstIndex == -1 || secondIndex == -1) {
        return true
    }

    return firstIndex < secondIndex
}

fun <T>moveEntry(list: List<T>, index: Int, char: T): List<T> {
    val l = list.filter { it != char }
    return l.subList(0, index) + char + l.subList(index, l.size);
}

fun sort(updates: List<Int>, pageOrderRules: List<Pair<Int, Int>>): List<Int> {
    var arr: List<Int> = updates
    var invalidRule = pageOrderRules.find { !testRule(it, arr) }
    var iterations = 0;
    while (invalidRule !== null) {
        iterations++
        var index = arr.indexOf(invalidRule.second)
        arr = moveEntry(arr, index, invalidRule.first)
        invalidRule = pageOrderRules.find { rule -> !testRule(rule, arr) }
    }
    println("iterations: $iterations")
    return arr
}


//X, X, X, X, X
//97, 75, 47, 61, 53

//75 (53, 47, 61)
//97 (61, 47, 53, 75)
//47 (53, 61)
//61 (53)
//53

fun main() {
    val input = FileUtil.getInput(5, sample = false)
    val separator = input.indexOf("")
    val pageOrderRules = input.subList(0, separator)
        .map(FileUtil.Companion::stringToIntegers)
        .map { Pair(it[0], it[1]) }
    val updates = input.subList(separator + 1, input.size)
        .map(FileUtil.Companion::stringToIntegers)

    var invalidLines = updates
        .filter { update -> !pageOrderRules.all { rule -> testRule(rule, update) } }
    val b = invalidLines.map { update ->
        sort(
            update,
            pageOrderRules.filter { rule ->
                update.contains(rule.first) && update.contains(rule.second)
            })
    }
    val c = b.map(::getMiddleValue)


    println(c.sum())
}

fun <T> getMiddleValue(ints: List<T>): T {
    return ints[ints.size / 2]
}
