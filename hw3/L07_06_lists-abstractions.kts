
// [🌹, 🌷, 🌻].forEach(::stopSmellSneeze)

// [🐮, 🥔, 🐔, 🌽].map(::heat)            -> [🍔, 🍟, 🍗, 🍿]

// [🍔, 🍟, 🍗, 🍿].filter(::isVegetarian) -> [🍟, 🍿]
// [🍔, 🍟, 🍗, 🍿].any(::isVegetarian)    -> true
// [🍔, 🍟, 🍗, 🍿].all(::isVegetarian)    -> false

// [🐟, 🍙, 🥬, 🍤].fold(🔳, ::addToBento) -> 🍱















val myList = listOf(2, 5, 0, 0, 4, 5, 8)

// map: produce the result of applying a "transformation" 
//      function to each element

fun doubleNum(num: Int): Int {
    return num * 2
}

fun excitement(num: Int): String {
    return "$num!!"
}

// println(myList.map(::doubleNum))


// forEach: perform an action using each element

fun sayHello(num: Int) {
    println("Hello, $num :)")
}


// filter: produce the subset that satisfy a predicate

fun isEven(num: Int): Boolean {
    return num % 2 == 0
}


// any: determine if at least one element satisfies a predicate

fun isNegative(num: Int): Boolean {
    return num < 0
}


// all: determine if ALL satisfy a predicate (note: empty is trivially satisfied)

fun isPositive(num: Int): Boolean {
    return num > 0
}


// fold: accumulates value (more later in the course) 
//       starting with initial value and applying operation 
//       from left to right to current accumulator value and each element.

fun myReverseString(oldString: String, newNum: Int): String {
    return "$newNum$oldString"
}

// println(myList.fold("", ::myReverseString))
