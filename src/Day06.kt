enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}

fun main() {
    fun visualizeState(
        visited: Array<Array<Boolean>>,
        grid: List<List<Boolean>>,
        position: Pair<Int, Int>,
        direction: Direction,
        obstrPos: MutableSet<Pair<Int, Int>>
    ) {
        visited.indices.forEach { y ->
            visited[y].indices.forEach { x ->
                if (!grid[y][x]) {
                    print('#')
                } else if (position.first == x && position.second == y) {
                    when (direction) {
                        Direction.UP -> print('^')
                        Direction.DOWN -> print('v')
                        Direction.LEFT -> print('<')
                        Direction.RIGHT -> print('>')
                    }
                } else if (obstrPos.contains(x to y)) {
                    print('O')
                } else if (visited[y][x]) {
                    print('*')
                } else {
                    print('.')
                }
            }
            println()
        }
    }

    fun predictLoop(
        position: Pair<Int, Int>,
        direction: Direction,
        grid: List<List<Boolean>>,
        visitedDir: Array<Array<MutableSet<Direction>>>
    ): Boolean {
        var curpos = position
        var curdir = direction
        val additionalObstacle = when (curdir) {
            Direction.UP -> curpos.first to curpos.second - 1
            Direction.DOWN -> curpos.first to curpos.second + 1
            Direction.LEFT -> curpos.first - 1 to curpos.second
            Direction.RIGHT -> curpos.first + 1 to curpos.second
        }

        while (true) {
            val nextPosition = when (curdir) {
                Direction.UP -> curpos.first to curpos.second - 1
                Direction.DOWN -> curpos.first to curpos.second + 1
                Direction.LEFT -> curpos.first - 1 to curpos.second
                Direction.RIGHT -> curpos.first + 1 to curpos.second
            }

            var movable: Boolean
            try {
                movable = grid[nextPosition.second][nextPosition.first] && nextPosition != additionalObstacle
            } catch (ex: IndexOutOfBoundsException) {
                return false
            }
            val rotatedDirection = when (curdir) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }

            if (movable) {
                curpos = nextPosition
            } else {
                curdir = rotatedDirection
            }

            if (visitedDir[curpos.second][curpos.first].contains(curdir)) {
                return true
            }
        }
    }

    fun part1(input: List<String>): Long {
        var direction: Direction = Direction.UP
        var position: Pair<Int, Int> = 0 to 0

        val grid = input
            .mapIndexed { y, line ->
                line.toCharArray().mapIndexed { x, c ->
                    when (c) {
                        '^' -> {
                            position = x to y
                            direction = Direction.UP
                        }

                        '<' -> {
                            position = x to y
                            direction = Direction.LEFT
                        }

                        '>' -> {
                            position = x to y
                            direction = Direction.RIGHT
                        }

                        'v' -> {
                            position = x to y
                            direction = Direction.DOWN
                        }
                    }
                    c != '#'
                }
            }

        val visited = grid.map { it.map { false }.toTypedArray() }.toTypedArray()
        val visitedDir = grid.map { it.map { mutableSetOf<Direction>() }.toTypedArray() }.toTypedArray()

        while (true) {
            visited[position.second][position.first] = true
            visitedDir[position.second][position.first].add(direction)

            //visualizeState(visited, grid, position, direction)

            val nextPosition = when (direction) {
                Direction.UP -> position.first to position.second - 1
                Direction.DOWN -> position.first to position.second + 1
                Direction.LEFT -> position.first - 1 to position.second
                Direction.RIGHT -> position.first + 1 to position.second
            }

            var movable: Boolean
            try {
                movable = grid[nextPosition.second][nextPosition.first]
            } catch (ex: IndexOutOfBoundsException) {
                break
            }

            if (movable) {
                position = nextPosition
            } else {
                direction = when (direction) {
                    Direction.UP -> Direction.RIGHT
                    Direction.RIGHT -> Direction.DOWN
                    Direction.DOWN -> Direction.LEFT
                    Direction.LEFT -> Direction.UP
                }
            }

            // Reached initial state
            //if (visitedDir[position.second][position.first].contains(direction)) {
            //    break
            //}
        }

        //visualizeState(visited, grid, position, direction)

        return visited.sumOf { it.count { it } }.toLong()
    }

    fun part2(input: List<String>): Long {
        var direction: Direction = Direction.UP
        var position: Pair<Int, Int> = 0 to 0

        val grid = input
            .mapIndexed { y, line ->
                line.toCharArray().mapIndexed { x, c ->
                    when (c) {
                        '^' -> {
                            position = x to y
                            direction = Direction.UP
                        }

                        '<' -> {
                            position = x to y
                            direction = Direction.LEFT
                        }

                        '>' -> {
                            position = x to y
                            direction = Direction.RIGHT
                        }

                        'v' -> {
                            position = x to y
                            direction = Direction.DOWN
                        }
                    }
                    c != '#'
                }
            }

        val visited = grid.map { it.map { false }.toTypedArray() }.toTypedArray()
        val visitedDir = grid.map { it.map { mutableSetOf<Direction>() }.toTypedArray() }.toTypedArray()
        val obstrPos = mutableSetOf<Pair<Int, Int>>()

        while (true) {
            visited[position.second][position.first] = true
            visitedDir[position.second][position.first].add(direction)

            //visualizeState(visited, grid, position, direction)

            val nextPosition = when (direction) {
                Direction.UP -> position.first to position.second - 1
                Direction.DOWN -> position.first to position.second + 1
                Direction.LEFT -> position.first - 1 to position.second
                Direction.RIGHT -> position.first + 1 to position.second
            }

            var movable: Boolean
            try {
                movable = grid[nextPosition.second][nextPosition.first]
            } catch (ex: IndexOutOfBoundsException) {
                break
            }
            val rotatedDirection = when (direction) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }

            if (movable) {
                if (predictLoop(position, direction, grid, visitedDir)) {
                    obstrPos.add(nextPosition)
                }
                position = nextPosition
            } else {
                direction = rotatedDirection
            }

            // Reached initial state
            //if (visitedDir[position.second][position.first].contains(direction)) {
            //    break
            //}
        }
1
        //visualizeState(visited, grid, position, direction, obstrPos)

        println(obstrPos)
        return obstrPos.size.toLong()
    }

    val testInput = readInput("Day06_test")
    if (testInput.isNotEmpty()) {
        check(part1(testInput), 41L)
        check(part2(testInput), 6L)
    }
    println("TEST PASSED")

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}