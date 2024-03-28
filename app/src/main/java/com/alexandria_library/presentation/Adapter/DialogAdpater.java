package com.alexandria_library.presentation.Adapter;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.alexandria_library.dso.Book;

public class DialogAdpater {
    private Context context;

    public DialogAdpater(Context context){
        this.context = context;
    }

    public void dialogForSuccess(String message, Book currentViewing){
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("SUCCESSFUL!!")
                .setMessage("\"" + currentViewing.getName() + "\" Successfully added to " + message +" List!")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

    public void dialogRemoveBookSuccess(Book currentViewing){
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("SUCCESSFUL!!")
                .setMessage("\"" + currentViewing.getName() + "\" Successfully removed from Library List!")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
    public void dialogRemoveBookFailered(Book currentViewing){
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("Failed!!")
                .setMessage("\"" + currentViewing.getName() + "\" Cannot removed from Library List!")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
    public void dialogForFailedLibrarian(){
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("Not success")
                .setMessage("Librarian doesn't have any Lists")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
    public void diaglofForFailedAdd(String message) {
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("Not success")
                .setMessage(message)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
    public void dialogForSuccessRemove(String message, Book currentViewing) {
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("Successfully")
                .setMessage("Successful removed \""+currentViewing.getName() + "\" from "+message +" list")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
    public void dialogForReaderCannotChangeLibrary(){
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("FAILED!!")
                .setMessage("Readers CANNOT modify library book list!!")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
    public void dialogForFailedRemove(){
        AlertDialog show = new AlertDialog.Builder(context)
                .setTitle("FAILED!!")
                .setMessage("Deleting book from list is failed")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
