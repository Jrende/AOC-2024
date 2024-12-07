package day6;

class Guard(var pos:Pos, var direction:Direction=Direction.NORTH) {
    fun turnRight() {
        direction = direction.turnRight();
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