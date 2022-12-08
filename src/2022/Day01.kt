package `2022`

import readText

fun main() {
    fun part1(input: String): Int {
        val chunks = input.split("\n\n").map { e ->
            e.lines().sumOf { it.toInt() }
        }
        return chunks.max()
    }

    fun part2(input: String): Int {
        val chunks = input.split("\n\n").map { e ->
            e.lines().sumOf { it.toInt() }
        }
        return chunks.sorted().reversed().take(3).sum()
    }

    val calorieCountData = readText("Day01")
    println(part1(calorieCountData))
    println(part2(calorieCountData))

    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)
}
