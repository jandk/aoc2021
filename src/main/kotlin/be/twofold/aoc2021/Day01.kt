package be.twofold.aoc2021

fun part1(input: List<Int>): Int {
    return input.asSequence()
        .zipWithNext()
        .count { it.second > it.first }
}

fun part2(input: List<Int>): Int {
    return input.asSequence()
        .windowed(3) { it.sum() }
        .zipWithNext()
        .count { it.second > it.first }
}

fun main() {
    val input = Util.readFile("/day01.txt").map { it.toInt() }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
