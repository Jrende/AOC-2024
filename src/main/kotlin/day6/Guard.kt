package day6;

import util.Pos

class Guard(var pos: Pos, var direction:Direction=Direction.NORTH) {
    fun turnRight() {
        direction = direction.turnRight();
    }

    fun copy(): Guard {
        return Guard(pos.toMutableList(), direction)
    }
}

class Turn(guard: Guard) {
    val pos: List<Int> = ArrayList(guard.pos)
    val direction = guard.direction

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Turn) return false

        return this.pos == other.pos && this.direction == other.direction
    }
}