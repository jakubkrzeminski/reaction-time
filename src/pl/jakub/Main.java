package pl.jakub;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static File log;
    static PrintWriter logWriter;
    static BufferedReader br;
    static long startTime = 0;
    static long endTime = 0;
    static long currentDate = System.currentTimeMillis() / 86400000;

    public static void main(String[] args) throws InterruptedException, IOException {

        JFrame frame = new MyFrame();
        log = new File("log.txt");
        log.createNewFile();

        while (true) {
            if (MyFrame.mode == 0) {
                TimeUnit.NANOSECONDS.sleep(1);
            }
            if (MyFrame.mode == 1) {
                MyFrame.timeLabel.setVisible(false);
                MyFrame.averageTimeLabel.setVisible(false);
                MyFrame.testCountLabel.setVisible(false);
                frame.getContentPane().setBackground(Color.RED);
                TimeUnit.MILLISECONDS.sleep((int) ((Math.random() * (MyFrame.maxTime - MyFrame.minTime)) + MyFrame.minTime));
                MyFrame.mode = 2;
            }
            else if (MyFrame.mode == 2) {
                frame.getContentPane().setBackground(Color.GREEN);
                startTime = System.nanoTime();
                MyFrame.mode = 0;
                MyFrame.previousMode = 2;
            }
            else if (MyFrame.mode == 3) {
                frame.getContentPane().setBackground(Color.BLUE);
                endTime = System.nanoTime();
                MyFrame.setTime(startTime, endTime);
                updateLog((endTime - startTime) / 1000000);
                MyFrame.timeLabel.setVisible(true);
                MyFrame.averageTimeLabel.setVisible(true);
                MyFrame.testCountLabel.setVisible(true);
                MyFrame.mode = 0;
                MyFrame.previousMode = 3;
            }
        }

    }

    static void updateLog(long testTime) throws IOException {
        br = new BufferedReader(new FileReader("log.txt"));
        String line;
        String lastLine = "";
        String content = "";

        if (br.readLine() != null) {
            br = new BufferedReader(new FileReader("log.txt"));
            while ((line = br.readLine()) != null) {
                content += line + "\n";
                lastLine = line;
            }
            String[] data = lastLine.split("\\s+");
            long time = Integer.parseInt(data[0]);
            long date = Long.parseLong(data[1]);
            long count = Integer.parseInt(data[2]);

            if (date == currentDate) {
                time = (time * count + testTime) / (count + 1);
                count++;
                String updatedContent = content.replaceAll(lastLine, time + " " + date + " " + count);
                logWriter = new PrintWriter("log.txt");
                logWriter.print(updatedContent);
                logWriter.close();
                MyFrame.averageTimeLabel.setText(String.valueOf(time));
                MyFrame.testCountLabel.setText(String.valueOf(count));
            } else {
                logWriter = new PrintWriter(new FileWriter("log.txt", true));
                firstRecord(testTime);
            }
        }
        else {
            logWriter = new PrintWriter(new FileWriter("log.txt"));
            firstRecord(testTime);
        }
    }

    static void firstRecord(long testTime) {
        logWriter.println(testTime + " " + currentDate + " " + "1");
        logWriter.close();
        MyFrame.averageTimeLabel.setText(String.valueOf(testTime));
        MyFrame.testCountLabel.setText("1");
    }

}
