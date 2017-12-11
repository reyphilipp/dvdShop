package utility

class Benutzer {
    fun liesZahlEinInt(frage: String, min: Int, max: Int): Int {
        var zahl: Int? = null
        do {
            println(frage)
            zahl = readLine()?.toIntOrNull()
        } while (zahl == null || zahl > max || zahl < min)
        return zahl
    }

    fun liesZahlEinDouble(frage: String, min: Double, max: Double): Double {
        var zahl: Double? = null
        do {
            println(frage)
            zahl = readLine()?.toDoubleOrNull()
        } while (zahl == null || zahl > max || zahl < min)
        return zahl
    }
}