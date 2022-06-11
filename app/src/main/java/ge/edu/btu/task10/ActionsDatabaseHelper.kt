package ge.edu.btu.task10

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ActionsDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "actions_database", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(
            "CREATE TABLE actions(" +
                    "id integer PRIMARY KEY," +
                    "action_type text," +
                    "create_date date)"
        )
    }

    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS actions")
        onCreate(database)
    }

    fun saveAction(actionType: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA)
        val strDate: String = sdf.format(Date())
        contentValues.put("action_type", actionType)
        contentValues.put("create_date", strDate)
        val success = db.insert("actions", null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range", "Recycle")
    fun getActions(): ArrayList<Action> {
        val list = ArrayList<Action>()
        val selectQuery = "SELECT  * FROM actions"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                val action = Action(
                    id = cursor.getInt(0),
                    actionType = cursor.getString(1).toString(),
                    createDate = cursor.getString(2).toString()
                )
                list.add(action)
            }
        }
        db.close()

        return list
    }

}
