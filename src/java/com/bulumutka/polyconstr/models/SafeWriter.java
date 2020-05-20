package com.bulumutka.polyconstr.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SafeWriter {
    public static void writeVector(List<Object> vector, String filePath) {
        try {
            var writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < vector.size(); ++i) {
                writer.write(String.valueOf(vector.get(i)));
                writer.newLine();
            }
            writer.close();
        } catch (IOException exception) {
            throw new RuntimeException("Error while writing vector: " + exception.getMessage());
        }
    }
}
