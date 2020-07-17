package com.example.learnsqlitecrud;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.input_form, null);
        final TextView firstItem = v.findViewById(R.id.first_item);
        final TextView secondItem = v.findViewById(R.id.second_item);

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
