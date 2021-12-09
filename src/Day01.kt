fun main() {
    fun part1(input: List<Int>): Int {
        return input
            .windowed(2)
            .count { (a, b) -> b > a }
    }

    fun part2(input: List<Int>): Int {
        return input
            .windowed(3)
            .map { it.sum() }
            .windowed(2)
            .count { (a, b) -> b > a }
    }

    val sonarReport = readInputInInt("Day01")
    println(part1(sonarReport))
    println(part2(sonarReport))

    // test if implementation meets criteria from the description, like:
    val testInput = readInputInInt("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)
}
