package nl.svendubbeld.smte4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    var position: Int = -1

    val criminalProvider by lazy { CriminalProvider(applicationContext) }
    var criminal: Criminal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", -1)
        }

        val criminal = criminalProvider.getCriminal(position)
        if (criminal !== null) {
            with(criminal) {
                txt_name.text = name
                txt_gender.text = gender
                txt_age.text = age.toString()
                txt_bounty.text = bountyDisplay
                txt_description.text = description
                img_mugshot.setImageDrawable(mugshot)

                lst_crimes.layoutManager = LinearLayoutManager(this@MainActivity)
                val adapter = CrimeAdapter(crimes!!)
                lst_crimes.adapter = adapter
            }
        } else {
            txt_name.text = resources.getString(R.string.criminal_not_found)
        }

        this.criminal = criminal

        btn_report.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        if (criminal != null) {
            val latLng = LatLng(criminal!!.lastKnownLocation!!.latitude, criminal!!.lastKnownLocation!!.longitude)
            map.addMarker(MarkerOptions().position(latLng).title(criminal!!.name))
            map.isMyLocationEnabled = true
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3f))
        }
    }
}
