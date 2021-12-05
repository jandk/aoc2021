package be.twofold.aoc2021

fun part1(lines: List<Line>): Int {
    return solve(lines, false)
}

fun part2(lines: List<Line>): Int {
    return solve(lines, true)
}

private fun solve(lines: List<Line>, useDiagonal: Boolean): Int {
    val horizontal = lines
        .filter { it.y1 == it.y2 }
        .flatMap { horizontal(it) }

    val vertical = lines
        .filter { it.x1 == it.x2 }
        .flatMap { vertical(it) }

    val allPoints = if (useDiagonal) {
        val diagonal = lines
            .filter { it.x1 - it.y1 == it.x2 - it.y2 || it.x1 + it.y1 == it.x2 + it.y2 }
            .flatMap { diagonal(it) }

        horizontal + vertical + diagonal
    } else {
        horizontal + vertical
    }

    return allPoints
        .groupingBy { it }
        .eachCount()
        .filterValues { it > 1 }
        .count()
}

fun main() {
    val input = Util.readFile("/day05.txt")
        .map { line(it) }

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun horizontal(line: Line): List<Point> {
    val xRange = range(line.x1, line.x2)
    return xRange.map { Point(it, line.y1) }
}

fun vertical(line: Line): List<Point> {
    val yRange = range(line.y1, line.y2)
    return yRange.map { Point(line.x1, it) }
}

fun diagonal(line: Line): List<Point> {
    val xRange = range(line.x1, line.x2)
    val yRange = range(line.y1, line.y2)
    return xRange.zip(yRange).map { Point(it.first, it.second) }
}

fun range(a: Int, b: Int) = if (a < b) a..b else a downTo b

fun line(s: String): Line {
    return s.split(',', ' ')
        .let { Line(it[0].toInt(), it[1].toInt(), it[3].toInt(), it[4].toInt()) }
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
data class Point(val x: Int, val y: Int)
