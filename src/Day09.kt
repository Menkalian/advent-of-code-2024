fun main() {
    fun part1(input: List<String>): Long {
        return 0L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readInput("Day09_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 0L)
        check(part2(testInput), 0L)
    }

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}