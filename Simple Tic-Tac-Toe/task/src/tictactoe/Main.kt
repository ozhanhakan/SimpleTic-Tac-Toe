package tictactoe

fun main() {
//    while (true) {
    print("Enter cells: ")
    val enterCells = readln().uppercase()
    println("-".repeat(enterCells.length))
    println(
        """
| ${enterCells[0]} ${enterCells[1]} ${enterCells[2]} |
| ${enterCells[3]} ${enterCells[4]} ${enterCells[5]} |
| ${enterCells[6]} ${enterCells[7]} ${enterCells[8]} |
    """.trimIndent()
    )
    println("-".repeat(enterCells.length))
    // stage 3/5 analyze
    val game = mutableListOf(
        mutableListOf(enterCells.substring(0, 1), enterCells.substring(1, 2), enterCells.substring(2, 3)),
        mutableListOf(enterCells.substring(3, 4), enterCells.substring(4, 5), enterCells.substring(5, 6)),
        mutableListOf(enterCells.substring(6, 7), enterCells.substring(7, 8), enterCells.substring(8, 9))
    )
    println(findGameState(enterCells, game))
/*
    println(Math.abs((countChar(enterCells, "X") - countChar(enterCells, "O"))) > 1)
    println(isWin(enterCells, game, "X") && isWin(enterCells, game, "O"))
    println("countChar(enterCells, x):"+countChar(enterCells, "X"))
    println("countChar(enterCells, o):"+countChar(enterCells, "O"))*/
    /*println(game2D.size)
println(game2D)
println(game2D[0].size)
println(game2D[0])
println(game2D[game2D.lastIndex].size)
println(game2D[game2D.lastIndex])*/
    // }
}

fun findGameState(enterCells: String, game: MutableList<MutableList<String>>): String {
    return when {
        impossible(enterCells, game) -> "Impossible"
        draw(enterCells, game) -> "Draw"
        xWins(enterCells, game) -> "X wins"
        oWins(enterCells, game) -> "O wins"
        isGameLast(enterCells, game) -> "Game not finished"
        else -> "BugBuBug"
    }
}

fun isGameLast(enterCells: String, game: MutableList<MutableList<String>>): Boolean {
    var gameLast = false
    if ((enterCells.contains("_") || enterCells.contains(" ")) &&
        (!xWins(enterCells, game) || !oWins(enterCells, game))
    ) {
        gameLast = true
    }
    // println("is Game Last?")
    return gameLast
}

fun countChar(s: String, ch: String, ignoreCase: Boolean = true): Int {
    var counter = 0
    val str = if (ignoreCase) s.uppercase() else s
    for (c in str.indices) {
        /*println(str[c].toString())
        println(ch.uppercase())
        println(str[c].toString().equals(ch.uppercase()))*/
        if (str[c].toString().equals(ch.uppercase())) {
            counter++
        }
    }
    return counter
}

fun draw(enterCells: String, game: MutableList<MutableList<String>>): Boolean {
    return (!isWin(enterCells, game, "O") &&
            !isWin(enterCells, game, "X") &&
            !isGameLast(enterCells, game)
            )
}

fun xWins(enterCells: String, game: MutableList<MutableList<String>>): Boolean {
    return isWin(enterCells, game, "X")
}

fun oWins(enterCells: String, game: MutableList<MutableList<String>>): Boolean {
    return isWin(enterCells, game, "O")
}

fun impossible(enterCells: String, game: MutableList<MutableList<String>>): Boolean {

    val noTwoWinner = isWin(enterCells, game, "X") && isWin(enterCells, game, "O")
    val noMoreTry = Math.abs((countChar(enterCells, "X") - countChar(enterCells, "O"))) > 1
    /* println("noTwoWinner: " + noTwoWinner)
     println("noMoreTry: " + noMoreTry)*/
    return noMoreTry || noTwoWinner

}

fun isWin(enterCells: String, game: MutableList<MutableList<String>>, player: String): Boolean {
    // row control

    var countColumn0 = 0
    var countColumn1 = 0
    var countColumn2 = 0
    var countXdiag1 = 0
    var countXdiag2 = 0

    for (i in 0 until game.size) {
        var countXrow = 0
        for (j in 0 until game.first().size) {
            // diag1 control ok
            if (i == j && (game[i][j] == player.uppercase())) {
                countXdiag1++
                if (countXdiag1 == 3) return true
            }
            // diag2 control -
            // 0,2 / 1,1 / 2,0
            if (i + j == 2 && (game[i][j] == player.uppercase())) {
                countXdiag2++
                if (countXdiag2 == 3) return true
            }
            // row control ok
            if ((game[i][j] == player.uppercase())) {
                countXrow++
                if (countXrow == 3) return true
            }
        }
        // column0 control ok
        if ((game[i][0] == player.uppercase())) {
            countColumn0++
            if (countColumn0 == 3) return true
        }
        // column1 control ok
        if ((game[i][1] == player.uppercase())) {
            countColumn1++
            if (countColumn1 == 3) return true
        }
        // column2 control ok
        if ((game[i][2] == player.uppercase())) {
            countColumn2++
            if (countColumn2 == 3) return true
        }
    }

    return false
}

/*
// test grids
// row0
 if ((game[0][0] == "X" || game[0][0] == "x") &&
     (game[0][1] == "X" || game[0][1] == "x") &&
     (game[0][2] == "X" || game[0][2] == "x")) {
     return true
 }
 // row1
 if ((game[1][0] == "X" || game[1][0] == "x") &&
     (game[1][1] == "X" || game[1][1] == "x") &&
     (game[1][2] == "X" || game[1][2] == "x")) {
     return true
 }
 // row2
 if ((game[2][0] == "X" || game[2][0] == "x") &&
     (game[2][1] == "X" || game[2][1] == "x") &&
     (game[2][2] == "X" || game[2][2] == "x")) {
     return true
 }
 // column0 ok
 if ((game[0][0] == "X" || game[0][0] == "x") &&
     (game[1][0] == "X" || game[1][0] == "x") &&
     (game[2][0] == "X" || game[2][0] == "x")) {
     return true
 }
 // column1
 if ((game[0][1] == "X" || game[0][1] == "x") &&
     (game[1][1] == "X" || game[1][1] == "x") &&
     (game[2][1] == "X" || game[2][1] == "x")) {
     return true
 }
 // column2
 if ((game[0][2] == "X" || game[0][2] == "x") &&
     (game[1][2] == "X" || game[1][2] == "x") &&
     (game[2][2] == "X" || game[2][2] == "x")) {
     return true
 }

 // diag1 ok
 if ((game[0][0] == "X" || game[0][0] == "x") &&
     (game[1][1] == "X" || game[1][1] == "x") &&
     (game[2][2] == "X" || game[2][2] == "x")) {
     return true
 }
 // diag2 ok
 if ((game[0][2] == "X" || game[0][2] == "x") &&
     (game[1][1] == "X" || game[1][1] == "x") &&
     (game[2][0] == "X" || game[2][0] == "x")) {
     return true
 }*/