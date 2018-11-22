package com.master.Mahesh;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class DynamicFragment extends Fragment
{
    String TAG="DynamicFragment";
    private RecyclerView rv;
    int position;
    ArrayList<MyDataLiust> data;

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

         position = getArguments().getInt("position");
        TextView textView=view.findViewById(R.id.commonTextView);
        ListView ll=view.findViewById(R.id.ll);
        Button sample=view.findViewById(R.id.sample);
        rv=view.findViewById(R.id.rr);
        textView.setText(String.valueOf("Category :  "+getArguments().getInt("position")));


        ArrayList<String> m=new ArrayList<>();

        m.add("1");
        m.add("2");
        m.add("3");

/*
        MyAdpter ma=new MyAdpter(getContext(),m);
        ll.setAdapter(ma);*/
        final MyAdapterRecyclerview ro;
        ro = new MyAdapterRecyclerview(getContext(), R.layout.inflator_item_list, m);


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemViewCacheSize(m.size());
        rv.setAdapter(ro);

        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=ro.getData();

                Log.e(TAG,"data"+data.get(0).getVal1()+"====="+data.get(0).getVal2()+"======"+data.get(0).getVal3());
            }
        });



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        Log.e(TAG,"tab position"+position);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }


    public ArrayList<MyDataLiust> savedata() {
        return data;
    }
}
