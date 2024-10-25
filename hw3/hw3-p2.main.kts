// -----------------------------------------------------------------
// Homework 3, Problem 2
// -----------------------------------------------------------------

// TODO 1/4: Design the data type FlashCard to represent a single
//           flash card. You should be able to represent the text
//           prompt on the front of the card as well as the text
//           answer on the back. Include at least 3 example cards
//           (which will come in handy later for tests!).
//

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// Defining FlashCard data class and define some example cards for testing later
data class FlashCard(val frontPrompt: String, val backAnswer: String)
val exampleCardOne = FlashCard("2+2", "4")
val exampleCardTwo = FlashCard("7*2", "14")
val exampleCardThree = FlashCard("15-8", "7")
val fcEmptyBack = FlashCard("Front", "")
val fcLongBack = FlashCard("Front", "Long answer")

// TODO 2/4: Design the data type Deck to represent a deck of
//           flash cards. The deck should have a name, as well
//           as a sequence of flash cards.
//
//           Include at least 2 example decks based upon the
//           card examples above.
//

// Defining Deck data class and define some example decks for testing later
data class Deck(val name: String, val deckList: List<FlashCard>)
val exampleDeckOne = Deck("deckOne", listOf(exampleCardOne, exampleCardTwo, exampleCardThree))
val exampleDeckTwo = Deck("deckTwo", listOf(exampleCardTwo, exampleCardThree, exampleCardOne))
val exampleDeckProblemEmpty = Deck("deckEmpty", listOf(exampleCardOne, fcEmptyBack))
val exampleDeckProblemLong = Deck("deckLong", listOf(exampleCardTwo, fcLongBack))

// TODO 3/4: Design the predicate areAllOneWordAnswers that
//           determines if the backs of all the cards in a deck
//           are a single word (i.e., have no spaces, which
//           includes a card with a blank back).
//
//           Hint: hidden in the name of this function is a
//                 reminder of a useful list function to use :)
//

// Takes in a deck, checks if all of the cards have one word answers and returns true if so; returns false otherwise
fun areAllOneWordAnswers(specificDeck: Deck): Boolean {
    fun oneWord(card: FlashCard): Boolean {
        return (" " !in card.backAnswer)
    }

    val cardsInDeck: List<FlashCard> = specificDeck.deckList
    return cardsInDeck.all(::oneWord)
}

// TODO 4/4: Design the predicate anyContainsPhrase that determines
//           if any of the cards in a deck contain the supplied
//           phrase.
//
//           Hints:
//           - string1.contains(string2) will be quite useful
//             here :)
//           - Again, the name of this function hints at a useful
//             list function we learned!
//

// Takes in two inputs- a deck and a phrase. Then, checks if any of the cards in the deck contain the phrase (either front or back)
// Returns true if the one of the cards contains the phrase, and false otherwise
fun containsPhrase(
    specificDeck: Deck,
    phrase: String,
): Boolean {
    fun containsPhrase(card: FlashCard): Boolean {
        return (phrase in card.frontPrompt || phrase in card.backAnswer)
    }

    val cardsInDeck: List<FlashCard> = specificDeck.deckList
    return cardsInDeck.any(::containsPhrase)
}

// Testing functions
@EnabledTest
fun testing() {
    testSame(areAllOneWordAnswers(exampleDeckOne), true)
    testSame(areAllOneWordAnswers(exampleDeckProblemLong), false)
    testSame(containsPhrase(exampleDeckOne, "2+2"), true)
    testSame(containsPhrase(exampleDeckProblemLong, "2+2"), false)
    testSame(areAllOneWordAnswers(exampleDeckProblemEmpty), true)
    testSame(containsPhrase(exampleDeckProblemEmpty, "7"), false)
}

fun main() {
    runEnabledTests(this)
}

main()
