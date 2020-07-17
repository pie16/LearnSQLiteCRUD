package com.example.learnsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> items;
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

        items = new ItemStorage(this).getItems();
        ListView listView = findViewById(R.id.item_list);
        ArrayAdapter<Item> adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
    }

    private class MyAdapter extends ArrayAdapter<Item> {

        public MyAdapter(Context context) {
            super(context, R.layout.simple_list_item, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Item item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_list_item, null);
            }
            ((TextView) convertView.findViewById(R.id.textView))
                    .setText(item.firstItem);
            ((TextView) convertView.findViewById(R.id.textView2))
                    .setText(item.secondItem);
            return convertView;
        }
    }


}
