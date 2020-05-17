package com.bulumutka.polyconstr.models;

import java.io.*;
import java.nio.Buffer;

public class PythonScript {
    public static String start(String script) {
        var builder = new ProcessBuilder();
        builder.command("python3", script);
        try {
            var p = builder.start();
            var output = new StringBuilder();
            var reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            p.waitFor();
            return output.toString();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException("Can't excecute python script: " + exception.getMessage());
        }
    }

    public static String startWithArgs(String script, String arg) {
        var builder = new ProcessBuilder();
        builder.command("python3", script);
        try {
            var p = builder.start();
            var output = new StringBuilder();
            var reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            var writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            writer.write(arg);
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            p.waitFor();
            return output.toString();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException("Can't excecute python script: " + exception.getMessage());
        }
    }
}
