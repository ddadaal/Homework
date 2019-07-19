package com.example.demo.test

import com.example.demo.app.GlobalVariables
import javafx.beans.property.SimpleStringProperty
import jdk.nashorn.internal.objects.Global
import tornadofx.*
import java.io.File

class SearchView : Fragment("My View") {

    var file = GlobalVariables.file

    override fun onDock() {
        this.file = GlobalVariables.file
    }

    private val searchTextProperty = SimpleStringProperty()
    private val searchText by searchTextProperty

    private val resultProperty = SimpleStringProperty()
    private val result by resultProperty

    override val root = vbox {
        paddingAll = 32.0

        spacing = 32.0

        hbox(spacing = 20.0) {
            label("请输入搜索项")
            textfield(searchTextProperty)
            button("确定") {
                setOnAction {
                    search()
                }
            }
        }

        label("搜索结果")

        textarea(resultProperty) {
            isEditable = false

            minHeight = 400.0
        }
    }

    fun search() {
        resultProperty.set("Searching with $searchText")
    }
}
