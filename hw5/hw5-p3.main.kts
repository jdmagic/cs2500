import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Homework 5, Problem 3
// -----------------------------------------------------------------

// In this problem you'll practice designing a data class that has
// methods, making your first upgrade to the project.

// TODO 1/1: Design the data type TaggedFlashCard to represent a
//           single flash card.
//
//           You should be able to represent the text prompt on
//           the front of the card, the text answer on the back,
//           as well as any number of textual tags (such as "hard"
//           or "science" -- this shouldn't come from any fixed
//           set of options, but truly open to however someone
//           wishes to categorize their cards).
//
//           Each card should have two convenience methods:
//           - isTagged, which determines if the card has a
//             supplied tag (e.g., has this card been tagged
//             as "hard"?)
//           - fileFormat, which produces a textual representation
//             of the card as "front|back|tag1,tag2,..."; that is
//             all three parts of the card separated with the pipe
//             ('|') character, and further separate any tags with
//             a comma (',')
//
//           Include *at least* 3 example cards, and make sure to
//           test the methods (in a single testTaggedFlashCard
//           function that has been annotated as @EnabledTest).
//

val sepCard = "|"
val sepTag = ","
// (just useful values for
// the separation characters)

// Represents a single flash card- front, back, and any number of tags
data class TaggedFlashCard(val front: String, val back: String, val tags: List<String>) {
    fun isTagged(tag: String): Boolean {
        return tags.contains(tag)
    }

    fun fileFormat(): String {
        return "$front$sepCard$back$sepCard${tags.joinToString(sepTag)}"
    }
}

val scienceCard = TaggedFlashCard("What is the chemical symbol for gold?", "Au", listOf("hard", "science"))
val mathCard = TaggedFlashCard("What is 5+5?", "10", listOf("easy", "math"))

@EnabledTest
fun testTaggedFlashCard() {
    testSame(scienceCard.isTagged("science"), true, "isTagged")
    testSame(scienceCard.isTagged("math"), false, "wrong tag")
    testSame(mathCard.fileFormat(), "What is 5+5?|10|easy,math", "fileFormat")
}

fun main() {
    runEnabledTests(this)
}

main()
