import khoury.runEnabledTests
import khoury.EnabledTest
import khoury.testSame

// -----------------------------------------------------------------
// Homework 6, Problem 1
// -----------------------------------------------------------------

// In this problem, you'll practice with looping and mutation to
// implement a form of the functional `map` function to actually
// change the elements of a supplied list (instead of returning
// a new list).

// TODO 1/1: Design the function myInplaceMap, which transforms the
//           elements of a supplied list using a supplied function.
//
//           For example, if the function was supplied [1, 2, 3] as
//           a mutable list, as well as a function that doubles a
//           supplied number, the supplied list would be [2, 4, 6]
//           after the function completed (note that the function
//           itself should not return any value).
//
//           Make sure to sufficiently test this function,
//           including empty vs lists that have elements, as well
//           as differing types.
//

// Transforms the elements of a supplied list using a supplied function in place, rather than creating a new list.
// Consequently, does not return anything
fun <T> myInplaceMap(list: MutableList<T>, function: (T) -> T) {
    for (i in list.indices) {
        list[i] = function(list[i])
    }
}

@EnabledTest
fun testMyInplaceMap() {
    val list1 = mutableListOf(1, 2, 3)
    myInplaceMap(list1, { it * 2 })
    testSame(list1, listOf(2, 4, 6), "multiply by two")

    val list2 = mutableListOf(1, 2, 3)
    myInplaceMap(list2, { it + 1})
    testSame(list2, listOf(2, 3, 4), "add one")

    val list3 = mutableListOf("one", "two", "three")
    myInplaceMap(list3, { it + "!"})
    testSame(list3, listOf("one!", "two!", "three!"), "add exclamation")
}

fun main() {
    runEnabledTests(this)
}

main()
