package com.example.mvpapp.ui.tasklist

import android.os.Bundle
import android.view.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.example.mvpapp.R
import com.example.mvpapp.data.Task
import com.example.mvpapp.ui.ScrollChildSwipeRefreshLayout

class TaskListFragment : Fragment(), TaskListContract.View {

    // Presenter
    private lateinit var presenter: TaskListContract.Presenter

    // Viewオブジェクト
    private lateinit var rootLayout: CoordinatorLayout
    private lateinit var noTasksLayout: ViewGroup
    private lateinit var refreshLayout: ScrollChildSwipeRefreshLayout
    private lateinit var taskList: RecyclerView
    private lateinit var taskListAdapter: TaskListAdapter
    private lateinit var fab: FloatingActionButton

    //
    // ライフサイクルメソッド
    //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.task_list_fragment, container, false)
        rootLayout = root.findViewById(R.id.task_list_container)
        noTasksLayout = root.findViewById(R.id.no_tasks_layout)
        refreshLayout = root.findViewById(R.id.refresh_layout)
        taskList = root.findViewById(R.id.tasks_list)
        fab = root.findViewById(R.id.add_task_fab)

        // メニューを表示させる
        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // メニュー項目を追加
        inflater.inflate(R.menu.task_list_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = TaskListInjection.providePresenter(this, this.requireContext())
        setupRecyclerView()
        setupRefreshLayout()
        setupFab()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // ActionBar上のメニューの処理
        // ゴミ箱アイコンを押したらタスク削除を行う
        when(item.itemId) {
            R.id.delete_all_tasks -> {
                presenter.deleteAllTasks()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    //
    // Contractで定義したメソッド
    //

    override fun showTasks(tasks: List<Task>) {
        if(tasks.isEmpty()) {
            // タスクが0の場合の表示
            taskList.visibility = View.GONE
            noTasksLayout.visibility = View.VISIBLE
        } else {
            // タスクがある場合の表示
            taskList.visibility = View.VISIBLE
            noTasksLayout.visibility = View.GONE

            taskListAdapter.submitList(tasks)
        }
    }

    override fun showError() {
        // スナックバーでエラー表示
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

    override fun navigateAddNewTask() {
        // タスク追加画面へ遷移
        val action = TaskListFragmentDirections
            .actionTaskListFragmentToEditTaskFragment(null)

        findNavController().navigate(action)
    }

    override fun navigateTaskDetail(taskId: String) {
        // タスクIDを引数とし、詳細画面へ遷移
        val action = TaskListFragmentDirections
            .actionTaskListFragmentToTaskDetailFragment(taskId)

        findNavController().navigate(action)
    }

    //
    // privateメソッド
    //

    private fun setupRecyclerView() {
        // RecyclerView(タスクリスト表示部分)の初期化
        taskListAdapter = TaskListAdapter({ task ->
            presenter.openTaskDetail(task)
        })
        taskList.adapter = taskListAdapter
    }

    private fun setupRefreshLayout() {
        refreshLayout.scrollUpChild = taskList
        refreshLayout.setOnRefreshListener {
            presenter.loadTasks()
        }
    }

    private fun setupFab() {
        fab.setOnClickListener {
            presenter.addTask()
        }
    }
}
