package nl.svendubbeld.smte4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : AppCompatActivity() {

    var position: Int = -1

    val criminalProvider by lazy { CriminalProvider(applicationContext) }

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
            }
        } else {
            txt_name.text = resources.getString(R.string.criminal_not_found)
        }

        btn_back.setOnClickListener {
            finish()
        }
    }
}
