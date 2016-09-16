package nl.svendubbeld.smte4

import java.text.NumberFormat

class Crime {

    var name: String? = null
    var bountyInDollars: Int = 0
    var description: String? = null

    val bountyDisplay: String get() = NumberFormat.getCurrencyInstance().format(bountyInDollars)
}