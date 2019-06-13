package org.jing1578.basicapplication.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by jing1578 on 2016/12/19.
 * 在 setCancelable()中传入了 false，表示 ProgressDialog 是不能通过 Back 键取消掉的
 * dialog.dismiss();
 */

public class CommonDialog {

   private void  commonAlertDialog(Context context){
       AlertDialog.Builder dialog = new AlertDialog.Builder
               (context);
       dialog.setTitle("This is Dialog");
       dialog.setMessage("Something important.");
       dialog.setCancelable(false);
       dialog.setPositiveButton("OK", new DialogInterface.
               OnClickListener() {

           @Override
           public void onClick(DialogInterface dialog, int which) {
           }
       });
       dialog.setNegativeButton("Cancel", new DialogInterface.
               OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
           }
       });
       dialog.show();
    }


    private void  commonProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog
                (context);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }



}
