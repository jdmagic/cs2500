// -----------------------------------------------------------------
// Homework 2, Problem 4
// -----------------------------------------------------------------

// Now it's time to write your first full, if not a bit silly, app!
//
// TODO 1/1: Finish designing the program loopSong, using
//           reactConsole, that prints...
//
//           A-B-C
//
//           then...
//
//           Easy as 1-2-3
//
//           repeatedly until the user types "done" in response
//           to "Easy as 1-2-3", at which point the program
//           prints...
//
//           Baby you and me girl!
//
//           and returns the number of times the song looped
//           (meaning, how many times it printed "Easy as 1-2-3").
//
//           To help, you have an enumeration that represents the
//           beginning of the song, and each subsequent situation.
//           You also have tests - you just need to finish
//           loopSong, as well as designing the helper functions
//           that handle each type of event.
//
//           Suggestions:
//           - First, design how you will represent the state of
//             your program; SongState is useful, but you'll also
//             need to keep track of how many times the song looped
//             (hmm... more than one piece of data in a single value,
//             what helps us there?).
//           - Next, have loopSong call reactConsole, providing
//             the initial state (ideally an example), and a set
//             of yet-to-be-designed helper functions, not
//             forgetting to return the number of song loops.
//           - Now, figure out the parameter/return types of each
//             helper, based upon how reactConsole works with your
//             data design.
//           - Then, write some tests to capture how these helper
//             functions *should* operate...
//             * songStateToText: should be producing the
//                                appropriate lyrics based on the
//                                situation (irrespective of loop
//                                count).
//             * nextSongState: should be figuring out what is the
//                              next situation based upon the
//                              current situation and what was
//                              typed (which matters only when
//                              "Easy as 1-2-3" was printed).
//             * isDone: should only return true when the current
//                       situation is that the song is done!
//            - And lastly, write the code for your helpers (as
//              informed by the types they use and tests!). You
//              might do these last two steps for each helper
//              individually; you can then test piece-by-piece if
//              you comment-out our tests and loopSong.
//
//           As with any interactive program, feel free to debug
//           by directly calling your program in main!
//

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.reactConsole
import khoury.runEnabledTests
import khoury.testSame

val lyricsABC = "A-B-C"
val lyrics123 = "Easy as 1-2-3"
val lyricsDone = "Baby you and me girl!"

// Represents state in the song
enum class SongState {
    START,
    ABC,
    EASY123,
    DONE,
}

data class SongLoopState(val state: SongState, val count: Int)

// accepts current song state and returns the appropriate text to output to console
fun songStateToText(currentState: SongLoopState): String {
    when (currentState.state) {
        SongState.ABC -> return(lyricsABC)
        SongState.EASY123 -> return(lyrics123)
        SongState.DONE -> return(lyricsDone)
        else -> return(lyricsABC)
    }
}

// accepts current song state and an input and returns the appropriate next song state
fun nextSongState(
    currentState: SongLoopState,
    input: String,
): SongLoopState {
    if (currentState.state == SongState.ABC) {
        return SongLoopState(SongState.EASY123, currentState.count)
    } else if (currentState.state == SongState.START) {
        return SongLoopState(SongState.EASY123, currentState.count)
    } else if (currentState.state == SongState.EASY123 && input == "done") {
        return SongLoopState(SongState.DONE, currentState.count + 1)
    } else {
        return SongLoopState(SongState.ABC, currentState.count + 1)
    }
}

// returns true when the song state is DONE
fun isDone(currentState: SongLoopState): Boolean {
    return currentState.state == SongState.DONE
}

// calls reactConsole which is used to loop the song
fun loopSong(): Int {
    // call reactConsole
    val songCount =
        reactConsole(
            initialState = SongLoopState(SongState.START, 0),
            stateToText = ::songStateToText,
            nextState = ::nextSongState,
            isTerminalState = ::isDone,
        )
    return songCount.count
}

// testing functions
@EnabledTest
fun testLoopSong() {
    testSame(
        captureResults(
            ::loopSong,
            "",
            "done",
        ),
        CapturedResult(
            1,
            lyricsABC,
            lyrics123,
            lyricsDone,
        ),
        "quick",
    )

    testSame(
        captureResults(
            ::loopSong,
            "a",
            "b",
            "done",
            "c",
            "d",
            "done",
        ),
        CapturedResult(
            3,
            lyricsABC,
            lyrics123,
            lyricsABC,
            lyrics123,
            lyricsABC,
            lyrics123,
            lyricsDone,
        ),
        "longer",
    )
}

fun main() {
    runEnabledTests(this)
}

main()
