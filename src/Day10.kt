fun main() {
    fun getScore(grid: List<List<Int>>, position: Pair<Int, Int>, height: Int/*, path: List<String>*/): Set<Pair<Int, Int>> {
        try {
            if (grid[position.first][position.second] != height) {
                return emptySet()
            }
        } catch (e: IndexOutOfBoundsException) {
            return emptySet()
        }
        if (height == 9) {
//            val fulPath = path + "(${position.first}, ${position.second})"
//            println(fulPath.joinToString(" -> "))
            return setOf(position)
        }
        return getScore(grid, position.first + 1 to position.second, height + 1) + //, path + "(${position.first}, ${position.second})") +
                getScore(grid, position.first - 1 to position.second, height + 1) + //, path + "(${position.first}, ${position.second})") +
                getScore(grid, position.first to position.second + 1, height + 1) + //, path + "(${position.first}, ${position.second})") +
                getScore(grid, position.first to position.second - 1, height + 1) //, path + "(${position.first}, ${position.second})")
    }

    fun getRating(grid: List<List<Int>>, position: Pair<Int, Int>, height: Int/*, path: List<String>*/): Long {
        try {
            if (grid[position.first][position.second] != height) {
                return 0
            }
        } catch (e: IndexOutOfBoundsException) {
            return 0
        }
        if (height == 9) {
//            val fulPath = path + "(${position.first}, ${position.second})"
//            println(fulPath.joinToString(" -> "))
            return 1
        }
        return getRating(grid, position.first + 1 to position.second, height + 1) + //, path + "(${position.first}, ${position.second})") +
                getRating(grid, position.first - 1 to position.second, height + 1) + //, path + "(${position.first}, ${position.second})") +
                getRating(grid, position.first to position.second + 1, height + 1) + //, path + "(${position.first}, ${position.second})") +
                getRating(grid, position.first to position.second - 1, height + 1) //, path + "(${position.first}, ${position.second})")
    }

    fun part1(input: List<String>): Long {
        val topo = input
            .mapIndexed { y, line ->
                line.toCharArray().mapIndexed { x, c ->
                    c.digitToInt()
                }
            }
        var sum = 0L

        for (x in topo.indices) {
            for (y in topo[x].indices) {
                if (topo[x][y] == 0) {
                    val score = getScore(topo, x to y, 0)//, emptyList())
//                    println("$x, $y = $score")
                    sum += score.size
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Long {
        val topo = input
            .mapIndexed { y, line ->
                line.toCharArray().mapIndexed { x, c ->
                    c.digitToInt()
                }
            }
        var sum = 0L

        for (x in topo.indices) {
            for (y in topo[x].indices) {
                if (topo[x][y] == 0) {
                    val rating = getRating(topo, x to y, 0)//, emptyList())
//                    println("$x, $y = $score")
                    sum += rating
                }
            }
        }

        return sum
    }

    val testInput = readInput("Day10_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 36L)
        check(part2(testInput), 81L)
    }

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}