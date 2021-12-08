package be.twofold.aoc2021

object Day06 {
    fun part1(input: List<Int>): Long {
        return solve(input, 80)
    }

    fun part2(input: List<Int>): Long {
        return solve(input, 256)
    }

    private fun solve(input: List<Int>, days: Int): Long {
        val groups = LongArray(9)
        input.forEach { groups[it]++ }

        for (i in 1..days) {
            val new = groups[0]
            System.arraycopy(groups, 1, groups, 0, groups.size - 1)
            groups[6] += new
            groups[8] = new
        }

        return groups.sum()
    }
}

fun main() {
    val input = Util.readFile("/day06.txt")
        .first()
        .split(',')
        .map { it.toInt() }

    println("Part 1: ${Day06.part1(input)}")
    println("Part 2: ${Day06.part2(input)}")
}
