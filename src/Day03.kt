fun main() {
    val mulRegex = "mul\\((?<op1>\\d{1,3}),(?<op2>\\d{1,3})\\)".toRegex()
    val extMulRegex = "(do\\(\\))|(don't\\(\\))|(mul\\((?<op1>\\d{1,3}),(?<op2>\\d{1,3})\\))".toRegex()

    fun part1(input: List<String>): Long {
        return input.sumOf {
            mulRegex.findAll(it)
                .sumOf {
                    it.groups["op1"]!!.value.toLong() * it.groups["op2"]!!.value.toLong()
                }
        }
    }
    
    fun part2(input: List<String>): Long {
        var sum = 0L
        var active = true
        for (line in input) {
            val results = extMulRegex.findAll(line)
            for (matchResult in results) {
                if (matchResult.value.startsWith("don")) {
                    active = false
                } else if (matchResult.value.startsWith("do")) {
                    active = true
                } else if (matchResult.value.startsWith("mul")) {
                    if (active) {
                        sum += matchResult.groups["op1"]!!.value.toLong() * matchResult.groups["op2"]!!.value.toLong()
                    }
                }
            }
        }
        return sum
    }
    
    val testInput = readInput("Day03_test")
    check(part1(testInput), 161L)
    check(part2(testInput), 48L)
    
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}