fun main() {
    fun part1(input: List<String>): Long {
        val separatorIndex = input.indexOfFirst { it.isBlank() }
        val precedenceRules = input.subList(0, separatorIndex)
            .map { it.split("|") }
            .map { it[0].toLong() to it[1].toLong() }
            .groupBy({ it.second }, { it.first })
        val printingSequences = input.subList(separatorIndex + 1, input.size)
            .map { it.split(",").map { it.toLong() } }

        return printingSequences
            .filter { sequence -> sequence.withIndex()
                .all { valueWithIndex ->
                    val pred = precedenceRules[valueWithIndex.value] ?: emptyList()
                    val followingPages = sequence.subList(valueWithIndex.index + 1, sequence.size)
                    pred.intersect(followingPages).isEmpty()
                }
            }
            .sumOf {
                it[it.size / 2]
            }
    }

    fun part2(input: List<String>): Long {
        val separatorIndex = input.indexOfFirst { it.isBlank() }
        val precedenceRules = input.subList(0, separatorIndex)
            .map { it.split("|") }
            .map { it[0].toLong() to it[1].toLong() }
            .groupBy({ it.second }, { it.first })
        val printingSequences = input.subList(separatorIndex + 1, input.size)
            .map { it.split(",").map { it.toLong() } }

        fun sortSequence(sequence: List<Long>, precedenceRules: Map<Long, List<Long>>): List<Long> {
            val result = mutableListOf<Long>()
            for (elem in sequence) {
                val precedence = precedenceRules[elem] ?: emptyList()
                val insertionIndex = result.indexOfFirst { precedence.contains(it) }
                if (insertionIndex != -1) {
                    result.add(insertionIndex, elem)
                } else {
                    result.add(elem)
                }
            }
            return result
        }

        return printingSequences
            .filter { sequence -> sequence.withIndex()
                .all { valueWithIndex ->
                    val pred = precedenceRules[valueWithIndex.value] ?: emptyList()
                    val followingPages = sequence.subList(valueWithIndex.index + 1, sequence.size)
                    pred.intersect(followingPages).isEmpty()
                }
                .not()
            }
            .map { sortSequence(sequence = it, precedenceRules) }
            .sumOf {
                it[it.size / 2]
            }
    }

    val testInput = readInput("Day05_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 143L)
        check(part2(testInput), 123L)
    }

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}