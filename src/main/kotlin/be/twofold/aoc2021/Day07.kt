package be.twofold.aoc2021

import kotlin.math.abs

object Day07 {
    fun part1(input: List<Int>): Int {
        val sorted = input.sorted()
        val median = sorted[sorted.size / 2]
        return sorted.sumOf { abs(it - median) }
    }

    fun part2(input: List<Int>): Int {
        val average = input.average().toInt()
        return input.sumOf { triangle(abs(it - average)) }
    }

    private fun triangle(n: Int) = n * (n + 1) / 2
}

fun main() {
    val input = Util.readFile("/day07.txt")
        .first()
        .split(',')
        .map { it.toInt() }

    println("Part 1: ${Day07.part1(input)}")
    println("Part 2: ${Day07.part2(input)}")
}
