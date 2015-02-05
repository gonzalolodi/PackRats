package co.mobilemakers.packrats;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by gonzalo.lodi on 05/02/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "documents.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Document, Integer> mDocumentDao = null;

    public Dao<Document, Integer> getDocumentDao() throws SQLException {

        if (mDocumentDao == null){
            mDocumentDao = getDao(Document.class);
        }
        return mDocumentDao;
    }

    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            Log.i(LOG_TAG, "Creating database");
            TableUtils.createTable(connectionSource, Document.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error creating database",e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
