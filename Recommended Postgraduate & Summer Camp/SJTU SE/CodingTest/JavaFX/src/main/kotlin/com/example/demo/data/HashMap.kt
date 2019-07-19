package com.example.demo.data

interface HashMap {

    companion object {
        const val initialSize = 8
    }

    fun get(key: Int): Int?

    fun set(key: Int, value: Int)

    fun delete(key: Int)

    fun createArray(size: Int): ArrayList<Pair<Int, Int>?> {
        val data = arrayListOf<Pair<Int, Int>?>()
        for (i in 0 until size) {
            data.add(null)
        }
        return data
    }
}