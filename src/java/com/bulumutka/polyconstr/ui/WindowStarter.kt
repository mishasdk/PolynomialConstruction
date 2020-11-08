package com.bulumutka.polyconstr.ui

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

private const val VIEW_PATH = "views/"
private const val FORMULAS_PATH = "${VIEW_PATH}ShowResultView.fxml"
private const val FORMULAS_TITLE = "Function's formulas"
private const val START_PATH = "${VIEW_PATH}MainView.fxml"
private const val START_TITLE = "Polynomial construction"

class WindowStarter {
    fun startWindow(windowType: WindowType) {
        when (windowType) {
            WindowType.FORMULAS -> proceedResultView()
            WindowType.START -> proceedMainView()
            else -> throw RuntimeException("No such window type: $windowType")
        }
    }

    private fun proceedMainView() {
        Stage().apply {
            title = START_TITLE
            this.scene = Scene(loadParent(START_PATH), 960.0, 540.0)
            show()
        }
    }

    private fun proceedResultView() {
        Stage().apply {
            title = FORMULAS_TITLE
            this.scene = Scene(loadParent(FORMULAS_PATH))
            centerOnScreen()
            show()
        }
    }

    private fun loadParent(path: String) =
        FXMLLoader.load<Parent>(javaClass.classLoader.getResource(path))
}
