import kotlin.math.absoluteValue

fun main() {
    fun safe(report: List<Int>): Boolean {
        val differences = report.windowed(2)
            .map { it[0] - it[1] }
        val isSafe = ((differences.all { it > 0 } || differences.all { it < 0 })
                && differences.all { 1 <= it.absoluteValue } && differences.all { it.absoluteValue <= 3 })
        return isSafe
    }

    fun safeWithDampener(report: List<Int>): Boolean {
        if (safe(report)) return true
        for (dampenedIndex in report.indices) {
            if (safe(report.filterIndexed { idx, _ -> idx != dampenedIndex })) {
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Long {
        return input
            .map { it.split("\\s+".toRegex()).map { it.toInt() } }
            .count { safe(it) }.toLong()
    }

    fun part2(input: List<String>): Long {
        return input
            .map { it.split("\\s+".toRegex()).map { it.toInt() } }
            .count { safeWithDampener(it) }.toLong()
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput), 2L)
    check(part2(testInput), 4L)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}