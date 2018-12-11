package com.master.Mahesh;

import android.content.Context;
import android.content.SharedPreferences;
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
    public MyAdapterRecyclerview ro;
    private int data_test = 90;
    Context mContext = null;
    private List<RowEntity> prevAvailableList;
    ArrayList<String> m = new ArrayList<>();
    TextView tv_treatment;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    int replicationPosition;

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
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment, container, false);
        sp = getActivity().getSharedPreferences("TreatmentReplicationValue", Context.MODE_PRIVATE);
        spe = sp.edit();
        initViews(view);
        Log.e(TAG, "onCreateView");

        return view;
    }


    private void initViews(final View view) {
        Log.e(TAG, " == >  initViews ");


        position = getArguments().getInt("position");
        TextView textView = view.findViewById(R.id.commonTextView);
        tv_treatment = view.findViewById(R.id.tv_treatment);
        Button sample = view.findViewById(R.id.sample);
        rv = view.findViewById(R.id.rr);

        textView.setText(String.valueOf("PLANT :  " + getArguments().getInt("position")));

        prevAvailableList = new ArrayList<>();
        getPrevDataAsyncTask();

        m.add("1");
        m.add("2");
        m.add("3");


        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Insert and get data using Database Async way
                 */
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Insert Data
                        List<RowEntity> list = RowDatabase
                                .getInstance(getActivity().getApplicationContext())
                                .getRowDao()
                                .getAllRow();

                        Log.e(TAG, "list size is " + list.size());
                        for (RowEntity li : list) {

                            Log.e(TAG, "Id is  " + li.getId());
                            Log.e(TAG, "Data of Row1 " + li.getRow1());
                            Log.e(TAG, "Data of Row2 " + li.getRow2());
                            Log.e(TAG, "Data of Row3 " + li.getRow3());
                            Log.e(TAG, "Data of Row4 " + li.getRow4());
                            Log.e(TAG, "TAB Position of above recode " + li.getTab_position());
                        }

                    }
                });
            }
        });


    }


    public List<RowEntity> getFrgData(final String tab_position) {
        // get Data by tab position
        List<RowEntity> list = RowDatabase
                .getInstance(getActivity())
                .getRowDao()
                .getRowByPositon(tab_position);
        Log.e(TAG, "List size =>> " + list.size());
        if (list.size() > 0) {
            prevAvailableList = list;
        } else {
            Log.e(TAG, "Data is not available for this tab position");
            prevAvailableList.clear();
        }
        return prevAvailableList;
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
        //Todo on pause we will save later
    }

    @Override
    public void onResume() {
        super.onResume();

        String selectedTreatment = sp.getString("treatment", "");
        String selectedReplication = sp.getString("replication", "");
        replicationPosition = sp.getInt("replicationPosition", -1);
        tv_treatment.setText(selectedTreatment);

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

    public ArrayList<MyDataLiust> getData1() {
        ArrayList<MyDataLiust> totaldata = ro.getTotaldata();
        Log.e(TAG, "this is from fragment  ==>>" + data_test);
        Log.e(TAG, "this is from fragment  ==>>" + isAdded());
        return totaldata;
    }


    /* this code will return data after the data retrive from the database*/
    private void getPrevDataAsyncTask() {
        PrevDataAsyncTask prevDataAsyncTask = new PrevDataAsyncTask(new FragmentCallback() {

            @Override
            public void onTaskDone() {
                startTaskAfterPrevData();
            }
        });
        prevDataAsyncTask.execute();
    }

    public interface FragmentCallback {
        void onTaskDone();
    }

    private void startTaskAfterPrevData() {
        Log.e(TAG,"Replication position is =>"+replicationPosition);
        ro = new MyAdapterRecyclerview(getContext(), R.layout.inflator_item_list, m, prevAvailableList,replicationPosition);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemViewCacheSize(m.size());
        rv.setAdapter(ro);
    }

    class PrevDataAsyncTask extends AsyncTask<Void, Void, List<RowEntity>> {
        private DynamicFragment.FragmentCallback mFragmentCallback;

        public PrevDataAsyncTask(DynamicFragment.FragmentCallback fragmentCallback) {
            mFragmentCallback = fragmentCallback;
        }

        @Override
        protected List<RowEntity> doInBackground(Void... params) {
            prevAvailableList = getFrgData(String.valueOf(position));
            return prevAvailableList;
        }

        @Override
        protected void onPostExecute(List<RowEntity> result) {
            mFragmentCallback.onTaskDone();
        }
    }
}
