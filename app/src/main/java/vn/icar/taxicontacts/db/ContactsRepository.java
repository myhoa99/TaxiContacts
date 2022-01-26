package vn.icar.taxicontacts.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ContactsRepository {

    private ContactsDao mContactsDao;
    private LiveData<List<Contacts>> mAllData;
    ContactsRepository(Application application) {
        ContactsRoomDatabase db = ContactsRoomDatabase.getDatabase(application);
        mContactsDao = (ContactsDao) db.contactsdao();
        mAllData = mContactsDao.getAlphabetizedWords();
    }
    LiveData<List<Contacts>> getAllData() {
        return mAllData;
    }
    void insert(Contacts contacts) {
        ContactsRoomDatabase.databaseWriteExecutor.execute(() -> {
            mContactsDao.insert(contacts);
        });
    }
}
