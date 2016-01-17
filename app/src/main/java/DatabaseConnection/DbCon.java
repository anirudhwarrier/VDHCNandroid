package DatabaseConnection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DbCon {
    public static Context context;
    public static SQLiteDatabase sqLiteDatabase;
    public static Cursor cursor;
    public static void init()throws Exception{
        sqLiteDatabase=context.openOrCreateDatabase("VDHCN",SQLiteDatabase.OPEN_READWRITE,null);
        String sql="create table if not exists login(uname varchar(45),pword varchar(45))";
        sqLiteDatabase.execSQL(sql);
//        sql=" CREATE TABLE if not exists network_details (\n" +
//                "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
//                "  ip varchar(30) NOT NULL,\n" +
//                "  post int(11) NOT NULL,\n" +
//                "  name varchar(45) NOT NULL,\n" +
//                "  imei varchar(20) NOT NULL,\n" +
//                "  PRIMARY KEY (id)\n" +
//                ")\n";
//        sqLiteDatabase.execSQL(sql);
//        sql="CREATE TABLE if not exists video_log (\n" +
//                "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
//                "  name varchar(45) NOT NULL,\n" +
//                "  location varchar(45) NOT NULL,\n" +
//                "  size int(11) NOT NULL,\n" +
//                "  date varchar(45) NOT NULL,\n" +
//                "  time varchar(45) NOT NULL,\n" +
//                "  PRIMARY KEY (id)\n" +
//                ")";
//        sqLiteDatabase.execSQL(sql);
//        sql="CREATE TABLE if not exists bt_details (\n" +
//                "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
//                "  btaddr varchar(45) NOT NULL,\n" +
//                "  name varchar(45) NOT NULL,\n" +
//                "  uuid varchar(45) NOT NULL,\n" +
//                "  imei varchar(45) NOT NULL,\n" +
//                "  PRIMARY KEY (id)\n" +
//                ") ";
//        sqLiteDatabase.execSQL(sql);
//        sql="CREATE TABLE if not exists `video_share` (\n" +
//                "  `id` int(11) NOT NULL,\n" +
//                "  `vid` int(11) NOT NULL,\n" +
//                "  `imei` varchar(45) NOT NULL,\n" +
//                "  `date` varchar(45) NOT NULL,\n" +
//                "  `time` varchar(45) NOT NULL,\n" +
//                "  PRIMARY KEY (`id`)\n" +
//                ") ";
//        sqLiteDatabase.execSQL(sql);
    }
    public static int putData(String sql){
        int i=0;
        System.out.println("SQL="+sql);
        try {
            sqLiteDatabase.execSQL(sql);
            i++;
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }
    public static Cursor getData(String sql){
        System.out.println("SQL="+sql);
        try {
            cursor=sqLiteDatabase.rawQuery(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cursor;
    }
    public static void closeMe(){
        try {
            cursor.close();
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
