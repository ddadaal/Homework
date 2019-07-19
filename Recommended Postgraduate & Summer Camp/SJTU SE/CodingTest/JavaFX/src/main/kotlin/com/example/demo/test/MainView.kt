package com.example.demo.test

import com.example.demo.app.GlobalVariables
import javafx.geometry.Pos
import tornadofx.*
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.concurrent.schedule

class MainView : View("全文搜索系统") {


    override val root = hbox {

        alignment = Pos.CENTER
        paddingAll = 100.0

        label("请选择要搜索的文本文件")

        button("浏览") {
            setOnAction {
                val files = chooseFile("选择文件", arrayOf())
                if (files.isNotEmpty()) {
                    GlobalVariables.file = files.first()
                    replaceWith<SearchView>(sizeToScene = true)
                }
            }
        }

    }

    fun timedTask() {

        val currentTime = System.currentTimeMillis()

        Timer("Delayed task", false).schedule(500) {

        }

        Timer(false).schedule(300, 300) {

        }

        System.nanoTime()
    }

    fun openFileDialog() {
        // 打开一个文件，返回List<File>
        chooseFile("选择一个文件", arrayOf()).first()

        // 保存一个文件筐
        chooseFile("保存到", arrayOf(), FileChooserMode.Save).first()
    }

    fun readFile(path: String) {
        val file = File(path)

        // 文件的所有行
        val lines: List<String> = file.readLines()
    }

    fun writeFile(path: String, content: String) {
        val file = File(path)

        // 直接写
        file.writeText(content)

        // 追加
        file.appendText(content)

        // 真正的写法
        FileOutputStream(file).use { out ->
            out.write(content.toByteArray())
        }
    }

//    public static Long getmicTime() {
//        Long cutime = System.currentTimeMillis() * 1000; // 微秒
//        Long nanoTime = System.nanoTime(); // 纳秒
//        return cutime + (nanoTime - nanoTime / 1000000 * 1000000) / 1000;
//    }
//
}