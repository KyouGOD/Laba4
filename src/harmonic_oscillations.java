import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class harmonic_oscillations extends JFrame {
    private JTextField length_input, constant_field, timer_field;
    private JButton start_button, stop_button;
    private JPanel animation_panel;

    private double length, constant;
    private Timer timer;
    private double time = 0;

    public harmonic_oscillations() {
        setTitle("Гармонические колебания");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel lengthLabel = new JLabel("Длина отрезка (q):");
        length_input = new JTextField(10);
        inputPanel.add(lengthLabel);
        inputPanel.add(length_input);

        JLabel constantLabel = new JLabel("Константа (w):");
        constant_field = new JTextField(10);
        inputPanel.add(constantLabel);
        inputPanel.add(constant_field);

        JLabel timerLabel = new JLabel("Время (t):");
        timer_field = new JTextField(10);
        inputPanel.add(timerLabel);
        inputPanel.add(timer_field);

        start_button = new JButton("Старт");
        stop_button = new JButton("Стоп");
        inputPanel.add(start_button);
        inputPanel.add(stop_button);

        animation_panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                double distance = length * (1 + Math.cos(constant * time)) / 2;
                int x = (int) (distance / length * getWidth());
                g.fillOval(x, getHeight() / 2 - 5, 10, 10);
            }
        };

        add(inputPanel, BorderLayout.NORTH);
        add(animation_panel, BorderLayout.CENTER);

        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnimation();
            }
        });

        stop_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnimation();
            }
        });
    }

    private void startAnimation() {
        try {
            length = Double.parseDouble(length_input.getText());
            constant = Double.parseDouble(constant_field.getText());
            time = Double.parseDouble(timer_field.getText());

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time += 0.01;
                    animation_panel.repaint();
                }
            });

            timer.start();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Некорректный ввод!");
        }
    }

    private void stopAnimation() {
        if (timer != null) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                harmonic_oscillations frame = new harmonic_oscillations();
                frame.setVisible(true);
            }
        });
    }
}