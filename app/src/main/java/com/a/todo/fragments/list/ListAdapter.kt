package com.a.todo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.a.todo.R
import com.a.todo.data.model.Priority
import com.a.todo.data.model.TodoData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.TodoViewHolder>() {

    var dataList = emptyList<TodoData>()

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.description_txt.text = dataList[position].description
        holder.itemView.title_txt.text = dataList[position].title

        holder.itemView.row_background.setOnClickListener {
            
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        when (dataList[position].priority) {

            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red
                )
            )

            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.yellow
                )
            )

            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(todoData: List<TodoData>) {
        this.dataList = todoData

        notifyDataSetChanged()
    }
}