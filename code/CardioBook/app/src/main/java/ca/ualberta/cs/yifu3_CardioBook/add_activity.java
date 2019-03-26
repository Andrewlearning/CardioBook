package ca.ualberta.cs.yifu3_CardioBook;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class add_activity extends Activity {

    private TextView add_date;
    private TextView add_time;
    private TextView add_systolic;
    private TextView add_diastolic;
    private TextView add_heart;
    private TextView add_commont;
    private Button add_back;
    private Button add_save;
    private InformationList store_information_List = new InformationList();
    private Context context;

    /**
     * connect the layout and the add_activity, and get the value from layout(user input)
     * and deal with the value and save the file, add the information into the information_list
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        context = getApplicationContext();

        //get the data form the user type in
        add_time = (TextView) findViewById(R.id.time_text);
        add_date = (TextView) findViewById(R.id.date_text);
        add_heart = (TextView) findViewById(R.id.heart_text);
        add_systolic = (TextView) findViewById(R.id.systolic_text);
        add_diastolic = (TextView) findViewById(R.id.diastolic_text);
        add_commont = (TextView) findViewById(R.id.commont_text);
        add_save = (Button)findViewById(R.id.save);
        add_back = (Button)findViewById(R.id.back);

        /**
         * set the usage for back button
         */
        add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(add_activity.this, MainActivity.class);
                startActivity(back);
            }
        });

        /**
         * set the respone of click the save button
         * store them and pass them into the main activity
         */
        add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check the text empry or not
                if (check_empty(add_time.getText().toString(), add_date.getText().toString(),
                        add_systolic.getText().toString()
                ,add_diastolic.getText().toString(), add_heart.getText().toString()) ){

                    //check the text in writing in correct form or not
                    if (check_date_format("yyyy-MM-dd", add_date.getText().toString())
                            && check_time_format("HH:mm", add_time.getText().toString()) &&
                            Integer.parseInt(add_diastolic.getText().toString()) > 0 &&
                            Integer.parseInt(add_systolic.getText().toString()) > 0 &&
                            Integer.parseInt(add_heart.getText().toString()) > 0
                            && add_commont.getText().toString().length() <= 20) {

                            Intent back_to_mainactivity = new Intent(add_activity.this, MainActivity.class);

                            store_information_List.loadFromFile(context);
                            Information new_information = new Information();


                            /**
                             * format the date
                             *
                             * set new date to targer information
                             *
                             */
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date new_date = new Date();
                            try {
                                new_date = format.parse(add_date.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            new_information.setDate(new_date);

                            /**
                             * set new comment to target book
                             */
                            new_information.setComment(add_commont.getText().toString());

                            new_information.setTime(add_time.getText().toString());

                            /**
                             * set new systolic to target information
                             */
                            new_information.setSystolicpressure(Integer.parseInt(add_systolic.getText().toString()));
                            /**
                             * set new distolicrpress to target information
                             */
                            new_information.setDiastolicpressure(Integer.parseInt(add_diastolic.getText().toString()));
                            /**
                             * set new heartrate to target information
                             */
                            new_information.setHeartrate(Integer.parseInt(add_heart.getText().toString()));

                        /**
                         * add the new_information into the informationlist
                         * and then save it into the file
                         */
                        store_information_List.add(new_information);
                            store_information_List.saveInFile(context);

                            startActivity(back_to_mainactivity);
                        } else {
                            Toast toast = Toast.makeText(context, "Enter date in 'yyyy-MM-dd' or " +
                                    " Enter time in 'hh:mm' in correct format , also all" +
                                    " integer should be positive", Toast.LENGTH_SHORT);
                            toast.show(); }


                    } else {
                            Toast toast = Toast.makeText(context, "please enter all the value ", Toast.LENGTH_SHORT);
                            toast.show(); }





               }


        });

    }

    /**
     *
     * @param date
     * @param time
     * @param systolic
     * @param diastolic
     * @param heart
     * @return boolean
     */
    private boolean check_empty(String date, String time,  String systolic ,String diastolic , String heart ){
        if (time.length() == 0 || date.length() == 0 ||  systolic.length() == 0 || diastolic.length() == 0
                || heart.length() == 0){
            return false;
        }
        return true;
    }

    /**
     * check_date_format
     * check if the date format is correct
     * @param format
     * @param value
     * @return
     *
     * source:https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
     */

    private boolean check_date_format(String format, String value){
        Date d = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            d = sdf.parse(value);
            if (!value.equals(sdf.format(d))){
                d = null;
            }
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return d != null;
    }

    /**
     *check the time format
     * @param format
     * @param value
     * @return boolean
     */
    private boolean check_time_format(String format, String value){
        Date t = null;
        try{
            SimpleDateFormat stf = new SimpleDateFormat(format);
            t = stf.parse(value);
            if (!value.equals(stf.format(t))){
                t = null;
            }
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return t != null;
    }



}
