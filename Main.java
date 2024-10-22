// Importing necessary libraries
import java.util.ArrayList;   // For using ArrayList to store positions of characters
import java.util.Scanner;     // For taking user input
import java.util.Random;      // For generating random numbers
import java.util.Arrays;      // For converting arrays to a string representation

// Main class containing the program entry point
public class Main {
    public static void main(String[] args) {

        // Initialize random number generator, scanner for input, and a string to store the password
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        String password = "";

        try {
            // Prompt the user to enter a password
            System.out.print("Enter your password: ");
            // Read the password input from the user
            password = input.nextLine();
        } catch (Exception e) {
            // Catch any exceptions and print an error message
            System.out.println("Error: " + e.getMessage());
        }

        // Create an instance of AlphabetPosition class
        AlphabetPosition alphabetPosition = new AlphabetPosition();

        // Get the alphabetic positions of characters in the password
        int[] positions = alphabetPosition.getAlphabetPositions(password);

        // For debugging purposes, print the alphabetic positions array
        System.out.println("- For debug purposes -");
        System.out.println("Alphabet positions: " + Arrays.toString(positions));

        // Call the Encrypt method to encrypt the password's alphabetic positions
        alphabetPosition.Encrypt(positions);
    }
}

// Class to manage alphabetic position and encryption logic
class AlphabetPosition {

    // Method to convert each letter in the string to its corresponding alphabetic position
    public int[] getAlphabetPositions(String input) {
        // Create a dynamic list to store the positions
        ArrayList<Integer> positions = new ArrayList<>();

        // Loop through each character of the input string (converted to lowercase)
        for (char c : input.toLowerCase().toCharArray()) {
            // If the character is a lowercase letter, calculate its alphabetic position
            if (c >= 'a' && c <= 'z') {
                int position = c - 'a' + 1;  // 'a' is position 1, 'b' is 2, and so on
                positions.add(position);     // Add position to the list
            }
        }

        // Convert the ArrayList to a regular array of integers
        int[] result = new int[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            result[i] = positions.get(i);
        }

        // Return the array of positions
        return result;
    }

    // Method to generate a random key (for encryption)
    public long generateKey() {
        Random rand = new Random();    // Create a new Random instance
        return rand.nextLong();        // Generate a random long value (which serves as the encryption key)
    }

    // Method to encrypt the alphabetic positions using random keys
    public void Encrypt(int[] positions) {
        // Arrays to store the generated keys and encrypted positions
        long[] genList = new long[positions.length];
        long[] EPos = new long[positions.length];

        // Loop through each alphabetic position
        for (int i = 0; i < positions.length; i++) {
            // Generate a random key
            long key = generateKey();
            // Multiply the alphabetic position by the generated key (simple encryption)
            long ENum = positions[i] * key;

            // Store the encrypted value and the key used for it
            EPos[i] = ENum;
            genList[i] = key;

            // If we've processed all positions, print the encrypted values and corresponding keys
            if (i == positions.length - 1) {
                for (int j = 0; j < EPos.length; j++) {
                    // Output the index, key, and encrypted value for each character in the input
                    System.out.println(j + " Keygen: " + genList[j]
                            + "\n" + j + " Enum: " + EPos[j]);
                }
            }
        }
    }
}
