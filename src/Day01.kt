fun main() {
    fun part1(input: List<Int>): Int {
        return input
            .windowed(2)
            .count { (a, b) -> b > a }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val sonarReport = readInputInInt("Day01")
    println(part1(sonarReport))

    // test if implementation meets criteria from the description, like:
    val testInput = readInputInInt("Day01_test")
    check(part1(testInput) == 10)
}
