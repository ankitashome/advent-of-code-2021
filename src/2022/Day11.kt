package `2022`

import Monkey
import Operation
import readText
import java.util.regex.Pattern

fun main() {
    // parse input into Monkey map
    fun getMonkeys(input: List<String>): Map<Int, Monkey> {
        val multiply = Pattern.compile("Operation: new = old \\* (\\d+)")
        val add = Pattern.compile("Operation: new = old \\+ (\\d+)")
        val monkeys = mutableMapOf<Int, Monkey>()
        var monkey = Monkey(-1)
        input.forEach {
            when {
                it.startsWith("Monkey") -> {
                    monkey = Monkey(id = it.split(" ").last().removeSuffix(":").toInt())
                }

                it.startsWith("Starting") -> {
                    monkey =
                        monkey.copy(items = it.split(":").last().split(",").map { item -> item.trim().toBigInteger() }
                            .toMutableList())
                }

                it.startsWith("Operation") -> {
                    val multiplyMatcher = multiply.matcher(it)
                    val additionMatcher = add.matcher(it)
                    if (multiplyMatcher.find()) {
                        monkey = monkey.copy(
                            operation = Operation.MULTIPLICATION,
                            operand = multiplyMatcher.group(1).toInt()
                        )
                    } else if (additionMatcher.find()) {
                        monkey = monkey.copy(
                            operation = Operation.ADDITION,
                            operand = additionMatcher.group(1).toInt()
                        )
                    }
                }

                it.startsWith("Test") -> {
                    monkey = monkey.copy(divisor = it.split("by").last().trim().toInt())
                }

                it.startsWith("If true") || it.startsWith("If false") -> monkey =
                    monkey.copy(monkeyPals = monkey.monkeyPals.plus(it.split("monkey").last().trim().toInt()))

                else -> // new line between monkeys
                    monkeys[monkey.id] = monkey
            }
            if (input.last() == it) {
                monkeys[monkey.id] = monkey
            }
        }
        return monkeys
    }

    fun part1(input: List<String>): Int {
        val monkeys = getMonkeys(input)
        (1..20).forEach { _ ->
            monkeys.forEach {
                it.value.inspect { i: Int -> monkeys[i]!! }
            }
        }
        return monkeys.map { it.value.inspections }.sortedDescending().take(2).reduce { a, b -> a * b }
    }

    fun part2(input: List<String>): Long {
        val monkeys = getMonkeys(input)
        (1..10000).forEach { i ->
            monkeys.forEach {
                it.value.inspect(isControlledWorry = false) { i: Int -> monkeys[i]!! }
            }

            if (i in listOf(1, 20, 1000,2000)){
                println("Round $i has monkey inspections ${monkeys.values.map { it.inspections }} and items ${monkeys.values.map { it.items }}")
            }
        }
        return monkeys.map { it.value.inspections.toLong() }.sortedDescending().take(2).reduce { a, b -> a * b }
    }

    val input = readText("Day11").split("\n").map { it.trim() }
    println(part1(input))
//    println(part2(dataStream,14))

    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day11_test").split("\n").map { it.trim() }
    check(part1(testInput) == 10605)
    //check(part2(testInput) == 2713310158)
}