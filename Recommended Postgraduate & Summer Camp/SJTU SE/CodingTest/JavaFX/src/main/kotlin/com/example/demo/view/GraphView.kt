package com.example.demo.view

import com.example.demo.data.CuckooHashMap
import com.example.demo.data.HashMap
import com.example.demo.data.LinearHashMap
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import tornadofx.*
import kotlin.random.Random

class PerfResult(val values: List<Long>, val totalTime: Long) {
    val min by lazy { values.min() }
    val max by lazy { values.max() }
    val average by lazy { values.average() }
    val size get() = values.size
}

class GraphView : View("Graph View") {

    private fun getRandomInt(): Int {
        val i = Random.nextInt()
        return if (i >= 0) i else -i
    }

    private fun graphInit() {
        // 1. generate random key and values

        val data = arrayListOf<Pair<Int, Int>>()
        data.ensureCapacity(10000)

        repeat(10000) {
            data.add(Pair(getRandomInt(), getRandomInt()))
        }

        // 2. generate insertion latencies
        val linearHashMap = LinearHashMap()
        val cuckooHashMap = CuckooHashMap()

        linearInsertionLatencies = insertionLatencies(linearHashMap, data)
        cuckooInsertionLatencies = insertionLatencies(cuckooHashMap, data)

        // insert the rest
        insertTheRest(linearHashMap, data)
        insertTheRest(cuckooHashMap, data)

        // 3. generate perfresult for all get for 10000 times
        linearAllGetPerf = allGetPerfResult(linearHashMap)
        cuckooAllGetPerf = allGetPerfResult(cuckooHashMap)
        linearMixedPerf = mixedPerfResult(linearHashMap)
        cuckooMixedPerf = mixedPerfResult(cuckooHashMap)


    }

    private fun allGetPerfResult(hashMap: HashMap): PerfResult {
        val allStartTime = System.nanoTime()

        val durations = arrayListOf<Long>()

        var result = 0

        repeat(10000) { i ->
            val startTime = System.nanoTime()
            result += hashMap.get(getRandomInt()) ?: 0
            durations.add(System.nanoTime() - startTime)
        }

        return PerfResult(durations, System.nanoTime() - allStartTime)
    }

    private fun mixedPerfResult(hashMap: HashMap): PerfResult {
        val allStartTime = System.nanoTime()

        val durations = arrayListOf<Long>()

        var c = true

        var result = 0

        repeat(10000) { i ->
            val startTime = System.nanoTime()
            if (c) {
                hashMap.set(getRandomInt(), getRandomInt())
            } else {
                result += hashMap.get(getRandomInt()) ?: 0 }
            durations.add(System.nanoTime() - startTime)
            c = !c
        }

        return PerfResult(durations, System.nanoTime() - allStartTime)
    }

    private fun insertTheRest(hashmap: HashMap, data: List<Pair<Int, Int>>) {
        (1000 until 10000).forEach { i -> hashmap.set(data[i].first, data[i].second) }

    }


    private lateinit var linearInsertionLatencies: List<Long>
    private lateinit var linearAllGetPerf: PerfResult
    private lateinit var linearMixedPerf: PerfResult

    private lateinit var cuckooInsertionLatencies: List<Long>
    private lateinit var cuckooAllGetPerf: PerfResult
    private lateinit var cuckooMixedPerf: PerfResult


    // unit: ns
    private fun insertionLatencies(hashMap: HashMap, data: List<Pair<Int, Int>>): List<Long> {

        val latencies = arrayListOf<Long>()
        latencies.ensureCapacity(1000)

        (0 until 1000).forEach { i ->
            val startTime = System.nanoTime()
            hashMap.set(data[i].first, data[i].second)
            val endTime = System.nanoTime()
            latencies.add(endTime - startTime)
        }

        return latencies
    }



    override val root = vbox {

        graphInit()

        paddingAll = 36.0

        linechart("First 1000 Insertion Latency", NumberAxis(), NumberAxis()) {
            series("Linear") {
                linearInsertionLatencies.forEachIndexed { index, latency ->
                    data(index, latency)
                }
            }
            series("Cuckoo") {
                cuckooInsertionLatencies.forEachIndexed { index, latency ->
                    data(index, latency)
                }
            }

        }

        barchart("Latency Chart", CategoryAxis(), NumberAxis()) {
            series("Linear") {
                data("All Get Average", linearAllGetPerf.average)
                data("All Get Min", linearAllGetPerf.min)
                data("All Get Max", linearAllGetPerf.max)
                data("Mixed Average", linearMixedPerf.average)
                data("Mixed Min", linearMixedPerf.min)
                data("Mixed Max", linearMixedPerf.max)
            }
            series("Cuckoo") {
                data("All Get Average", cuckooAllGetPerf.average)
                data("All Get Min", cuckooAllGetPerf.min)
                data("All Get Max", cuckooAllGetPerf.max)
                data("Mixed Average", cuckooMixedPerf.average)
                data("Mixed Min", cuckooMixedPerf.min)
                data("Mixed Max", cuckooMixedPerf.max)
            }
        }

        barchart("Throughput Chart", CategoryAxis(), NumberAxis()) {
            series("Linear") {
                data("All Get Throughput", linearAllGetPerf.size.toDouble() / linearAllGetPerf.totalTime * 10000000)
                data("Mixed Get Throughput", linearMixedPerf.size.toDouble() / linearMixedPerf.totalTime * 10000000)
            }
            series("Cuckoo") {
                data("All Get Throughput", cuckooAllGetPerf.size.toDouble() / cuckooAllGetPerf.totalTime * 10000000)
                data("Mixed Get Throughput", cuckooMixedPerf.size.toDouble() / cuckooAllGetPerf.totalTime * 10000000)
            }
        }


    }





}
