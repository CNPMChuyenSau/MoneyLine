package nocompany.quanlytaichinhcanhan.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nocompany.quanlytaichinhcanhan.model.Username;

public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_USERNAME ="username_list";
    private static final String TABLE_USERNAME ="account";
    private static final String USERNAME ="email";
    private static final String PHONENUMBER ="phonenumber";
    private static final String PASSWORD ="password";
    private static final String GIOITINH ="gioitinh";
    private static final String LOAIVITIEN ="loaivitien";
    private static final String SOTIEN = "sotien";
    private  static int VERSION = 1;

    public DBManager(Context context) {
        super(context, DATABASE_USERNAME, null, VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlQuery = "CREATE TABLE " + TABLE_USERNAME + " (" +
                USERNAME + " TEXT primary key, " +
                PHONENUMBER + " TEXT, " +
                PASSWORD + " TEXT, " +
                GIOITINH + " TEXT, " +
                LOAIVITIEN + " TEXT, " +
                SOTIEN + " TEXT)";
        db.execSQL(sqlQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_USERNAME);
        onCreate(db);

    }

    public boolean addData(Username username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean result = true;

        ContentValues cv=new ContentValues();
        cv.put(USERNAME,username.getmEmail());
        cv.put(PHONENUMBER,username.getmPhonenumber());
        cv.put(PASSWORD,username.getmPasswork());
        cv.put(GIOITINH,username.getmGioitinh());
        cv.put(LOAIVITIEN,username.getmLoaivitien());
        cv.put(SOTIEN,username.getmSotien());
        long r = db.insert(TABLE_USERNAME,null, cv);
        db.close();

        if(r==-1)
            result=false;

        return result;
    }

    //Xoa Username
    public void Delete_User1(String t_email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_USERNAME+" WHERE "+USERNAME+"='"+t_email+"'");
        db.close();
    }
    //kiem tra dang nhap
    public Username getUsername(String Eamil_Id)
    {
        Username username = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USERNAME, PHONENUMBER, PASSWORD, GIOITINH, LOAIVITIEN,SOTIEN};
        String selection = USERNAME + " = ?";
        String[] selectionArgs = {Eamil_Id};
        Cursor cursor = db.query(TABLE_USERNAME, columns, selection,
                selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            cursor.moveToFirst();

            username = new Username(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
        }
        db.close();
        return username;
    }

    // kiem tra dang nhap
    public int check_Account(String Email_ID, String Password_ID)
    {
        Username username = getUsername(Email_ID);
        if(username==null)
            return 0;
        else {
            if(!username.getmPasswork().equals(Password_ID))
                return 1;
        }
        return 2;
    }
    //tim password
    public String Search_Passwod(String Email_ID, String Phonenumber_ID)
    {
        Username username = getUsername(Email_ID);
        if(username==null)
            return "0";
        else {
            if(username.getmPhonenumber().equals(Phonenumber_ID))
                return username.getmPasswork();
        }
        return  "1";
    }


}
