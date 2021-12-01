package be.twofold.aoc2021

object Util {

    fun readFile(name: String): List<String> {
        val stream = Util.javaClass.getResourceAsStream(name)
            ?: throw IllegalArgumentException("Invalid file: $name")

        return stream.bufferedReader().readLines()
    }

}
