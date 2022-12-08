package `2022`

import readInput
fun main() {
    fun part1(input: List<String>): Int {
        val possibleScore = mapOf(
            "AX" to 4, // Rock-Rock = draw(3)+ rock(1)
            "AY" to 8,
            "AZ" to 3,
            "BX" to 1,
            "BY" to 5,
            "BZ" to 9,
            "CX" to 7,
            "CY" to 2,
            "CZ" to 6
        )
        return input
            .map { it.replace(" ", "").trim() }
            .sumOf { possibleScore[it]!! }
    }

    fun part2(input: List<String>): Int {
        val possibleScore = mapOf(
            "AX" to 3, // Rock-Lose = scissor(3)
            "AY" to 4,
            "AZ" to 8,
            "BX" to 1,
            "BY" to 5,
            "BZ" to 9,
            "CX" to 2,
            "CY" to 6,
            "CZ" to 7
        )
        return input
            .map { it.replace(" ", "").trim() }
            .sumOf { possibleScore[it]!! }
    }

    val strategyGuide = readInput("Day02")
    println(part1(strategyGuide))
    println(part2(strategyGuide))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)
}
