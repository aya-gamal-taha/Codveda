import java.util.Random;
import java.util.Scanner;

/**
 * Number Guessing Game
 * 
 * Game Description:
 * The program generates a random number between 1-100, and the user tries to guess it.
 * 
 * Features:
 * - Random number generation using Java's Random class
 * - Feedback system (too high, too low, correct)
 * - Limited number of attempts (7 attempts)
 * - Input validation and error handling
 * - Option to play multiple rounds
 * 
 * @author Clean Code Version
 * @version 2.0
 */
public class NumberGuessingGame {
    // Game Constants
    private static final int MIN_NUMBER = 1;           // Minimum possible number
    private static final int MAX_NUMBER = 100;         // Maximum possible number
    private static final int MAX_ATTEMPTS = 7;         // Maximum allowed attempts
    
    // Game Tools
    private final Random randomGenerator;              // Random number generator
    private final Scanner inputScanner;                // Input scanner
    
    /**
     * Constructor - initializes required tools for the game
     */
    public NumberGuessingGame() {
        this.randomGenerator = new Random();
        this.inputScanner = new Scanner(System.in);
    }
    
    /**
     * Program entry point
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.startGameSession();
        game.cleanup();
    }
    
    /**
     * Starts the main game session
     * 
     * Manages multiple rounds and displays statistics
     */
    public void startGameSession() {
        // Display game welcome
        displayWelcomeMessage();
        
        // Session tracking variables
        boolean shouldContinuePlaying = true;
        int totalGamesPlayed = 0;
        int totalGamesWon = 0;
        
        // Main game loop
        while (shouldContinuePlaying) {
            totalGamesPlayed++;
            
            // Play one round
            boolean playerWonThisRound = playOneRound();
            
            // Update statistics
            if (playerWonThisRound) {
                totalGamesWon++;
            }
            
            // Display statistics
            displayGameStatistics(totalGamesPlayed, totalGamesWon);
            
            // Ask player to continue
            shouldContinuePlaying = askPlayerToContinue();
        }
        
        // Farewell message
        displayFarewellMessage();
    }
    
    /**
     * Plays one round of the game
     * 
     * @return true if player won, false if player lost
     */
    private boolean playOneRound() {
        // Generate secret number
        int secretNumber = generateRandomNumber();
        
        // Round tracking variables
        int currentAttempt = 0;
        boolean playerHasWon = false;
        
        // Display round instructions
        displayRoundInstructions();
        
        // Attempts loop
        while (currentAttempt < MAX_ATTEMPTS && !playerHasWon) {
            currentAttempt++;
            
            // Display attempt number
            displayAttemptPrompt(currentAttempt);
            
            // Get valid guess
            int playerGuess = getValidPlayerGuess();
            
            // Evaluate guess
            if (playerGuess == secretNumber) {
                // Player won!
                playerHasWon = true;
                displayVictoryMessage(secretNumber, currentAttempt);
                displayPerformanceFeedback(currentAttempt);
                
            } else {
                // Wrong guess - provide hints
                provideFeedbackForWrongGuess(playerGuess, secretNumber, currentAttempt);
                
                // Display remaining attempts
                if (currentAttempt < MAX_ATTEMPTS) {
                    displayRemainingAttempts(currentAttempt);
                }
            }
        }
        
        // If player didn't win
        if (!playerHasWon) {
            displayGameOverMessage(secretNumber);
        }
        
        return playerHasWon;
    }
    
    /**
     * Provides hints to player based on guess proximity
     * 
     * @param playerGuess Player's guess
     * @param secretNumber Secret number
     * @param attemptNumber Attempt number
     */
    private void provideProximityHint(int playerGuess, int secretNumber, int attemptNumber) {
        // Calculate distance between guess and correct number
        int distanceFromTarget = Math.abs(playerGuess - secretNumber);
        
        // Provide hints after 3 attempts
        if (attemptNumber >= 3) {
            // Hints based on proximity
            if (distanceFromTarget <= 5) {
                System.out.println("🔥 Very close! You're burning hot!");
            } else if (distanceFromTarget <= 15) {
                System.out.println("🌡️ Getting warmer!");
            } else if (distanceFromTarget <= 30) {
                System.out.println("❄️ Getting colder...");
            } else {
                System.out.println("🧊 Very cold! Way off!");
            }
        }
    }
    
    /**
     * Gets a valid guess from the player
     * 
     * Validates input and asks for re-entry if invalid
     * 
     * @return Valid number between MIN_NUMBER and MAX_NUMBER
     */
    private int getValidPlayerGuess() {
        while (true) {
            // Read input
            String userInput = inputScanner.nextLine().trim();
            
            // Check for empty input
            if (userInput.isEmpty()) {
                System.out.print("❌ Please enter a number: ");
                continue;
            }
            
            try {
                // Try to convert text to number
                int playerGuess = Integer.parseInt(userInput);
                
                // Check valid range
                if (playerGuess < MIN_NUMBER || playerGuess > MAX_NUMBER) {
                    System.out.printf("❌ Please enter a number between %d and %d: ", 
                                    MIN_NUMBER, MAX_NUMBER);
                    continue;
                }
                
                return playerGuess;
                
            } catch (NumberFormatException e) {
                // Invalid input
                System.out.print("❌ Invalid input! Please enter a valid number: ");
            }
        }
    }
    
    /**
     * Asks player if they want to continue playing
     * 
     * @return true if player wants to continue, false if they want to stop
     */
    private boolean askPlayerToContinue() {
        System.out.print("\n🔄 Would you like to play another round? (y/n): ");
        
        while (true) {
            String userResponse = inputScanner.nextLine().trim().toLowerCase();
            
            // Yes response
            if (userResponse.equals("y") || userResponse.equals("yes")) {
                return true;
            } 
            // No response
            else if (userResponse.equals("n") || userResponse.equals("no")) {
                return false;
            } 
            // Invalid response
            else {
                System.out.print("❌ Please enter 'y' for yes or 'n' for no: ");
            }
        }
    }
    
    // ===============================
    // Helper Methods for Display and Interaction
    // ===============================
    
    /**
     * Generates a random number within the specified range
     * 
     * @return Random number between MIN_NUMBER and MAX_NUMBER
     */
    private int generateRandomNumber() {
        return randomGenerator.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
    }
    
    /**
     * Displays game welcome message
     */
    private void displayWelcomeMessage() {
        System.out.println("🎯 Welcome to the Number Guessing Game! 🎯");
        System.out.println("=".repeat(70));
    }
    
    /**
     * Displays instructions for a new round
     */
    private void displayRoundInstructions() {
        System.out.printf("\n🎲 I'm thinking of a number between %d and %d...\n", 
                         MIN_NUMBER, MAX_NUMBER);
        System.out.printf("You have %d attempts to guess it!\n\n", 
                         MAX_ATTEMPTS);
    }
    
    /**
     * Displays current attempt prompt
     * 
     * @param attemptNumber Current attempt number
     */
    private void displayAttemptPrompt(int attemptNumber) {
        System.out.printf("Attempt %d/%d: ", 
                         attemptNumber, MAX_ATTEMPTS);
    }
    
    /**
     * Displays victory message
     * 
     * @param secretNumber Secret number
     * @param attempts Number of attempts used
     */
    private void displayVictoryMessage(int secretNumber, int attempts) {
        System.out.printf("\n🎉 Congratulations! You guessed it right! 🎉\n");
        System.out.printf("The number was %d and you found it in %d attempt%s!\n", 
                         secretNumber, attempts, attempts == 1 ? "" : "s");
    }
    
    /**
     * Displays performance feedback based on number of attempts
     * 
     * @param attempts Number of attempts used
     */
    private void displayPerformanceFeedback(int attempts) {
        if (attempts == 1) {
            System.out.println("🏆 AMAZING! First try! You're a mind reader! 🔮");
        } else if (attempts <= 3) {
            System.out.println("⭐ Excellent! Very impressive guessing! 👏");
        } else if (attempts <= 5) {
            System.out.println("👍 Good job! Nice guessing skills! 😊");
        } else {
            System.out.println("😅 Phew! You made it just in time! 🕐");
        }
    }
    
    /**
     * Provides feedback for wrong guess
     * 
     * @param playerGuess Player's guess
     * @param secretNumber Secret number
     * @param attemptNumber Attempt number
     */
    private void provideFeedbackForWrongGuess(int playerGuess, int secretNumber, int attemptNumber) {
        if (playerGuess < secretNumber) {
            System.out.println("📈 Too low! Try a higher number.");
        } else {
            System.out.println("📉 Too high! Try a lower number.");
        }
        
        // Provide proximity hints
        provideProximityHint(playerGuess, secretNumber, attemptNumber);
    }
    
    /**
     * Displays remaining attempts
     * 
     * @param currentAttempt Current attempt
     */
    private void displayRemainingAttempts(int currentAttempt) {
        int remaining = MAX_ATTEMPTS - currentAttempt;
        System.out.printf("💡 You have %d attempt%s remaining.\n\n", 
                         remaining, remaining == 1 ? "" : "s");
    }
    
    /**
     * Displays game over message
     * 
     * @param secretNumber Secret number
     */
    private void displayGameOverMessage(int secretNumber) {
        System.out.printf("\n💔 Game Over! You've used all %d attempts.\n", 
                         MAX_ATTEMPTS);
        System.out.printf("The number I was thinking of was: %d\n", 
                         secretNumber);
        System.out.println("🍀 Better luck next time! 🍀");
    }
    
    /**
     * Displays game statistics
     * 
     * @param totalGames Total games
     * @param gamesWon Games won
     */
    private void displayGameStatistics(int totalGames, int gamesWon) {
        double winRate = (double) gamesWon / totalGames * 100;
        System.out.println("\n📊 Game Statistics:");
        System.out.printf("Games Played: %d | Games Won: %d | Win Rate: %.1f%%\n", 
                         totalGames, gamesWon, winRate);
    }
    
    /**
     * Displays farewell message
     */
    private void displayFarewellMessage() {
        System.out.println("\n🎮 Thanks for playing! Goodbye! 👋");
    }
    
    /**
     * Cleanup resources
     */
    private void cleanup() {
        if (inputScanner != null) {
            inputScanner.close();
        }
    }
}
