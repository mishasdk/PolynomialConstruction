package com.bulumutka.polyconstr.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

private const val PY_WORK_DIR = "python/"
private const val PYTHON_COMMAND = "python3"

fun ls() {
    try {
        val process = ProcessBuilder("pwd").start()
        val output = BufferedReader(InputStreamReader(process.inputStream))
        process.waitFor()
        println(output.readLines())
    } catch (exc: IOException) {
        println("Error: $exc")
    }
}

fun getFormulasPy(path: String): String {
    try {
        val command = listOf(PYTHON_COMMAND, PY_WORK_DIR + "get_formulas.py", path)
        val process = ProcessBuilder(command).start()
        val output = BufferedReader(InputStreamReader(process.inputStream))
        val error = BufferedReader(InputStreamReader(process.errorStream))

        var line: String? = output.readLine()
        var lastLine = ""
        while (line != null) {
            println(line)
            lastLine = line
            line = output.readLine()
        }

        val exitCode = process.waitFor()

        if (exitCode != 0) {
            println("Error: exit code is $exitCode")
            for (line in error.readLines()) {
                println(line)
            }
            return "Error"
        }

        return lastLine
    } catch (exc: IOException) {
        println("Error: $exc")
        return "Error"
    }
}
