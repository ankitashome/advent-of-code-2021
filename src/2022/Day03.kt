package `2022`

import readInput

fun main() {
    fun getAggregatedPrio(items: List<Char>): Int {
        val prioMap: MutableMap<Char, Int> = mutableMapOf()
        var counter = 0

        for (ll in 'a'..'z') {
            prioMap[ll] = ++counter
        }
        for (ul in 'A'..'Z') {
            prioMap[ul] = ++counter
        }
        return items.sumOf { prioMap[it]!! }
    }

    fun String.splitByLength(): Pair<Set<Char>, Set<Char>> {
        val itemsPerComp = this.length / 2
        return this.substring(0, itemsPerComp).toSet() to
                this.substring(itemsPerComp, this.length).toSet()
    }

    fun getCommonItem(items: Set<String>): List<Char> {
        return if (items.size == 1) { //find common in one entry
            val itemsPerComp = items.first().splitByLength()
            itemsPerComp.first.intersect(itemsPerComp.second).toList()
        } else { //find common amongst multiple entries
            // items.chunked(3).
            listOf()
        }
    }

    fun part1(input: Set<String>): Int {
        return getAggregatedPrio(input.flatMap { getCommonItem(setOf( it)) })
    }

//    fun part2(input: Set<String>): Int {
//        input.flatMap { getCommonItem() }
//    }

    val rucksackContent = readInput("Day03").toSet()
    println(part1(rucksackContent))
//    println(part2(calorieCountData))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test").toSet()
    check(part1(testInput) == 157)
//    check(part2(testInput) == 70)
}
