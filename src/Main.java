import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Main {
    static double start;
    static boolean CzyStart = false;
    public static void main(String[] args){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        JFrame obj=new JFrame();
        obj.setBounds(width/3,height/3, 700, 600);
        obj.setTitle("Gra");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.getContentPane().setBackground(new Color(16, 161, 210));
        obj.setLayout(null);

        // Delay the color change by 5000 milliseconds (5 seconds)
        Timer timer = new Timer(5000, e -> {
            obj.getContentPane().setBackground(new Color(52,180,0));
            start = System.currentTimeMillis();
        });

        JLabel labelstart = new JLabel("To play the game press Start button");
        labelstart.setFont(new Font("Arial", Font.BOLD, 30));
        labelstart.setBounds(75, 170, 550, 40);
        obj.add(labelstart);

        JButton buttonstart = new JButton("Start");
        buttonstart.setBounds(275, 320, 120, 50);
        buttonstart.setFont(new Font("Arial", Font.BOLD, 28));

        timer.setRepeats(false);

        obj.add(buttonstart);
        buttonstart.addActionListener(e12 -> {
            obj.getContentPane().setBackground(new Color(169,0,0));
            obj.remove(buttonstart);
            obj.remove(labelstart);
            timer.start();
            CzyStart = true;
        });

        JButton restart = new JButton("Restart");
        restart.setBounds(275, 320, 130, 50);
        restart.setFont(new Font("Arial", Font.BOLD, 28));

        obj.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (CzyStart) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        timer.stop();
                        if (Objects.equals(obj.getContentPane().getBackground(), new Color(169, 0, 0))) {
                            JLabel label = new JLabel("Wait until you see the green color!");
                            label.setFont(new Font("Arial", Font.BOLD, 30));
                            label.setBounds(95, 170, 550, 40);
                            obj.getContentPane().setBackground(new Color(16, 161, 210));
                            obj.add(label);

                            CzyStart = false;

                            restart.addActionListener(e12 -> {
                                CzyStart = true;
                                obj.remove(restart);
                                obj.getContentPane().setBackground(new Color(169, 0, 0));
                                timer.restart();
                                obj.remove(label);
                                timer.start();
                            });
                        } else {
                            double end = System.currentTimeMillis();
                            double result = end - start;
                            JLabel label = new JLabel("Your score: " + result + " ms");
                            label.setFont(new Font("Arial", Font.BOLD, 30));
                            label.setBounds(200, 220, 380, 30);
                            obj.getContentPane().setBackground(new Color(16, 161, 210));
                            obj.add(label);

                            restart.addActionListener(e1 -> {
                                obj.remove(restart);
                                obj.getContentPane().setBackground(new Color(169, 0, 0));
                                timer.restart();
                                obj.remove(label);
                                timer.start();
                            });
                        }
                        obj.add(restart);
                    }
                }
            }
        });
    }
}