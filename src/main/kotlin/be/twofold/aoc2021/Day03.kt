package be.twofold.aoc2021

fun part1(input: List<String>): Int {
    val count = input.size
    val width = input[0].length
    var result = 0
    for (i in 0 until width) {
        val isOne = input.count { it[i] == '1' } >= (count / 2)
        result = (result shl 1) or (if (isOne) 1 else 0)
    }
    return result * (result xor ((1 shl width) - 1))
}

fun part2(input: List<String>): Int {
    val first = removeCommon(input, true)
    val second = removeCommon(input, false)
    return first * second
}

private fun removeCommon(input: List<String>, mostCommon: Boolean): Int {
    var index = 0
    var result = input
    while (result.size > 1) {
        val grouped = result.groupBy { it[index] }
        val ones = grouped['1']!!
        val zeros = grouped['0']!!
        result = if ((ones.size >= zeros.size) xor mostCommon) zeros else ones
        index++
    }
    return result.first().toInt(2)
}

fun main() {
    val input = Util.readFile("/day03.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
