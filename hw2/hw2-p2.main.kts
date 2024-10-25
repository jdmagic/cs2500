// -----------------------------------------------------------------
// Homework 2, Problem 2
// -----------------------------------------------------------------

// TODO 1/1: Finish designing the function wakeupTime that asks at
//           the console what hour a person likes to wake up in
//           the morning. Depending on their response, your
//           function provides a particular response. You should
//           NOT use reactConsole for this problem.
//
//           To help, you have been supplied a set of tests --
//           uncomment these and once your function passes, you
//           should be in good shape!
//
//           If you are having trouble, try calling your function
//           from main and debug!
//

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.input
import khoury.runEnabledTests
import khoury.testSame

val wakeupPrompt = "What hour in the morning (1-11) do you like to wakeup?"
val wakeupNotANumber = "You did not enter a number :("
val wakeupNotInRange = "You did not enter an hour from 1-11am"
val wakeupEarly = "Before 8am? Early bird catches the worm!"
val wakeupOther = "8am or later? Coffee time!"

// takes an input as a wakeup time, then tries to determine what text to return
// if it fails, returns the text explaining that the input was not a number
fun wakeupTime() {
    println(wakeupPrompt)
    val time = input()
    try {
        val timeInt: Int = time.toInt()
        if ((timeInt < 8) && (timeInt >= 1)) {
            println(wakeupEarly)
        } else if ((timeInt >= 8) && (timeInt <= 11)) {
            println(wakeupOther)
        } else {
            println(wakeupNotInRange)
        }
    } catch (e: Throwable) {
        println(wakeupNotANumber)
    }
}

// testing functions
@EnabledTest
fun testWakeupTime() {
    // helps to test, given what is typed at the console
    // and what the expected output should be
    fun testHelp(
        consoleIn: String,
        expectedOut: String,
    ) {
        testSame(
            captureResults(::wakeupTime, consoleIn),
            CapturedResult(
                Unit,
                wakeupPrompt,
                expectedOut,
            ),
            consoleIn,
        )
    }

    testHelp("howdy", wakeupNotANumber)
    testHelp("0", wakeupNotInRange)
    testHelp("12", wakeupNotInRange)
    testHelp("5", wakeupEarly)
    testHelp("8", wakeupOther)
    testHelp("11", wakeupOther)
}

fun main() {
    wakeupTime()
    runEnabledTests(this)
}

main()
