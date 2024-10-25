// TODO 1/5: does a supplied string have a vowel?

fun hasAVowel(s: String): Boolean {
    for (i in s.lowercase()) {
        if (i in "aeiouy") return true
    }
    
    return false
}

println(hasAVowel("Howdy")) // yes!
println(hasAVowel("Act")) // yes!
println(hasAVowel("Rrrgggh")) // no!


// TODO 2/5: index of the first vowel, or -1

fun firstVowel(s: String): Int {
    for (i in 0..s.length-1) {
        if (s[i] in "aeiouy") return i
    }
    
    return -1
}

println(firstVowel("Howdy")) // 1
println(firstVowel("Act")) // 0
println(firstVowel("Rrrgggh")) // -1


// TODO 3/5: print a string in reverse
//           (with a new line AFTER)

fun printReverse(s: String) {
    // finish!
}

// printReverse("howdy")
// should be ydwoh on one line


// TODO 4/5: print a rectangle of @'s
//           (they're listening to you...)

fun drawBox(rows: Int, cols: Int, c: Char) {
    // finish!
}

drawBox(5, 3, '@')
// should look like...
// 
// @@@
// @@@
// @@@
// @@@
// @@@


// TODO 5/5: print a multiplication table

fun multiplicationTable(n: Int) {
    // finish!
}

// multiplicationTable(4)

// easier version...
// 1 2 3 4 
// 2 4 6 8 
// 3 6 9 12 
// 4 8 12 16

// harder version...
// 1  2  3  4  
// 2  4  6  8  
// 3  6  9  12 
// 4  8  12 16 
