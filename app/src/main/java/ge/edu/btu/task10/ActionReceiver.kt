package ge.edu.btu.task10

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val database = ActionsDatabaseHelper(context!!)
        database.saveAction(intent!!.action.toString())
    }
}
