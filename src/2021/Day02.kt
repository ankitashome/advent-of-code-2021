import Direction.DOWN
import Direction.FORWARD
import Direction.UP
import Direction.valueOf

enum class Direction {
    FORWARD, UP, DOWN
}

data class CourseData(
    val direction: Direction,
    val position: Int
)

fun main() {
    fun part1(input: List<CourseData>): Int {
        var horizontalPos = 0
        var depth = 0
        input.forEach {
            when (it.direction) {
                FORWARD -> horizontalPos += it.position
                UP      -> depth -= it.position
                DOWN    -> depth += it.position
            }
        }
        return horizontalPos * depth
    }

    fun part2(input: List<CourseData>): Int {
        var horizontalPos = 0
        var depth = 0
        var aim = 0
        input.forEach {
            when (it.direction) {
                FORWARD -> {
                    horizontalPos += it.position
                    depth += aim * it.position
                }
                UP      -> aim -= it.position
                DOWN    -> aim += it.position
            }
        }
        return horizontalPos * depth
    }

    val courseInput = readInput("Day02")
    val courseData = courseInput.map {
        val (dir, pos) = it.split(" ")
        CourseData(
            direction = valueOf(dir.uppercase()),
            position = pos.toInt()
        )
    }
    println(part1(courseData))
    println(part2(courseData))
}
