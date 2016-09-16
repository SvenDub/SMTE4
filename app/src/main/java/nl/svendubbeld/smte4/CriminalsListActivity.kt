package nl.svendubbeld.smte4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_criminals_list.*

class CriminalsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criminals_list)

        lst_criminals.layoutManager = LinearLayoutManager(this)

        val adapter = CriminalAdapter(CriminalProvider(applicationContext).getCriminals()) {
            Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
        }
        lst_criminals.adapter = adapter
    }
}
