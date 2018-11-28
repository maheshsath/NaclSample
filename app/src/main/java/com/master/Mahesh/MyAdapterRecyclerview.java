package com.master.Mahesh;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.master.Mahesh.callBack.CallBackListener;
import com.master.Mahesh.room.RowDatabase;
import com.master.Mahesh.room.RowEntity;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyAdapterRecyclerview extends RecyclerView.Adapter<MyAdapterRecyclerview.MyViewHolder> {

    String TAG = "MyAdapterRecyclerview";

    Context context;
    int item;
    ArrayList<String> data;
    String val1 = "0";
    String val2 = "0";
    String val3 = "0";
    private int total = 0;
    Integer val11 = 0, val22 = 0, val33 = 0, val44;
    public  ArrayList<MyDataLiust> totaldata = new ArrayList<>();
    public static ArrayList<MyDataLiust> returnData = new ArrayList<>();
    public  MyAdapterRecyclerview ro;
    int var = 30;

    String row1,row2,row3,row4;


    public MyAdapterRecyclerview(Context context, int lv_harvesting_item, ArrayList<String> hsd) {
        this.context = context;
        this.item = lv_harvesting_item;
        this.data = hsd;


    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView line1, e1, e2, e3, e4;

        public MyViewHolder(View itemView) {
            super(itemView);

            line1 = (TextView) itemView.findViewById(R.id.one);
            e1 = (EditText) itemView.findViewById(R.id.e1);
            e2 = (EditText) itemView.findViewById(R.id.e2);
            e3 = (EditText) itemView.findViewById(R.id.e3);
            e4 = (EditText) itemView.findViewById(R.id.e4);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

         /*row1 = holder.e1.getText().toString();
         row2 = holder.e2.getText().toString();
         row3 = holder.e3.getText().toString();
         row4 = holder.e4.getText().toString();*/

        holder.line1.setText(data.get(position));

        final MyDataLiust m1 = new MyDataLiust();

        holder.e1.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                val1 = holder.e1.getText().toString().trim();

                if (!val1.equalsIgnoreCase("")) {


                    val11 = Integer.parseInt(val1);


                    m1.setVal1(val11);
                    //m1.setVal2(val22);
                    // m1.setVal3(val33);
                    totaldata.add(position, m1);
                    total = doSum(position);
                    m1.setVal4(total);
                    totaldata.add(position,m1);
                    holder.e4.setText("" + total);

                } else if (val1.equalsIgnoreCase("")) {
                    val11 = 0;
                    m1.setVal1(val11);

                    totaldata.add(position, m1);
                    total = doSum(position);
                    m1.setVal4(total);
                    totaldata.add(position,m1);
                    holder.e4.setText("" + total);
                }
            }

        });
        holder.e2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                val2 = holder.e2.getText().toString().trim();

                if (!val2.equalsIgnoreCase("")) {

                    val22 = Integer.parseInt(val2);


                    //m1.setVal1(val11);
                    m1.setVal2(val22);
                    // m1.setVal3(val33);
                    
                    totaldata.add(position, m1);
                    total = doSum(position);
                    m1.setVal4(total);
                    totaldata.add(position,m1);
                    holder.e4.setText("" + total);
                    Log.e(TAG, "afterTextChanged val22 (!val22.equalsIgnoreCase() ");
                } else if (val2.equalsIgnoreCase("")) {
                    val22 = 0;
                    //m1.setVal1(val11);
                    m1.setVal2(val22);
                    //m1.setVal3(val33);
                    
                    totaldata.add(position, m1);
                    total = doSum(position);
                    m1.setVal4(total);
                    totaldata.add(position,m1);
                    holder.e4.setText("" + total);
                }


            }
        });
        holder.e3.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                val3 = holder.e3.getText().toString().trim();

                if (!val3.equalsIgnoreCase("")) {


                    val33 = Integer.parseInt(val3);

                    //m1.setVal1(val11);
                    //m1.setVal2(val22);
                    m1.setVal3(val33);
                    

                    totaldata.add(position, m1);
                    total = doSum(position);
                    m1.setVal4(total);
                    totaldata.add(position,m1);
                    holder.e4.setText("" + total);
                    Log.e(TAG, "afterTextChanged val3 (!val3.equalsIgnoreCase() ");
                } else if (val3.equalsIgnoreCase("")) {
                    val33 = 0;

                    //m1.setVal1(val11);
                    //m1.setVal2(val22);
                    m1.setVal3(val33);
                    
                    totaldata.add(position, m1);
                    total = doSum(position);
                    m1.setVal4(total);
                    totaldata.add(position,m1);
                    holder.e4.setText("" + total);

                }


            }
        });


        Log.e(TAG, "size ===>>> " + totaldata.size());
    }

    public ArrayList<MyDataLiust> getData() {
        Log.e(TAG, "Total data list size is " + returnData.size());
        Log.e(TAG, " data list size is " + returnData.size());
        return returnData;
    }

    public void addListToReturn(int val1,int val2,int val3,int val4,int pos){
        MyDataLiust m = new MyDataLiust();
        m.setVal1(val1);
        m.setVal2(val2);
        m.setVal3(val3);
        m.setVal4(val4);

        Log.e(TAG,"Size of return List size"+returnData.size());
        Log.e(TAG,"position to add the data to the list "+pos);
        if(returnData.size()==0){
            returnData.add(pos,m);
        }else{
            if(pos==0){
                returnData.remove(pos);
                returnData.add(pos,m);
            }else if(pos==1){
                if(returnData.size()==1){
                    returnData.add(pos,m);
                }else{
                    returnData.remove(pos);
                    returnData.add(pos,m);
                }
            }else{
                if(returnData.size()==2){
                    returnData.add(pos,m);
                }else{
                    returnData.remove(pos);
                    returnData.add(pos,m);
                }
            }
        }
    }


    private int doSum(int position) {
        int sum = totaldata.get(position).getVal1() + totaldata.get(position).getVal2() + totaldata.get(position).getVal3();
        //addListToReturn(totaldata.get(position).getVal1(),totaldata.get(position).getVal2(),totaldata.get(position).getVal3(),sum,position);
        return sum;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e(TAG, "onViewDetachedFromWindow");
        saveData("onViewDetachedFromWindow");
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.e(TAG, "onDetachedFromRecyclerView");
        saveData("onDetachedFromRecyclerView");
    }

    private void saveData(String onViewDetachedFromWindow) {
        Log.e(TAG, "===>>> " + "saveData " + onViewDetachedFromWindow);
    }

    private CallBackListener mCustomeListener;

    public void setmCustomeListener(CallBackListener mCustomeListener) {
        this.mCustomeListener = mCustomeListener;
        doSomeTask();
    }

    private void doSomeTask() {
        //check if listener is set or not.
        if (mCustomeListener == null)
            return;
        else
            mCustomeListener.getData("sdafasdfafd  asdfasdfdfdfadf");
    }

}

