package com.example.demo.data

class LinearHashMap: HashMap {

    private var count = 0

    var size = HashMap.initialSize
        private set

    private var data = createArray(size)



    override fun get(key: Int): Int? {
        val initialIndex = key % size

        for (i in 0 until size) {
            val index = (initialIndex + i) % size
            val pair = data[index] ?: return null
            if (pair.first == key) {
                return pair.second
            }
        }

        return null
    }

    private fun internalSet(key: Int, value: Int, size: Int, data: ArrayList<Pair<Int, Int>?>) {

        val initialIndex = key % size

        for (i in 0 until size) {
            val index = (initialIndex + i) % size
            val pair = data[index]
            if (pair == null) {
                data[index] = Pair(key, value)
                count++
                return
            }

            if (pair.first == key) {
                data[index] = Pair(key, value)
                return
            }
        }

    }

    override fun set(key: Int, value: Int) {

        internalSet(key, value, size, data)

        // expand if needed
        if (count > size / 2) {
            count = 0
            val newSize = size * 2
            val newData = createArray(newSize)

            data.forEach{ pair ->
                if (pair != null) {
                    internalSet(pair.first, pair.second, newSize, newData)
                }
            }

            this.size = newSize
            this.data = newData
        }

    }

    override fun delete(key: Int) {
        // find the key
        val initialIndex = key % size

        for (i in 0 until size) {
            val index = (initialIndex + i) % size

            val pair = data[index] ?: return

            var found = false
            if (pair.first == key) {
                // found the pair
                found = true
                // 1. remove the pair
                data[index] = null

                // 2. find the last such item and move it here
                var last = index
                for (j in 1 until size) {
                    val nextIndex = (index + j) % size
                    val nextPair = data[nextIndex] ?: break

                    if (nextPair.first % size == initialIndex) {
                        last = nextIndex
                    }
                }

                if (last != index) {
                    data[index] = data[last]
                    data[last] = null
                }
            }
            if (found) {
                break
            }
        }

    }

}