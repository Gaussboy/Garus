package gaussboy.sw.model

import gaussboy.sw.modes.SHEEP
import gaussboy.sw.modes.WOLF
import kotlin.random.Random

val distanceMap = mutableMapOf<String, Int>()

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

class Bot(_field: Array<Int>) {

    private val field: Array<Int> = _field

    fun calculateMove(): String {
        var sheepCurrentCell = ""
        for (cell in mapOfCells) {
            if (cell.value == field.indexOf(SHEEP)) {
                sheepCurrentCell = cell.key
            }
        }

        fillDistanceMap()
        val turnsList = getPossibleBotTurns(sheepCurrentCell)
        val priorityList = arrayListOf<Int>()
        for (turn in turnsList) {
            val dist = distanceMap[turn]
            var prior = if (dist == null) -100 else -dist
            if (sheepCurrentCell[1] > turn[1]) {
                prior++
            }
            priorityList.add(prior)
        }

        var maxPriority = -100

        for (i in 0 until turnsList.size) {
            if (priorityList[i] > maxPriority) {
                maxPriority = priorityList[i]
            }
        }

        val finalTurnsList = arrayListOf<String>()

        for (i in 0 until turnsList.size) {
            if (priorityList[i] == maxPriority) {
                finalTurnsList.add(turnsList[i])
            }
        }

        val finalTurnIndex = if (finalTurnsList.size == 1) 0 else Random.nextInt(finalTurnsList.size)

        val target = finalTurnsList[finalTurnIndex]
        println("Ход овцы - $sheepCurrentCell-$target \n")

        return "$sheepCurrentCell $target"
    }

    private fun fillDistanceMap(): Int {
        distanceMap.clear()
        var arrayList = arrayListOf<String>()
        for (c in 'a' until 'i') {
            val cell = c.plus("1")
            if (((c.toInt() + '1'.toInt()) % 2 == 0) && (field[mapOfCells[cell] ?: error("")] != WOLF)) {
                arrayList.add(cell)
            }
        }
        var n = 0
        while (arrayList.isNotEmpty()) {
            val arrayList2 = arrayListOf<String>()
            for (cell in arrayList) {
                var n2 = 0
                if (distanceMap[cell] != null) {
                    continue
                }
                if (field[mapOfCells[cell] ?: error("")] == WOLF) {
                    n2 = -1
                } else {
                    n2 = n
                    arrayList2.addAll(getPossibleBotTurns(cell))
                }
                distanceMap[cell] = n2
            }
            n++
            arrayList = arrayList2
        }
        return distanceMap.size
    }

    private fun getPossibleBotTurns(cell: String): ArrayList<String> {
        val cell1 = cell[0]
        val cell2 = cell[1]
        val c11 = cell[0] + 1
        val c12 = cell[0] - 1
        val c21 = cell[1] + 1
        val c22 = cell[1] - 1
        val turnsList = arrayListOf<String>()

        var target = ""

        if (cell2 < '8') {
            target = c12.plus(c21.toString())
            if ((cell1 > 'a') && (field[mapOfCells[target] ?: error("")] != WOLF)) {
                turnsList.add(target)
            }

            target = c11.plus(c21.toString())
            if ((cell1 < 'h') && (field[mapOfCells[target] ?: error("")] != WOLF)) {
                turnsList.add(target)
            }
        }
        if (cell2 > '1') {
            target = c12.plus(c22.toString())
            if ((cell1 > 'a') && (field[mapOfCells[target] ?: error("")] != WOLF)) {
                turnsList.add(target)
            }

            target = c11.plus(c22.toString())
            if ((cell1 < 'h') && (field[mapOfCells[target] ?: error("")] != WOLF)) {
                turnsList.add(target)
            }
        }
        return turnsList
    }
}
