fun main() {
    fun part1(input: List<String>): Int {
        val columns = input[0].indices
        var gammaBits = ""
        var epsilonBits = ""

        for (col in columns) {
            val (zeroes, ones) = input.countBits(col)
            gammaBits += if (zeroes > ones) "0" else "1"
            epsilonBits += if (zeroes < ones) "0" else "1"
        }
        return gammaBits.toInt(2) * epsilonBits.toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun oxygenRatingBitCriteria(bitIndex: Int, input: List<String>): List<String> {
            val (zeroes, ones) = input.countBits(bitIndex)
            return if (zeroes > ones) input.filter { it[bitIndex] == '0' }
            else if (zeroes == ones) input.filter { it[bitIndex] == '1' }
            else input.filter { it[bitIndex] == '1' }
        }

        fun co2ScrubberRatingBitCriteria(bitIndex: Int, input: List<String>): List<String> {
            val (zeroes, ones) = input.countBits(bitIndex)
            return if (zeroes > ones) input.filter { it[bitIndex] == '1' }
            else if (zeroes == ones) input.filter { it[bitIndex] == '0' }
            else input.filter { it[bitIndex] == '0' }
        }

        val oxygenRating = ratingGenerator(input) { bitIndex, data -> oxygenRatingBitCriteria(bitIndex, data) }
        val co2ScrubberRating =
            ratingGenerator(input) { bitIndex, data -> co2ScrubberRatingBitCriteria(bitIndex, data) }
        return oxygenRating * co2ScrubberRating
    }

    val diagnosticReport = readInput("Day03")
    println(part1(diagnosticReport))
    println(part2(diagnosticReport))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

}

fun ratingGenerator(
    input: List<String>,
    applyBitCriteria: (bitIndex: Int, input: List<String>) -> List<String>
): Int {
    var filteredList = input
    var bitIndex = 0
    while (filteredList.size != 1) {
        filteredList = applyBitCriteria(bitIndex, filteredList)
        bitIndex++
    }
    return if (filteredList.size == 1) filteredList[0].toInt(2) else 0
}

private fun List<String>.countBits(col: Int): Pair<Int, Int> {
    var zeroes = 0
    var ones = 0
    for (entry in this) {
        if (entry[col] == '0') zeroes++ else ones++
    }
    return zeroes to ones
}

