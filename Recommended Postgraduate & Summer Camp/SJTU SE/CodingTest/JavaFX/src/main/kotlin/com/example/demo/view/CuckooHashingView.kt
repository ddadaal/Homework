package com.example.demo.view

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.Parent
import tornadofx.*

class CuckooHashingView : View("Graph View") {


    val keyProperty = SimpleStringProperty()
    val valueProperty = SimpleStringProperty()


    override val root: Parent = vbox {
        paddingAll = 36.0

        hbox {
            spacing = 8.0
            label("键")
            textfield(keyProperty)
            label("值")
            textfield(valueProperty)
            button("插入")
            button("删除")
        }

        hbox {
            spacing = 40.0
            vbox {
                label("H1(x) = x mod 8")
            }

            vbox {
                label("H2(x) = (x div 8) mod 8")
            }
        }

    }

}