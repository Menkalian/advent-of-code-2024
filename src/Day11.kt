import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main() {
    val scope = CoroutineScope(Dispatchers.Default)

    fun countEvolvedStones(stone: Long, remainingBlinks: Int = 25): Long {
        if (remainingBlinks == 0) {
            return 1L
        }

        if (stone == 0L) {
            return countEvolvedStones(1, remainingBlinks - 1)
        } else {
            val stoneTxt = stone.toString()
            if (stoneTxt.length % 2 == 0) {
                val splitIdx = stoneTxt.length / 2
                return countEvolvedStones(stoneTxt.subSequence(0, splitIdx).toString().toLong(), remainingBlinks - 1) +
                        countEvolvedStones(
                            stoneTxt.subSequence(splitIdx, stoneTxt.length).toString().toLong(),
                            remainingBlinks - 1
                        )
            } else {
                return countEvolvedStones(stone * 2024, remainingBlinks - 1)
            }
        }
    }

    fun part1(input: List<String>): Long {
        return input.first().split("\\s+".toRegex())
            .map { it.toLong() }
            .sumOf { countEvolvedStones(it) }
    }

    fun part2(input: List<String>): Long {
        return input.first().split("\\s+".toRegex())
            .map { it.toLong() }
            .sumOf {
                val res = countEvolvedStones(it, 75)
                print("$it done.")
                res
            }
    }

    val testInput = readInput("Day11_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 55312L)
//        check(part2(testInput), 0L)
    }

    println("TEST DONE")

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}