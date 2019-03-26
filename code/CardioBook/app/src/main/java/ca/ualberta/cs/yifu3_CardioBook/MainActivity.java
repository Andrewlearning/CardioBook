/*
 * Copyright <YEAR> <COPYRIGHT HOLDER>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package ca.ualberta.cs.yifu3_CardioBook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import static android.content.ContentValues.TAG;

/**
 * Represent a CardioBook
 *
 * @author yifu chen
 * @version 1.0
 * @since 1.0
 *
 */
public class MainActivity extends Activity {
    private ListView oldinformationList;
    private Multiview_adapter adapter;
    private InformationList store_information_List = new InformationList();
    private ArrayList<Information> information_List = new ArrayList<Information>();
    private Context context;

    /** Called when the activity is first created. */
    /**
     *
     * @param savedInstanceState creat the save button and clear button , and make the save button can save the information
     * and clear button can delect all the tweet in the listMul
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /**
         * get the data from saving file
         */
        store_information_List = new InformationList();
        context = getApplicationContext();
        store_information_List.loadFromFile(context);


        /**
         * set the listview clicklisterner
         */
        oldinformationList = (ListView) findViewById(R.id.oldinfomationList);
        oldinformationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent info1 = new Intent(MainActivity.this,edit_activity.class);
                info1.putExtra("info", i);

                startActivity(info1);

            }


        });

        /**
         * set the usage for add button
         */
        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_activity.class);
                startActivity(intent);
            }
        });


    }


    /**
     *display the information list onto the activity
     */
    @Override
    protected void onStart(){
        super.onStart();
        adapter = new Multiview_adapter(this,store_information_List.getInformation_list());
        oldinformationList.setAdapter(adapter);
    }


}