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
                Direction.FORWARD -> horizontalPos += it.position
                Direction.UP      -> depth -= it.position
                Direction.DOWN    -> depth += it.position
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
                Direction.FORWARD -> {
                    horizontalPos += it.position
                    depth += aim * it.position
                }
                Direction.UP      -> aim -= it.position
                Direction.DOWN    -> aim += it.position
            }
        }
        return horizontalPos * depth
    }

    val courseInput = readInput("Day02")
    val courseData = courseInput.map {
        val split = it.split(" ")
        CourseData(
            direction = Direction.valueOf(split.first().uppercase()),
            position = split.last().toInt()
        )
    }
    println(part1(courseData))
    println(part2(courseData))
}
