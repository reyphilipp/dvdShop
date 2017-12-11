package utility

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Zeit {
    fun jetztigeZeit():String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        return currentDateTime.format(formatter)
    }
    fun zeitSub(Tage:Int):String {
        val Dauer = Tage.toLong()
        val currentDateTime = LocalDateTime.now().plusDays(Dauer)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        return currentDateTime.format(formatter)
    }
}