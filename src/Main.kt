package filmShop

fun main(args: Array<String>) {
    val shop = Shop()
    shop.init()
    do {
        shop.begruessung()
        shop.auswahl()
        shop.verabschiedung()
    } while (true)
}