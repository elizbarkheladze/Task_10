package ge.edu.btu.task10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActionsRecyclerAdapter(
    private val items: ArrayList<Action>
) : RecyclerView.Adapter<ActionsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.action_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(item: Action) {
            itemView.findViewById<TextView>(R.id.actionType).text = item.actionType
            itemView.findViewById<TextView>(R.id.actionCreateDate).text = item.createDate
        }
    }
}
