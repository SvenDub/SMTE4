package nl.svendubbeld.smte4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra("name")) {
            txt_name.text = intent.getStringExtra("name")
        }

        btn_report.setOnClickListener {
            startActivity(Intent(applicationContext, ReportActivity::class.java))
        }
    }
}
