package com.example.aayush.aburrido;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class EventDataBaseSchema {
        public static abstract class registerDataBase implements BaseColumns {
        public static final String TABLE_NAME = "EVENT";
        public static final String COLUMN_NAME_ENTRY_OBJECTID = "Object_ID";
        public static final String COLUMN1_NAME = "Number_Of_Rating";
        public static final String COLUMN2_NAME = "Twitter_Rating";
        public static final String COLUMN3_NAME = "Name";
        public static final String COLUMN4_NAME = "Location";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String BLOB_TYPE = " BLOB";
    /*
        key entry id is different from id , key entry id 100134 ,100135 is  whereas normal id is 1,2,3,4 kindof . these are proper id
     */
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + registerDataBase.TABLE_NAME + "(" +
            registerDataBase._ID + COMMA_SEP +
            registerDataBase.COLUMN_NAME_ENTRY_OBJECTID+COMMA_SEP+
            registerDataBase.COLUMN1_NAME + TEXT_TYPE + COMMA_SEP +
            registerDataBase.COLUMN2_NAME + TEXT_TYPE + COMMA_SEP +
            registerDataBase.COLUMN3_NAME + TEXT_TYPE + COMMA_SEP +
            registerDataBase.COLUMN4_NAME + TEXT_TYPE + COMMA_SEP +
            " PRIMARY KEY (" + registerDataBase.COLUMN_NAME_ENTRY_OBJECTID + COMMA_SEP + registerDataBase.COLUMN3_NAME + ")" +
            ");";
    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + registerDataBase.TABLE_NAME;
    private dataBaseHelper DBHelper;
    private final Context context;

    public EventDataBaseSchema(Context cntxt) {
        this.context = cntxt;
        DBHelper = new dataBaseHelper(context);
    }

    private SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Event.db";

    @SuppressWarnings("JavadocReference")
    public static class dataBaseHelper extends SQLiteOpenHelper {

        /**
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
    public EventDataBaseSchema open() {
        db = DBHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDataBaseInstance() {
        return db;
    }

    public long insertEntry(String OID, String NUMBER_RATING, String TWITTER_RATING, String name,String Location) {
        db = DBHelper.getWritableDatabase();
        ContentValues newValue = new ContentValues();
        newValue.put(registerDataBase.COLUMN_NAME_ENTRY_OBJECTID, OID);
        newValue.put(registerDataBase.COLUMN1_NAME, NUMBER_RATING);
        newValue.put(registerDataBase.COLUMN2_NAME, TWITTER_RATING);
        newValue.put(registerDataBase.COLUMN3_NAME, name);
        newValue.put(registerDataBase.COLUMN4_NAME, Location);
        //return fastest way to find any row i.e rowID
        return db.insert(registerDataBase.TABLE_NAME, null, newValue);

    }

    public int deleteEntry(String Event) {
        db = DBHelper.getWritableDatabase();
        String where = registerDataBase.COLUMN3_NAME + "=?";
        return db.delete(registerDataBase.TABLE_NAME, where, new String[]{Event});
    }

    public String getNumberOfRatings(String eventName) {
        db = DBHelper.getReadableDatabase();
        String[] projection = {
                registerDataBase.COLUMN1_NAME
        };
        String where = registerDataBase.COLUMN3_NAME + "=?";
        Cursor c = db.query(
                registerDataBase.TABLE_NAME,
                projection,         //you could have also given star since you have selected almost all
                where,
                new String[]{eventName},
                null, null, null);
        c.moveToFirst();
        String output = c.getString(c.getColumnIndexOrThrow(registerDataBase.COLUMN1_NAME));
        return output;
    }

    public String getRatings(String eventName) {
        db = DBHelper.getReadableDatabase();
        String[] projection = {
                registerDataBase.COLUMN2_NAME,
        };
        String where = registerDataBase.COLUMN3_NAME + "=?";
        Cursor c = db.query(
                registerDataBase.TABLE_NAME,
                projection,         //you could have also given star since you have selected almost all
                where,
                new String[]{eventName},
                null, null, null);
        c.moveToFirst();
        String output = c.getString(c.getColumnIndexOrThrow(registerDataBase.COLUMN2_NAME));
        return output;
    }
    public Cursor getEntry(String Event) {
        db = DBHelper.getReadableDatabase();
        String[] projection = {
                registerDataBase.COLUMN1_NAME,
                registerDataBase.COLUMN2_NAME,
                registerDataBase.COLUMN3_NAME,
                registerDataBase.COLUMN4_NAME,
        };
        String where = registerDataBase.COLUMN3_NAME + "=?";
        Cursor c= db.query(
                registerDataBase.TABLE_NAME,
                projection,
                where,
                new String[]{Event},
                null, null, null);
        c.moveToFirst();
        return c;

    }
    public ArrayList<cursorObject> getTop5Entry() {
        db = DBHelper.getReadableDatabase();
        String[] projection = {
                registerDataBase.COLUMN1_NAME,
                registerDataBase.COLUMN2_NAME,
                registerDataBase.COLUMN3_NAME,
                registerDataBase.COLUMN4_NAME,
        };
        String where = registerDataBase.COLUMN3_NAME;
        Cursor c= db.query(
                registerDataBase.TABLE_NAME,
                projection,
                where,
                new String[]{null},
                null, registerDataBase.COLUMN2_NAME+" desc", " 5");
        c.moveToFirst();
        ArrayList<cursorObject> obj=new ArrayList<>();
        while(c.moveToNext()){
            cursorObject temp=new cursorObject();
            temp.setEventName(c.getString(c.getColumnIndexOrThrow(registerDataBase.COLUMN3_NAME)));

            temp.setLocation(c.getString(c.getColumnIndexOrThrow(registerDataBase.COLUMN4_NAME)));

            temp.setTwitterRating(c.getString(c.getColumnIndexOrThrow(registerDataBase.COLUMN2_NAME)));

            obj.add(temp);
        }
        return obj;
    }


    public int updateEntry(String Event, String twitterRating, String numberOfRating) {
        db = DBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        String selection = registerDataBase.COLUMN3_NAME + " =?";
        values.put(registerDataBase.COLUMN3_NAME, Event);
        int count = db.update(
                registerDataBase.TABLE_NAME,
                values,
                selection,
                new String[]{twitterRating, numberOfRating}
        );
        return count;
    }

}
