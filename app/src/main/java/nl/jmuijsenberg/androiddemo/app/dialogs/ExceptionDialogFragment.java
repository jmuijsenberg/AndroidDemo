package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nl.jmuijsenberg.androiddemo.R;

public class ExceptionDialogFragment  extends DialogFragment {
    private static final String ARG_EXCEPTION_MESSAGE = "message";

    public ExceptionDialogFragment() {
    }

    public static ExceptionDialogFragment newInstance(Throwable e) {
        ExceptionDialogFragment fragment = new ExceptionDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXCEPTION_MESSAGE, e.getMessage());
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String title = null;
        String message = null;
        Drawable icon = null;

        if (getArguments() != null) {
            title = getString(getArguments().getInt(ARG_EXCEPTION_MESSAGE));
        }

        builder.setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.cancel), null);

        return builder.create();
    }
}