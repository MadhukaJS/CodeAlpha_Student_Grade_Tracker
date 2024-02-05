import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorUI extends JFrame {
    private JTextField[] gradeFields;
    private JLabel resultLabel;

    public GradeCalculatorUI() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
        layoutComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        // Prompt for the number of students
        int numStudents = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of students:"));

        // Initialize array of JTextFields for grade input
        gradeFields = new JTextField[numStudents];
        for (int i = 0; i < numStudents; i++) {
            gradeFields[i] = new JTextField(5);
        }

        // Button to trigger calculations
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });

        // JLabel to display results
        resultLabel = new JLabel();

        // Set layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the frame
        add(new JLabel("Enter grades for each student:"));
        for (int i = 0; i < numStudents; i++) {
            add(gradeFields[i]);
        }
        add(calculateButton);
        add(resultLabel);
    }

    private void layoutComponents() {
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void calculateGrades() {
        try {
            // Calculate average, highest, and lowest grades
            double[] grades = new double[gradeFields.length];

            for (int i = 0; i < gradeFields.length; i++) {
                grades[i] = Double.parseDouble(gradeFields[i].getText());
            }

            double sum = 0;
            double highest = grades[0];
            double lowest = grades[0];

            for (double grade : grades) {
                sum += grade;

                if (grade > highest) {
                    highest = grade;
                }

                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = sum / gradeFields.length;

            // Display results
            resultLabel.setText("Average Grade: " + average + " | Highest Grade: " + highest + " | Lowest Grade: " + lowest);

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter numeric grades.");
        }
    }


    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorUI();
            }
        });
    }
}
