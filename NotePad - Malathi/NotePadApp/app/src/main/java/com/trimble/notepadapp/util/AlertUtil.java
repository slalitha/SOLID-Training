package com.trimble.notepadapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.trimble.notepadapp.R;


public class AlertUtil {

    public interface IAlertCallbacks {
        void sendFilePath(String filePath);
    }

    public static void showCustomAlertDialog(Context context, final IAlertCallbacks iAlertCallbacks) {

        View promptsView = LayoutInflater.from(context).inflate(R.layout.layout_alert_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(promptsView);

        final EditText userInput = promptsView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                iAlertCallbacks.sendFilePath("/sdcard/" + userInput.getText().toString());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }


    public static void showConfirmationAlertDialog(final Context context, DialogInterface.OnClickListener clickListener) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder
                .setTitle("Confirmation Dialog")
                .setMessage("Do you want to save the changes?")
                .setCancelable(false)
                .setPositiveButton("OK", clickListener)
                .setNegativeButton("Cancel", clickListener);
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public static void CreateAlertDialogWithRadioButtonGroup(Context context,String title, String[] values, DialogInterface.OnClickListener dialogInterface) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);

        builder.setSingleChoiceItems(values, -1, dialogInterface);
        AlertDialog alertDialog1 = builder.create();
        alertDialog1.show();

    }
}
