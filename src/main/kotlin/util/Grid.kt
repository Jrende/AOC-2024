package util

typealias Grid = MutableList<MutableList<String>>
fun Grid.forEachGrid(func: (i: Int, j: Int, value: String) -> Unit) {
    this.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            func(i, j, value)
        }
    }
}
