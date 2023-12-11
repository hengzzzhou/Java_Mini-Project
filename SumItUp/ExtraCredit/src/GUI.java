import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * The GUI class represents the main graphical user interface for the SumItUp application.
 * It extends the JFrame class and provides methods for initializing the JFrame, initializing the data,
 * and creating the user interface components.
 */
public class GUI extends JFrame {
    /**
     * The input that the command line argument is given
     */
    private int max;

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
     * My QM number is 210985436 so MAX = 25;
     * It means input must between 10 and 25
     */
    private final int MAX;


    /**
     * Constructs a new GUI object with the specified maximum value.
     *
     * @param max the maximum value for the operands
     */
    public GUI(int max) {
        MAX = 25;
        this.setMax(max);
        initJFrame();
        initData();
        initUI();
        pack();
        setVisible(true);
    }

    /**
     * Sets the maximum value for the operands.
     * If the specified max value is invalid, throws a maxOutOfBoundsException.
     *
     * @param max the maximum value for the operands
     * @throws maxOutOfBoundsException if the max value is invalid
     */
    private void setMax(int max) {
        if (max > MAX || max < 10) {
            throw new maxOutOfBoundsException();
        }
        this.max = max;
    }

    /**
     * Initializes the JFrame properties.
     * Sets the size, title, location, and default close operation of the JFrame.
     */
    private void initJFrame() {
        setSize(586, 540);
        setTitle("Welcome to SumItUp!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes the data by generating random values for the left and right operands.
     */
    private void initData() {
        Random random = new Random();
        randomIntLeft = random.nextInt(max) + 1;
        randomIntRight = random.nextInt(max) + 1;
    }

    /**
     * Initializes the user interface components.
     * Sets the layout, adds labels and image panels, and configures the input panel and check button.
     */
    private void initUI() {
        setLayout(new BorderLayout());

        // Create and add the NorthJLabel
        NorthJLabel = new JLabel("Enter two operands, result and click on 'Check!'", JLabel.CENTER);
        this.add(NorthJLabel, BorderLayout.NORTH);

        // Create and add the plusJLabel with plus icon
        ImageIcon icon = new ImageIcon("image/plus.png"); // plus icon
        JLabel plusJLabel = new JLabel(icon); // label for plus icon
        this.add(plusJLabel, BorderLayout.CENTER);

        // Create and add imagesPanel1 for displaying left operand images
        JPanel imagesPanel1 = getjPanel(randomIntLeft, BorderLayout.WEST); // panel for displaying left operand images

        // Create and add imagesPanel2 for displaying right operand images
        JPanel imagesPanel2 = getjPanel(randomIntRight, BorderLayout.EAST); // panel for displaying right operand images

        // Create inputPanel and configure input components
        JPanel inputPanel = new JPanel(); // panel for input components
        Integer[] numbers1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        Integer[] numbers2 = new Integer[50];
        for (int i = 0; i < numbers2.length; i++) {
            numbers2[i] = i + 1;
        }
        JComboBox<Integer> leftJComboBox = new JComboBox<>(numbers1); // combo box for left operand
        JComboBox<Integer> rightJComboBox = new JComboBox<>(numbers1); // combo box for right operand
        JComboBox<Integer> resultJComBox = new JComboBox<>(numbers2); // combo box for result
        leftJComboBox.setSelectedIndex(-1);
        rightJComboBox.setSelectedIndex(-1);
        resultJComBox.setSelectedIndex(-1);
        inputPanel.add(leftJComboBox);
        JLabel j = new JLabel("+");
        inputPanel.add(j);
        inputPanel.add(rightJComboBox);
        JLabel j2 = new JLabel("=");
        inputPanel.add(j2);
        inputPanel.add(resultJComBox);

        // Create and add the checkButton
        JButton checkButton = new JButton("Check!");
        checkButton.addActionListener(e -> {
            if (leftJComboBox.getSelectedItem() != null && rightJComboBox.getSelectedItem() != null && resultJComBox.getSelectedItem() != null) {
                Integer operand1 = (Integer) leftJComboBox.getSelectedItem();
                Integer operand2 = (Integer) rightJComboBox.getSelectedItem();
                Integer result = (Integer) resultJComBox.getSelectedItem();

                if (operand1 + operand2 == result && operand1 == randomIntLeft && operand2 == randomIntRight) {
                    NorthJLabel.setText("Correct! Have another go?");
                    leftJComboBox.setSelectedIndex(-1);
                    rightJComboBox.setSelectedIndex(-1);
                    resultJComBox.setSelectedIndex(-1);
                    initData();
                    removeImages(imagesPanel1);
                    removeImages(imagesPanel2);
                    initImages(imagesPanel1, randomIntLeft);
                    initImages(imagesPanel2, randomIntRight);
                } else {
                    NorthJLabel.setText("Wrong! Try again!");
                }
            }
        });

        inputPanel.add(checkButton);
        add(inputPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates and returns a JPanel for displaying the images.
     *
     * @param randomIntLeft the random value for the left operand
     * @param position      the position to add the panel (e.g., BorderLayout.WEST)
     * @return the created JPanel
     */
    private JPanel getjPanel(int randomIntLeft, String position) {
        JPanel imagesPanel1 = new JPanel(new GridLayout(0, 5)); // Create a panel with a grid layout for the images
        initImages(imagesPanel1, randomIntLeft); // Initialize the images in the panel
        add(imagesPanel1, position); // Add the panel to the specified position in the JFrame
        return imagesPanel1;
    }

    /**
     * Initializes the images in the specified panel based on the count value.
     *
     * @param panel the panel to add the images to
     * @param count the number of images to display
     */
    private void initImages(JPanel panel, int count) {
        for (int i = 0; i < 25; i++) { // Display up to 25 images
            ImageIcon imageIcon = new ImageIcon("image/rabbit.jpg"); // Load the image
            JLabel label = new JLabel(imageIcon); // Create a JLabel with the image
            label.setVisible(i < count); // Show or hide the label based on the count
            panel.add(label); // Add the label to the panel
        }
    }

    /**
     * Removes all images from the specified panel.
     *
     * @param panel the panel to remove the images from
     */
    private void removeImages(JPanel panel) {
        panel.removeAll(); // Remove all components from the panel
        panel.revalidate(); // Revalidate the panel to update the layout
        panel.repaint(); // Repaint the panel to reflect the changes
    }
}
