package filmShop

class Db {
    val filmListe = arrayListOf<Film>(
            Film("Star Wars Epsode 1",0, 8.6, true),
            Film("Atomic Blonde",1, 10.5, true),
            Film("Abgang mit Stil",2, 5.6, true),
            Film("Deepwater Horizon",3, 9.6, true),
            Film("Spaceballs",0, 9.6, true),
            Film("Demokrat Läppli",4, 15.8, true))

    val genre  = arrayListOf<String>(
            "SciFi", "Action", "Comedy", "Drama", "Schweiz")
    val mietPreisInProzent = 50
    val mietDauerInTagen =  7
}