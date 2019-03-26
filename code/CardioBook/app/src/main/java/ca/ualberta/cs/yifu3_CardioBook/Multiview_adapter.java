package ca.ualberta.cs.yifu3_CardioBook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * connect with the list_item layout
 */
public class Multiview_adapter extends BaseAdapter {
    private Context context;
    public static LayoutInflater inflater = null;
    public ArrayList<Information> list;

    /**
     *
     * @return list.size()
     * returns total of items in the list
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     *
     * @param position
     * @return Object
     * returns list item at the specified position
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     *
     * @param position
     * @return position
     * get item id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * constuctor
     * @param context
     * @param list
     */
    public Multiview_adapter(Context context, ArrayList<Information> list) {
        this.context = context;
        this.list = list;
    }


    /**
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return View
     * make the elements can be display into the main activity
     * also set the limit , when the pressure above or lower than the limit , will change the color
     *
     *
     */
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        if (row == null) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, null);
        }

        TextView item_diastolic = (TextView) row.findViewById(R.id.item_diastolic);
        TextView item_systolic = (TextView) row.findViewById(R.id.item_systolic);
        TextView item_date = (TextView) row.findViewById(R.id.item_date);
        TextView item_heartrate = (TextView) row.findViewById(R.id.item_heartrate);

        /**
         * set color for textview
         * resource from:https://stackoverflow.com/questions/4602902/how-to-set-the-text-color-of-textview-in-code
         */
        if(list.get(i).getDiastolicpressure() < 60 || list.get(i).getDiastolicpressure() > 90
                || list.get(i).getSystolicpressure() < 90 || list.get(i).getSystolicpressure() > 140){
            item_heartrate.setTextColor(Color.RED);
            item_date.setTextColor(Color.RED);
            item_diastolic.setTextColor(Color.RED);
            item_systolic.setTextColor(Color.RED);
        }

        /*
         * set the textview item date with format
         */
        Date temp = list.get(i).getDate();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String temp_date = df.format(temp);
        item_date.setText(temp_date);

        /*
        set the textview item for display for diastolic systolic and heartrate
         */
        item_diastolic.setText("diastolic pressure:" + Integer.toString(list.get(i).getDiastolicpressure()) + " mm Hg" );
        item_systolic.setText("systolic pressure: " +Integer.toString(list.get(i).getSystolicpressure()) + " mm Hg");
        item_heartrate.setText("heart rate: " + Integer.toString(list.get(i).getHeartrate()) + "/min");


        return row;
    }
}
