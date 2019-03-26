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

public class edit_activity extends Activity {
    private TextView edit_date;
    private TextView edit_time;
    private TextView edit_systolic;
    private TextView edit_diastolic;
    private TextView edit_heart;
    private TextView edit_commont;
    private InformationList store_information_list = new InformationList();
    private Button edit_save;
    private Button edit_delete;

    private Information view_information;
    private Information view_target_information;
    private Context context;


    /**
     *get the activity from what user click , and pass the index from mainactivity to
     * this special information activity , and user can change the data for this activity
     * and save the data into the file
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);


        /*
         *get the information from the saving file
         */
        context = getApplicationContext();
        store_information_list.loadFromFile(context);
        final Intent old_intent = getIntent();


        /**
         * get the imformation from which one we click on the main activity
         */
        int viewindex = Integer.parseInt(old_intent.getExtras().get("info").toString());
        view_information = store_information_list.getInformation_list().get(viewindex);


        /*
         *get the content from what the user type in the textview
         */
        Date temp_date = view_information.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String correct_date = df.format(temp_date);
        edit_date = (TextView) findViewById(R.id.date_text);
        edit_date.setText(correct_date);


        String temp_time = view_information.getTime();
        edit_time = (TextView) findViewById(R.id.time_text);
        edit_time.setText(temp_time);

        String temp_comment = view_information.getComment();
        edit_commont = (TextView) findViewById(R.id.commont_text);
        edit_commont.setText(temp_comment);

        Integer temp_systolic = view_information.getSystolicpressure();
        edit_systolic = (TextView) findViewById(R.id.systolic_text);
        edit_systolic.setText(temp_systolic.toString());

        Integer temp_diastolic = view_information.getDiastolicpressure();
        edit_diastolic = (TextView) findViewById(R.id.diastolic_text);
        edit_diastolic.setText(temp_diastolic.toString());

        Integer temp_heart = view_information.getHeartrate();
        edit_heart = (TextView) findViewById(R.id.heart_text);
        edit_heart.setText(temp_heart.toString());

        /*
        get the button information
         */
        edit_save = (Button) findViewById(R.id.save);
        edit_delete = (Button) findViewById(R.id.delete);



        /**
         * set the respone of click the save button
         * store them and pass them into the main activity
         */
        edit_save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if (check_empty(edit_time.getText().toString(), edit_date.getText().toString(),
                       edit_systolic.getText().toString(),edit_diastolic.getText().toString(),
                        edit_heart.getText().toString()) ){

                    if (check_date_format("yyyy-MM-dd", edit_date.getText().toString())
                            && check_time_format("HH:mm", edit_time.getText().toString()) &&
                            Integer.parseInt(edit_diastolic.getText().toString()) > 0 &&
                            Integer.parseInt(edit_systolic.getText().toString()) > 0 &&
                            Integer.parseInt(edit_heart.getText().toString()) > 0
                            && edit_commont.getText().toString().length() <= 20) {

                        Intent back_to_mainactivity = new Intent(edit_activity.this, MainActivity.class);

                        //get id of the target information that wants to edit, then use id to get the information
                        int information_id = Integer.parseInt(old_intent.getExtras().get("info").toString());
                        view_target_information = store_information_list.getInformation_list().get(information_id);

                        /**
                         * set new time to targer information
                         */
                        view_target_information.setTime(edit_time.getText().toString());

                        /**
                         * format the date
                         *
                         * set new date to targer information
                         *
                         */
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date new_date = new Date();
                        try {
                            new_date = format.parse(edit_date.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        view_target_information.setDate(new_date);

                        /**
                         * set new comment to information
                         */
                        view_target_information.setComment(edit_commont.getText().toString());


                        /**
                         * set new systolic to target information
                         */
                        view_target_information.setSystolicpressure(Integer.parseInt(edit_systolic.getText().toString()));
                        /**
                         * set new distolicrpress to target information
                         */
                        view_target_information.setDiastolicpressure(Integer.parseInt(edit_diastolic.getText().toString()));
                        /**
                         * set new heartrate to target information
                         */
                        view_target_information.setHeartrate(Integer.parseInt(edit_heart.getText().toString()));



                        store_information_list.saveInFile(context);

                        startActivity(back_to_mainactivity);
                    }else{
                        Toast toast = Toast.makeText(context,"Enter date in 'yyyy-MM-dd' or " +
                                " Enter time in 'hh:mm' in correct format , also all" +
                                " integer should be positve , comment should less than 20 chars",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }else{
                    Toast toast = Toast.makeText(context," please enter all the value",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        });

        /**
         * set delect button to delect this information
         */
        edit_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_to_main = new Intent(edit_activity.this, MainActivity.class);

                store_information_list.loadFromFile(context);
//                we using getExtras() to make sure which view is our clicking at
                int information_id = Integer.parseInt(old_intent.getExtras().get("info").toString());

                store_information_list.delect(information_id);
//                remember to save the change of the file after delect
                store_information_list.saveInFile(context);
                startActivity(back_to_main);
            }
        });


    }


    /**
     *check the all the value is empty or not expecial comment
     * @param date
     * @param time
     * @param systolic
     * @param diastolic
     * @param heart
     * @return boolean
     */
    private boolean check_empty(String date, String time , String systolic ,String diastolic , String heart ){
        if (time.length() == 0 || date.length() == 0  || systolic.length() == 0 || diastolic.length() == 0
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
     * check the time format correct or not
     * @param format
     * @param value
     * @return
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
