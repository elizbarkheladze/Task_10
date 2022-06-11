package ge.edu.btu.task10

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var actionsList: RecyclerView
    lateinit var adapter: ActionsRecyclerAdapter
    lateinit var database: ActionsDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ActionsDatabaseHelper(this)
        adapter = ActionsRecyclerAdapter(database.getActions())
        actionsList = findViewById(R.id.actionsList)

        actionsList.layoutManager = LinearLayoutManager(this)
        actionsList.adapter = adapter

        val receiver = ActionReceiver()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_HEADSET_PLUG)
        }
        registerReceiver(receiver, filter)
    }
}
