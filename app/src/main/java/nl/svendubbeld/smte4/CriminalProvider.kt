package nl.svendubbeld.smte4

import android.content.Context
import android.location.Location
import java.util.*

class CriminalProvider
/**
 * Constructor of the CriminalProvider.
 * @param context The context (i.e. the activity) that is using this provider.
 */
(private val context: Context) {

    init {
        if (criminalList.isEmpty()) {
            fillCriminalList()
        }
    }

    /**
     * Get the list with all stored criminals
     * @return the list with criminals
     */
    fun getCriminals(): List<Criminal> = criminalList

    /**
     * Get a specific criminal
     * @param index the index of the criminal in the list
     * *
     * @return the criminal
     */
    fun getCriminal(index: Int): Criminal? {
        try {
            return criminalList[index]
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * Creates a criminals.
     */
    private fun fillCriminalList() {
        //Load criminal and crime information from resource xml files (see res/values folder):
        val criminalNames = context.resources.getStringArray(R.array.criminalNames)
        val criminalDetails = context.resources.getStringArray(R.array.criminalDetails)

        val drawableIds = intArrayOf(R.drawable.mugshot1, R.drawable.mugshot2, R.drawable.mugshot3, R.drawable.mugshot4, R.drawable.mugshot5)

        val crimeNames = context.resources.getStringArray(R.array.crimeNames)
        val crimeDetails = context.resources.getStringArray(R.array.crimeDetails)

        for (criminalIndex in criminalNames.indices) {
            val someCriminal = Criminal()

            someCriminal.name = criminalNames[criminalIndex]
            someCriminal.description = criminalDetails[criminalIndex]

            val drawableId = drawableIds[criminalIndex % drawableIds.size]
            someCriminal.mugshot = context.resources.getDrawable(drawableId, context.theme)

            val r = Random()
            someCriminal.gender = if (r.nextBoolean()) "Male" else "Female"
            someCriminal.age = 10 + r.nextInt(100)

            someCriminal.lastKnownLocation = Location("")
            someCriminal.lastKnownLocation!!.latitude = -180.0 + r.nextDouble() * 180.0
            someCriminal.lastKnownLocation!!.longitude = -180.0 + r.nextDouble() * 180.0

            someCriminal.crimes = ArrayList<Crime>()
            val maxNumberOfCrimes = context.resources.getStringArray(R.array.crimeNames).size
            val numCrimes = 1 + r.nextInt(maxNumberOfCrimes)
            for (c in 0..numCrimes - 1) {
                val crime = createRandomCrime(crimeNames, crimeDetails)
                someCriminal.crimes!!.add(crime)
            }

            criminalList.add(someCriminal)
        }

        Collections.shuffle(criminalList)
    }

    /**
     * Creates a random crime. The name and description are picked from the resource files.
     * @return The random crime.
     */
    private fun createRandomCrime(crimeNames: Array<String>, crimeDetails: Array<String>): Crime {
        val randomCrime = Crime()
        val r = Random()

        var randomInt = r.nextInt(crimeNames.size)
        randomCrime.name = crimeNames[randomInt]

        randomInt = r.nextInt(crimeDetails.size)
        randomCrime.description = crimeDetails[randomInt]

        randomCrime.bountyInDollars = r.nextInt(100000)

        return randomCrime
    }

    companion object {

        /**
         * This list contains the generated criminals.
         * Notice the static. This means that all CriminalProvider classes share the same list.
         */
        private var criminalList: MutableList<Criminal> = ArrayList()
    }
}