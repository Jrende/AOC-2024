package util


typealias Pos = List<Int>;
fun Grid.findGuard(): Pos? {
    this.forEachIndexed { x, line ->
        line.forEachIndexed { y, char ->
            if (char == "^") {
                return listOf(x, y)
            }
        }
    }
    return null
}

fun Grid.printGrid() {
    this.forEach { line ->
        line.forEach { char ->
            print("$char")
        }
        println()
    }
    println()
}

fun Grid.sum(): Int {
    return this.fold(0) { acc, line ->
        acc + line.count { it == "X"}
    }
}