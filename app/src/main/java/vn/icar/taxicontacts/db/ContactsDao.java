package vn.icar.taxicontacts.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ContactsDao {
    @Query("SELECT * FROM contacts_table ")
    LiveData<List<Contacts>> getAlphabetizedWords();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contacts contacts);





}
