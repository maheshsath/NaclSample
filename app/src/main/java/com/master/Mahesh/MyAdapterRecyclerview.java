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

import com.master.Mahesh.room.RowEntity;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRecyclerview extends RecyclerView.Adapter<MyAdapterRecyclerview.MyViewHolder> {

    public ArrayList<MyDataLiust> totaldata = new ArrayList<>();
    public MyAdapterRecyclerview ro;
    String TAG = "MyAdapterRecyclerview";
    Context context;
    int item;
    ArrayList<String> data;
    List<RowEntity> prevAvailableList;
    int editablePosition;

    public MyAdapterRecyclerview(Context context, int lv_harvesting_item, ArrayList<String> hsd, List<RowEntity> prevAvailableList, int editablePosition) {
        this.context = context;
        this.item = lv_harvesting_item;
        this.data = hsd;
        this.prevAvailableList = prevAvailableList;
        this.editablePosition = editablePosition;

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
        return new MyAdapterRecyclerview.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.line1.setText(data.get(position));

        //setting previous data back to the fragment view
        if (prevAvailableList.size() > 0) {
            if (!prevAvailableList.get(position).getRow1().equals("0"))
                holder.e1.setText(prevAvailableList.get(position).getRow1());

            if (!prevAvailableList.get(position).getRow2().equals("0"))
                holder.e2.setText(prevAvailableList.get(position).getRow2());

            if (!prevAvailableList.get(position).getRow3().equals("0"))
                holder.e3.setText(prevAvailableList.get(position).getRow3());

            if (!prevAvailableList.get(position).getRow4().equals("0"))
                holder.et_total.setText(prevAvailableList.get(position).getRow4());
        }
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
                holder.et_total.setText("" + data);
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
                holder.et_total.setText("" + data);
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
                holder.et_total.setText("" + data);

            }
        });

       /* holder.e1.setFocusable(false);
        holder.e2.setFocusable(false);
        holder.e3.setFocusable(false);
        holder.e1.setFocusableInTouchMode(false);
        holder.e2.setFocusableInTouchMode(false);
        holder.e3.setFocusableInTouchMode(false);*/

        if (position == editablePosition) {
            holder.e1.setFocusable(true);
            holder.e2.setFocusable(true);
            holder.e3.setFocusable(true);
            holder.e1.setFocusableInTouchMode(true);
            holder.e2.setFocusableInTouchMode(true);
            holder.e3.setFocusableInTouchMode(true);
        }
        doSumPro(position, holder.e1.getText().toString().trim(), holder.e2.getText().toString().trim(), holder.e3.getText().toString().trim());
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
        m11.setVal4(data4);
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

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView line1;
        EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, et_total;

        public MyViewHolder(View itemView) {
            super(itemView);

            line1 = itemView.findViewById(R.id.one);
            e1 = itemView.findViewById(R.id.e1);
            e2 = itemView.findViewById(R.id.e2);
            e3 = itemView.findViewById(R.id.e3);
            e3 = itemView.findViewById(R.id.e4);
            e3 = itemView.findViewById(R.id.e5);
            e3 = itemView.findViewById(R.id.e6);
            e3 = itemView.findViewById(R.id.e7);
            e3 = itemView.findViewById(R.id.e8);
            e3 = itemView.findViewById(R.id.e9);
            et_total = itemView.findViewById(R.id.et_total);
        }
    }

    public ArrayList<MyDataLiust> getTotaldata() {
        return totaldata;
    }

   /* public void updateReplicationValue(int replicationValue){
        this.editablePosition = replicationValue;
    }
*/
}