import khoury.fileReadAsList
val names:List<String> = (fileReadAsList("names.txt"))
val records:List<String> = (fileReadAsList("records.txt"))
println(names)

fun toInitials(name: String): String {
    val seperatedNames = name.split(" ")
    val firstInitial = (seperatedNames.first().first())
    val lastInitial = (seperatedNames.last().first())
    val combinedInitials = "$firstInitial$lastInitial"
    return combinedInitials
}





fun toHistories(record: String): Boolean {
    val separatedRecords:List<String> = record.split(",")


    fun isW(str: String): Boolean {
        return (str == "W") 
    }

    return separatedRecords.filter(::isW).size >= separatedRecords.size/2.0
}

println(toHistories(records[1]))


println(toInitials(names[3]))








// val testList = listOf("first","second","third")

// fun run(iteration:Int):Boolean{
//     return (testList[iteration] == "third")
// }

// val newTestList = testList.filter(::run)
// println(newTestList)