package com.example.demo.data

import java.io.File

const val basePath = "C:\\Users\\viccrubs\\Projects\\Homework\\Recommended Postgraduate & Summer Camp\\SJTU SE\\CodingTest\\question\\"

data class AssertionException(val expected: String, val actual: String, val input: String, val line: Int) : Exception()

object HashMapTest {

    fun test(hashMap: HashMap, small: Boolean) {

        val filename = if (small) "small" else "large"

        val inFile = File("$basePath$filename.in")
        val ansFile = File("$basePath$filename.ans")

        val ansReader = ansFile.bufferedReader()
        val inReader = inFile.bufferedReader()
        var line = 0

        while (true) {
            val input = inReader.readLine() ?: break
            line++
            val cmd = input.split(" ")

            val op = cmd[0]
            val val1 = cmd[1].toInt()
            val val2 = if (cmd.size > 2) cmd[2].toInt() else null

            when (op) {
                "Get" -> {
                    val expected = ansReader.readLine()
                    val actual = hashMap.get(val1).toString()
                    if (expected != actual) {
                        throw AssertionException(expected, actual, input, line)
                    } else {
                        println("Test passed. $input")
                    }
                }
                "Set" -> {
                    hashMap.set(val1, val2!!)
                }
                "Del" -> {
                    hashMap.delete(val1)
                }
            }
        }

        ansReader.close()
        inReader.close()
    }

}

fun main() {
    val linear = LinearHashMap()

//    HashMapTest.test(linear, true)
//    HashMapTest.test(linear, false)

    val cuckooHashMap = CuckooHashMap()
//    HashMapTest.test(cuckooHashMap, true)
    HashMapTest.test(cuckooHashMap, false)

//    linear.set(1, 1)
//    linear.set(9, 9)
//    linear.set(4, 4)
//    linear.set(33, 33)
//    linear.set(6, 6)

//    linear.delete(1)

}