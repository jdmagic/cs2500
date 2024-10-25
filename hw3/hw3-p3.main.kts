// -----------------------------------------------------------------
// Homework 3, Problem 3
// -----------------------------------------------------------------

// TODO 1/1: Design the program showNumbers that uses reactConsole
//           to simply print to the screen all the numbers in a
//           supplied list. For example, running...
//
//           showNumbers(listOf(5, 4, 3, 2, 1))
//
//           should show at the terminal...
//
//
//           5
//
//           4
//
//           3
//
//           2
//
//           1
//
//           Done!
//
//
//           (Note: this assumes the user doesn't type anything
//           between the numbers; even if they did, you should
//           ignore it in the transition function.)
//
//           Remember to start by thinking about your state
//           representation. While there are multiple approaches,
//           here are a couple suggestions:
//
//           1. Represent state as a list; transitioning to the
//              next list then involves list.drop(1) to produce
//              a new list without the first element of the old
//              list; you'll want to check for list.isEmpty()
//
//           2. Design a data class that keeps the supplied list
//              AND the current index (that you can increment
//              until it is no longer valid)
//
//           To help, you have a couple tests, which don't at all
//           depend on how *you* choose to represent state :)
//
//           If you need a refresher on reactConsole, recall that
//           there are a series of videos/files on Canvas walking
//           you through the design process and some example
//           programs :)
//

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.reactConsole
import khoury.runEnabledTests
import khoury.testSame


// data class CountLoopState(val state:List<Int>, val count:Int)

// initial state

// convert a state to text
fun countStateToText(currentState: List<Int>): String {
    if (currentState.size > 0) {
        return(currentState[0].toString())
    } else {
        return("Done!")
    }
}

// given a state, get a new one
fun nextCountState(
    currentState: List<Int>,
    input: String,
): List<Int> {
    return (currentState.drop(1))
}

// determine when state is done
fun isDone(currentState: List<Int>): Boolean {
    return currentState.isEmpty()
}

fun showNumbers(numbersList: List<Int>): Int {
    // call reactConsole
    val numberCount =
        reactConsole(
            initialState = numbersList,
            stateToText = ::countStateToText,
            nextState = ::nextCountState,
            isTerminalState = ::isDone,
        )
    return numberCount.count()
}

@EnabledTest
fun testShowNumbers() {
    // makes a captureResults-friendly function :)
    fun helpTest(numList: List<Int>): () -> Unit {
        fun showMyNumbers() {
            showNumbers(numList)
        }

        return ::showMyNumbers
    }

    testSame(
        captureResults(
            helpTest(emptyList<Int>()),
            "",
        ),
        CapturedResult(
            Unit,
            "Done!",
        ),
        "empty",
    )

    testSame(
        captureResults(
            helpTest(listOf(5, 4, 3, 2, 1)),
            "",
            "",
            "",
            "",
            "",
            "",
        ),
        CapturedResult(
            Unit,
            "5",
            "4",
            "3",
            "2",
            "1",
            "Done!",
        ),
        "5/4/3/2/1",
    )
}

fun main() {
    runEnabledTests(this)
}

main()
