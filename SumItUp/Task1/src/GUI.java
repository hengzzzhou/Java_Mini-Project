import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 * The GUI class represents the main graphical user interface for the SumItUp application.
 *  The class represents a JFrame window for a simple arithmetic game.
 *   It allows the user to enter two operands and a result and checks if the sum is correct.
 */
public class GUI extends JFrame {
    /**
     * Randomly generated value for the left operand.
     * it means the number of rabbits on the left.
     */
    private int randomIntLeft;

    /**
     * Randomly generated value for the right operand.
     * it means the number of rabbits on the right.
     */
    private int randomIntRight;

    /**
     * Label for displaying instructions.
     */
    private JLabel NorthJLabel;

    /**
     * Constructs a GUI object and initializes the JFrame.
     */
    public GUI() {
        initJFrame();//initialize the JFrame
        initData();//initialize two random data
        initUI();//initialize the ui
        pack();
        setVisible(true);
    }

    /**
     * Initializes the JFrame properties.
     */
    private void initJFrame() {
        setSize(500, 540); // Sets the size of the JFrame
        setTitle("Welcome to SumItUp!"); // Sets the title of the JFrame
        setLocationRelativeTo(null); // Centers the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the close operation of the JFrame
    }

    /**
     * Initializes the data by generating random integers for the operands.
     */
    private void initData() {
        Random random = new Random();
        randomIntLeft = random.nextInt(10) + 1; // Generates a random number between 1 and 10 (inclusive) for the left operand
        randomIntRight = random.nextInt(10) + 1; // Generates a random number between 1 and 10 (inclusive) for the right operand
    }

    /**
     * Initializes the user interface components and adds them to the JFrame.
     */
    private void initUI() {
        setLayout(new BorderLayout()); // Sets the layout manager for the JFrame

        NorthJLabel = new JLabel("Enter two operands, result and click on 'Check!'", JLabel.CENTER);
        this.add(NorthJLabel, BorderLayout.NORTH); // Adds the label to the north of the JFrame

        ImageIcon icon = new ImageIcon("image/plus.png");
        JLabel plusJLabel = new JLabel(icon);
        this.add(plusJLabel, BorderLayout.CENTER); // Adds the plus image to the center of the JFrame

        JPanel imagesPanelWest = getjPanel(randomIntLeft,  BorderLayout.WEST); // Creates a panel for displaying images on the left
        JPanel imagesPanelEast = getjPanel(randomIntRight, BorderLayout.EAST); // Creates a panel for displaying images on the right

        JPanel inputPanel = new JPanel();
        final JTextField operand1TextField = new JTextField(2); // Text field for the first operand
        inputPanel.add(operand1TextField);
        inputPanel.add(new JLabel("+"));
        final JTextField operand2TextField = new JTextField(2); // Text field for the second operand
        inputPanel.add(operand2TextField);
        inputPanel.add(new JLabel("="));
        final JTextField resultTextField = new JTextField(2); // Text field for the result
        inputPanel.add(resultTextField);

        JButton checkButton = new JButton("Check!");
        checkButton.addActionListener(new ActionListener() {
            /**
             * Action performed when the 'Check!' button is clicked.
             *
             * @param e The ActionEvent object.
             */
            public void actionPerformed(ActionEvent e) {
                if (!operand1TextField.getText().isEmpty() && !operand2TextField.getText().isEmpty() && !resultTextField.getText().isEmpty()) {
                    int operand1 = Integer.parseInt(operand1TextField.getText()); // Converts the text in the operand1TextField to an integer
                    int operand2 = Integer.parseInt(operand2TextField.getText()); // Converts the text in the operand2TextField to an integer
                    int result = Integer.parseInt(resultTextField.getText()); // Converts the text in the resultTextField to an integer

                    if (operand1 + operand2 == result && operand1 == randomIntLeft && operand2 == randomIntRight) {
                        NorthJLabel.setText("Correct! Have another go?");
                        operand1TextField.setText("");
                        operand2TextField.setText("");
                        resultTextField.setText("");
                        initData();
                        removeImages(imagesPanelWest);
                        removeImages(imagesPanelEast);
                        initImages(imagesPanelWest, randomIntLeft);
                        initImages(imagesPanelEast, randomIntRight);
                    } else {
                        NorthJLabel.setText("Wrong! Try again!");
                    }
                }
            }
        });

        inputPanel.add(checkButton);
        add(inputPanel, BorderLayout.SOUTH); // Adds the inputPanel to the south of the JFrame
    }

    /**
     * Creates a JPanel with image labels based on the count and position provided.
     *
     * @param randomIntLeft The count of images to display.
     * @param position          The alignment for the panel.
     * @return The created JPanel.
     */
    private JPanel getjPanel(int randomIntLeft, String position) {
        JPanel imagesPanel1 = new JPanel(new GridLayout(0, 3));
        initImages(imagesPanel1, randomIntLeft);
        add(imagesPanel1, position); // Adds the panel to the specified position (west or east) of the JFrame
        return imagesPanel1;
    }

    /**
     * Initializes the image labels in the panel based on the count provided.
     *
     * @param panel    The JPanel to add the image labels to.
     * @param count    The count of images to display.
     */
    private void initImages(JPanel panel, int count) {
        for (int i = 0; i < 12; i++) {
            ImageIcon imageIcon = new ImageIcon("image/rabbit.jpg");
            JLabel label = new JLabel(imageIcon);
            label.setVisible(i < count); // Sets the visibility of the label based on the count
            panel.add(label);
        }
    }

    /**
     * Removes all image labels from the panel.
     *
     * @param panel The JPanel to remove the image labels from.
     */
    private void removeImages(JPanel panel) {
        panel.removeAll(); // Removes all components from the panel
        panel.revalidate(); // Invalidates the panel to trigger a re-layout
        panel.repaint(); // Repaints the panel to reflect the changes
    }
}
