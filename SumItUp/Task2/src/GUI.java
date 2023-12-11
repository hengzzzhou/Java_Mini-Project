import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
     * Constructs a new instance of the GUI class.
     * Initializes the JFrame, data, and UI components.
     * Sets the visibility of the window to true.
     */
    public GUI() {
        initJFrame();
        initData();
        initUI();
        pack();
        setVisible(true);
    }

    /**
     * Initializes the JFrame properties.
     * Sets the size, title, location, and default close operation.
     */
    private void initJFrame() {
        setSize(586, 540); // Set the size of the JFrame
        setTitle("Welcome to SumItUp!"); // Set the title of the JFrame
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the JFrame is closed
    }

    /**
     * Initializes the data for random numbers.
     * Generates random integers for the left and right operands.
     */
    private void initData() {
        Random random = new Random();
        randomIntLeft = random.nextInt(10) + 1; // Generate a random number between 1 and 10 for the left operand
        randomIntRight = random.nextInt(10) + 1; // Generate a random number between 1 and 10 for the right operand
    }

    /**
     * Initializes the UI components of the JFrame.
     * Sets the layout, adds labels and panels, and attaches event listeners to the check button.
     */
    private void initUI() {
        setLayout(new BorderLayout()); // Set the layout manager for the JFrame

        NorthJLabel = new JLabel("Enter two operands, result and click on 'Check!'", JLabel.CENTER); // Create a label with a message
        this.add(NorthJLabel, BorderLayout.NORTH); // Add the label to the top of the JFrame

        ImageIcon icon = new ImageIcon("image/plus.png"); // Load the plus sign image
        JLabel plusJLabel = new JLabel(icon); // Create a JLabel with the plus sign image
        this.add(plusJLabel, BorderLayout.CENTER); // Add the JLabel to the center position of the JFrame

        JPanel imagesPanel1 = getjPanel(randomIntLeft, BorderLayout.WEST); // Create a panel for displaying images based on the left operand
        JPanel imagesPanel2 = getjPanel(randomIntRight, BorderLayout.EAST); // Create a panel for displaying images based on the right operand

        JPanel inputPanel = new JPanel(); // Create a panel for input components
        Integer[] numbers1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // Array of numbers for operands
        Integer[] numbers2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 }; // Array of numbers for results
        JComboBox<Integer> leftJComboBox = new JComboBox<>(numbers1); // ComboBox for selecting the left operand
        JComboBox<Integer> rightJComboBox = new JComboBox<>(numbers1); // ComboBox for selecting the right operand
        JComboBox<Integer> resultJComBox = new JComboBox<>(numbers2); // ComboBox for selecting the result
        leftJComboBox.setSelectedIndex(-1); // Set the initial selection to none
        rightJComboBox.setSelectedIndex(-1); // Set the initial selection to none
        resultJComBox.setSelectedIndex(-1); // Set the initial selection to none
        inputPanel.add(leftJComboBox);
        JLabel j = new JLabel("+");
        inputPanel.add(j);
        inputPanel.add(rightJComboBox);
        JLabel j2 = new JLabel("=");
        inputPanel.add(j2);
        inputPanel.add(resultJComBox);

        JButton checkButton = new JButton("Check!"); // Create a button for checking the answer
        checkButton.addActionListener(e -> {
            if (leftJComboBox.getSelectedItem() != null && rightJComboBox.getSelectedItem() != null && resultJComBox.getSelectedItem() != null) {
                Integer operand1 = (Integer) leftJComboBox.getSelectedItem(); // Get the selected value for the left operand
                Integer operand2 = (Integer) rightJComboBox.getSelectedItem(); // Get the selected value for the right operand
                Integer result = (Integer) resultJComBox.getSelectedItem(); // Get the selected value for the result

                if (operand1 + operand2 == result && operand1 == randomIntLeft && operand2 == randomIntRight) {
                    NorthJLabel.setText("Correct! Have another go?"); // Display a success message
                    leftJComboBox.setSelectedIndex(-1); // Reset the left operand selection
                    rightJComboBox.setSelectedIndex(-1); // Reset the right operand selection
                    resultJComBox.setSelectedIndex(-1); // Reset the result selection
                    initData(); // Generate new random numbers
                    removeImages(imagesPanel1); // Remove the previous images from the left panel
                    removeImages(imagesPanel2); // Remove the previous images from the right panel
                    initImages(imagesPanel1, randomIntLeft); // Initialize the images in the left panel based on the new count
                    initImages(imagesPanel2, randomIntRight); // Initialize the images in the right panel based on the new count
                } else {
                    NorthJLabel.setText("Wrong! Try again!"); // Display an error message
                }
            }
        });

        inputPanel.add(checkButton);
        add(inputPanel, BorderLayout.SOUTH); // Add the input panel to the bottom of the JFrame
    }

    /**
     * Creates a JPanel with image labels based on the given count and position.
     * Adds the JPanel to the specified position in the JFrame.
     *
     * @param randomIntLeft The count of images to display in the panel.
     * @param position      The position where the panel will be added (WEST or EAST).
     * @return The created JPanel.
     */
    private JPanel getjPanel(int randomIntLeft, String position) {
        JPanel imagesPanel1 = new JPanel(new GridLayout(0, 3)); // Create a panel with a grid layout for the images
        initImages(imagesPanel1, randomIntLeft); // Initialize the images in the panel
        add(imagesPanel1, position); // Add the panel to the specified position in the JFrame
        return imagesPanel1;
    }

    /**
     * Initializes the images in the given panel based on the count.
     * Displays image labels up to the given count, hides the rest.
     *
     * @param panel The panel to add the image labels.
     * @param count The count of images to display.
     */
    private void initImages(JPanel panel, int count) {
        for (int i = 0; i < 12; i++) { // Display up to 12 images
            ImageIcon imageIcon = new ImageIcon("image/rabbit.jpg"); // Load the image
            JLabel label = new JLabel(imageIcon); // Create a JLabel with the image
            label.setVisible(i < count); // Show or hide the label based on the count
            panel.add(label); // Add the label to the panel
        }
    }

    /**
     * Removes all image labels from the given panel.
     *
     * @param panel The panel to remove the image labels from.
     */
    private void removeImages(JPanel panel) {
        panel.removeAll(); // Remove all components from the panel
        panel.revalidate(); // Revalidate the panel to update the layout
        panel.repaint(); // Repaint the panel to reflect the changes
    }
}
