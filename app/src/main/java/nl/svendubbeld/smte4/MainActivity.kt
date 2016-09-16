package nl.svendubbeld.smte4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var position: Int = -1

    val criminalProvider by lazy { CriminalProvider(applicationContext) }

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

        btn_report.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }
}
