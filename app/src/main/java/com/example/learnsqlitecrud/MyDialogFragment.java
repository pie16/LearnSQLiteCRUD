package com.example.learnsqlitecrud;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    private int id;

    public MyDialogFragment() {
        this.id = 0;
    }

    public MyDialogFragment(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Item item = new ItemStorage(getContext()).getItem(id);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.input_form, null);
        final EditText firstItem = v.findViewById(R.id.first_item);
        final EditText secondItem = v.findViewById(R.id.second_item);
        if (id != 0) {
            firstItem.setText(item.firstItem);
            secondItem.setText(item.secondItem);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Ввод")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Item item = new Item();
                                item.firstItem = firstItem.getText().toString();
                                item.secondItem = secondItem.getText().toString();
                                if (id != 0) {
                                    item.id = id;
                                    new ItemStorage(getContext()).updateItem(item);
                                }
                                new ItemStorage(getContext()).createItem(item);
                            }
                        })
                .create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().recreate();

    }


}
