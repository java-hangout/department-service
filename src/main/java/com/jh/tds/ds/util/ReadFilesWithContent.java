package com.jh.tds.ds.util;

import java.io.*;

public class ReadFilesWithContent {

    public static void main(String[] args) {
        // Specify the directory of the ReactJS project
        String projectRoot = "D:\\OSBI\\workspaceui\\task-display-system-ui\\src"; // Change to the path of your ReactJS project
        String outputFile = "D:\\OSBI\\workspace\\department-service\\src\\main\\resources\\output\\fileList_ui.txt"; // Output file to store file paths and contents

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            File rootDir = new File(projectRoot);

            if (!rootDir.exists() || !rootDir.isDirectory()) {
                System.out.println("Invalid directory path: " + projectRoot);
                return;
            }

            // Start processing files
            processFilesRecursively(rootDir, writer);

            System.out.println("File paths and contents written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void processFilesRecursively(File dir, BufferedWriter writer) throws IOException {
        File[] files = dir.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively process sub-directories
                processFilesRecursively(file, writer);
            } else {
                // Write file path
                writer.write("File: " + file.getAbsolutePath());
                writer.newLine();

                // Write file content
                writer.write("Contents:");
                writer.newLine();

                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    writer.write("Error reading file: " + e.getMessage());
                    writer.newLine();
                }

                writer.write("------------------------------------------------------------");
                writer.newLine();
            }
        }
    }
}
