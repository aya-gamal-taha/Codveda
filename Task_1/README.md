# 🎯 Number Guessing Game / لعبة تخمين الأرقام

## 📝 Description / الوصف

**English:**
A fun and interactive console-based number guessing game where players try to guess a randomly generated number between 1 and 100. The game provides intelligent feedback and hints to help players find the correct answer within a limited number of attempts.

**العربية:**
لعبة تفاعلية ممتعة تعتمد على وحدة التحكم حيث يحاول اللاعبون تخمين رقم عشوائي بين 1 و 100. تقدم اللعبة تعليقات ذكية وتلميحات لمساعدة اللاعبين في العثور على الإجابة الصحيحة خلال عدد محدود من المحاولات.

## ✨ Features / المميزات

### English:
- 🎲 **Random Number Generation**: Uses Java's Random class for fair number generation
- 🎯 **Smart Feedback System**: Provides "too high" or "too low" hints
- 🔥 **Proximity Hints**: Advanced hints based on how close your guess is (hot/cold system)
- 📊 **Statistics Tracking**: Tracks games played, won, and win rate
- 🎮 **Multiple Rounds**: Play as many rounds as you want
- ✅ **Input Validation**: Robust error handling for invalid inputs
- 🏆 **Performance Feedback**: Different congratulatory messages based on performance
- 🧹 **Resource Management**: Proper cleanup of system resources

### العربية:
- 🎲 **توليد أرقام عشوائية**: يستخدم فئة Random في Java لتوليد أرقام عادلة
- 🎯 **نظام تعليقات ذكي**: يوفر تلميحات "عالي جداً" أو "منخفض جداً"
- 🔥 **تلميحات القرب**: تلميحات متقدمة بناءً على مدى قرب تخمينك (نظام ساخن/بارد)
- 📊 **تتبع الإحصائيات**: يتتبع الألعاب المُلعبة والمكسوبة ومعدل الفوز
- 🎮 **جولات متعددة**: العب أكبر عدد من الجولات كما تريد
- ✅ **التحقق من الإدخال**: معالجة قوية للأخطاء للمدخلات غير الصالحة
- 🏆 **تعليقات الأداء**: رسائل تهنئة مختلفة بناءً على الأداء
- 🧹 **إدارة الموارد**: تنظيف مناسب لموارد النظام

## 🎮 How to Play / كيفية اللعب

### English:
1. **Start the Game**: Run the program and you'll see a welcome message
2. **Make Your Guess**: Enter a number between 1 and 100
3. **Get Feedback**: The game will tell you if your guess is too high, too low, or correct
4. **Use Hints**: After 3 attempts, you'll get proximity hints (hot/cold)
5. **Win or Lose**: You have 7 attempts to guess the correct number
6. **Play Again**: Choose to play another round or exit
7. **View Statistics**: See your overall performance stats

### العربية:
1. **ابدأ اللعبة**: شغل البرنامج وستظهر لك رسالة ترحيب
2. **اجعل تخمينك**: أدخل رقماً بين 1 و 100
3. **احصل على التعليقات**: ستخبرك اللعبة إذا كان تخمينك عالياً جداً أو منخفضاً جداً أو صحيحاً
4. **استخدم التلميحات**: بعد 3 محاولات، ستحصل على تلميحات القرب (ساخن/بارد)
5. **اربح أو اخسر**: لديك 7 محاولات لتخمين الرقم الصحيح
6. **العب مرة أخرى**: اختر لعب جولة أخرى أو الخروج
7. **اعرض الإحصائيات**: شاهد إحصائيات أدائك العام

## 🚀 Getting Started / البدء

### Prerequisites / المتطلبات المسبقة
- Java Development Kit (JDK) 8 or higher
- Command line terminal or IDE

### Running the Game / تشغيل اللعبة

```bash
# Compile the Java file
javac NumberGuessingGame.java

# Run the game
java NumberGuessingGame
```

## 🏗️ Code Architecture / هيكل الكود

### English:
The game follows **Clean Code principles** with:
- **Single Responsibility**: Each method has one clear purpose
- **Meaningful Names**: Descriptive method and variable names
- **Small Methods**: Functions are kept short and focused
- **Proper Error Handling**: Robust input validation
- **Resource Management**: Proper cleanup of system resources
- **Separation of Concerns**: Display logic separated from game logic

### العربية:
تتبع اللعبة **مبادئ الكود النظيف** مع:
- **المسؤولية الواحدة**: كل دالة لها غرض واضح واحد
- **أسماء ذات معنى**: أسماء وصفية للدوال والمتغيرات
- **دوال صغيرة**: الدوال قصيرة ومركزة
- **معالجة صحيحة للأخطاء**: التحقق القوي من صحة الإدخال
- **إدارة الموارد**: تنظيف مناسب لموارد النظام
- **فصل الاهتمامات**: منطق العرض منفصل عن منطق اللعبة

## 📋 Game Rules / قواعد اللعبة

### English:
- **Range**: Numbers are between 1 and 100 (inclusive)
- **Attempts**: Maximum 7 attempts per round
- **Hints**: Basic high/low hints always provided
- **Proximity Hints**: Available after 3rd attempt
- **Multiple Rounds**: Play as many rounds as desired
- **Statistics**: Win rate calculated and displayed

### العربية:
- **النطاق**: الأرقام بين 1 و 100 (شاملة)
- **المحاولات**: حد أقصى 7 محاولات لكل جولة
- **التلميحات**: تلميحات عالي/منخفض الأساسية متوفرة دائماً
- **تلميحات القرب**: متاحة بعد المحاولة الثالثة
- **جولات متعددة**: العب أكبر عدد من الجولات كما ترغب
- **الإحصائيات**: يتم حساب وعرض معدل الفوز

## 🎯 Performance Ratings / تقييمات الأداء

### English:
- **🏆 AMAZING**: Guess in 1 attempt (Mind reader!)
- **⭐ Excellent**: Guess in 2-3 attempts
- **👍 Good**: Guess in 4-5 attempts  
- **😅 Just in Time**: Guess in 6-7 attempts

### العربية:
- **🏆 مذهل**: التخمين في محاولة واحدة (قارئ أفكار!)
- **⭐ ممتاز**: التخمين في 2-3 محاولات
- **👍 جيد**: التخمين في 4-5 محاولات
- **😅 في الوقت المناسب**: التخمين في 6-7 محاولات

## 🔧 Technical Details / التفاصيل التقنية

### Classes and Methods / الفئات والدوال:
- `NumberGuessingGame`: Main game class
- `startGameSession()`: Manages multiple game rounds
- `playOneRound()`: Handles single game round logic
- `getValidPlayerGuess()`: Input validation and processing
- `provideProximityHint()`: Advanced hint system
- `displayGameStatistics()`: Statistics tracking and display

### Constants / الثوابت:
- `MIN_NUMBER = 1`: Minimum possible number
- `MAX_NUMBER = 100`: Maximum possible number  
- `MAX_ATTEMPTS = 7`: Maximum attempts per round

## 📊 Example Game Session / مثال على جلسة لعب

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

## 🤝 Contributing / المساهمة

### English:
Feel free to contribute to this project by:
- Adding new features
- Improving the user interface
- Enhancing the hint system
- Adding more languages
- Optimizing performance

### العربية:
لا تتردد في المساهمة في هذا المشروع من خلال:
- إضافة ميزات جديدة
- تحسين واجهة المستخدم
- تعزيز نظام التلميحات
- إضافة المزيد من اللغات
- تحسين الأداء

## 📄 License / الترخيص

This project is open source and available under the MIT License.

---

**Enjoy the game! / استمتع باللعبة!** 🎮✨