package Package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchGUI extends JFrame {
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private JLabel milliLabel;

    private Timer timer;
    private long startTime;
    private long elapsedTime;
    private long prevMillis;

    public StopwatchGUI() {
        setTitle("Stopwatch");
        setSize(450, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 28));

        milliLabel = new JLabel(".00");
        milliLabel.setFont(new Font("Arial", Font.BOLD, 18));

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        add(timeLabel);
        add(milliLabel);
        add(startButton);
        add(stopButton);
        add(resetButton);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });
    }

    private void start() {
        if (!timer.isRunning()) {
            startTime = System.currentTimeMillis() - elapsedTime;
            prevMillis = elapsedTime;
            timer.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
    }

    private void stop() {
        if (timer.isRunning()) {
            timer.stop();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            prevMillis = elapsedTime;
        }
    }

    private void reset() {
        timer.stop();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        timeLabel.setText("00:00:00");
        milliLabel.setText(".00");
        elapsedTime = 0;
    }

    private void updateDisplay() {
        long currentTime = System.currentTimeMillis();
        elapsedTime = currentTime - startTime;
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / 60000) % 60;
        long hours = (elapsedTime / 3600000);
        long milliseconds = elapsedTime % 1000;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        String milliString = String.format(".%03d", milliseconds);
        timeLabel.setText(timeString);
        milliLabel.setText(milliString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StopwatchGUI stopwatch = new StopwatchGUI();
                stopwatch.setVisible(true);
            }
        });
    }
}
