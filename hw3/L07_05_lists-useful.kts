val myList = listOf(2, 5, 0, 0, 4, 5, 8)

// how many?
println(myList.size)
println(myList.isEmpty())

// get an element by "index" (starts at 0!)
println(myList[0]) // first element
println(myList[ myList.size-1 ]) // last element

// is something `in` a list? or not?
println(4 in myList)
println(7 !in myList)

// get a new list...
println(myList.reversed())
println(myList.sorted())
println(myList.take(2))
println(myList.drop(2))

// get a string by...
println(myList.joinToString("-"))


// many more :)
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/
