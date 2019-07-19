package com.example.demo.data

class CuckooHashMap: HashMap {

    var size = HashMap.initialSize
        private set

    private val array1 = createArray(size)

    private val array2 = createArray(size)

    private fun H1(key: Int): Int {
        return key % size
    }

    private fun H2(key: Int): Int {
        return (key / size) % size
    }


    override fun get(key: Int): Int? {
        val item1 = array1[H1(key)]
        if (item1 != null && item1.first == key) {
            return item1.second
        }

        val item2 = array2[H2(key)]
        if (item2 != null && item2.first == key) {
            return item2.second
        }

        return null
    }

    fun internalSet(key: Int, value: Int, onFirst: Boolean) {
        if (onFirst) {
            val original = array1[H1(key)]
            array1[H1(key)] = Pair(key, value)

            if (original != null) {
                internalSet(original.first, original.second, false)
            }
        } else {
            val original = array2[H2(key)]
            array2[H2(key)] = Pair(key, value)

            if (original != null) {
//                internalSet(original.first, original.second, true, depth+1)
            }
        }
    }

    fun expand() {
        val newArray1 = createArray(size * 2)
        val newArray2 = createArray(size * 2)

    }

    override fun set(key: Int, value: Int) {
        try {
            internalSet(key, value, true)
        } catch (e: StackOverflowError) {
            expand()
            internalSet(key, value, true)
        }
    }

    override fun delete(key: Int) {
        val item1 = array1[H1(key)]
        if (item1 != null && item1.first == key) {
            array1[H1(key)] = null
            return
        }

        val item2 = array2[H2(key)]
        if (item2 != null && item2.first == key) {
            array2[H2(key)] = null
            return
        }
    }

}