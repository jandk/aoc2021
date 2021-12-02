package be.twofold.aoc2021

fun part1(input: List<Pair<String, Int>>): Int {
    var depth = 0
    var position = 0

    input.forEach {
        when (it.first) {
            "forward" -> position += it.second
            "down" -> depth += it.second
            "up" -> depth -= it.second
        }
    }

    return depth * position
}

fun part2(input: List<Pair<String, Int>>): Int {
    var aim = 0
    var depth = 0
    var position = 0

    input.forEach {
        when (it.first) {
            "forward" -> {
                position += it.second
                depth += aim * it.second
            }
            "down" -> aim += it.second
            "up" -> aim -= it.second
        }
    }

    return depth * position
}

fun main() {
    val input = Util.readFile("/day02.txt")
        .map { s -> s.split(' ').let { it[0] to it[1].toInt() } }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
