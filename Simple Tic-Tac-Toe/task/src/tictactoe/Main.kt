package tictactoe

fun main() {
    println("Enter cells: ")
    val enterCells = readln()
    println("-".repeat(enterCells.length))
    println(
        """
| ${enterCells[0]} ${enterCells[1]} ${enterCells[2]} |
| ${enterCells[3]} ${enterCells[4]} ${enterCells[5]} |
| ${enterCells[6]} ${enterCells[7]} ${enterCells[8]} |
    """.trimIndent()
    )
    println("-".repeat(enterCells.length))
}