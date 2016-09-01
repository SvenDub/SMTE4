package nl.svendubbeld.smte4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mTxtName;
    private Button mBtnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtName = (EditText) findViewById(R.id.txt_name);
        mBtnName = (Button) findViewById(R.id.btn_name);

        mBtnName.setOnClickListener(view -> {
            String name = "World";
            if (!mTxtName.getText().toString().isEmpty()) {
                name = mTxtName.getText().toString();
            }
            Toast.makeText(MainActivity.this, "Hello " + name + "!", Toast.LENGTH_SHORT).show();
        });
    }
}
