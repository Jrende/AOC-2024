package util

typealias Grid<T> = MutableList<MutableList<T>>
fun <T>Grid<T>.forEachGrid(func: (i: Int, j: Int, value: T) -> Unit) {
    this.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            func(i, j, value)
        }
    }
}


fun <T>Grid<T>.at(pos: Vec2Int): T {
    return this[pos.x][pos.y];
}
