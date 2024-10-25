// represents that a linked list can either be empty,
// or a node with numeric data and a reference to
// the next such list

sealed class LinkedListNumbers {
    object Empty: LinkedListNumbers()

    data class Node(
        val myData: Int, 
        val next: LinkedListNumbers
    ): LinkedListNumbers()
}


fun llNum(llN: LinkedListNumbers) {
    when (llN) {
        is LinkedListNumbers.Empty -> ???
        is LinkedListNumbers.Node ->
            llN.myData
            llNum(llN.next)
    }
}



val emptyNumberList = LinkedListNumbers.Empty

val list1   = LinkedListNumbers.Node(1, emptyNumberList)
val list21  = LinkedListNumbers.Node(2, list1)
val list321 = LinkedListNumbers.Node(3, list21)

println(list321)

// guiding questions for type-driven development,
// based upon the "main" input type ...
// 
// 1. how many value/type cases?
//    (if >1, `when`; types -> `is`)
// 2. what sub-pieces can you extract?
//    (if data class, all fields)
// 3. are sub-pieces are designed types?
//    (if so, call helper based on that type)
// 
// Example: L09_01 (Quiz 1 Problem 3)
// 

// TODO: apply to LinkedListNumbers


// guiding process for resulting recursion...
// 1. what to return in the base case(s)?
// 2. now imagine the recursive call worked...
//    a) what type would you get?
//    b) what would that represent?
//    c) focus on just the current value,
//       how can you combine it with the
//       recursive result to make this
//       current element work?
// 

// example... sum up a list of numbers

fun mySum(llN: LinkedListNumbers): Int {
    return when (llN) {
        is LinkedListNumbers.Empty -> 0
        is LinkedListNumbers.Node -> llN.myData  + mySum(llN.next)
    }
}



// 1. what is the "sum" of an empty list?
// 2. given a list, consider the sum of
//    all numbers after the first element...
//    a) what is the type of that?
//    b) what does it represent?
//    c) how can we combine it with the
//       first number to produce the
//       overall sum?
//    

// TODO: what *must* be tested?
//       (given the input type)

// TODO: product of the numbers in a list

fun myProduct(llN: LinkedListNumbers): Int {
    return when (llN) {
        is LinkedListNumbers.Empty -> 1
        is LinkedListNumbers.Node -> llN.myData * myProduct(llN.next)
    }
}








// WARNING

fun scary(nums: LinkedListNumbers): Int {
    return when (nums) {
        is LinkedListNumbers.Empty -> 1
        is LinkedListNumbers.Node -> nums.myData * scary(nums)
    }
}

// println(scary(emptyNumberList))
// println(scary(list321))


// we can also use this type of iteration with
// Kotlin lists; consider the following 
// self-referential definition...

// a Kotlin list is either..
// - empty
// - an element, followed by a Kotlin list

// TODO: design mySum

fun myKotlinSum(kl: List<Int>): Int {
    return when (kl.isEmpty()) {
        true -> 0
        false -> kl[0] + myKotlinSum(kl.drop(1))
    }
}


// println(mySum(emptyList<Int>()))
// println(mySum(listOf(1, 2, 3)))



// TODO: implement the `any` abstraction...
//       - recursively for linked lists
//       - recursively for Kotlin lists
//       - using `for` on Kotlin lists
//       

fun isEven(num: Int): Boolean {
    return num % 2 == 0
}

fun myAnyLLRecursive(nums: LinkedListNumbers, pred: (Int)->Boolean): Boolean {
    return when (nums) {
        is LinkedListNumbers.Empty -> false
        is LinkedListNumbers.Node ->
            pred(nums.myData) ||
            myAnyLLRecursive(nums.next, pred)
    }
}

// println(myAnyLLRecursive(emptyNumberList, ::isEven))
// println(myAnyLLRecursive(list1, ::isEven))
// println(myAnyLLRecursive(list321, ::isEven))

// fun myAnyLRecursive(nums: List<Int>, pred: (Int)->Boolean): Boolean {
//     return when (nums.isEmpty()) {
//         true -> false
//         false -> 
// pre(nums[0]) ||

//     }
// }

// println(myAnyLRecursive(emptyList<Int>(), ::isEven))
// println(myAnyLRecursive(listOf(1), ::isEven))
// println(myAnyLRecursive(listOf(3, 2, 1), ::isEven))

fun myAnyLFor(nums: List<Int>, pred: (Int)->Boolean): Boolean {
    for (num in nums) {
        if (pred(num)) {

        }
    }
}

// println(myAnyLFor(emptyList<Int>(), ::isEven))
// println(myAnyLFor(listOf(1), ::isEven))
// println(myAnyLFor(listOf(3, 2, 1), ::isEven))
