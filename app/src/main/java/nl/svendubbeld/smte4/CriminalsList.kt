package nl.svendubbeld.smte4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_criminals_list.*

class CriminalsList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criminals_list)

        val criminals = resources.getStringArray(R.array.names)
        lst_criminals.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, criminals)
        lst_criminals.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val name = criminals[position]
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }
}
