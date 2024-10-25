// -----------------------------------------------------------------
// Homework 2, Problem 3
// -----------------------------------------------------------------

// We are making nametags for a (magical) student event, and want
// to make sure they have enough space for all the letters in each
// name. We are also formatting the nametags so that they have a
// consistent format: Last, First Middle - this way the teachers
// can accurately praise/critique their students.
//
// TODO 1/1: Given the data types and examples below, design the
//           function numCharsNeeded that takes a magical pair and
//           returns the number of characters that would be
//           necessary to represent the longer student's formatted
//           name.
//
//           For instance, if Hermione and Ron are paired, her
//           formatted name is "Granger, Hermione Jean" (22
//           characters) and his is "Weasley, Ron Bilius" (19
//           characters), and so the function should return 22.
//
//           Note the nested data types, and so duplicated work -
//           let type-driven development lead you to an effective
//           decomposition of well-designed functions :)
//

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame


// represents a person's first/middle/last names
data class Name(val first: String, val middle: String, val last: String)

val harryJPotter = Name("Harry", "James", "Potter")
val hermioneJeanGranger = Name("Hermione", "Jean", "Granger")
val ronBiliusWeasley = Name("Ron", "Bilius", "Weasley")

// represents a pairing of two names
data class MagicPair(val p1: Name, val p2: Name)

val magicHarryRon = MagicPair(harryJPotter, ronBiliusWeasley)
val magicHarryHermione = MagicPair(harryJPotter, hermioneJeanGranger)
val magicHermioneRon = MagicPair(hermioneJeanGranger, ronBiliusWeasley)

// helper function
fun formatName(name: Name): String {
    return ("${name.last}, ${name.first} ${name.middle}")
}

// determines the number of characters needed for a particular pair
fun numCharsNeeded(pair: MagicPair): Int {
    if (formatName(pair.p1).length > formatName(pair.p2).length) {
        return formatName(pair.p1).length
    } else {
        return formatName(pair.p2).length
    }
}

// tests
@EnabledTest
fun testingNumCharsNeeded() {
    testSame(numCharsNeeded(magicHarryRon), 19, "HarryRon")
    testSame(numCharsNeeded(magicHarryHermione), 22, "HarryHermione")
    testSame(numCharsNeeded(magicHermioneRon), 22, "HermioneRon")
}

fun main() {
    runEnabledTests(this)
}

main()
