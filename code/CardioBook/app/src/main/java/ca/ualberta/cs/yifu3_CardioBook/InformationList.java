package ca.ualberta.cs.yifu3_CardioBook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class InformationList {
    private ArrayList<Information> information_list = new ArrayList<Information>();
    private static final String FILENAME = "Cadiobook.sav";

    /**
     * get the specific information from information list
     * @return ArrayList<Information>
     */
    public ArrayList<Information> getInformation_list() {
        return information_list;
    }

    /**
     * add a information to information list
     * @param new_information
     */
    public void add(Information new_information) {
        this.information_list.add(new_information);
    }

    /**
     * delect a information from information list base on index
     * @param i
     */
    public void delect(int i) {
        this.information_list.remove(i);
    }

    /**
     * // basicaly copy from lonely tweeter
     * @param context
     */
    public void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(this.information_list, writer);
            writer.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * // basicaly copy from lonely tweeter
     * @param context
     */
    public void loadFromFile(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Information>>() {
            }.getType();
            //take from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            information_list = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            information_list = new ArrayList<Information>();

        }
    }

}

