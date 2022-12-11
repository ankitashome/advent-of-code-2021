package `2022`

import readInput

private const val RANGE_DELIMITER: String = "-"
private const val SECTION_DELIMITER: String = ","
fun main() {
    fun List<String>.toPair() = this.first() to this.last()
    fun List<String>.toIntRange() = this.first().toInt()..this.last().toInt()

    fun isTotallyOverlapping(firstSection: IntRange, secondSection: IntRange): Boolean {
        return (firstSection.contains(secondSection.first) && firstSection.contains(secondSection.last)) ||
                (secondSection.contains(firstSection.first) && secondSection.contains(firstSection.last))
    }

    fun isTotallyOverlapping(sections: Pair<String, String>): Boolean {
        val firstSection = sections.first.split(RANGE_DELIMITER).toIntRange()
        val secondSection = sections.second.split(RANGE_DELIMITER).toIntRange()
        return isTotallyOverlapping(firstSection, secondSection)
    }

    fun isPartiallyOverlapping(sections: Pair<String, String>): Boolean {
        val firstSection = sections.first.split(RANGE_DELIMITER).toIntRange()
        val secondSection = sections.second.split(RANGE_DELIMITER).toIntRange()
        return isTotallyOverlapping(firstSection, secondSection) ||
                (firstSection.first <= secondSection.last && firstSection.last >= secondSection.first)
    }

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(SECTION_DELIMITER).toPair() }
            .count { isTotallyOverlapping(it) }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(SECTION_DELIMITER).toPair() }
            .count { isPartiallyOverlapping(it) }
    }

    val assignments = readInput("Day04")
    println(part1(assignments))
    println(part2(assignments))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)
}