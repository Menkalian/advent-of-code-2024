fun main() {
    fun List<Long>.combinePlus(): List<Long> {
        if (size < 2) throw IllegalArgumentException()
        return buildList {
            add(this@combinePlus[0] + this@combinePlus[1])
            addAll(this@combinePlus.subList(2, this@combinePlus.size))
        }
    }

    fun List<Long>.combineTimes(): List<Long> {
        if (size < 2) throw IllegalArgumentException()
        return buildList {
            add(this@combineTimes[0] * this@combineTimes[1])
            addAll(this@combineTimes.subList(2, this@combineTimes.size))
        }
    }

    fun List<Long>.combineConcat(): List<Long> {
        if (size < 2) throw IllegalArgumentException()
        return buildList {
            add((this@combineConcat[0].toString() + this@combineConcat[1].toString()).toLong())
            addAll(this@combineConcat.subList(2, this@combineConcat.size))
        }
    }

    fun problemPossible(target: Long, operands: List<Long>): Boolean {
        if (operands.size == 1) {
            return operands[0] == target
        }
        if (operands.isEmpty() || operands.any { it > target }) {
            return false
        }

        if (problemPossible(target, operands.combineTimes())) {
            return true
        }
        if (problemPossible(target, operands.combinePlus())) {
            return true
        }
        return false
    }

    fun problemPossible2(target: Long, operands: List<Long>): Boolean {
        if (operands.size == 1) {
            return operands[0] == target
        }
        if (operands.isEmpty() || operands.any { it > target }) {
            return false
        }

        if (problemPossible2(target, operands.combineConcat())) {
            return true
        }
        if (problemPossible2(target, operands.combineTimes())) {
            return true
        }
        if (problemPossible2(target, operands.combinePlus())) {
            return true
        }
        return false
    }

    fun part1(input: List<String>): Long {
        return input.map {
            val split = it.split(":")
            val target = split[0].trim().toLong()
            val ops = split[1].trim().split(" ").map { it.toLong() }
            target to ops
        }
            .filter { problemPossible(it.first, it.second) }
            .sumOf { it.first }
    }

    fun part2(input: List<String>): Long {
        return input.map {
            val split = it.split(":")
            val target = split[0].trim().toLong()
            val ops = split[1].trim().split(" ").map { it.toLong() }
            target to ops
        }
            .filter { problemPossible2(it.first, it.second) }
            .sumOf { it.first }
    }

    val testInput = readInput("Day07_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 3749L)
        check(part2(testInput), 11387L)
    }

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}