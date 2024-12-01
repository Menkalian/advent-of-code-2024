import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Long {
        val pairList = input.map { it.split("\\s+".toRegex()) }
            .map { it[0].toLong() to it[1].toLong() }
            .toList()
        val firstList = pairList.map { it.first }
            .sorted()
        val secondList = pairList.map { it.second }
            .sorted()

        return firstList.zip(secondList)
            .map { (it.first - it.second).absoluteValue }
            .sum()
    }

    fun part2(input: List<String>): Long {
        val pairList = input.map { it.split("\\s+".toRegex()) }
            .map { it[0].toLong() to it[1].toLong() }
            .toList()
        val secondList = pairList.map { it.second }
            .groupBy { it }
            .mapValues { it.value.size }

        return pairList.map { it.first }
            .map { it * (secondList[it] ?: 0) }
            .sum()
    }

    val testInput = readInput("Day01_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 11L)
        check(part2(testInput), 31L)
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}