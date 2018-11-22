package com.master.Mahesh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterRecyclerview extends RecyclerView.Adapter<MyAdapterRecyclerview.MyViewHolder> implements MyInterfacs{

    Context context;
    int item;
    ArrayList<String> data;
    String val1="0";
    String val2="0";
    String val3="0";
    private int total=0;
    Integer val11=0,val22=0,val33=0,val44;
    ArrayList<MyDataLiust> totaldata=new ArrayList<>();




    public MyAdapterRecyclerview(Context context, int lv_harvesting_item, ArrayList<String> hsd)
    {
        this.context=context;
        this.item=lv_harvesting_item;
        this.data=hsd;


    }

    @Override
    public ArrayList<MyDataLiust> getData() {
        return totaldata;
    }

//    public List<HarvastDetails> getHarvastDetailsList() {
//
//
//    }

    class  MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView line1,e1,e2,e3,e4;

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

        View v = LayoutInflater.from(parent.getContext()).inflate(item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        holder.line1.setText(data.get(position));


        final MyDataLiust m1=new MyDataLiust();
        holder.e1.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {


            val1=holder.e1.getText().toString().trim();

                if (!val1.equalsIgnoreCase("")) {





                    val11=Integer.parseInt(val1);


                    m1.setVal1(val11);
                    //m1.setVal2(val22);
                   // m1.setVal3(val33);


                    totaldata.add(position,m1);
                    total=doSum(position);
                    holder.e4.setText(""+total);
                }else if (val1.equalsIgnoreCase(""))
                {
                    val11=0;


                    m1.setVal1(val11);
                    //m1.setVal2(val22);
                    //m1.setVal3(val33);


                    totaldata.add(position,m1);
                    total=doSum(position);
                    holder.e4.setText(""+total);
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
            public void afterTextChanged(Editable editable)
            {
                val2=holder.e2.getText().toString().trim();

                if (!val2.equalsIgnoreCase("")) {

                   val22=Integer.parseInt(val2);


                    //m1.setVal1(val11);
                    m1.setVal2(val22);
                   // m1.setVal3(val33);

                    totaldata.add(position,m1);

                    total=doSum(position);
                    holder.e4.setText(""+total);
                }else if (val2.equalsIgnoreCase("")){
                    val22=0;
                    //m1.setVal1(val11);
                    m1.setVal2(val22);
                    //m1.setVal3(val33);

                    totaldata.add(position,m1);

                    total=doSum(position);
                    holder.e4.setText(""+total);
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
            public void afterTextChanged(Editable editable)
            {
                val3=holder.e3.getText().toString().trim();

                if (!val3.equalsIgnoreCase("")) {


                    val33=Integer.parseInt(val3);

                    //m1.setVal1(val11);
                    //m1.setVal2(val22);
                    m1.setVal3(val33);

                    totaldata.add(position,m1);
                    total=doSum(position);
                    holder.e4.setText(""+total);
                }else if (val3.equalsIgnoreCase(""))
                {
                    val33=0;

                    //m1.setVal1(val11);
                    //m1.setVal2(val22);
                    m1.setVal3(val33);

                    totaldata.add(position,m1);
                    total=doSum(position);
                    holder.e4.setText(""+total);

                }


            }
        });



    }

    private int doSum(int position) {



        int sum=totaldata.get(position).getVal1()+totaldata.get(position).getVal2()+totaldata.get(position).getVal3();
        return sum;
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }






}
