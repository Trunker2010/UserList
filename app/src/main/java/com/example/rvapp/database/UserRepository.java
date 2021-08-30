package com.example.rvapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rvapp.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class UserRepository {
    private ArrayList<User> userList;
    private SQLiteDatabase database;
    private Context context;

    public UserRepository(Context context) {
        this.context = context.getApplicationContext();
        this.database = new DBHelper(this.context).getWritableDatabase();
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);


//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://d92522wz.beget.tech/handlerAddUser.php?name="+user.getUserName()+"&lastname="+user.getUserLastName()+"&phone="+user.getPhone()+"&uuid="+user.getUuid().toString());
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    InputStream is = urlConnection.getInputStream();
//                    InputStreamReader reader = new InputStreamReader(is);
//                    int i;
//                    StringBuilder result = new StringBuilder();
//                    while ((i=reader.read()) != -1){
//                        result.append((char)i);
//                    }
//                    System.out.println(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        Thread t = new Thread(runnable);
//        t.start();

    }

    public void updateUser(User user) {
        // Реализуем изменение данных
        String[] args = new String[]{user.getUuid().toString()};
        ContentValues userContentValues = getContentValues(user);
        database.update(UserDbSchema.UserTable.NAME,userContentValues,UserDbSchema.Cols.UUID + "= ?",args);
    }

    public void deleteUser(UUID uuid) {
        // Отправляем запрос на удаление пользователя по его UUID
        String[] args = new String[]{uuid.toString()};
        database.delete(UserDbSchema.UserTable.NAME, UserDbSchema.Cols.UUID + "= ?", args);
    }


    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.Cols.USERLASTNAME, user.getUserLastName());
        values.put(UserDbSchema.Cols.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers() {
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME, null, null, null, null, null, null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList() {
        this.userList = new ArrayList<>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }

        } finally {
            cursorWrapper.close();
        }
        return this.userList;
    }
}