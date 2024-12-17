package day6;

enum class Direction(val dir: List<Int>) {
    NORTH(listOf( -1, 0)),
    SOUTH(listOf(1, 0)),
    WEST(listOf(0, -1)),
    EAST(listOf(0, 1));

    fun turnRight(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}