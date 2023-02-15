package `2022`

import readInput

fun main() {
    val registerPerCycle = mutableMapOf<Int, Int>()
    fun part1(input: List<String>): Int {
        var registerVal = 1
        var cycles = 0

        // may be use associate to create an immutable map instead
        input.forEach {
            if (it.contains("noop")) {
                registerPerCycle[++cycles] = registerVal
            } else {
                registerPerCycle[++cycles] = registerVal
                registerPerCycle[++cycles] = registerVal
                registerVal += it.split(" ").last().toInt()
            }
        }
        var signalStrength = 0
        for (i in 20..220 step 40) {
            signalStrength += registerPerCycle[i]!! * i
        }
        return signalStrength
    }

//    fun part2(input: String): Int {
//        return part1(input)
//    }

    val dataStream = readInput("Day10")
    println(part1(dataStream))
//    println(part2(dataStream,14))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
//    check(part2(testInput,14) == 26)
}