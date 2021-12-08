package be.twofold.aoc2021

object Day04 {
    fun part1(boards: List<Board>, inputs: IntArray): Int {
        for (input in inputs) {
            for (board in boards) {
                val mark = board.mark(input)
                if (mark != -1) {
                    return mark
                }
            }
        }
        return 0
    }

    fun part2(boards: List<Board>, inputs: IntArray): Int {
        val allBoards = boards.toMutableList()

        for (input in inputs) {
            for (board in allBoards.toList()) {
                val mark = board.mark(input)
                if (mark != -1) {
                    if (allBoards.size == 1) {
                        return mark
                    }
                    allBoards.remove(board)
                }
            }
        }
        return 0
    }

    class Board(private val board: IntArray) {
        fun get(x: Int, y: Int) = board[index(x, y)]

        fun mark(n: Int): Int {
            for (y in (0 until 5)) {
                for (x in (0 until 5)) {
                    val index = index(x, y)
                    if (board[index] == n) {
                        board[index] = -1
                        return checkBingo(x, y, n)
                    }
                }
            }
            return -1
        }

        private fun checkBingo(x: Int, y: Int, n: Int): Int {
            if (checkRow(y) || checkColumn(x)) {
                return board.filter { it > 0 }.sum() * n
            }
            return -1
        }

        private fun checkRow(y: Int) = (0 until 5).all { board[index(it, y)] == -1 }
        private fun checkColumn(x: Int) = (0 until 5).all { board[index(x, it)] == -1 }
        private fun index(x: Int, y: Int) = y * 5 + x
    }
}

fun main() {
    val input = Util.readFile("/day04.txt")
    val inputs = input[0]
        .split(',')
        .map { it.toInt() }
        .toIntArray()

    val boards = input.asSequence()
        .drop(2)
        .filter { it.isNotEmpty() }
        .map { s -> s.trim().split(Regex("\\s+")).map { it.toInt() } }
        .chunked(5)
        .map { Day04.Board(it.flatten().toIntArray()) }
        .toList()

    println("Part 1: ${Day04.part1(boards, inputs)}")
    println("Part 2: ${Day04.part2(boards, inputs)}")
}
