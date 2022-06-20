package tictactoe

fun main() {
    print("Enter cells: ")
    val enterCells = readln().uppercase()
    val game = mutableListOf(
        mutableListOf(enterCells.substring(0, 1), enterCells.substring(1, 2), enterCells.substring(2, 3)),
        mutableListOf(enterCells.substring(3, 4), enterCells.substring(4, 5), enterCells.substring(5, 6)),
        mutableListOf(enterCells.substring(6, 7), enterCells.substring(7, 8), enterCells.substring(8, 9))
    )
    showTable(game)
    while (true) {
        print("Enter the coordinates: ")
        val (row, col) = readln().split(" ")
        if (!"0123456789".contains(row) || !"0123456789".contains(col)) {
            println("You should enter numbers!")
            continue
        }
        val x = row.toInt()
        val y = col.toInt()
        if (x !in 1..3 || y !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        if (game[x - 1][y - 1] == "X" || game[x - 1][y - 1] == "O") {
            println("This cell is occupied! Choose another one!")
            continue
        }
        //println("$x, $y")
        game[x - 1][y - 1] = "X"
        showTable(game)
        //println("game[x - 1][y - 1]: " + game[x - 1][y - 1])

        // println(findGameState(enterCells, game))
        break
    }
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

fun showTable(game: MutableList<MutableList<String>>) {
    println("-".repeat(9))
    println(
        """
| ${game[0][0]} ${game[0][1]} ${game[0][2]} |
| ${game[1][0]} ${game[1][1]} ${game[1][2]} |
| ${game[2][0]} ${game[2][1]} ${game[2][2]} |
    """.trimIndent()
    )
    println("-".repeat(9))
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
    return noMoreTry || noTwoWinner
}

fun isWin(enterCells: String, game: MutableList<MutableList<String>>, player: String): Boolean {
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
