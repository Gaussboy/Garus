package gaussboy.sw.ui

class PrettyFieldPrinting {

    fun printField(field: Array<Int>) {
        var counter = 0
        println("|   | A | B | C | D | E | F | G | H |")
        for (i in 7 downTo 0) {
            print("| ${i+1} |")
            if (i % 2 == 0) {
                for (j in 0..3) {
                    print(" ${getSymbol(field[counter])} |///|")
                    counter++
                }
            } else {
                for (j in 0..3) {
                    print("///| ${getSymbol(field[counter])} |")
                    counter++
                }
            }
            print(" ${i+1} |")
            print("\n")
            println("--------------------------------------")
        }
        println("|   | A | B | C | D | E | F | G | H |")
        print("\n\n")
    }

    fun getSymbol(num: Int): String {
        return when (num) {
            1 -> {
                "S"
            }
            2 -> {
                "W"
            }
            else -> " "
        }
    }
}
