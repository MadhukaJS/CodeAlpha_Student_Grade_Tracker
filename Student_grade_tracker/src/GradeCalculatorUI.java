import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class GradeCalculatorUI extends JFrame {
    private ArrayList<Double> grades = new ArrayList<>();
    private JLabel averageLabel, highestLabel, lowestLabel;

    public GradeCalculatorUI() {
        setTitle("Student Grade Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton enterGradeButton = new JButton("Enter Student's Grade");
        enterGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gradeStr = JOptionPane.showInputDialog(null, "Enter student's grade:");
                try {
                    double grade = Double.parseDouble(gradeStr);
                    if (grade < 0 || grade > 100) {
                        JOptionPane.showMessageDialog(null, "Grade must be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        grades.add(grade);
                        JOptionPane.showMessageDialog(null, "Grade added successfully.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(enterGradeButton);

        JButton calculateButton = new JButton("Calculate Average Grade");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grades.isEmpty()) {
                    double sum = 0;
                    for (double grade : grades) {
                        sum += grade;
                    }
                    double average = sum / grades.size();
                    DecimalFormat df = new DecimalFormat("#.##");
                    averageLabel.setText("Average grade: " + df.format(average));
                } else {
                    averageLabel.setText("No grades entered yet.");
                }
            }
        });
        mainPanel.add(calculateButton);

        JButton highestButton = new JButton("Find Highest Grade");
        highestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grades.isEmpty()) {
                    double highest = Collections.max(grades);
                    highestLabel.setText("Highest grade: " + highest);
                } else {
                    highestLabel.setText("No grades entered yet.");
                }
            }
        });
        mainPanel.add(highestButton);

        JButton lowestButton = new JButton("Find Lowest Grade");
        lowestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grades.isEmpty()) {
                    double lowest = Collections.min(grades);
                    lowestLabel.setText("Lowest grade: " + lowest);
                } else {
                    lowestLabel.setText("No grades entered yet.");
                }
            }
        });
        mainPanel.add(lowestButton);

        averageLabel = new JLabel("Average grade: ");
        mainPanel.add(averageLabel);

        highestLabel = new JLabel("Highest grade: ");
        mainPanel.add(highestLabel);

        lowestLabel = new JLabel("Lowest grade: ");
        mainPanel.add(lowestLabel);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GradeCalculatorUI();
            }
        });
    }
}
