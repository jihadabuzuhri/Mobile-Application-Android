package ps.aaup.jihad_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
    static final String Key_RowID="_id";
    static final String Key_Name="name";
    static final String Key_email="email";

    static final String Tag="DBAdapter";
    static final String Database_Name="MyDB";
    static final String Database_Table="contacts";
    static final int Database_version=1;

    static final String Create_Database=" create table contacts (_id integer primary key autoincrement,"+
            " name text not null, email text not null);";
    final Context context;

    DatabaseHelper DBHelper;
    static SQLiteDatabase db;
    public DBAdapter (Context ct){
        this.context=ct;
        DBHelper = new DatabaseHelper(ct);
    }


    ///Inner Class DatabaseHelper
    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context ctx){
            super(ctx, Database_Name, null, Database_version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(Create_Database);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w(Tag, "Upgrading Database from version 1 to version 2");
            db.execSQL("DROP TABLE IF EXISTS contacts");
        }
    }//end of DatabaseHelper

    public DBAdapter open() throws SQLException {
        db=DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        DBHelper.close();
    }

    public long insterConact (String name, String email){
        ContentValues val= new ContentValues();
        val.put(Key_Name, name);
        val.put(Key_email, email);

        return db.insert(Database_Table, null, val);
    }

    public int deleteContact(long rowId){
        return db.delete(Database_Table,Key_RowID+"="+rowId, null );
    }


    public int deleteContact_by_N_E(String name, String email){
        if (email.equals("")){
            String arg[]=new String[]{name};
            return db.delete(Database_Table,Key_Name+" LIKE ?", arg );

        }
        else  if (name.equals("")){
            String arg[]=new String[]{email};
            return db.delete(Database_Table,Key_email+" LIKE ?", arg );
        }

        else {
            String arg[]=new String[]{name,email};
            return db.delete(Database_Table,Key_Name+" LIKE ?"+" and "+Key_email+" LIKE ?", arg );
        }

    }

    public Cursor getAllContacts(){
        return db.query(Database_Table, new String []{Key_RowID, Key_Name, Key_email},null, null,null, null, null);
    }
    public Cursor getContact(long rowId) throws SQLException{
        Cursor mCursor= db.query(true, Database_Table, new String []{Key_RowID, Key_Name, Key_email}, Key_RowID+"="+rowId, null, null, null, null, null);
        if(mCursor!=null)
            mCursor.moveToFirst();
        return mCursor;
    }

    public boolean updateContact(long rowId, String name, String email){
        ContentValues args= new ContentValues();
        args.put(Key_Name, name);
        args.put(Key_email, email);
        return db.update(Database_Table, args, Key_RowID+"="+rowId, null)>0;
    }


    public int updateContact_by_N_E(String name, String email){

        ContentValues args= new ContentValues();
        args.put(Key_Name, name);
        args.put(Key_email, email);

        String Warg[]=new String[]{name,email};
        return db.update(Database_Table, args, Key_Name+" LIKE ?"+" or "+Key_email+" LIKE ?", Warg);
    }



}