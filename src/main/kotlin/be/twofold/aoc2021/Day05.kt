package be.twofold.aoc2021

object Day05 {
    fun part1(lines: List<Line>): Int {
        return solve(lines, false)
    }

    fun part2(lines: List<Line>): Int {
        return solve(lines, true)
    }

    private fun solve(lines: List<Line>, useDiagonal: Boolean): Int {
        val horizontal = lines
            .filter { it.y1 == it.y2 }
            .flatMap { range(it.x1, it.x2).map { x -> Point(x, it.y1) } }

        val vertical = lines
            .filter { it.x1 == it.x2 }
            .flatMap { range(it.y1, it.y2).map { y -> Point(it.x1, y) } }

        val allPoints = if (useDiagonal) {
            val diagonal = lines
                .filter { it.x1 - it.y1 == it.x2 - it.y2 || it.x1 + it.y1 == it.x2 + it.y2 }
                .flatMap { (x1, y1, x2, y2) ->
                    range(x1, x2).zip(range(y1, y2))
                        .map { Point(it.first, it.second) }
                }

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

    private fun range(a: Int, b: Int) = if (a < b) a..b else a downTo b

    fun line(s: String): Line {
        return s.split(',', ' ')
            .let { Line(it[0].toInt(), it[1].toInt(), it[3].toInt(), it[4].toInt()) }
    }

    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
    data class Point(val x: Int, val y: Int)
}

fun main() {
    val input = Util.readFile("/day05.txt")
        .map { Day05.line(it) }

    println("Part 1: ${Day05.part1(input)}")
    println("Part 2: ${Day05.part2(input)}")
}
