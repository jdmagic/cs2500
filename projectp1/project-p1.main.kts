import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.fileReadAsList
import khoury.isAnInteger
import khoury.linesToString
import khoury.reactConsole
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Project: Part 1, Summary
// -----------------------------------------------------------------

// You are going to design an application to allow a user to
// self-study using flash cards. In this part of the project,
// a user will...

// 1. Be prompted to choose from a menu of available flash
//    card decks; this menu will repeat until a valid
//    selection is made.
//
// 2. Proceed through each card in the selected deck,
//    one-by-one. For each card, the front is displayed,
//    and the user is allowed time to reflect; then the
//    back is displayed; and the user is asked if they
//    got the correct answer.
//
// 3. Once the deck is exhausted, the program outputs the
//    number of self-reported correct answers and ends.
//

// Of course, we'll design this program step-by-step, AND
// you've already done pieces of this in homework!!
// (Note: you are welcome to leverage your prior work and/or
// code found in the sample solutions & lecture notes.)
//

// Lastly, here are a few overall project requirements...
// - Since mutation hasn't been covered in class, your design is
//   NOT allowed to make use of mutable variables and/or lists.
// - As included in the instructions, all interactive parts of
//   this program MUST make effective use of the reactConsole
//   framework.
// - Staying consistent with our Style Guide...
//   * All functions must have:
//     a) a preceding comment specifying what it does
//     b) an associated @EnabledTest function with sufficient
//        tests using testSame
//   * All data must have:
//     a) a preceding comment specifying what it represents
//     b) associated representative examples
// - You will be evaluated on a number of criteria, including...
//   * Adherence to instructions and the Style Guide
//   * Correctly producing the functionality of the program
//   * Design decisions that include choice of tests, appropriate
//     application of list abstractions, and task/type-driven
//     decomposition of functions.
//

// -----------------------------------------------------------------
// Data design
// (Hint: see Homework 3, Problem 2)
// -----------------------------------------------------------------

// TODO 1/2: Design the data type FlashCard to represent a single
//           flash card. You should be able to represent the text
//           prompt on the front of the card as well as the text
//           answer on the back. Include at least 3 example cards
//           (which will come in handy later for tests!).
//

// data class to represent a single flash card
data class FlashCard(val front: String, val back: String)

// example flash cards
val cardH = FlashCard("Hydrogen", "H")
val cardHe = FlashCard("Helium", "He")
val cardLi = FlashCard("Lithium", "Li")

// TODO 2/2: Design the data type Deck to represent a deck of
//           flash cards. The deck should have a name, as well
//           as a Kotlin list of flash cards.
//
//           Include at least 2 example decks based upon the
//           card examples above.
//

// data class to represent a single deck
data class Deck(val name: String, val cards: List<FlashCard>)

// example deck
val deckChemicalSymbols = Deck("Chemical Symbols", listOf(cardH, cardHe, cardLi))

// -----------------------------------------------------------------
// Generating flash cards
// -----------------------------------------------------------------

// One benefit of digital flash cards is that sometimes we can
// use code to produce cards that match a known pattern without
// having to write all the fronts/backs by hand!
//

// TODO 1/1: Design the function perfectSquares that takes a
//           count (assumed to be positive) and produces the
//           list of flash cards that tests that number of the
//           first squares.
//
//           For example, the first three perfect squares...
//
//            1. front (1^2 = ?), back (1)
//            2. front (2^2 = ?), back (4)
//            3. front (3^2 = ?), back (9)
//
//           have been supplied as named values.
//
//           Hint: you might consider combining your
//                 kthPerfectSquare function from Homework 1
//                 with the list constructor in Homework 3.
//

val square1Front = "1^2 = ?"
val square2Front = "2^2 = ?"
val square3Front = "3^2 = ?"

val square1Back = "1"
val square2Back = "4"
val square3Back = "9"

val square1 = FlashCard(square1Front, square1Back)
val square2 = FlashCard(square2Front, square2Back)
val square3 = FlashCard(square3Front, square3Back)

// returns the corresponding perfect square
fun kthPerfectSquare(k: Int): Int {
    val kplus1 = k
    return kplus1 * kplus1
}

@EnabledTest
fun testKthPerfectSquare() {
    testSame(kthPerfectSquare(1), 1, "one")
    testSame(kthPerfectSquare(2), 4, "two")
    testSame(kthPerfectSquare(3), 9, "three")
}

// generates a list of cards, where each card is a perfect square
fun perfectSquares(count: Int): List<FlashCard> {
    return List(count) { index -> FlashCard("${index + 1}^2 = ?", "${kthPerfectSquare(index + 1)}") }
}

@EnabledTest
fun testPerfectSquares() {
    testSame(perfectSquares(0), listOf(), "zero")
    testSame(perfectSquares(1), listOf(square1), "one")
    testSame(perfectSquares(2), listOf(square1, square2), "two")
    testSame(perfectSquares(3), listOf(square1, square2, square3), "three")
}

val deckPerfectSquares = Deck("Perfect Squares", perfectSquares(5))

// -----------------------------------------------------------------
// Files of cards
// -----------------------------------------------------------------

// Consider a simple format for storing flash cards in a file:
// each card is a line in the file, where the front comes first,
// separated by a "pipe" character ('|'), followed by the text
// on the back of the card.
//

val charSep = "|"

// TODO 1/3: Design the function cardToString that takes a flash
//           card as input and produces a string according to the
//           specification above ("front|back"). Make sure to
//           test all your card examples!
//

// converts a card to a string, adding the seperator
fun cardToString(card: FlashCard): String {
    return card.front + charSep + card.back
}

@EnabledTest
fun testCardToString() {
    testSame(cardToString(cardH), "Hydrogen|H", "Hydrogen")
    testSame(cardToString(cardHe), "Helium|He", "Helium")
    testSame(cardToString(cardLi), "Lithium|Li", "Lithium")
}

// TODO 2/3: Design the function stringToCard that takes a string,
//           assumed to be in the format described above, and
//           produces the corresponding flash card.
//
//           Hints:
//           - look back to how we extracted data from CSV
//             (comma-separated value) files (such as in
//             Homework 3)!
//           - a great way to test: for each of your card
//             examples, pass them through the function in TODO
//             1 to convert them to a string; then, pass that
//             result to this function... you *should* get your
//             original flash card back :)
//

// converts a string to a card, using the seperator to split front and back
fun stringToCard(str: String): FlashCard {
    val split = str.split(charSep)
    return FlashCard(split[0], split[1])
}

@EnabledTest
fun testStringToCard() {
    testSame(stringToCard("Hydrogen|H"), cardH, "Hydrogen")
    testSame(stringToCard("Helium|He"), cardHe, "Helium")
    testSame(stringToCard("Lithium|Li"), cardLi, "Lithium")
}

// TODO 3/3: Design the function readCardsFile that takes a path
//           to a file and produces the corresponding list of
//           flash cards found in the file.
//
//           If the file does not exist, return an empty list.
//           Otherwise, you can assume that every line is
//           formatted in the string format we just worked with.
//
//           Hint:
//           - Think about how HW3-P1 effectively used an
//             abstraction to process all the lines in a
//             file assuming a known pattern.
//           - We've provided an "example.txt" file that you can
//             use for testing if you'd like; also make sure to
//             test your function when the supplied file does not
//             exist!
//

// reads a file of cards, returning a list of cards
fun readCardsFile(path: String): List<FlashCard> {
    val lines = fileReadAsList(path)
    return lines.map(::stringToCard)
}

val deckCapitals = Deck("Capitals", readCardsFile("capitals.txt"))

@EnabledTest
fun testReadCardsFile() {
    testSame(readCardsFile("example.txt"), listOf(FlashCard("front 1", "back 1"), FlashCard("front 2", "back 2")), "example")
    testSame(readCardsFile("capitals.txt"), deckCapitals.cards, "capitals")
    testSame(readCardsFile("invalidfile.txt"), listOf(), "invalid file")
}

// -----------------------------------------------------------------
// Processing a self-report
// (Hint: see Homework 2)
// -----------------------------------------------------------------

// In our program, we will ask for a self-report as to whether
// the user got the correct answer for a card, SO...

// TODO 1/1: Finish designing the function isPositive that
//           determines if the supplied string starts with
//           the letter "y" (either upper or lowercase).
//
//           You've been supplied with a number of tests - make
//           sure you understand what they are doing!
//

// determines if a string starts with "y", ignoring case
fun isPositive(str: String): Boolean {
    return str.lowercase().startsWith("y")
}

@EnabledTest
fun testIsPositive() {
    fun helpTest(
        str: String,
        expected: Boolean,
    ) {
        testSame(isPositive(str), expected, str)
    }

    helpTest("yes", true)
    helpTest("Yes", true)
    helpTest("YES", true)
    helpTest("yup", true)

    helpTest("nope", false)
    helpTest("NO", false)
    helpTest("nah", false)
    helpTest("not a chance", false)

    // should pass,
    // despite doing the wrong thing
    helpTest("indeed", false)
}

// -----------------------------------------------------------------
// Choosing a deck from a menu
// -----------------------------------------------------------------

// Now let's work on providing a menu of decks from which a user
// can choose what they want to study.

// TODO 1/2: Finish design the function choicesToText that takes
//           a list of strings (assumed to be non-empty) and
//           produces the textual representation of a menu of
//           those options.
//
//           For example, given...
//
//           ["a", "b", "c"]
//
//           The menu would be...
//
//           "1. a
//            2. b
//            3. c
//
//            Enter your choice"
//
//            As you have probably guessed, this will be a key
//            piece of our rendering function :)
//
//            Hints:
//            - Think back to Homework 3 when we used a list
//              constructor to generate list elements based
//              upon an index.
//            - If you can produce a list of strings, the
//              linesToString function in the Khoury library
//              will bring them together into a single string.
//            - Make sure to understand the supplied tests!
//

val promptMenu = "Enter your choice"

// converts a list of choices to a menu
fun choicesToText(choices: List<String>): String {
    val numberedChoices = choices.mapIndexed { index, choice -> "${index + 1}. $choice" }
    return linesToString(numberedChoices + "" + promptMenu)
}

@EnabledTest
fun testChoicesToText() {
    val optA = "apple"
    val optB = "banana"
    val optC = "carrot"

    testSame(
        choicesToText(listOf(optA)),
        linesToString(
            "1. $optA",
            "",
            promptMenu,
        ),
        "one",
    )

    testSame(
        choicesToText(listOf(optA, optB, optC)),
        linesToString(
            "1. $optA",
            "2. $optB",
            "3. $optC",
            "",
            promptMenu,
        ),
        "three",
    )
}

// TODO 2/2: Finish designing the program chooseOption that takes
//           a list of decks, produces a corresponding numbered
//           menu (1-# of decks, each showing its name), and
//           returns the deck corresponding to the number entered.
//           (Of course, keep displaying the menu until a valid
//           number is entered.)
//
//           Hints:
//            - Review the "Valid Number Example" of reactConsole
//              as one example of how to validate input. In this
//              case, however, since we know that we have a valid
//              range of integers, we can simplify the state
//              representation significantly :)
//            - To help you get started, the chooseOption function
//              has been written, but you must complete the helper
//              functions; look to the comments below for guidance.
//              You can then play "signature detective" to figure
//              out the parameters/return type of the functions you
//              need to write :)
//            - Lastly, as always, don't forget to sufficiently
//              test all the functions you write in this problem!
//

// Takes in a deck and returns the name of the deck
fun getDeckName(deck: Deck): String {
    return deck.name
}

@EnabledTest
fun testGetDeckName() {
    testSame(getDeckName(deckChemicalSymbols), "Chemical Symbols", "Chemical Symbols")
    testSame(getDeckName(deckPerfectSquares), "Perfect Squares", "Perfect Squares")
}

// Takes in a string and a range of valid indices and returns the string as an integer if it is valid
fun keepIfValid(
    typedInput: String,
    validIndices: IntRange,
): Int {
    if (!isAnInteger(typedInput)) {
        return -1
    } else if ((typedInput.toInt() - 1) !in validIndices) {
        return -1
    } else {
        return typedInput.toInt() - 1
    }
}

@EnabledTest
fun keepIfValidTest() {
    testSame(keepIfValid("1", 0..2), 0, "valid")
    testSame(keepIfValid("2", 0..2), 1, "valid")
    testSame(keepIfValid("3", 0..2), 2, "valid")
    testSame(keepIfValid("4", 0..2), -1, "exceeds range")
    testSame(keepIfValid("0", 0..2), -1, "0")
    testSame(keepIfValid("a", 0..2), -1, "invalid input")
    testSame(keepIfValid("", 0..2), -1, "empty string")
}

// Takes in the name of the deck and tells the user that they chose that deck
fun choiceAnnouncement(deckName: String): String {
    return "you chose: $deckName"
}

@EnabledTest
fun testChoiceAnnouncement() {
    testSame(choiceAnnouncement("Chemical Symbols"), "you chose: Chemical Symbols", "Chemical Symbols")
    testSame(choiceAnnouncement("Perfect Squares"), "you chose: Perfect Squares", "Perfect Squares")
}

// a program to allow the user to interactively select
// a deck from the supplied, non-empty list of decks
fun chooseOption(decks: List<Deck>): Deck {
    // since the event handlers will need some info about
    // the supplied decks, the functions inside
    // chooseOption provide info about them while the
    // parameter is in scope

    // TODO: Above chooseOption, design the function
    //       getDeckName, which returns the name of
    //       a supplied deck.
    fun renderDeckOptions(state: Int): String {
        return choicesToText(decks.map(::getDeckName))
    }

//     // TODO: Above chooseOption, design the function
//     //       keepIfValid, that takes the typed input
//     //       as a string, as well as the valid
//     //       indices of the decks; note that the list indices
//     //       will be in the range [0, size), whereas the
//     //       user will see and work with [1, size].
//     //
//     //       If the user did not type a valid integer,
//     //       or not one in [1, size], return -1; otherwise
//     //       return the string converted to an integer, but
//     //       subtract 1, which makes it a valid list index.

    fun transitionOptionChoice(
        ignoredState: Int,
        kbInput: String,
    ): Int {
        return keepIfValid(kbInput, decks.indices)
    }

//     // TODO: nothing, but understand this :)
    fun validChoiceEntered(state: Int): Boolean {
        return state in decks.indices
    }

//     // TODO: Above chooseOption, design the function
//     //       choiceAnnouncement that takes the selected
//     //       deck name and returns an announcement that
//     //       makes you happy. For a simple example, given
//     //       "fundies" as the chosen deck name, you might
//     //       return "you chose: fundies"
    fun renderChoice(state: Int): String {
        return choiceAnnouncement(getDeckName(decks[state]))
    }

    return decks[
        reactConsole(
            initialState = -1,
            stateToText = ::renderDeckOptions,
            nextState = ::transitionOptionChoice,
            isTerminalState = ::validChoiceEntered,
            terminalStateToText = ::renderChoice,
        ),
    ]
}

@EnabledTest
fun testChooseOption() {
    fun helpTest(decks: List<Deck>): () -> Deck {
        fun chooseThisOption(): Deck {
            return chooseOption(decks)
        }

        return ::chooseThisOption
    }

    testSame(
        captureResults(
            helpTest(
                listOf(
                    deckCapitals,
                    deckPerfectSquares,
                    deckChemicalSymbols,
                ),
            ),
            "1",
        ),
        CapturedResult(
            deckCapitals,
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "you chose: Capitals",
        ),
        "Choice 1",
    )
    testSame(
        captureResults(
            helpTest(
                listOf(
                    deckCapitals,
                    deckPerfectSquares,
                    deckChemicalSymbols,
                ),
            ),
            "invalidstring",
            "1",
        ),
        CapturedResult(
            deckCapitals,
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "you chose: Capitals",
        ),
        "Invalid Input",
    )
    testSame(
        captureResults(
            helpTest(
                listOf(
                    deckCapitals,
                    deckPerfectSquares,
                    deckChemicalSymbols,
                ),
            ),
            "5",
            "2",
        ),
        CapturedResult(
            deckPerfectSquares,
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "you chose: Perfect Squares",
        ),
        "Number Out of Range, then Choice 2",
    )
    testSame(
        captureResults(
            helpTest(
                listOf(
                    deckCapitals,
                    deckPerfectSquares,
                    deckChemicalSymbols,
                ),
            ),
            "",
            "3",
        ),
        CapturedResult(
            deckChemicalSymbols,
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "you chose: Chemical Symbols",
        ),
        "Empty Input, then Choice 3",
    )
}

// -----------------------------------------------------------------
// Studying a deck
// -----------------------------------------------------------------

// Now let's design a program to allow a user to study through a
// supplied deck of flash cards.

// TODO 1/2: Design the data type StudyState to keep track of...
//           - which card you are currently studying in the deck
//           - are you looking at the front or back
//           - how many correct answers have been self-reported
//             thus far
//
//           Create sufficient examples so that you convince
//           yourself that you can represent any situation that
//           might arise when studying a deck.
//
//           Hints:
//           - Look back to the reactConsole problems in HW2 and
//             HW3; the former involved keeping track of a count
//             of loops (similar to the count of correct answers),
//             and the latter involved options for keeping track
//             of where you are in a list with reactConsole.
//

// data class to represent the state of studying a deck
data class StudyState(val cards: List<FlashCard>, val isFront: Boolean, val correctAnswers: Int)

// TODO 2/2: Now, using reactConsole, design the program studyDeck
//           that for each card in a supplied deck, allows the
//           user to...
//
//           1. see the front (pause and think)
//           2. see the back
//           3. respond as to whether they got the answer
//
//           At the end, the user is told how many they self-
//           reported as correct (and this number is returned).
//
//           You have been supplied some prompts for steps #1
//           and #2 - feel free to change them if you'd like :)
//
//           Suggestions...
//           - Review the reactConsole videos/examples
//           - Start with studyDeck:
//             * write some tests to convince yourself you know
//               what your program is supposed to do!
//             * figure out how you'll create the initial state
//             * give names to the handlers you'll need
//             * how will you return the number correct?
//             * now comment-out this function, so that you can
//               design/test the handlers without interference :)
//           - For each handler...
//             * Play signature detective: based upon how it's
//               being used with reactConsole, what data will it
//               be given and what does it produce?
//             * Write some tests to convince yourself you know
//               its job.
//             * Write the code and don't move on till your tests
//               pass.
//            - Suggested ordering...
//              1. Am I done studying yet?
//              2. Rendering
//                 - It's a bit simpler to have a separate
//                   function for the terminal state.
//                 - The linesToString function is your friend to
//                   combine the card with the prompts.
//                 - Think about good decomposition when making
//                   the decision about front vs back content.
//              3. Transition
//                 - Start with the two main situations
//                   you'll find yourself in...
//                   > front->back
//                   > back->front
//                 - Then let a helper figure out how to handle
//                   the details of self-report
//
//            You've got this :-)
//

fun studyDeck(deck: Deck): Int {
    fun renderState(state: StudyState): String {
        if (state.cards.size == 0) {
            return "You got ${state.correctAnswers} correct"
        } else if (state.isFront) {
            return linesToString(state.cards[0].front, studyThink)
        } else {
            return linesToString(state.cards[0].back, studyCheck)
        }
    }

    fun transitionState(
        state: StudyState,
        typedInput: String,
    ): StudyState {
        if (state.isFront || typedInput == "") {
            return StudyState(state.cards, false, state.correctAnswers)
        } else if (isPositive(typedInput)) {
            return StudyState(state.cards.drop(1), true, state.correctAnswers + 1)
        } else {
            return StudyState(state.cards.drop(1), true, state.correctAnswers)
        }
    }

    fun isDone(state: StudyState): Boolean {
        return state.cards.size == 0
    }

    return reactConsole(
        initialState = StudyState(deck.cards, true, 0),
        stateToText = ::renderState,
        nextState = ::transitionState,
        isTerminalState = ::isDone,
    ).correctAnswers
}

@EnabledTest
fun testStudyDeck() {
    fun helpTest(deck: Deck): () -> Int {
        fun studyThisDeck(): Int {
            return studyDeck(deck)
        }

        return ::studyThisDeck
    }

    testSame(
        captureResults(
            helpTest(
                deckChemicalSymbols,
            ),
            "",
            "y",
            "",
            "y",
            "",
            "y",
        ),
        CapturedResult(
            3,
            "Hydrogen",
            studyThink,
            "H",
            studyCheck,
            "Helium",
            studyThink,
            "He",
            studyCheck,
            "Lithium",
            studyThink,
            "Li",
            studyCheck,
            "You got 3 correct",
        ),
        "All correct",
    )
    testSame(
        captureResults(
            helpTest(
                deckChemicalSymbols,
            ),
            "",
            "y",
            "",
            "n",
            "",
            "n",
        ),
        CapturedResult(
            1,
            "Hydrogen",
            studyThink,
            "H",
            studyCheck,
            "Helium",
            studyThink,
            "He",
            studyCheck,
            "Lithium",
            studyThink,
            "Li",
            studyCheck,
            "You got 1 correct",
        ),
        "One correct",
    )
    testSame(
        captureResults(
            helpTest(
                deckChemicalSymbols,
            ),
            "",
            "y",
            "",
            "n",
            "",
            "",
            "y",
        ),
        CapturedResult(
            2,
            "Hydrogen",
            studyThink,
            "H",
            studyCheck,
            "Helium",
            studyThink,
            "He",
            studyCheck,
            "Lithium",
            studyThink,
            "Li",
            studyCheck,
            "Li",
            studyCheck,
            "You got 2 correct",
        ),
        "Two correct, and repeated empty input",
    )
}

val studyThink = "Think of the result? Press enter to continue"
val studyCheck = "Correct? (Y)es/(N)o"

// -----------------------------------------------------------------
// Final app!
// -----------------------------------------------------------------

// Now you just get to put this all together 💃

// TODO 1/1: Design the function chooseAndStudy, where you'll
//           follow the comments in the supplied code to leverage
//           your prior work to allow the user to choose a deck,
//           study it, and return the number of correct self-
//           reports.
//
//           Your deck options MUST include at least one from each
//           of the following categories...
//
//           - Coded by hand (such as an example in data design)
//           - Read from a file (ala readCardsFile)
//           - Generated by code (ala perfectSquares)
//
//           Note: while this is an interactive program, you won't
//                 directly use reactConsole - instead, just call
//                 the programs you already designed above :)
//
//           And of course, don't forget to test at least two runs
//           of this completed program!
//
//           (And, consider adding this to main so you can see the
//           results of all your hard work so far this semester!)
//

// // lets the user choose a deck and study it,
// // returning the number self-reported correct
fun chooseAndStudy(): Int {
    // 1. Construct a list of options
    // (ala the instructions above)
    val deckOptions =
        listOf(
            // TODO: at least...
            // deck from file via readCardsFile,
            deckCapitals,
            // deck from code via perfectSquares
            deckPerfectSquares,
            // deck hand-coded
            deckChemicalSymbols,
        )

    // 2. Use chooseOption to let the user
    //    select a deck
    val deckChosen = chooseOption(deckOptions)

    // 3. Let the user study, return the
    //    number correctly answered
    return studyDeck(deckChosen)
}

@EnabledTest
fun testChooseAndStudy() {
    testSame(
        captureResults(::chooseAndStudy, "3", "", "y", "", "n", "", "n"),
        CapturedResult(
            1,
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "you chose: Chemical Symbols",
            "Hydrogen",
            studyThink,
            "H",
            studyCheck,
            "Helium",
            studyThink,
            "He",
            studyCheck,
            "Lithium",
            studyThink,
            "Li",
            studyCheck,
            "You got 1 correct",
        ),
    )
    testSame(
        captureResults(::chooseAndStudy, "2", "", "y", "", "n", "", "n", "", "y", "", "y"),
        CapturedResult(
            3,
            "1. Capitals",
            "2. Perfect Squares",
            "3. Chemical Symbols",
            "",
            "Enter your choice",
            "you chose: Perfect Squares",
            "1^2 = ?",
            studyThink,
            "1",
            studyCheck,
            "2^2 = ?",
            studyThink,
            "4",
            studyCheck,
            "3^2 = ?",
            studyThink,
            "9",
            studyCheck,
            "4^2 = ?",
            studyThink,
            "16",
            studyCheck,
            "5^2 = ?",
            studyThink,
            "25",
            studyCheck,
            "You got 3 correct",
        ),
    )
}

// -----------------------------------------------------------------

fun main() {
    chooseAndStudy()
}

runEnabledTests(this)
main()
