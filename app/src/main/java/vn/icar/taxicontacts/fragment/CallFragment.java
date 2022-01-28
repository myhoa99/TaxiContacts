package vn.icar.taxicontacts.fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import vn.icar.taxicontacts.R;
import vn.icar.taxicontacts.adapter.RecyclerViewAdapterCalls;
import vn.icar.taxicontacts.models.Calls;

public class CallFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<Calls> lstCalls;
    RecyclerViewAdapterCalls adapter;

    /*Constructor*/
    public CallFragment() {
    }

    /*Override*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.history_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.calls_recyclerview);

        adapter = new RecyclerViewAdapterCalls(getContext(), lstCalls);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstCalls = new ArrayList<>();
        getCallLogs();

    }

    @SuppressLint("Range")
    public void getCallLogs(){

        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursorLog = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null );
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd-MM hh:mm a");
        Date date;

        while(cursorLog.moveToNext()){
             String id = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls._ID));
             String name = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.CACHED_NAME)) == null ? "" : cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.CACHED_NAME));
             String phoneNumber = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.NUMBER));
             String duration = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.DURATION));
            int time = Integer.parseInt(duration);

            date = new Date(Long.valueOf(cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.DATE))));

            String d = dateFormat.format(date);

            Calls call = new Calls(id, name.equals("") ? phoneNumber : name, name.equals("")? "" : name.substring(0, 1).toUpperCase(),
                    R.drawable.contact_image, convertTime(time), d + " ,  ", phoneNumber, date );

            //Log.i("The first name ", name);name.e ? "" : name.substring(0, 1).toUpperCase()

            lstCalls.add(call);
        }
        Collections.sort(lstCalls, new ComparaterByDate());
        Collections.reverse(lstCalls);

    }
    private class ComparaterByDate implements Comparator<Calls> {
        @Override
        public int compare(Calls o1, Calls o2) {
            return o1.getDateraw().compareTo(o2.getDateraw());
        }
    }

    public String convertTime(int timeTotal){
        int min = 0, sec = 0;
        String time;
        if(timeTotal >= 60){
            min = timeTotal/60;
            sec = timeTotal%60;
            time = Integer.toString(min) + " min, "+ Integer.toString(sec) + " sec";
        }else {
            time = Integer.toString(timeTotal) + " sec";
        }
        return time;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.uploadCallsLog(newCall());
    }


    @SuppressLint("Range")
    public  List<Calls> newCall(){
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursorLog = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        cursorLog.moveToLast();

        @SuppressLint("Range") String id = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls._ID));
        int max = Integer.parseInt(lstCalls.get(0).getId());
        for (int i = 1;i<lstCalls.size()-1;i++){
            if(max < Integer.parseInt(lstCalls.get(i).getId())){
                max = Integer.parseInt(lstCalls.get(i).getId());
            }
        }

        if(Integer.parseInt(id) == max){
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd-MM hh:mm a");
            Date date;

            @SuppressLint("Range") String name = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.CACHED_NAME));
             @SuppressLint("Range") String phoneNumber = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.NUMBER));
           @SuppressLint("Range") String duration = cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.DURATION));
            int time = Integer.parseInt(duration);

            date = new Date(Long.valueOf(cursorLog.getString(cursorLog.getColumnIndex(CallLog.Calls.DATE))));

            String d = dateFormat.format(date);
            Calls call = new Calls(id, name == null ? phoneNumber : name, name == null ? "" : name.substring(0, 1).toUpperCase(),
                    R.drawable.contact_image, convertTime(time), d + " ,  ", phoneNumber, date );

            lstCalls.add(call);
            Collections.sort(lstCalls, new ComparaterByDate());
            Collections.reverse(lstCalls);

        }
        return lstCalls;
    }
}
