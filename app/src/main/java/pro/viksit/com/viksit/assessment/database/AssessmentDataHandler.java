package pro.viksit.com.viksit.assessment.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.assessment.pojo.AssessmentResultPojo;

/**
 * Created by Feroz on 17-04-2017.
 */

public class AssessmentDataHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "viksit_assessment";
    private static final String TABLE = "assessmentdata";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "data";
    private static final String EXTERNALSTORAGE = getPath();


    public AssessmentDataHandler(Context context) {
        super(context, EXTERNALSTORAGE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTENT_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void saveContent(String id, String content) {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try{
            cursor= getData(Integer.parseInt(id));
            db= this.getWritableDatabase();
        if (cursor != null && cursor.getCount() > 0) {
            System.out.println("updateContent done");
            updateContent(id, content);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, id);
            contentValues.put(KEY_NAME, content);
            db.insert(TABLE, null, contentValues);
            System.out.println("saveContent done");
        }}finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            if(db != null)
            db.close();

        }
    }

    public void updateContent(String id, String content) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, id);
            contentValues.put(KEY_NAME, content);
            db.update(TABLE, contentValues, "id = ? ", new String[]{id});
            System.out.println("updateContent done");
        }finally {
            if(db!= null){
                db.close();
            }
        }
    }

    public Integer deleteContent(Integer id) {
        Integer temp_id = null;
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            temp_id = db.delete(TABLE,
                    "id = ? ",
                    new String[]{Integer.toString(id)});
        }finally {
            if(db != null)
            db.close();
        }
        System.out.println("delete content done");
        return temp_id;

    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE + " where id=" + id + "", null);
        System.out.println("get content done");

        return res;
    }


    public List<AssessmentResultPojo> getAllContent() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<AssessmentResultPojo> assessmentResultPojos= new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        try {
            db = this.getWritableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    try {
                        assessmentResultPojos.add(new Gson().fromJson(cursor.getString(1), AssessmentResultPojo.class));
                    } catch (JsonSyntaxException jse) {
                        jse.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }
        }finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            if(db != null)
                db.close();
        }
        return assessmentResultPojos;
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    private static String getPath() {

        String path = DATABASE_NAME;
        try {
            if (isExternalStorageReadable()) {
                path = Environment.getExternalStorageDirectory() + ""
                        + File.separator + ".Viksit"
                        + File.separator + DATABASE_NAME;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
