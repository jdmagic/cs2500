// -----------------------------------------------------------------
// Homework 2, Problem 1
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// TODO 1/1: Design the predicate startsWithY that determines if
//           the supplied string starts with the letter "y"
//           (either upper or lowercase).
//
//           Hints:
//            - The string.startsWith(prefix) function will help
//              evaluate the prefix (even if the string is too
//              short).
//            - The string.lowercase/uppercase() functions help
//              you not worry about case.
//            - Remember that "designing" a function means to
//              document and test it!
//

// takes a string, converts it to lowercase, and checks if it starts with a lowercase y
fun startsWithY(word: String): Boolean {
    when {
        word.lowercase().startsWith("y") -> return(true)
        else -> return(false)
    }
}

// tests
@EnabledTest
fun testing() {
    testSame((startsWithY("Hello")), false, "Hello")
    testSame((startsWithY("Yahoo")), true, "Yahoo")
    testSame((startsWithY("yes")), true, "yes")
}

fun main() {
    runEnabledTests(this)
    println(startsWithY("Hello"))
    println(startsWithY("Yahoo"))
    println(startsWithY("yes"))
}

main()
