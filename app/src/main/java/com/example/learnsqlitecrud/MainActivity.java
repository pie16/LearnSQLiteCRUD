package com.example.learnsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.create_item);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(manager, "dialog");
            }
        });

        String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
                "Костя", "Игорь", "Анна", "Денис", "Андрей" };
        ListView listView = findViewById(R.id.item_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, names);
        listView.setAdapter(adapter);
    }


}
