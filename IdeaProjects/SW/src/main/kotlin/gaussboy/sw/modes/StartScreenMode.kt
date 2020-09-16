package gaussboy.sw.modes

import kotlin.system.exitProcess

class StartScreenMode {
    init {
        while (true) {
            println(
                "Enter your choice:\n" +
                        "1.Start game\n" +
                        "2.Check rules (in russian)\n" +
                        "3.Exit game"
            )
            val usrChoice = readLine()
            if (usrChoice != null) {
                when (usrChoice.trim()) {
                    "1" -> GameMode().start()
                    "2" -> println("PRAVILA: В начале игры овца и волки расставлены по краям доски друг против друга, " +
                            "овца ходит первой.\n" +
                            "Цель овцы — дойти до противоположного края доски, в этом случае она выиграла партию. \n" +
                            "Волки выигрывают, если им удаётся окружить овцу или прижать её к краю, " +
                            "так что она не может больше ходить.")
                    "3" -> exitProcess(1)
                }
            }
        }
    }
}