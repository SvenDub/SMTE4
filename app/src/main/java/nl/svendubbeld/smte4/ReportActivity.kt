package nl.svendubbeld.smte4

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : AppCompatActivity(), LocationListener {

    val REQUEST_CODE_PERMISSION_LOCATION = 1

    var position: Int = -1

    val criminalProvider by lazy { CriminalProvider(applicationContext) }
    var criminal: Criminal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", -1)
        }

        val criminal = criminalProvider.getCriminal(position)
        if (criminal !== null) {
            with(criminal) {
                txt_name.text = name
                img_mugshot.setImageDrawable(mugshot)
                btn_gps.setOnClickListener {
                    val coords = "" + lastKnownLocation!!.latitude + "," + lastKnownLocation!!.longitude
                    val geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$coords?q=$coords"))
                    startActivity(geoIntent)
                }

                btn_share.setOnClickListener {
                    val coords = "" + lastKnownLocation!!.latitude + "," + lastKnownLocation!!.longitude
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Help me find $name! " + (if (gender.equals("Male")) "H" else "Sh") + "e is $age years old.\n\nDetails:\n$description\n\nLast know location:\n$coords")
                    shareIntent.type = "text/plain"
                    startActivity(Intent.createChooser(shareIntent, "Share"))
                }
            }
        } else {
            txt_name.text = resources.getString(R.string.criminal_not_found)
        }

        this.criminal = criminal

        btn_back.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE_PERMISSION_LOCATION)
            return
        }
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 0f, this)
    }

    private fun stopLocationUpdates() {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.removeUpdates(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_PERMISSION_LOCATION -> if (grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Location disabled", Toast.LENGTH_SHORT).show()
            } else {
                startLocationUpdates()
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        if (criminal != null) {
            with(criminal!!) {
                Log.d("Location", "Me: " + location)
                Log.d("Location", "Them: " + lastKnownLocation)

                if (btn_sca.isChecked) {
                    val distanceTo = location.distanceTo(lastKnownLocation)
                    Toast.makeText(this@ReportActivity, "Distance: " + distanceTo + "m", Toast.LENGTH_SHORT).show()
                    if (distanceTo < 100) {
                        val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        val pattern = longArrayOf(20, 50, 100, 200, 40, 100)
                        vibrator.vibrate(pattern, -1)
                    }
                }
            }
        }
    }

    override fun onProviderDisabled(provider: String?) {
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }
}
