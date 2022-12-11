package `2022`

import readText

fun main() {
    fun part1(input: String, markerSize: Int): Int {
        val packetMarker = input.windowed(markerSize)
            .map { it.toCharArray().distinct() }
            .find { it.size == markerSize }!!.joinToString("")
        return input.indexOf(packetMarker) + markerSize //add marker length to beginning of the index
    }

    fun part2(input: String, markerSize: Int): Int {
        return part1(input, markerSize)
    }

    val dataStream = readText("Day06")
    println(part1(dataStream, 4))
    println(part2(dataStream,14))

    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day06_test")
    check(part1(testInput,4) == 11)
    check(part2(testInput,14) == 26)
}