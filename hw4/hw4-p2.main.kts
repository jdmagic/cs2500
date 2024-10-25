// -----------------------------------------------------------------
// Homework 4, Problem 2
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem we'll practice a more functional approach:
// recursively iterating through self-referential lists...

// data type to contain a self-referential list of integers
sealed class SRListNums {
    // no numbers
    object End : SRListNums()

    // an element, as well as
    // a reference to the rest
    // of the list
    data class Element(
        val num: Int,
        val rest: SRListNums,
    ) : SRListNums()
}

// example of building up [8, 6, 7, 5, 3, 0, 9]
val srEmpty = SRListNums.End
val sr9 = SRListNums.Element(9, srEmpty)
val sr09 = SRListNums.Element(0, sr9)
val sr309 = SRListNums.Element(3, sr09)
val sr5309 = SRListNums.Element(5, sr309)
val sr75309 = SRListNums.Element(7, sr5309)
val sr675309 = SRListNums.Element(6, sr75309)
val sr8675309 = SRListNums.Element(8, sr675309)

// TODO 1/3: Finish designing the function sRListToList, which
//           converts a self-referential list of integers to an
//           equivalent Kotlin list. You have tests to help, and
//           may type-driven development guide you to a well-
//           designed solution :)
//

// converts a self referential list to a regular list using recursion
fun sRListToList(srL: SRListNums): List<Int> {
    return when (srL) {
        is SRListNums.End -> listOf()
        is SRListNums.Element -> listOf(srL.num) + (sRListToList(srL.rest))
    }
}

// testing function
@EnabledTest
fun testSRListToList() {
    testSame(
        sRListToList(srEmpty),
        emptyList<Int>(),
        "empty",
    )

    testSame(
        sRListToList(sr9),
        listOf(9),
        "[9]",
    )

    testSame(
        sRListToList(sr09),
        listOf(0, 9),
        "[0, 9]",
    )

    testSame(
        sRListToList(sr8675309),
        listOf(8, 6, 7, 5, 3, 0, 9),
        "jenny",
    )
}

// TODO 2/3: Now try the opposite - listToSRList.
//
//           Hint: think about one element of the Kotlin list
//                 at a time, from left-to-right; if you had
//                 nothing in the list, what would that be?
//                 otherwise, how could you then make a self-
//                 referential list recursively?
//

// converts a regular list to a self referential list using recursion
fun listToSRList(list: List<Int>): SRListNums {
    return when (list.isEmpty()) {
        true -> SRListNums.End
        false -> SRListNums.Element(list[0], listToSRList(list.drop(1)))
    }
}

// more tests
@EnabledTest
fun testListToSRList() {
    testSame(
        listToSRList(emptyList<Int>()),
        srEmpty,
        "empty",
    )

    testSame(
        listToSRList(listOf(9)),
        sr9,
        "[9]",
    )

    testSame(
        listToSRList(listOf(0, 9)),
        sr09,
        "[0, 9]",
    )

    testSame(
        listToSRList(listOf(8, 6, 7, 5, 3, 0, 9)),
        sr8675309,
        "jenny",
    )
}

// TODO 3/3: Lastly, finish designing filterSRList as a way to do
//           what filter does for Kotlin lists. Let type-driven
//           programming be your guide, and you have tests
//           for clarity!
//
//           Note: you should NOT use the conversion functions
//                 above just to use Kotlin's filter - try to do
//                 it recursively!
//

// uses recursion to filter a self referential list
fun filterSRList(
    srL: SRListNums,
    filterFunction: (Int) -> Boolean,
): SRListNums {
    return when (srL) {
        is SRListNums.End -> SRListNums.End
        is SRListNums.Element -> {
            if (filterFunction(srL.num)) {
                SRListNums.Element(srL.num, filterSRList(srL.rest, filterFunction))
            } else {
                filterSRList(srL.rest, filterFunction)
            }
        }
    }
}

// even more tests..
@EnabledTest
fun testFilterSRList() {
    // is a supplied number even?
    fun isEven(num: Int): Boolean {
        return num % 2 == 0
    }

    // is a supplied number non-positive?
    fun isNonPositive(num: Int): Boolean {
        return num <= 0
    }

    testSame(
        filterSRList(srEmpty, ::isEven),
        srEmpty,
        "empty",
    )

    testSame(
        filterSRList(sr8675309, ::isEven),
        SRListNums.Element(
            8,
            SRListNums.Element(
                6,
                SRListNums.Element(
                    0,
                    SRListNums.End,
                ),
            ),
        ),
        "even",
    )

    testSame(
        filterSRList(sr8675309, ::isNonPositive),
        SRListNums.Element(
            0,
            SRListNums.End,
        ),
        "non-positive",
    )
}

// main function
fun main() {
    runEnabledTests(this)
}

main()
