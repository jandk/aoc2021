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
        val patterns = split[0].split(' ')

        val one = patterns.single { it.length == 2 }.toSet()
        val cf = one.first()
        val fc = one.last()

        val four = patterns.single { it.length == 4 }.toSet()
        val bd = (four - one).first()
        val db = (four - one).last()

        val seven = patterns.single { it.length == 3 }.toSet()
        val a = (seven - one).single()

        val zero = patterns.single { it.length == 6 && (!it.contains(bd) || !it.contains(db)) }.toSet()
        val b = if (zero.contains(bd)) bd else db
        val d = if (!zero.contains(bd)) bd else db

        val six = patterns.single { it.length == 6 && (!it.contains(cf) || !it.contains(fc)) }.toSet()
        val c = if (!six.contains(cf)) cf else fc
        val f = if (six.contains(cf)) cf else fc

        val nine = patterns.single { it.length == 6 && it.toSet() != zero && it.toSet() != six }.toSet()
        val e = ("abcdefg".toSet() - nine.toSet()).single()
        val g = ("abcdefg".toSet() - setOf(a, b, c, d, e, f)).single()

        // println(arrayOf(a, b, c, d, e, f, g).joinToString(" "))

        val two = patterns.single { it.length == 5 && it.toSet() == setOf(a, c, d, e, g) }.toSet()
        val three = patterns.single { it.length == 5 && it.toSet() == setOf(a, c, d, f, g) }.toSet()
        val five = patterns.single { it.length == 5 && it.toSet() == setOf(a, b, d, f, g) }.toSet()
        val eight = patterns.single { it.length == 7 }.toSet()

        val numbers = listOf(zero, one, two, three, four, five, six, seven, eight, nine)

        var result = 0
        for (output in split[1].split(' ').map { it.toSet() }) {
            for ((index, number) in numbers.withIndex()) {
                if (output == number) {
                    result = result * 10 + index
                }
            }
        }

        return result
    }
}

fun main() {
    val input = Util.readFile("/day08.txt")

    println("Part 1: ${Day08.part1(input)}")
    println("Part 2: ${Day08.part2(input)}")
}
