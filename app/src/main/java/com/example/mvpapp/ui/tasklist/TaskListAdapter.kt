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

// リスト(RecyclerView)にデータを表示するためのクラス
class TaskListAdapter(
    private val taskItemClickCallback: TaskItemClickCallback
): ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    // リスト要素のView作成処理
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)

        return TaskViewHolder(view)
    }

    // リスト要素に値をセットした時の処理
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, taskItemClickCallback)
    }

    // リスト要素1つ1つのViewを定義するクラス
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

// リスト要素をクリックした場合のコールバック定義
fun interface TaskItemClickCallback {
    fun onClick(task: Task)
}

// リスト更新時の差分計算用クラス
// リスト要素を全更新すると計算量が膨大になるので、変更があった要素だけを更新する
class TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
    // 何を元に同じ要素とみなすか
    // falseの場合、要素の追加・削除が行われる
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    // 何を元に要素の中身が変わっていないとみなすか
    // falseの場合、要素の更新(onBindViewHolder)が行われる
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
