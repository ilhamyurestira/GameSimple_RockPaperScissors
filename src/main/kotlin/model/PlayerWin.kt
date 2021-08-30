package model

class PlayerWin : Result() {
    override fun printResult(player: PlayerInfo?) {
        println("Pemain ${player!!.playerNumber} MENANG!")
    }
}