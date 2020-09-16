package gaussboy.sw.modes

import gaussboy.sw.model.Bot
import gaussboy.sw.ui.PrettyFieldPrinting
import java.lang.Exception

const val EMPTY_CELL = 0
const val SHEEP = 1
const val WOLF = 2
val mapOfCells = mapOf(
    "b8" to 0, "d8" to 1, "f8" to 2, "h8" to 3,
    "a7" to 4, "c7" to 5, "e7" to 6, "g7" to 7,
    "b6" to 8, "d6" to 9, "f6" to 10, "h6" to 11,
    "a5" to 12, "c5" to 13, "e5" to 14, "g5" to 15,
    "b4" to 16, "d4" to 17, "f4" to 18, "h4" to 19,
    "a3" to 20, "c3" to 21, "e3" to 22, "g3" to 23,
    "b2" to 24, "d2" to 25, "f2" to 26, "h2" to 27,
    "a1" to 28, "c1" to 29, "e1" to 30, "g1" to 31
)

class GameMode {
    private var isPlayerTurn = false

    private var field = Array(32) { EMPTY_CELL }

    fun makeAMove(moveStr: String): Boolean {
        val move = getMove(moveStr)
        return if (isPlayerTurn) {
            if (!checkIfMoveCorrect(move))
                false
            else {
                field[move.first] = 0
                field[move.second] = WOLF
                checkWOL()
                true
            }
        } else {
            if (move.first == move.second) {
                val printing = PrettyFieldPrinting()
                printing.print(field)
                val ending = EndMode(true)
            }
            field[move.first] = 0
            field[move.second] = SHEEP
            checkWOL()
            true
        }
    }


    fun start() {
        println("Первый ход за овцой")
        field[1] = SHEEP
        field[28] = WOLF
        field[29] = WOLF
        field[30] = WOLF
        field[31] = WOLF
        val printing = PrettyFieldPrinting()
        printing.print(field)
        val bot = Bot()
        while (true) {
            if (isPlayerTurn) {
                println("Введите ваш ход:")
                val turn = readLine()
                if (makeAMove(checkMoveString(turn))) {
                    isPlayerTurn = false
                    printing.print(field)
                } else
                    println("Так cходить невозможно!")
            } else {
                makeAMove(bot.makeAMove(field))
                isPlayerTurn = true
                printing.print(field)
            }
        }
    }


    private fun checkMoveString(input: String?): String {
        return input!!//TODO()
    }

    private fun checkIfMoveCorrect(move: Pair<Int, Int>): Boolean {
        if ((move.first !in field.indices) || (move.second !in field.indices)) {
            return false
        }
        if (field[move.first] == EMPTY_CELL) {
            return false
        }
        if (field[move.second] != EMPTY_CELL) {
            return false
        }
        if ((field[move.first] == WOLF) && (move.first < move.second)) {
            return false
        }
        if (isPlayerTurn && field[move.first] != WOLF) {
            return false
        }
        if (move.first == move.second) {
            return false
        }

        if ((move.first / 4) % 2 == 0) { //если мы находимся в четном ряду

            if ((move.first - 4 != move.second) && (move.first - 3 != move.second)) {
                return false
            }

        } else { // нечетный ряд
            if ((move.first - 4 != move.second) && (move.first - 5 != move.second)) {
                return false
            }
        }
        return true
    }

    private fun checkWOL() {
        val sheepCurrentIndex = field.indexOf(SHEEP)
        if (sheepCurrentIndex in 28..31) {
            val printing = PrettyFieldPrinting()
            printing.print(field)
            val ending = EndMode(false)
        } else {
            if ((sheepCurrentIndex / 4) % 2 == 0) {
                if (sheepCurrentIndex !in 0..3) {
                    if ((field[sheepCurrentIndex + 4] != EMPTY_CELL) && (field[sheepCurrentIndex + 5] != EMPTY_CELL) &&
                        (field[sheepCurrentIndex - 4] != EMPTY_CELL) && (field[sheepCurrentIndex - 3] != EMPTY_CELL)
                    ) {
                        val printing = PrettyFieldPrinting()
                        printing.print(field)
                        val ending = EndMode(true)
                    }
                } else if ((field[sheepCurrentIndex + 4] != EMPTY_CELL) && (field[sheepCurrentIndex + 5] != EMPTY_CELL)) {
                    val printing = PrettyFieldPrinting()
                    printing.print(field)
                    val ending = EndMode(true)
                }
            } else if ((field[sheepCurrentIndex + 4] != EMPTY_CELL) && (field[sheepCurrentIndex + 3] != EMPTY_CELL) &&
                (field[sheepCurrentIndex - 4] != EMPTY_CELL) && (field[sheepCurrentIndex - 5] != EMPTY_CELL)
            ) {
                val printing = PrettyFieldPrinting()
                printing.print(field)
                val ending = EndMode(true)
            }
        }

    }

    private fun getMove(move: String): Pair<Int, Int> {
        return try {
            if (move == "exit") {
                val ending = EndMode(false)
            }
            val spltdMove = move.split(" ")
            (mapOfCells[spltdMove[0].toLowerCase()] ?: error("")) to (mapOfCells[spltdMove[1].toLowerCase()]
                ?: error(""))
        } catch (e: Exception) {
            0 to 0
        }
    }
}