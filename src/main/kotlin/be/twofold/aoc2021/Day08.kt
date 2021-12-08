package be.twofold.aoc2021

object Day08 {
    fun part1(input: List<String>): Int {
        return input
            .map { it.split('|').let { it[1] } }
            .flatMap { it.split(' ') }
            .count { it.length in listOf(2, 3, 4, 7) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { decode(it) }
    }

    private fun decode(s: String): Int {
        val split = s.split('|').map { it.trim() }
        val patterns = split[0].split(' ').map { it.toSet() }

        val one = patterns.single { it.size == 2 }
        val four = patterns.single { it.size == 4 }
        val seven = patterns.single { it.size == 3 }
        val eight = patterns.single { it.size == 7 }

        val fourDiff = four - one
        val three = patterns.single { it.size == 5 && it.containsAll(one) }
        val five = patterns.single { it.size == 5 && it.containsAll(fourDiff) }
        val two = patterns.single { it.size == 5 && it != three && it != five }

        val nine = patterns.single { it.size == 6 && it.containsAll(four) }
        val six = patterns.single { it.size == 6 && it != nine && it.containsAll(fourDiff) }
        val zero = patterns.single { it.size == 6 && it != nine && it != six }

        val numbers = mapOf(
            zero to 0, one to 1, two to 2, three to 3, four to 4,
            five to 5, six to 6, seven to 7, eight to 8, nine to 9
        )

        return split[1]
            .split(' ')
            .map { numbers[it.toSet()]!! }
            .reduce { a, i -> a * 10 + i }
    }
}

fun main() {
    val input = Util.readFile("/day08.txt")

    println("Part 1: ${Day08.part1(input)}")
    println("Part 2: ${Day08.part2(input)}")
}
