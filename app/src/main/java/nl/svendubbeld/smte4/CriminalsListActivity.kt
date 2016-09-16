package nl.svendubbeld.smte4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_criminals_list.*

class CriminalsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criminals_list)

        lst_criminals.layoutManager = LinearLayoutManager(this)

        val adapter = CriminalAdapter(CriminalProvider(applicationContext).getCriminals()) { criminal: Criminal, position: Int ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
        lst_criminals.adapter = adapter
    }
}
