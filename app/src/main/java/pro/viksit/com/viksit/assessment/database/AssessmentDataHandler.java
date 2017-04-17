package pro.viksit.com.viksit.assessment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        Cursor cursor = getData(Integer.parseInt(id));
        if (cursor != null && cursor.getCount() > 0) {
            System.out.println("updateContent done");
            updateContent(id, content);
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, id);
            contentValues.put(KEY_NAME, content);
            db.insert(TABLE, null, contentValues);
            System.out.println("saveContent done");
        }
    }

    public void updateContent(String id, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, content);
        db.update(TABLE, contentValues, "id = ? ", new String[]{id});
        System.out.println("updateContent done");

    }

    public Integer deleteContent(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("delete content done");
        return db.delete(TABLE,
                "id = ? ",
                new String[]{Integer.toString(id)});

    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE + " where id=" + id + "", null);
        System.out.println("get content done");

        return res;
    }


    public List<String> getAllContent() {
        List<String> ppt_ids = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ppt_ids.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return ppt_ids;
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
