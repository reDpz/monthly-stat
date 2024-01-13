package com.monthlystat.tui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class App {

    final String logDir = "logs/";

    public App() {
        File logFolder = new File(logDir);
        if (!logFolder.exists() || logFolder.isFile()) {
            logFolder.mkdir();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            app.writeCurrentDay("30");
            readFile(app.getMonthFile());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public String todayDD() {
        return DateTimeFormatter.ofPattern("d").format(LocalDateTime.now());
    }

    public String todayMMDDYYYY() {
        return DateTimeFormatter.ofPattern("ddMMyyyy").format(LocalDateTime.now());
    }

    public String getMonth() {
        return DateTimeFormatter.ofPattern("MMyyyy").format(LocalDateTime.now());
    }

    public File getMonthFile() { // FP stands for file path
        return new File(logDir + getMonth() + ".csv");
    }

    public void writeCurrentDay(String input) throws IOException {
        // System.out.println(todayDD());

        File currentMonth = getMonthFile();

        // get current month and open appropriate file
        if (!currentMonth.exists() || currentMonth.isDirectory()) {
            // create file
            currentMonth.createNewFile();

            // write current day to file
            try {
                FileOutputStream fos = new FileOutputStream(currentMonth, true);
                String toWrite = todayDD() + ", " + input + "\n";
                fos.write(toWrite.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void readFile(File file) {
        try {
            FileInputStream reader = new FileInputStream(file);
            System.out.println(reader.toString());
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
