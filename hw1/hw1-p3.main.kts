// -----------------------------------------------------------------
// Homework 1, Problem 3
// -----------------------------------------------------------------

// TODO 1/1: Write the function makeGreeter that takes a string
//           and produces a function, which then outputs a
//           greeting to any supplied name. Ok, that was a bit
//           confusing, so here's an example...
//
val myGreeter = makeGreeter("Howdy")
//           println(myGreeter("World"))
//
//           which should output "Howdy World" to the screen.
//
//           Once you think you have it, try using it for a couple
//           greetings and names in your main, similar to the
//           example above.
//
//           Hint: remember that "referencing" a function as a
//                 value is slightly different from calling it;
//                 the latter is just functionName(argument),
//                 whereas the former involves using the ::'s,
//                 such as ::functionName.
//

fun makeGreeter(greeter: String): (String) -> String {
    fun greeting(recipient: String): String {
        return "$greeter $recipient"
    }

    return ::greeting
}

// defines some constants for various words that could be used to create greetings, and then runs a few examples
fun main() {
    val creativeGreeting = "Howdy"
    val typicalGreeting = "Hello"
    val casualGreeting = "Hi"

    val everyone = "World"
    val aFew = "Friends"
    val formalAquaintance = "Sir"

    println(myGreeter("World")) // this one is already defined in line 10 from the example
    println(makeGreeter(casualGreeting)(everyone))
    println(makeGreeter(typicalGreeting)(formalAquaintance))
    println(makeGreeter(typicalGreeting)(aFew))
}

main()
