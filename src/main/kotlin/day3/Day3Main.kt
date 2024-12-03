package day3

import util.FileUtil
import kotlin.math.abs

fun expect(input: String, exp: String) {
    if (input.matches(Regex(exp))) {

    }
}

fun String.splitKeeping(str: String): List<String> {
    return this.split(str).flatMap { listOf(it, str) }.dropLast(1).filterNot { it.isEmpty() }
}

fun String.splitKeeping(vararg strs: String): List<String> {
    var res = listOf(this)
    strs.forEach { str ->
        res = res.flatMap { it.splitKeeping(str) }
    }
    return res
}

fun main() {
    val input = FileUtil.getInput(3).joinToString("");
    val doAndDontInput = input.splitKeeping("don't").flatMap { it: String ->
        if (it == "don't") listOf(it) else it.splitKeeping("do")
    }

    val doInput = ArrayList<String>()
    var dont = false
    doAndDontInput.forEach {
        if (it == "don't") {
            dont = true;
        } else if (it == "do") {
            dont = false
        } else if (!dont) {
            doInput.add(it)
        }
    }

    var currentInput = doInput.joinToString("")
    var validInput = ""
    var mults = ArrayList<String>()

    fun take(exp: String): Boolean {
        val char = currentInput.take(1)
        currentInput = currentInput.drop(1);
        if (char.matches(Regex(exp))) {
            validInput += char
            return true;
        } else {
//            validInput = ""
            return false;
        }
    }

    fun peek(exp: String): Boolean {
        val char = currentInput.take(1)
        return char.matches(Regex(exp));
    }

    fun success() {
        mults.add(validInput)
        validInput = ""
    }

    fun failure() {
        validInput = ""
    }

    fun mul(): Boolean {
        if (!take("m")) return false
        if (!take("u")) return false
        if (!take("l")) return false
        if (!take("\\(")) return false
        while (peek("\\d")) {
            take("\\d")
        }
        if (!take(",")) return false
        while (peek("\\d")) {
            take("\\d")
        }
        if (!take("\\)")) return false
        return true;
    }

    fun parseDoOrDont(): Boolean {
        if (!take("d")) return false
        if (!take("o")) return false
        if (!peek("n't")) {
            return true
        };

        if (!take("n")) return false
        if (!take("'")) return false
        if (!take("t")) return false
        return true;
    }




    while (currentInput.isNotEmpty()) {
        if (mul()) {
            success();
        } else {
            failure()
        }
    }
    var result = mults
        .map { it.substring(it.indexOf('(') + 1, it.lastIndexOf(")")) }
        .map { it.split(',')}
        .map { it.map (String::toInt) }
        .map { it[0] * it[1]}
        .sum()
//        .map { list -> list.map()}
    println(result)
}