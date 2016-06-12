package com.example.aayush.aburrido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by dinesh on 12-Jun-16.
 */
public class KeywordDatabaseSchema {
    public static abstract class KeywordDatabase implements BaseColumns{
        public static final String TABLE_NAME = "KeyWords";
        public static final String COLUMN_NAME_ENTRY_OBJECTID = "Object_ID";
        public static final String COLUMN1_NAME = "Type";
        public static final String COLUMN2_NAME = "KeyWord";
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String BLOB_TYPE = " BLOB";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + KeywordDatabase.TABLE_NAME + "(" +
            KeywordDatabase._ID + COMMA_SEP +
            KeywordDatabase.COLUMN_NAME_ENTRY_OBJECTID+COMMA_SEP+
            KeywordDatabase.COLUMN1_NAME + TEXT_TYPE + COMMA_SEP +
            KeywordDatabase.COLUMN2_NAME + TEXT_TYPE +
            " PRIMARY KEY (" + KeywordDatabase.COLUMN_NAME_ENTRY_OBJECTID +")" +
            ");";
    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + KeywordDatabase.TABLE_NAME;
    private dataBaseHelper DBHelper;
    private final Context context;

    public KeywordDatabaseSchema(Context cntxt) {
        this.context = cntxt;
        DBHelper = new dataBaseHelper(context);
    }

    private SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KeyWord.db";

    public static class dataBaseHelper extends SQLiteOpenHelper {

        /* *
         * Create a helper object to create, open, and/or manage a database.
         * This method always returns very quickly.  The database is not actually
         * created or opened until one of {@link #getWritableDatabase} or
         * {@link #getReadableDatabase} is called.
         *
         * @param context to use to open or create the database
         * @param name    of the database file, or null for an in-memory database
         * @param factory to use for creating cursor objects, or null for the default
         * @param version number of the database (starting at 1); if the database is older,
         *                {@link #onUpgrade} will be used to upgrade the database; if the database is
         *                newer, {@link #onDowngrade} will be used to downgrade the database
         */
        public dataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * Called when the database is created for the first time. This is where the
         * creation of tables and the initial population of the tables should happen.
         *
         * @param db The database.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        /**
         * Called when the database needs to be upgraded. The implementation
         * should use this method to drop tables, add tables, or do anything else it
         * needs to upgrade to the new schema version.
         * <p/>
         * <p>
         * The SQLite ALTER TABLE documentation can be found
         * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
         * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
         * you can use ALTER TABLE to rename the old table, then create the new table and then
         * populate the new table with the contents of the old table.
         * </p><p>
         * This method executes within a transaction.  If an exception is thrown, all changes
         * will automatically be rolled back.
         * </p>
         *
         * @param db         The database.
         * @param oldVersion The old database version.
         * @param newVersion The new database version.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_TABLE);
            onCreate(db);
        }
    }

    //    private SecretKeySpec sks;
    public KeywordDatabaseSchema open() {
        db = DBHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDataBaseInstance() {
        return db;
    }

    public long insertKeyWord(String OID,String Type, String keyword) {
        db = DBHelper.getWritableDatabase();
        ContentValues newValue = new ContentValues();
        newValue.put(KeywordDatabase.COLUMN_NAME_ENTRY_OBJECTID, OID);
        newValue.put(KeywordDatabase.COLUMN1_NAME, Type);
        newValue.put(KeywordDatabase.COLUMN2_NAME, keyword);
        return db.insert(KeywordDatabase.TABLE_NAME, null, newValue);

    }
    public ArrayList<String> getKeyWords(String type) {
        db = DBHelper.getReadableDatabase();
        String[] projection = {
                KeywordDatabase.COLUMN2_NAME,
        };
        String where = KeywordDatabase.COLUMN1_NAME + "=?";
        Cursor c = db.query(
                KeywordDatabase.TABLE_NAME,
                projection,         //you could have also given star since you have selected almost all
                where,
                new String[]{type},
                null, null, null);
        c.moveToFirst();
        ArrayList<String> output=new ArrayList<String>();
        while(!c.isAfterLast()){
             output.add(c.getString(c.getColumnIndexOrThrow(KeywordDatabase.COLUMN2_NAME)));
        }
        return output;
    }


}

