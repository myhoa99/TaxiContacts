package vn.icar.taxicontacts.db;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ContactsViewModel extends AndroidViewModel {

    private ContactsRepository mRepository;
    private final LiveData<List<Contacts>> mAllData;

    public ContactsViewModel(Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
        mAllData = mRepository.getAllData();
    }

    public LiveData<List<Contacts>> getAllData() {
        return mAllData;
    }

    public void insert(Contacts contacts) {
        mRepository.insert(contacts);

    }
}
