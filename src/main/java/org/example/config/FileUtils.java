package org.example.config;

import com.opencsv.CSVReader;
import org.example.entity.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    private final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private String fileName;
    private CSVReader csvReader;
    private FileReader fileReader;
    private File file;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    public Line readLine() {
        try {
            if (csvReader == null) {
                initReader();
                csvReader.skip(1);
            };
            String[] line = csvReader.readNext();
            if (line == null) return null;
            return new Line(line[0],Long.valueOf(line[1]));
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Error while reading line in file: " + this.fileName);
            return null;
        }
    }

    private void initReader() throws Exception {
        ClassLoader classLoader = this
                .getClass()
                .getClassLoader();
        if (file == null) file = new File(classLoader
                .getResource(fileName)
                .getFile());
        if (fileReader == null) fileReader = new FileReader(file);
        if (csvReader == null) csvReader = new CSVReader(fileReader);
    }

    public void closeReader() {
        try {
            csvReader.close();
            fileReader.close();
        } catch (IOException e) {
            logger.error("Error while closing reader.");
        }
    }
}

