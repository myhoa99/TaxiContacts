package vn.icar.taxicontacts.fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.icar.taxicontacts.R;
import vn.icar.taxicontacts.adapter.RecyclerViewAdapterContact;
import vn.icar.taxicontacts.models.Contact;

public class ContactFragment extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private ArrayList<Contact> lstContact;
    private RecyclerViewAdapterContact adapter;
    LinearLayout ln_null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.contact_recyclerview);
        adapter = new RecyclerViewAdapterContact(getContext(), lstContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;
    }
    @SuppressLint("Range")
    public void loadContacList(){
        //Accessing to contact list and get info
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            List<String> lstPhoneNumber = new ArrayList<>();
            List<String> lstEmail = new ArrayList<>();

            //Phone
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                    , new String[]{id}, null);

            while (phoneCursor.moveToNext() && phoneCursor != null){
                @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                lstPhoneNumber.add(phoneNumber);
            }
            phoneCursor.close();

            //Email
            Cursor emailCursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI
                    , null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?"
                    , new String[]{id}, null);
            while (emailCursor.moveToNext() && emailCursor != null){
                @SuppressLint("Range") String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                lstEmail.add(email);
            }
            emailCursor.close();

            String address = "";

            Uri postal_uri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
            Cursor postal_cursor  = getActivity().getContentResolver().query(postal_uri,null,  ContactsContract.Data.CONTACT_ID + "="+id, null,null);
            while(postal_cursor.moveToNext())
            {
//                String Strt = postal_cursor.getString(postal_cursor.getColumnIndex(StructuredPostal.STREET));
                address = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
//                String cntry = postal_cursor.getString(postal_cursor.getColumnIndex(StructuredPostal.COUNTRY));
            }
            postal_cursor.close();

            lstContact.add(new Contact(id, name, name.substring(0, 1).toUpperCase()
                    , lstPhoneNumber.size() == 0 ? "" : lstPhoneNumber.get(0)
                    , lstEmail.size()== 0 ? "" : lstEmail.get(0), address));
        }
        cursor.close();
        Collections.sort(lstContact, new CustomComparaterLetterContact());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContact = new ArrayList<Contact>();
        loadContacList();
    }

    @Override
    public void onStart() {
        super.onStart();
        ln_null = (LinearLayout) getView().findViewById(R.id.ln_null);
        if(lstContact.size() != 0){
            ln_null.setVisibility(View.GONE);
            addNewContact();
            adapter.notifyDataSetChanged();
        } else{
            ln_null.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("Range")
    public void addNewContact(){
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        cursor.moveToLast();

        @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        //Get max ID in lstContact List to Compare
        int max = Integer.parseInt(lstContact.get(0).getId());
        for (int i = 1;i<lstContact.size()-1;i++){
            if(max < Integer.parseInt(lstContact.get(i).getId())){
                max = Integer.parseInt(lstContact.get(i).getId());
            }
        }

        if(Integer.parseInt(id) == max){
        } else if(Integer.parseInt(id) > max) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            List<String> lstPhoneNumber = new ArrayList<>();
            List<String> lstEmail = new ArrayList<>();

            //Phone
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                    , new String[]{id}, null);

            while (phoneCursor.moveToNext() && phoneCursor != null){
                @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                lstPhoneNumber.add(phoneNumber);
            }
            phoneCursor.close();

            //Email
            Cursor emailCursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI
                    , null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?"
                    , new String[]{id}, null);
            while (emailCursor.moveToNext() && emailCursor != null){
                @SuppressLint("Range") String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                lstEmail.add(email);
            }
            emailCursor.close();

            String address = "";
            Uri postal_uri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
            Cursor postal_cursor  = getActivity().getContentResolver().query(postal_uri,null,  ContactsContract.Data.CONTACT_ID + "="+id, null,null);
            while(postal_cursor.moveToNext())
            {
//                String Strt = postal_cursor.getString(postal_cursor.getColumnIndex(StructuredPostal.STREET));
                address = postal_cursor.getString(postal_cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
//                String cntry = postal_cursor.getString(postal_cursor.getColumnIndex(StructuredPostal.COUNTRY));
            }
            postal_cursor.close();
            cursor.close();

            lstContact.add(new Contact(id, name, name.substring(0, 1).toUpperCase()
                    , lstPhoneNumber.size() == 0 ? "" : lstPhoneNumber.get(0)
                    , lstEmail.size()== 0 ? "" : lstEmail.get(0), address));
            Collections.sort(lstContact, new CustomComparaterLetterContact());
        }
    }

    private class CustomComparaterLetterContact implements Comparator<Contact> {
        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }


}