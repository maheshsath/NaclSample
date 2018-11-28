package com.master.Mahesh;

import android.content.Context;
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

import java.util.ArrayList;

public class MyAdapterRecyclerview extends RecyclerView.Adapter<MyAdapterRecyclerview.MyViewHolder> {

    public static ArrayList<MyDataLiust> returnData = new ArrayList<>();
    public ArrayList<MyDataLiust> totaldata = new ArrayList<>();
    public MyAdapterRecyclerview ro;
    String TAG = "MyAdapterRecyclerview";
    Context context;
    int item;
    ArrayList<String> data;
    String val1 = "0";
    String val2 = "0";
    String val3 = "0";
    Integer val11 = 0, val22 = 0, val33 = 0, val44;
    int var = 30;
    String row1, row2, row3, row4;
    MyDataLiust m1;
    MyDataLiust m2;
    MyDataLiust m3;
    private int total = 0;
    private CallBackListener mCustomeListener;

    public MyAdapterRecyclerview(Context context, int lv_harvesting_item, ArrayList<String> hsd) {
        this.context = context;
        this.item = lv_harvesting_item;
        this.data = hsd;


        for (int i = 0; i < hsd.size(); i++) {
            MyDataLiust m = new MyDataLiust();
            m.setVal1(0);
            m.setVal2(0);
            m.setVal3(0);

            totaldata.add(m);

        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.line1.setText(data.get(position));


        holder.e1.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                int data = doSumPro(position, holder.e1.getText().toString().trim(), holder.e2.getText().toString().trim(), holder.e3.getText().toString().trim());
                holder.e4.setText("" + data);
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


                int data = doSumPro(position, holder.e1.getText().toString().trim(), holder.e2.getText().toString().trim(), holder.e3.getText().toString().trim());
                holder.e4.setText("" + data);

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


                int data = doSumPro(position, holder.e1.getText().toString().trim(), holder.e2.getText().toString().trim(), holder.e3.getText().toString().trim());
                holder.e4.setText("" + data);

            }
        });


        Log.e(TAG, "size ===>>> " + totaldata.size());
    }

    private int doSumPro(int position, String val1, String val2, String val3) {
        MyDataLiust m11 = new MyDataLiust();

        int data1 = 0;
        int data2 = 0;
        int data3 = 0;
        if (!val1.equalsIgnoreCase("")) {
            data1 = Integer.parseInt(val1);
            m11.setVal1(data1);
        } else {
            m11.setVal1(0);
        }
        if (!val2.equalsIgnoreCase("")) {
            data2 = Integer.parseInt(val2);
            m11.setVal2(data2);
        } else {
            m11.setVal2(0);
        }
        if (!val3.equalsIgnoreCase("")) {
            data3 = Integer.parseInt(val3);
            m11.setVal3(data3);
        } else {
            m11.setVal3(0);

        }
        int data4 = data1 + data2 + data3;


        if (totaldata.size() > 0) {

            try {
                totaldata.remove(position);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        totaldata.add(position, m11);

        return data4;


    }

    public ArrayList<MyDataLiust> getData() {
        Log.e(TAG, "Total data list size is " + returnData.size());
        Log.e(TAG, " data list size is " + returnData.size());
        return totaldata;
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

}

