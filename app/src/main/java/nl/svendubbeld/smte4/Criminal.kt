package nl.svendubbeld.smte4

import android.graphics.drawable.Drawable
import android.location.Location

class Criminal {

    var name: String? = null
    var gender: String? = null
    var description: String? = null
    var age: Int = 0

    var crimes: MutableList<Crime>? = null

    var mugshot: Drawable? = null
    var lastKnownLocation: Location? = null

    val bountyInDollars: Int get() = crimes?.sumBy { it.bountyInDollars } ?: 0
}