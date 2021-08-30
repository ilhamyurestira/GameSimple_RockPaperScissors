import model.Draw
import model.PlayerInfo
import model.PlayerWin
import utils.PlayerName
import java.util.*

class App {

    companion object {
        private var isAgain: String? = null
        private lateinit var playerName: PlayerName
        val firstPlayer = PlayerInfo(1)
        val secondPlayer = PlayerInfo(2)
        private val Choice: List<String> = arrayListOf("batu", "gunting", "kertas")

        @JvmStatic
        fun main(args: Array<String>) {
            App().start()
        }
    }

    fun start() {
        playerName = PlayerName()
        header()
        inputPlayerName()
        do {
            play(firstPlayer)
            play(secondPlayer)
            process(firstPlayer, secondPlayer)
            do {
                print("Ulangi Permainan (Y/N)? "); isAgain = readLine()?.uppercase()
            } while (isAgain.equals("Y").not() && isAgain.equals("N").not())
        } while (isAgain.equals("Y"))
    }

    private fun header() {
        println(
            """
            ==========================
            GAME SUIT TERMINAL VERSION
            ==========================
        """.trimIndent()
        )
    }

    private fun inputPlayerName() {
        println("Masukan Nama Pemain 1 = ")
        val namePlayer1 = readLine().orEmpty()
        println("Masukan Nama Pemain 2 = ")
        val namePlayer2 = readLine().orEmpty()
        println("==========================")
        println("Selamat Bertanding!")
        println("${playerName.name(namePlayer1)} VS ${playerName.name(namePlayer2)}")
        println("==========================")
    }

    private fun play(player: PlayerInfo) {
        do {
            println("${player.playerNumber}. Masukan Pilihan Pemain ${player.playerNumber}:(batu/gunting/kertas)")
            player.choose = readLine()?.lowercase(Locale.getDefault()) ?: ""
            val condition = player.choose == "" || !Choice.contains(player.choose)
            if (condition) {
                println("Pilihan tidak ada (Masukan (batu/gunting/kertas)")
            }
        } while (condition)
    }

    private fun process(firstPlayer: PlayerInfo, secondPlayer: PlayerInfo) {
        if (firstPlayer.choose == secondPlayer.choose) {

            Draw().printResult(null)

        } else if (firstPlayer.choose == "gunting" && secondPlayer.choose == "kertas"
            || firstPlayer.choose == "batu" && secondPlayer.choose == "gunting"
            || firstPlayer.choose == "kertas" && secondPlayer.choose == "batu"
        ) {

            PlayerWin().printResult(firstPlayer)

        } else if (firstPlayer.choose == "gunting" && secondPlayer.choose == "batu"
            || firstPlayer.choose == "kertas" && secondPlayer.choose == "gunting"
            || firstPlayer.choose == "batu" && secondPlayer.choose == "kertas"
        ) {

            PlayerWin().printResult(secondPlayer)
        }
    }
}