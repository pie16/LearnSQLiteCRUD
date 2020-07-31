package com.example.learnsqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
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
    private Button addButton;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.create_item);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        items = new ItemStorage(this).getItems();
        ListView listView = findViewById(R.id.item_list);
        ArrayAdapter<Item> adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
    }

    void showDialog() {
        FragmentManager manager = getSupportFragmentManager();
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(manager, "dialog");
    }

    private class MyAdapter extends ArrayAdapter<Item> {

        public MyAdapter(Context context) {
            super(context, R.layout.simple_list_item, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Item item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.simple_list_item, null);
            }
            ((TextView) convertView.findViewById(R.id.idItem))
                    .setText(String.valueOf(item.id));
            ((TextView) convertView.findViewById(R.id.firstItem))
                    .setText(item.firstItem);
            ((TextView) convertView.findViewById(R.id.secondItem))
                    .setText(item.secondItem);

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] lists = {"Edit", "Delete"};

                    new AlertDialog.Builder(getContext())
                            .setItems(lists, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int list) {
//                                    dialog.dismiss();
                                    if (list == 0) {
                                        FragmentManager manager = getSupportFragmentManager();
                                        MyDialogFragment myDialogFragment = new MyDialogFragment(item.id);
                                        myDialogFragment.show(manager, "editDialog");
                                    }
                                    if (list == 1) {
                                        new ItemStorage(getContext()).deleteItem(item.id);
                                        recreate();
                                    }
                                }
                            }).show();

                    return true;
                }
            });
            return convertView;
        }
    }


}
