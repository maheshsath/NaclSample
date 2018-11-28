package com.master.Mahesh;

import android.content.Context;
import android.os.AsyncTask;
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

import com.master.Mahesh.room.RowDatabase;
import com.master.Mahesh.room.RowEntity;

import java.util.ArrayList;
import java.util.List;


public class DynamicFragment extends Fragment {
    String TAG = "DynamicFragment";
    private RecyclerView rv;
    int position;
    ArrayList<MyDataLiust> data;
   public  MyAdapterRecyclerview ro;
    private int data_test = 90;
    Context mContext = null;


    public static DynamicFragment newInstance() {
        return new DynamicFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);
        initViews(view);
        Log.e(TAG, "onCreateView");

        return view;
    }

    private void initViews(View view) {

        Log.e(TAG, " == >  initViews ");

        position = getArguments().getInt("position");
        TextView textView = view.findViewById(R.id.commonTextView);
        ListView ll = view.findViewById(R.id.ll);
        Button sample = view.findViewById(R.id.sample);
        rv = view.findViewById(R.id.rr);
        textView.setText(String.valueOf("Category :  " + getArguments().getInt("position")));



        ArrayList<String> m = new ArrayList<>();

        m.add("1");
        m.add("2");
        m.add("3");

/*
        MyAdpter ma=new MyAdpter(getContext(),m);
        ll.setAdapter(ma);*/

        ro = new MyAdapterRecyclerview(getContext(), R.layout.inflator_item_list, m);


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemViewCacheSize(m.size());
        rv.setAdapter(ro);



        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = ro.getData();

                Log.e(TAG, "data" + data.get(0).getVal1() + "=====" + data.get(0).getVal2() + "======" + data.get(0).getVal3());
                Log.e(TAG, "data" + data.get(1).getVal1() + "=====" + data.get(1).getVal2() + "======" + data.get(1).getVal3());
                Log.e(TAG, "data" + data.get(2).getVal1() + "=====" + data.get(2).getVal2() + "======" + data.get(2).getVal3());
                Log.e(TAG, "data size" + data.size());

                /**
                 *  Insert and get data using Database Async way
                 */
                /*AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Insert Data
                        List<RowEntity> list = RowDatabase
                                .getInstance(getActivity().getApplicationContext())
                                .getRowDao()
                                .getAllUsers();

                        Log.e(TAG,"list size is "+list.size());
                        Log.e(TAG,"data of first list row3 is  "+list.get(0).getRow1());

                        for(RowEntity li:list){
                            Log.e(TAG,"DATA is "+li.getRow1());
                            Log.e(TAG,"DATA is "+li.getRow2());
                            Log.e(TAG,"DATA is "+li.getRow3());
                            Log.e(TAG,"DATA is "+li.getRow4());
                        }

                    }
                });*/
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        Log.e(TAG, "tab position" + position);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

           /* if (isVisibleToUser) {
                Log.e(TAG, "Don't save data right now because fragment in visible to user");
            } else {
                Log.e(TAG, "save data right now because fragment no more visible to user");
                Log.e(TAG,"this is from fragment"+ data_test);

            }
*/
        super.setUserVisibleHint(isVisibleToUser);
    }
    public ArrayList<MyDataLiust> getData1(){
        Log.e(TAG,"this is from fragment  ==>>"+ data_test);
        Log.e(TAG,"this is from fragment  ==>>"+ isAdded());
        //Log.e(TAG,"this is from fragment  ==>>"+ dynamicFragment);
        return MyAdapterRecyclerview.returnData;
    }


}
