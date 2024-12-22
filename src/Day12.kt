fun main() {
    fun part1(input: List<String>): Long {
        return 0L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readInput("Day12_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 140L)
        check(part2(testInput), 0L)
    }

    val test2Input = readInput("Day12_test2")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 772L)
        check(part2(testInput), 0L)
    }

    val test3Input = readInput("Day12_test3")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 1930L)
        check(part2(testInput), 0L)
    }

    val input = readInput("Day12")
    part1(input).println()
    part2(input).println()
}