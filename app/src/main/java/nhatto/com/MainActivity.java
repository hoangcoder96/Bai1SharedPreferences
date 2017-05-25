package nhatto.com;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String SHARED_PREFERENCES_NAME = "Nhatto";
    private final String MY_NAME = "my_name";
    private final String AGE = "age";
    private final String IS_SINGLE = "is_single";
    private final String WEIGHT = "weight";
    private Button btnSaveData;
    private Button btnReadData;
    private Button btnRemoveByKey;
    private Button btnRemoveAll;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        btnRemoveByKey = (Button) findViewById(R.id.btn_remove_by_key);
        btnRemoveAll = (Button) findViewById(R.id.btn_remove_all);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
        btnRemoveByKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeByKey(MY_NAME);
                readData();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
            }
        });
    }

    private void addData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MY_NAME, "Hoang Nhat To");
        editor.putInt(AGE, 21);
        editor.putBoolean(IS_SINGLE, false);
        editor.putLong(WEIGHT, 45);

        editor.apply();
        Toast.makeText(MainActivity.this, "Save Successfully", Toast.LENGTH_SHORT).show();

    }

    private void readData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(MY_NAME, "Nhat");
        int age = sharedPreferences.getInt(AGE, 0);
        boolean is_single = sharedPreferences.getBoolean(IS_SINGLE, false);
        long weight = sharedPreferences.getLong(WEIGHT, 0);
        String address = sharedPreferences.getString("ADDRESS", "Ninh Binh");

        Log.d(TAG, "Nhatto: "
                + "Name:" + name + "\n"
                + "Age:" + age + "\n"
                + "Single:" + is_single + "\n"
                + "Weight:" + weight + "\n"
                + "Address:" + address);
    }

    private void removeByKey(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    private void removeAll(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
