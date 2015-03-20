package com.example.mehari.moderartuilab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.net.URI;


public class MainActivity extends ActionBarActivity {


    private static final String TAG = "ModernArtUI";
    private DialogFragment mDialog;
    LinearLayout box1;
    LinearLayout box2;
    LinearLayout box3;
    LinearLayout box4;
    LinearLayout box5;
    int box1Theme=0x2196f3;
    int box2Theme=0xf44336;
    int box4Theme=0xffeb3b;
    int box5Theme=0x4caf50;




    private SeekBar mseekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        box1= (LinearLayout) findViewById(R.id.box1);
        box2= (LinearLayout) findViewById(R.id.box2);
        box3= (LinearLayout) findViewById(R.id.box3);
        box4= (LinearLayout) findViewById(R.id.box4);
        box5= (LinearLayout) findViewById(R.id.box5);





         mseekBar= (SeekBar) findViewById(R.id.seekBar);

         mseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                 box1.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(box1Theme+ progress )));

                 box2.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(box2Theme+ progress  )));

                 box4.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(box4Theme+ progress )));

                 box5.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(box5Theme+ progress )));



/**
                        if (progress <=10) {

                         box1.setBackgroundColor(Color.parseColor("#0D47A1"));
                         box2.setBackgroundColor(Color.parseColor("#B71C1C"));

                        }
                     if (progress <= 20 && progress>10) {
                         box1.setBackgroundColor(Color.parseColor("#1565C0"));
                         box2.setBackgroundColor(Color.parseColor("#C62828"));
                     }

                     if (progress <= 30 && progress>20) {
                         box1.setBackgroundColor(Color.parseColor("#1976D2"));
                         box2.setBackgroundColor(Color.parseColor("#D32F2F"));
                     }

                        if (progress <= 40 && progress>30 ) {

                            box1.setBackgroundColor(Color.parseColor("#1E88E5"));
                            box2.setBackgroundColor(Color.parseColor("#E53935"));
                        }
                        if (progress <= 50 && progress>40) {

                            box1.setBackgroundColor(Color.parseColor("#2196F3"));
                            box2.setBackgroundColor(Color.parseColor("#F44336"));

                        }
                        if (progress <= 60 && progress>50) {

                            box1.setBackgroundColor(Color.parseColor("#42a5F5"));
                            box2.setBackgroundColor(Color.parseColor("#EF5350"));
                        }
                        if (progress <= 70 && progress>60 ) {


                            box1.setBackgroundColor(Color.parseColor("#64B5F6"));
                            box2.setBackgroundColor(Color.parseColor("#E57373"));
                        }
                        if (progress <= 80 && progress>70) {


                            box1.setBackgroundColor(Color.parseColor("#90CAF9"));
                            box2.setBackgroundColor(Color.parseColor("#EF9A9A"));
                        }
                        if (progress <= 90 && progress>80) {
                            box1.setBackgroundColor(Color.parseColor("#BBDEFB"));
                            box2.setBackgroundColor(Color.parseColor("#FFCDD2"));
                        }

                        if (progress >= 100) {
                            box1.setBackgroundColor(Color.parseColor("#E3F2FD"));
                            box2.setBackgroundColor(Color.parseColor("#FFEBEE"));
                        }
**/


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_MoreInfo) {

            // Create a new AlertDialogFragment
            mDialog = AlertDialogFragment.newInstance();
            // Show AlertDialogFragment
            mDialog.show(getFragmentManager(), "Alert");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Abort or complete ShutDown based on value of shouldContinue
    private void continueShutdown(boolean shouldContinue) {

        if (shouldContinue) {
                //create and start an intent to visit MOMA
            Uri webPage= Uri.parse("http://www.MoMA.org");
            Intent visitMoma=new Intent(Intent.ACTION_VIEW,webPage);

            startActivity(visitMoma);


        } else {

            // Abort ShutDown and dismiss dialog
            mDialog.dismiss();
        }

    }


    public static class  AlertDialogFragment extends DialogFragment{


        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)
                    .setNegativeButton(R.string.not_now,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ((MainActivity) getActivity())
                                            .continueShutdown(false);
                                }
                            }
                    )
                            // Set up Yes Button
                    .setPositiveButton(R.string.visit_moma,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    ((MainActivity) getActivity())
                                            .continueShutdown(true);
                                }
                            }).create();




        }
    }
}
