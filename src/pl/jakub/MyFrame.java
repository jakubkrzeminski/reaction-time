package pl.jakub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame implements MouseListener {
    static int mode = 0;
    static int previousMode = 0;
    static int maxTime = 5000;
    static int minTime = 2000;
    ImageIcon img = new ImageIcon("img.png");

    static JLabel timeLabel;
    static JLabel averageTimeLabel;
    static JLabel testCountLabel;

    MyFrame() {
        this.setTitle("Reaction Time");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.getContentPane().setBackground(Color.BLUE);
        this.setVisible(true);

        timeLabel = new JLabel();
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timeLabel.setVisible(true);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        this.add(timeLabel, BorderLayout.CENTER);

        averageTimeLabel = new JLabel();
        averageTimeLabel.setForeground(Color.BLACK);
        averageTimeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        averageTimeLabel.setVisible(false);
        this.add(averageTimeLabel, BorderLayout.WEST);

        testCountLabel = new JLabel();
        testCountLabel.setForeground(Color.BLACK);
        testCountLabel.setFont(new Font("Arial", Font.BOLD, 30));
        testCountLabel.setVisible(false);
        this.add(testCountLabel, BorderLayout.EAST);

        this.addMouseListener(this);
    }

    static void setTime(long startTime, long endTime) {
        timeLabel.setText(String.valueOf((endTime - startTime) / 1000000));
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mode == 0) {
            if (previousMode == 0) mode = 1;
            if (previousMode == 2) mode = 3;
            if (previousMode == 3) mode = 1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
