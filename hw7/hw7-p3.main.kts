import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Homework 7, Problem 3
// -----------------------------------------------------------------

// A useful list abstraction that we haven't yet covered is `zip`
// (via the Kotlin docs): returns a list of pairs built from the
// elements of this list and the other list with the same index;
// the returned list has length of the shortest list.

// In this problem you'll practice using the imperative form of the
// accumulator pattern to implement this useful abstraction!

// TODO 1/1: apply the imperative form of the accumulator pattern
//           to finish designing the function, myZip.
//
//           Notes:
//           - While you'll need a mutable list inside the
//             function, you should return an immutable list.
//           - A Pair is a built-in type in Kotlin; look it up in
//             the docs to understand what it does!
//           - You have been supplied tests that should pass once
//             the function has been completed.
//

// Takes in two lists, returns a list of pairs of elements from the two lists
fun <A, B> myZip(
    listA: List<A>,
    listB: List<B>,
): List<Pair<A, B>> {
    val result = mutableListOf<Pair<A, B>>()
    val minSize = minOf(listA.size, listB.size)
    for (i in 0 until minSize) {
        result.add(Pair(listA[i], listB[i]))
    }
    return result
}

@EnabledTest
fun testMyZip() {
    fun <A, B> helpTest(
        listA: List<A>,
        listB: List<B>,
        desc: String,
    ) {
        testSame(
            myZip(listA, listB),
            listA.zip(listB),
            "$desc: a zip b",
        )

        testSame(
            myZip(listB, listA),
            listB.zip(listA),
            "$desc: b zip a",
        )
    }

    helpTest(
        emptyList<String>(),
        emptyList<Int>(),
        "empty/empty",
    )

    helpTest(
        emptyList<String>(),
        listOf(1, 2),
        "empty/non-empty",
    )

    helpTest(
        listOf(1, 2, 3),
        listOf("a", "b"),
        "mixed",
    )
}

fun main() {
    runEnabledTests(this)
}

main()
