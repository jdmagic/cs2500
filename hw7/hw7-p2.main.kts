import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Homework 7, Problem 2
// -----------------------------------------------------------------

// In this problem you'll implement an algorithm in natural-
// language processing (NLP), which helps you reason about how
// "close" two pieces of text are from one another, by computing
// their "distance" as the minimum number of single-character
// changes (e.g., adding a character, removing one, or
// substituting) required to change one into another.
//
// TODO 1/1: Finish designing the function levenshteinDistance
//           (https://en.wikipedia.org/wiki/Levenshtein_distance),
//           which computes the distance between two strings.
//
//           Notes:
//           - In the Wikipedia article, the "Definition" is really
//             what you want to translate to Kotlin; this is a
//             common task in software development - translating a
//             theoretical/mathematical description of an approach
//             into code for your system. You are encouraged to
//             make your code look as similar as possible to this
//             description!
//           - In class we learned some best practices about the
//             use of recursion (vs iteration); in this case, you
//             can assume we'll apply this to relatively short
//             texts, which makes a recursive approach reasonable.
//           - You have been supplied some tests to help make sure
//             you got it right. Be sure to make sure you
//             understand the metric and tests before you start
//             coding!
//

// recursive levenshtein distance algorithm to figure out the distance between two strings
// as mentioned on Wikipedia and in the instructions, this is probably very inefficient
// one of the more complicated integrations on Wikipedia would be better long term
fun levenshteinDistance(
    t: String,
    s: String,
): Int {
    // if the first string is empty, return length of the second string
    if (t.isEmpty()) {
        return s.length
    }

    // if the second string is empty, return length of the first string
    if (s.isEmpty()) {
        return t.length
    }

    // if the first character of the first string is the same as the first character of the second string, ignore them
    if (t[0] == s[0]) {
        return levenshteinDistance(t.substring(1), s.substring(1))
    }

    // otherwise, the distance is the minimum of:
    // the distance between the first string and the rest of the second string,
    // the distance between the rest of the first string and the second string,
    // and the distance between the rest of the first string and the rest of the second string
    return 1 +
        minOf(
            levenshteinDistance(t, s.substring(1)),
            levenshteinDistance(t.substring(1), s),
            levenshteinDistance(t.substring(1), s.substring(1)),
        )
}

@EnabledTest
fun testLevenshteinDistance() {
    testSame(
        levenshteinDistance("", "howdy"),
        5,
        "'', 'howdy'",
    )

    testSame(
        levenshteinDistance("howdy", ""),
        5,
        "'howdy', ''",
    )

    testSame(
        levenshteinDistance("howdy", "howdy"),
        0,
        "'howdy', 'howdy'",
    )

    testSame(
        levenshteinDistance("kitten", "sitting"),
        3,
        "'kitten', 'sitting'",
    )

    testSame(
        levenshteinDistance("sitting", "kitten"),
        3,
        "'sitting', 'kitten'",
    )
}

fun main() {
    runEnabledTests(this)
}

main()
