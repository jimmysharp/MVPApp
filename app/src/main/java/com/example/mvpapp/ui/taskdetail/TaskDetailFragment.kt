package com.example.mvpapp.ui.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.example.mvpapp.R
import com.example.mvpapp.data.Task
import com.example.mvpapp.ui.ScrollChildSwipeRefreshLayout

class TaskDetailFragment : Fragment(), TaskDetailContract.View {

    // Fragmentに渡される引数
    private val args: TaskDetailFragmentArgs by navArgs()

    // Presenter
    private lateinit var presenter: TaskDetailContract.Presenter

    // Viewオブジェクト
    private lateinit var rootLayout: ViewGroup
    private lateinit var refreshLayout: ScrollChildSwipeRefreshLayout
    private lateinit var taskDetailLayout: ViewGroup
    private lateinit var noDataLayout: ViewGroup
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var fab: FloatingActionButton

    //
    // ライフサイクルメソッド
    //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // XMLのロード
        val root = inflater.inflate(R.layout.task_detail_fragment, container, false)

        // Viewオブジェクトの取得
        rootLayout = root.findViewById(R.id.task_detail_container)
        refreshLayout = root.findViewById(R.id.refresh_layout)
        taskDetailLayout = root.findViewById(R.id.task_detail_layout)
        noDataLayout = root.findViewById(R.id.no_data_layout)
        title = root.findViewById(R.id.task_detail_title)
        description = root.findViewById(R.id.task_detail_description)
        fab = root.findViewById(R.id.edit_task_fab)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = TaskDetailInjection.providePresenter(this, this.requireContext())
        setupFab()
    }

    override fun onResume() {
        super.onResume()

        presenter.start(args.taskId)
    }

    override fun onPause() {
        super.onPause()

        presenter.stop()
    }

    //
    // Contractで定義したメソッド
    //

    override fun showTaskDetail(task: Task) {
        //TextViewの書き換え
        title.text = task.title
        description.text = task.description

        // タスク表示用のViewを表示
        taskDetailLayout.visibility = View.VISIBLE
        noDataLayout.visibility = View.GONE
    }

    override fun showNoData() {
        // データが存在しないとき用のViewを表示
        taskDetailLayout.visibility = View.GONE
        noDataLayout.visibility = View.VISIBLE
    }

    override fun showError() {
        // Snackberでエラーを表示
        Snackbar
            .make(rootLayout, "Error", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun showLoadingIndicator() {
        refreshLayout.post { refreshLayout.isRefreshing = true }
    }

    override fun hideLoadingIndicator() {
        refreshLayout.post { refreshLayout.isRefreshing = false }
    }

    override fun navigateEditTask(taskId: String) {
        // タスクIDを引数に、タスク編集画面へ遷移
        val action = TaskDetailFragmentDirections
            .actionTaskDetailFragmentToEditTaskFragment(taskId)

        findNavController().navigate(action)
    }

    //
    // privateメソッド
    //

    private fun setupFab() {
        fab.setOnClickListener {
            presenter.editTask()
        }
    }
}