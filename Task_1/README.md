# 🎯 Number Guessing Game 
## 📝 Description 

A fun and interactive console-based number guessing game where players try to guess a randomly generated number between 1 and 100. The game provides intelligent feedback and hints to help players find the correct answer within a limited number of attempts.

## ✨ Features 

- 🎲 **Random Number Generation**: Uses Java's Random class for fair number generation
- 🎯 **Smart Feedback System**: Provides "too high" or "too low" hints
- 🔥 **Proximity Hints**: Advanced hints based on how close your guess is (hot/cold system)
- 📊 **Statistics Tracking**: Tracks games played, won, and win rate
- 🎮 **Multiple Rounds**: Play as many rounds as you want
- ✅ **Input Validation**: Robust error handling for invalid inputs
- 🏆 **Performance Feedback**: Different congratulatory messages based on performance
- 🧹 **Resource Management**: Proper cleanup of system resources

## 🎮 How to Play 

1. **Start the Game**: Run the program and you'll see a welcome message
2. **Make Your Guess**: Enter a number between 1 and 100
3. **Get Feedback**: The game will tell you if your guess is too high, too low, or correct
4. **Use Hints**: After 3 attempts, you'll get proximity hints (hot/cold)
5. **Win or Lose**: You have 7 attempts to guess the correct number
6. **Play Again**: Choose to play another round or exit
7. **View Statistics**: See your overall performance stats


## 🚀 Getting Started 

### Prerequisites 
- Java Development Kit (JDK) 8 or higher
- Command line terminal or IDE

### Running the Game 

```bash
# Compile the Java file
javac NumberGuessingGame.java

# Run the game
java NumberGuessingGame
```

## 🏗️ Code Architecture 

The game follows **Clean Code principles** with:
- **Single Responsibility**: Each method has one clear purpose
- **Meaningful Names**: Descriptive method and variable names
- **Small Methods**: Functions are kept short and focused
- **Proper Error Handling**: Robust input validation
- **Resource Management**: Proper cleanup of system resources
- **Separation of Concerns**: Display logic separated from game logic

## 📋 Game Rules 

- **Range**: Numbers are between 1 and 100 (inclusive)
- **Attempts**: Maximum 7 attempts per round
- **Hints**: Basic high/low hints always provided
- **Proximity Hints**: Available after 3rd attempt
- **Multiple Rounds**: Play as many rounds as desired
- **Statistics**: Win rate calculated and displayed

## 🎯 Performance Ratings 

- **🏆 AMAZING**: Guess in 1 attempt (Mind reader!)
- **⭐ Excellent**: Guess in 2-3 attempts
- **👍 Good**: Guess in 4-5 attempts  
- **😅 Just in Time**: Guess in 6-7 attempts


## 🔧 Technical Details 

### Classes and Methods 
- `NumberGuessingGame`: Main game class
- `startGameSession()`: Manages multiple game rounds
- `playOneRound()`: Handles single game round logic
- `getValidPlayerGuess()`: Input validation and processing
- `provideProximityHint()`: Advanced hint system
- `displayGameStatistics()`: Statistics tracking and display

### Constants 
- `MIN_NUMBER = 1`: Minimum possible number
- `MAX_NUMBER = 100`: Maximum possible number  
- `MAX_ATTEMPTS = 7`: Maximum attempts per round

## 📊 Example Game Session 

```
🎯 Welcome to the Number Guessing Game! 🎯
======================================================================

🎲 I'm thinking of a number between 1 and 100...
You have 7 attempts to guess it!

Attempt 1/7: 50
📈 Too low! Try a higher number.
💡 You have 6 attempts remaining.

Attempt 2/7: 75
📉 Too high! Try a lower number.
💡 You have 5 attempts remaining.

Attempt 3/7: 65
📈 Too low! Try a higher number.
🌡️ Getting warmer!
💡 You have 4 attempts remaining.

Attempt 4/7: 70
🎉 Congratulations! You guessed it right! 🎉
The number was 70 and you found it in 4 attempts!
👍 Good job! Nice guessing skills! 😊

📊 Game Statistics:
Games Played: 1 | Games Won: 1 | Win Rate: 100.0%

🔄 Would you like to play another round? (y/n):
```

## 🤝 Contributing 

Feel free to contribute to this project by:
- Adding new features
- Improving the user interface
- Enhancing the hint system
- Adding more languages
- Optimizing performance


## 📄 License 

This project is open source and available under the MIT License.

---

**Enjoy the game!** 🎮✨
