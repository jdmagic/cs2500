import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// -----------------------------------------------------------------
// Homework 7, Problem 1
// -----------------------------------------------------------------

// In this problem, you'll practice applying list abstractions in
// order to design a useful algorithm, topK!

// When working with data, it is often helpful to be able to get
// the "best" set of data points, by some measure. For example...
//
// a) The single longest string in a list of strings
// b) The 2 smallest numbers in a list of integers
// c) The 5 points that are closest to the y-axis
//    (i.e., the absolute value of their x-coordinateag
//    is smallest).
//
// To start, consider the following definition of an "evaluation"
// function: one that takes an input of some type and associates
// an output "score" (where bigger scores are understood to be
// better for the task at hand):

// a way to "score" a particular type of data
typealias EvaluationFunction<T> = (T) -> Int

// TODO 1/1: Design the function topK that takes a list of
//           items, a corresponding evaluation function, and k
//           (assumed to be a postive integer), and then returns
//           the k items in the list that get the highest score
//           (if there are ties, you are free to return any of the
//           winners; if there aren't enough items in the list,
//           return as many as you can).
//
//           To help...
//           -  Your tests must cover the three examples above,
//              each time covering the empty list, a list whose
//              size is at larger than the indicated k, and a
//              non-empty list whose size is smaller than k (this
//              last test isn't necessary for (a)). Here's a data
//              type for example (c):

// A two-dimensional point
data class Point2D(val x: Int, val y: Int) {
    // distance to the y-axis
    fun distToYAxis(): Int = if (x > 0) x else -x
}

val p2DOrigin = Point2D(0, 0)
val p2DRight = Point2D(3, -4)
val p2DLeft = Point2D(-10, 7)

val p2D1 = Point2D(1, 9)
val p2D2 = Point2D(-2, 8)
val p2D3 = Point2D(3, 4)
val p2D4 = Point2D(7, 6)
val p2D5 = Point2D(5, 5)

@EnabledTest
fun testPoint2D() {
    testSame(
        p2DOrigin.distToYAxis(),
        0,
        "origin/distance",
    )

    testSame(
        p2DRight.distToYAxis(),
        3,
        "right/distance",
    )

    testSame(
        p2DLeft.distToYAxis(),
        10,
        "left/distance",
    )
}
//           - Here is a set of steps you are encouraged to follow
//             (using appropriate abstractions) in order to code up
//             your function:
//
//             1. Given your list of items, produce a
//                list of each item with its score (as given
//                by the evaluation function); here's a useful
//                type to capture that pairing:

// an association between an item and a score
data class ItemScore<T>(val item: T, val score: Int)

//             2. Sort the list of these pairs, biggest-first
//                (the sortedByDescending member function might
//                be useful here).
//
//             3. Now that you have the list in order, you no
//                longer need the scores; produce a list that
//                maintains this order, but just contains the
//                items.
//
//             4. Finally, just return the first k items of this
//                list (cough, that you "take").
//

// Takes a list of items, an evaluation function, and a positive integer k,
// and returns the k items in the list that get the highest score.
fun <T> topK(
    items: List<T>,
    function: EvaluationFunction<T>,
    k: Int,
): List<T> {
    val itemScores = items.map { ItemScore(it, function(it)) }
    val sortedItemScores = itemScores.sortedByDescending { it.score }
    val sortedItems = sortedItemScores.map { it.item }
    return sortedItems.take(k)
}

// empty lists for tests
val emptyStringList: List<String> = emptyList()
val emptyIntList: List<Int> = emptyList()
val emptyPoint2DList: List<Point2D> = emptyList()

@EnabledTest
fun testTopKLongestString() {
    testSame(
        topK(emptyStringList, { it.length }, 1),
        emptyStringList,
        "Empty list",
    )
    testSame(
        topK(listOf("a", "bb", "ccc", "dddd"), { it.length }, 1),
        listOf("dddd"),
        "Longest string, list longer than k",
    )
    testSame(
        topK(listOf("a", "bb", "ccc", "dddd"), { it.length }, 10),
        listOf("dddd", "ccc", "bb", "a"),
        "Longest string, k out of range",
    )
}

@EnabledTest
fun testTopKTwoSmallestNumbers() {
    testSame(
        topK(emptyIntList, { it * -1 }, 1),
        emptyIntList,
        "Empty list",
    )
    testSame(
        topK(listOf(1, 2, 3, 4), { it * -1 }, 2),
        listOf(1, 2),
        "Two smallest numbers, list longer than k",
    )
    testSame(
        topK(listOf(1, 2, 3, 4), { it * -1 }, 10),
        listOf(1, 2, 3, 4),
        "Two smallest numbers, k out of range",
    )
}

@EnabledTest
fun testTopK5PointsClosestToYAxis() {
    testSame(
        topK(emptyPoint2DList, { it.distToYAxis() * -1 }, 1),
        emptyPoint2DList,
        "Empty list",
    )
    testSame(
        topK(
            listOf(p2DOrigin, p2DRight, p2DLeft, p2D1, p2D2, p2D3, p2D4, p2D5),
            { it.distToYAxis() * -1 },
            5,
        ),
        listOf(p2DOrigin, p2D1, p2D2, p2DRight, p2D3),
        "5 points closest to y-axis, list longer than k",
    )
    testSame(
        topK(
            listOf(p2DOrigin, p2DRight, p2DLeft),
            { it.distToYAxis() * -1 },
            5,
        ),
        listOf(p2DOrigin, p2DRight, p2DLeft),
        "5 points closest to y-axis, list shorter than k",
    )
}

fun main() {
    runEnabledTests(this)
}

main()
