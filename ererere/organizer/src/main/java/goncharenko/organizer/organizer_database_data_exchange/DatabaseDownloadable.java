package goncharenko.organizer.organizer_database_data_exchange;

import goncharenko.organizer.database_class.StoreableInDatabase;

import java.sql.ResultSet;

public interface DatabaseDownloadable {
    void updateRecord(int itemId,StoreableInDatabase object);
    void deleteRecord(int itemId);
    void addRecord(StoreableInDatabase object);
    ResultSet getAllRecords();
    int getRecordId(StoreableInDatabase object);
    ResultSet getRecord(int itemId);
}
