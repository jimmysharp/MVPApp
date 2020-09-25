package com.example.mvpapp.ui.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpapp.R
import com.example.mvpapp.data.Task

class TaskListAdapter(
    private val taskItemClickCallback: TaskItemClickCallback
): ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, taskItemClickCallback)

    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val container: ViewGroup = view.findViewById(R.id.task_item_container)
        private val title: TextView = view.findViewById(R.id.task_item_title)
        private val description: TextView = view.findViewById(R.id.task_item_description)

        fun bind(task: Task, taskItemClickCallback: TaskItemClickCallback) {
            title.text = task.title
            description.text = task.description

            container.setOnClickListener {
                taskItemClickCallback.onClick(task)
            }
        }
    }
}

fun interface TaskItemClickCallback {
    fun onClick(task: Task)
}

class TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
