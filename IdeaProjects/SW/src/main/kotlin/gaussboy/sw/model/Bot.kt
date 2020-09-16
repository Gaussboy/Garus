package gaussboy.sw.model

import gaussboy.sw.modes.SHEEP
import gaussboy.sw.modes.mapOfCells

class Bot {
    var counter = 0

    fun makeAMove(field: Array<Int>) : String {
        var sheepCurrentIndex = field.indexOf(SHEEP)
        if ((sheepCurrentIndex / 4) % 2 == 0) { //если мы находимся в четном ряду

            if (field[sheepCurrentIndex + 4] == 0 && field[sheepCurrentIndex + 5] == 0) {
                return if (counter % 2 == 0) {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex + 4)
                } else {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex + 5)
                }
            } else {
                return if (counter % 2 == 0) {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex - 4)
                } else {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex - 3)
                }
            }

        } else { // нечетный ряд
            if (field[sheepCurrentIndex + 3] == 0 && field[sheepCurrentIndex + 4] == 0) {
                return if (counter % 2 == 0) {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex + 3)
                } else {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex + 4)
                }
            } else {
                return if (counter % 2 == 0) {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex - 4)
                } else {
                    counter++
                    getAnswer(sheepCurrentIndex, sheepCurrentIndex - 5)
                }
            }
        }
    }

    private fun getAnswer(start: Int, end: Int) : String {
        var resStart = ""
        var resEnd = ""
        for (i in mapOfCells.keys) {
            if (mapOfCells[i] == start)
                resStart = i

            if (mapOfCells[i] == end)
                resEnd = i
        }
        return "$resStart $resEnd"
    }
}
