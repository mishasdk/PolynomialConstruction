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

fun getFormulasPy(data: List<String>): String {
    try {
        val command = mutableListOf(PYTHON_COMMAND, PY_WORK_DIR + "get_formulas.py").apply { addAll(data) }
        val process = ProcessBuilder(command).start()
        val output = BufferedReader(InputStreamReader(process.inputStream))
        val error = BufferedReader(InputStreamReader(process.errorStream))
        val exitCode = process.waitFor()

        val lines = output.readLines()
        val result = StringBuilder()
        for (line in lines) {
            result.append(line)
        }

        if (exitCode != 0) {
            println("Error: exit code is $exitCode")
            for (line in error.readLines()) {
                println(line)
            }
            return "Error while script executing:\n $result"
        }

        return result.toString()
    } catch (exc: IOException) {
        println("Error: $exc")
        return "Error"
    }
}
