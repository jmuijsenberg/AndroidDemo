package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import nl.jmuijsenberg.androiddemo.R;

public class LoginDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_login, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setTitle("login")
                .setView(view)
                // Add action buttons
                .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    // See http://adilatwork.blogspot.mx/2012/11/android-dialogfragment-dialog-sizing.html
    @Override
    public void onStart() {
        super.onStart();

        int dialogWidth = 1000;
        int dialogHeight = 500;

        if (getDialog() != null) {
            getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
        }
    }
}
