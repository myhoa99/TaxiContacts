package vn.icar.taxicontacts.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contacts.class}, version = 1, exportSchema = false)
abstract class ContactsRoomDatabase extends RoomDatabase {

    abstract ContactsDao contactsdao();
    private static volatile ContactsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ContactsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactsViewHolder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactsRoomDatabase.class, "Contacts_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ContactsDao dao = INSTANCE.contactsdao();

            });
        }
    };

}
