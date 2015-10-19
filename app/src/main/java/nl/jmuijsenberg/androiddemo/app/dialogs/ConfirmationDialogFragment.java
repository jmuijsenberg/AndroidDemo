package nl.jmuijsenberg.androiddemo.app.dialogs;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nl.jmuijsenberg.androiddemo.R;

public class ConfirmationDialogFragment extends DialogFragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_ICON = "icon";

    private int mTitleResourceId;
    private int mMessageResourceId;
    private int mIconResourceId;

    public ConfirmationDialogFragment() {
    }

    public static ConfirmationDialogFragment newInstance(int titleResourceId,
                                                         int messageResourceId,
                                                         int iconResourceId) {
        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, titleResourceId);
        args.putInt(ARG_MESSAGE, messageResourceId);
        args.putInt(ARG_ICON, iconResourceId);
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String title = null;
        String message = null;
        Drawable icon = null;

        if (getArguments() != null) {
            title = getString(getArguments().getInt(ARG_TITLE));
            message = getString(getArguments().getInt(ARG_MESSAGE));
            icon = getResources().getDrawable(getArguments().getInt(ARG_ICON));
        }

        builder.setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.cancel), null);

        return builder.create();
    }
}
