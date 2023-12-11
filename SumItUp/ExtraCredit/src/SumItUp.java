/**
 * The SumItUP class contains the main method for starting the SumItUp program.
 * It takes a command line argument to determine the maximum value allowed for the operands.
 * The application such that the maximum number of Rabbit images displayed
 * for each operand max can be any number between 10 and MAX (inclusive), specified as a
 * command line argument.
 * Whenever a correct answer is entered, the number of Rabbit images
 * per operand will change to any number between 1 and max (inclusive). There should be no more
 * than 5 Rabbit images per row.
 */
public class SumItUp {

    /**
     * The main method of the SumItUP program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int max;
        try {
            max = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The array is out of bounds because no command line argument is given.");
            System.exit(1);
            return;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid integer.");
            System.exit(1);
            return;
        }

        try {
            new GUI(max);
        } catch (maxOutOfBoundsException e) {
            System.out.println("The input must be between 1 and 25 (inclusive). You gave a number outside the valid range!");
            System.exit(1);
        }
    }
}
