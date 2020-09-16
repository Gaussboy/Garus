package gaussboy.sw.modes

import kotlin.system.exitProcess

class EndMode (winOrLose: Boolean) {
    init {
        while (true) {
            if (winOrLose) {
                println("YOU WON!!!")
            } else {
                println("YOU LOSE :c")
            }
            println(
                "Restart or Exit?\n" +
                        "1. Restart\n" +
                        "2. Exit"
            )
            val usrChoice = readLine()
            if (usrChoice != null) {
                when (usrChoice.trim()) {
                    "1" -> GameMode().start()
                    "2" -> exitProcess(1)
                }
            }

        }
    }
}
