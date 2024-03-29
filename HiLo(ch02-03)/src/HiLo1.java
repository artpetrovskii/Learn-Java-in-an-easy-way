import java.util.Scanner;

public class HiLo1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String playAgain = "";
        do {
            int theNumber = (int)(Math.random() * 201 - 100);
            // System.out.println( theNumber );
            int guess = 0;
            while (guess != theNumber) {
                System.out.println("Guess a number between -100 and 100:");
                guess = scan.nextInt();
                if (guess < theNumber)
                    System.out.println(guess + " is too low. Try again.");
                else if (guess > theNumber)
                    System.out.println(guess + " is too high. Try again.");
                else
                    System.out.println(guess + " is correct. You win!");
            } 
            System.out.println("Would you like to play again (y/n)?");
            playAgain = scan.next();
        } while (playAgain.equalsIgnoreCase("y"));
        System.out.println("Thank you for playing! Goodbye.");
        scan.close();
    }
}
