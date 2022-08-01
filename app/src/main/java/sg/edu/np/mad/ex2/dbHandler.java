package sg.edu.np.mad.ex2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "users.db";

    public static String users = "user";
    public static String name_COLUMN = "name";
    public static String desc_COLUMN = "description";
    public static String id_COLUMN = "id";
    public static String followed_COLUMN = "followed";


    public static int DATABASE_VERSION = 4;

    public dbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + users +"(" + name_COLUMN + " TEXT," + desc_COLUMN
                + " TEXT," + id_COLUMN + " INT," + followed_COLUMN + " INT" + ")";

        db.execSQL(query);

        String add = "INSERT INTO " + users +"(" + name_COLUMN + ", " + desc_COLUMN
                + ", " + id_COLUMN + ", " + followed_COLUMN + ")"
                + "VALUES"
                + "(" + "'name1', " + "'desc1', " + "1, " + "0 " + "),"
                + "(" + "'name2', " + "'desc2', " + "2, " + "0 " + "),"
                + "(" + "'name3', " + "'desc3', " + "3, " + "0 " + "),"
                + "(" + "'name4', " + "'desc4', " + "4, " + "0 " + ")";

        db.execSQL(add);
    }
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + users);
    }

    public ArrayList<user> getUsers(){
        ArrayList<user> queryResult = new ArrayList<>();

        String query = "SELECT * FROM " + users;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int counter = 0;

        while(true){
            if(cursor.moveToPosition(counter)){
                user currentUser = new user();
                currentUser.setUserName(cursor.getString(0));
                currentUser.setDescription(cursor.getString(1));
                currentUser.setId(cursor.getInt(2));
                if(cursor.getInt(3) == 0){
                    currentUser.setFollowed(false);
                }
                else{
                    currentUser.setFollowed(true);
                }
                queryResult.add(currentUser);
                counter += 1;
            }
            else{
                break;
            }
        }
        cursor.close();
        db.close();
        return queryResult;
    }

    public void updateUser(user USER){
        int userID = USER.getId();
        int bool;
        if (USER.isFollowed()){
            bool = 1;
        }
        else{
            bool = 0;
        }

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + users + " SET " + followed_COLUMN + " = " + bool + " WHERE " + userID);
    }
}
