package util

import kotlin.math.sqrt

data class Vec2Int(val x: Int, val y: Int) {
    operator fun plus(other: Vec2Int): Vec2Int {
        return Vec2Int(this.x + other.x, this.y + other.y)
    }

    operator fun minus(other: Vec2Int): Vec2Int {
        return Vec2Int(this.x - other.x, this.y - other.y)
    }

    fun distance(): Double {
        return sqrt((this.x * this.x).toDouble() + (this.y * this.y).toDouble());
    }

    fun <T>withinGrid(input: Grid<T>): Boolean {
        return this.x >= 0 && this.x < input[0].size && this.y >= 0 && this.y < input[1].size;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vec2Int

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
