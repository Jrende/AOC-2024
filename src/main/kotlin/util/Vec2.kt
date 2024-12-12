package util

data class Vec2Int(val x: Int, val y: Int) {
    operator fun plus(other: Vec2Int): Vec2Int {
        return Vec2Int(this.x + other.x, this.y + other.y)
    }

    operator fun minus(other: Vec2Int): Vec2Int {
        return Vec2Int(this.x - other.x, this.y - other.y)
    }

    fun withinGrid(input: MutableList<MutableList<String>>): Boolean {
        return this.x >= 0 && this.x < input[0].size && this.y >= 0 && this.y < input[1].size;
    }
}
