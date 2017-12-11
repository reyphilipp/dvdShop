package filmShop

import utility.Zeit
import utility.Geldautomat
import utility.Benutzer

class Shop {
    private val filmDb = Db()
    private var FilmIstVorhanden = arrayListOf<Film>()
    private var FilmIstNichtVorhanden = arrayListOf<Film>()
    fun init() {
        FilmIstVorhanden = filmDb.filmListe
    }

    fun begruessung() {
        val currentDateTime = Zeit().jetztigeZeit()
        println("Guten Tag, willkommen im DVD Shop. Heute ist der: $currentDateTime")
    }

    fun auswahl() {
        var auswahl = Benutzer().liesZahlEinInt("Mit 1 wählen Sie Aufnahme, mit 2 Ausleih und mit 3 Zurückgeben", 1, 3)
        when (auswahl) {
            1 -> aufnehmen(auswahlFilm())
            2 -> ausleihen(auswahlFilm())
            3 -> zurueckgeben(auswahlFilmRueckgabe())
        }
    }

    private fun aufnehmen(Film: Int) {
        println("Sie haben den Film ${FilmIstVorhanden[Film].titel} gewählt")
        println("Der Film kostet %.2f".format(FilmIstVorhanden[Film].preis) + " CHF")
        if (Geldautomat().bezahlen(FilmIstVorhanden[Film].preis)) {
        }
    }

    private fun ausleihen(Film: Int) {
        println("Sie haben den Film ${FilmIstVorhanden[Film].titel} gewählt")
        println("Der Film kostet %.2f".format(FilmIstVorhanden[Film].preis * (filmDb.mietPreisInProzent / 100.0)) + " CHF")
        if (Geldautomat().bezahlen(FilmIstVorhanden[Film].preis * (filmDb.mietPreisInProzent / 100.0))) {
            FilmIstNichtVorhanden.add(FilmIstVorhanden[Film])
            FilmIstVorhanden.removeAt(Film)
            val currentDateTime = Zeit().jetztigeZeit()
            println("Rückgabe bis spätesten: " + Zeit().zeitSub(Db().mietDauerInTagen))
        }
    }

    private fun zurueckgeben(Film: Int) {
        if (Benutzer().liesZahlEinInt("Sie haben den Film ${FilmIstNichtVorhanden[Film].titel} gewählt. Bitte um Rückgabe [1]", 1, 1) == 1) {
            FilmIstVorhanden.add(FilmIstNichtVorhanden[Film])
            FilmIstNichtVorhanden.removeAt(Film)
        }
    }

    private fun auswahlFilm(): Int {
        var dvd: Int = 0
        var auswahl = Benutzer().liesZahlEinInt("Mit 1 wählen Sie nach Filmtitel, mit 2 nach Genre", 1, 2)
        when (auswahl) {
            1 -> dvd = auswahlFilmNachTitel()
            2 -> dvd = auswahlFilmNachGenre()
        }
        return dvd
    }

    private fun auswahlFilmRueckgabe(): Int {
        for (i in FilmIstNichtVorhanden.indices) {
            println(i.toString() + ": " + "${FilmIstNichtVorhanden[i].titel}: Kaufpreis: " + preis(i, true) + " ,Mietpreis: " + preis(i, false))

        }
        return Benutzer().liesZahlEinInt("Wähle zwischen 0 und " + FilmIstNichtVorhanden.size.minus(1), 0, FilmIstNichtVorhanden.size.minus(1))
    }

    private fun auswahlFilmNachGenre(): Int {
        for (i in filmDb.genre.indices) {
            println(i.toString() + ": " + "${filmDb.genre[i]}")
        }
        val dbGenre = Benutzer().liesZahlEinInt("Wähle zwischen 0 und " + filmDb.genre.size.minus(1), 0, filmDb.genre.size.minus(1))
        for (i in FilmIstVorhanden.indices) {
            if (filmDb.filmListe[i].genre == dbGenre) {
                println(i.toString() + ": " + "${FilmIstVorhanden[i].titel}: Kaufpreis: " + preis(i, true) + " ,Mietpreis: " + preis(i, false))
            }
        }
        return Benutzer().liesZahlEinInt("Wähle zwischen 0 und " + FilmIstVorhanden.size.minus(1), 0, filmDb.genre.size)
    }

    private fun auswahlFilmNachTitel(): Int {

        for (i in FilmIstVorhanden.indices) {
            println(i.toString() + ": " + "${FilmIstVorhanden[i].titel}: Kaufpreis: " + preis(i, true) + " ,Mietpreis: " + preis(i, false))

        }
        return Benutzer().liesZahlEinInt("Wähle zwischen 0 und " + FilmIstVorhanden.size.minus(1), 0, FilmIstVorhanden.size.minus(1))
    }


    private fun preis(Film: Int, Kauf: Boolean): String {
        if (Kauf) {
            return "%.2f".format(FilmIstVorhanden[Film].preis)
        } else {
            var Mietpreis = FilmIstVorhanden[Film].preis * (filmDb.mietPreisInProzent / 100.0)
            return "%.2f".format(Mietpreis)
        }
    }

    fun verabschiedung() {
        println("goodbye")
    }
}