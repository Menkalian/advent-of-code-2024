fun main() {
    fun <T> ignoreIooB(default: T? = null, run: () -> T): T? {
        try {
            return run()
        } catch (e: IndexOutOfBoundsException) {
            // ignore
            return default
        }
    }

    fun functionalIncrement(test: () -> Boolean): Int {
        return if (test()) {
            1
        } else {
            0
        }
    }

    fun checkXMAS(c1: Char, c2: Char, c3: Char, c4: Char): Boolean {
        return (c1 == 'X' && c2 == 'M' && c3 == 'A' && c4 == 'S')
                || (c4 == 'X' && c3 == 'M' && c2 == 'A' && c1 == 'S')
    }

    fun checkX_MAS(data: Array<Array<Char>>, x: Int, y: Int): Boolean {
        return data[y][x] == 'A'
                && ((data[y - 1][x - 1] == 'M' && data[y + 1][x + 1] == 'S') || (data[y - 1][x - 1] == 'S' && data[y + 1][x + 1] == 'M'))
                && ((data[y - 1][x + 1] == 'M' && data[y + 1][x - 1] == 'S') || (data[y - 1][x + 1] == 'S' && data[y + 1][x - 1] == 'M'))
    }

    fun part1(input: List<String>): Long {
        val wordsearch = input
            .map { it.toCharArray().map { it.uppercaseChar() }.toTypedArray() }
            .toTypedArray()
        var count = 0L

        for (y in wordsearch.indices) {
            for (x in wordsearch[y].indices) {
                count += functionalIncrement {
                    ignoreIooB(false) {
                        checkXMAS(wordsearch[y][x], wordsearch[y][x + 1], wordsearch[y][x + 2], wordsearch[y][x + 3])
                    } ?: false
                }
                count += functionalIncrement {
                    ignoreIooB(false) {
                        checkXMAS(
                            wordsearch[y][x],
                            wordsearch[y + 1][x + 1],
                            wordsearch[y + 2][x + 2],
                            wordsearch[y + 3][x + 3]
                        )
                    } ?: false
                }
                count += functionalIncrement {
                    ignoreIooB(false) {
                        checkXMAS(
                            wordsearch[y][x],
                            wordsearch[y + 1][x - 1],
                            wordsearch[y + 2][x - 2],
                            wordsearch[y + 3][x - 3]
                        )
                    } ?: false
                }
                count += functionalIncrement {
                    ignoreIooB(false) {
                        checkXMAS(wordsearch[y][x], wordsearch[y + 1][x], wordsearch[y + 2][x], wordsearch[y + 3][x])
                    } ?: false
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Long {
        val wordsearch = input
            .map { it.toCharArray().map { it.uppercaseChar() }.toTypedArray() }
            .toTypedArray()
        var count = 0L

        for (y in wordsearch.indices) {
            for (x in wordsearch[y].indices) {
                count += functionalIncrement {
                    ignoreIooB(false) {
                        checkX_MAS(wordsearch, x, y)
                    } ?: false
                }
            }
        }

        return count
    }

    val testInput = readInput("Day04_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 18L)
        check(part2(testInput), 9L)
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}