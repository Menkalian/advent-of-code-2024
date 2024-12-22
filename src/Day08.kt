fun main() {
    fun part1(input: List<String>): Long {
        val grid = input
            .mapIndexed { y, line ->
                line.toCharArray()
            }.toTypedArray()

        val antinodes = input.map { it.map { false }.toTypedArray() }.toTypedArray()

        val groups = mutableMapOf<Char, MutableSet<Pair<Int, Int>>>()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val c = grid[y][x]
                if (c != '.') {
                    groups
                        .getOrPut(c) { mutableSetOf() }
                        .add(y to x)
                }
            }
        }

        for (entry in groups) {
            for (p1 in entry.value) {
                for (p2 in entry.value) {
                    if (p1 != p2) {
                        val yD = p2.first - p1.first
                        val xD = p2.second - p1.second
                        try {
                            antinodes[p1.first - yD][p1.second - xD] = true
                        } catch (e: IndexOutOfBoundsException) {
                        }
                        try {
                            antinodes[p2.first + yD][p2.second + xD] = true
                        } catch (e: IndexOutOfBoundsException) {
                        }
                    }
                }
            }
        }

//        println(antinodes.joinToString("\n") {
//            it.joinToString("") { if (it) "#" else "." }
//        })
        return antinodes.sumOf { it.count { it } }.toLong()
    }

    fun part2(input: List<String>): Long {
        val grid = input
            .mapIndexed { y, line ->
                line.toCharArray()
            }.toTypedArray()

        val antinodes = input.map { it.map { false }.toTypedArray() }.toTypedArray()

        val groups = mutableMapOf<Char, MutableSet<Pair<Int, Int>>>()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val c = grid[y][x]
                if (c != '.') {
                    groups
                        .getOrPut(c) { mutableSetOf() }
                        .add(y to x)
                }
            }
        }

        for (entry in groups) {
            for (p1 in entry.value) {
                for (p2 in entry.value) {
                    if (p1 != p2) {
                        val yD = p2.first - p1.first
                        val xD = p2.second - p1.second
                        var m = 0
                        while (true) {
                            try {
                                antinodes[p1.first - m * yD][p1.second - m * xD] = true
                            } catch (e: IndexOutOfBoundsException) {
                                break
                            }
                            m += 1
                        }
                        m = 0
                        while (true) {
                            try {
                                antinodes[p2.first + m * yD][p2.second + m * xD] = true
                            } catch (e: IndexOutOfBoundsException) {
                                break
                            }
                            m += 1
                        }
                    }
                }
            }
        }

        return antinodes.sumOf { it.count { it } }.toLong()
    }

    val testInput = readInput("Day08_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 14L)
        check(part2(testInput), 34L)
    }

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}