package utility

class Geldautomat {
    fun bezahlen(preis: Double): Boolean {
        var istBetrag = 0.0
        var sollBetrag = preis
        aufforderungBezahlen(sollBetrag)
        istBetrag = Benutzer().liesZahlEinDouble("", 0.0, 100.0)

        while (istBetrag < sollBetrag) {
            aufforderungBezahlen(rundeBetrag(sollBetrag - istBetrag))
            sollBetrag = sollBetrag - istBetrag
            istBetrag = Benutzer().liesZahlEinDouble("", 0.0, 100.0)
        }
        if (istBetrag == sollBetrag) {
            return true
        }
        if (istBetrag > sollBetrag) {
            rueckgeld(istBetrag, sollBetrag)
            return true
        }
        return false
    }

    private fun aufforderungBezahlen(Betrag: Double) {
        println("Wirf %.2f".format(Betrag) + " CHF ein:")
    }

    private fun rueckgeld(istBetrag: Double, sollBetrag: Double) {
        println("%.2f".format(istBetrag - sollBetrag) + " CHF Rückgeld wird ausgegeben")
    }

    private fun rundeBetrag(betrag: Double): Double {
        return Math.round(betrag * 100.0) / 100.0
    }
}