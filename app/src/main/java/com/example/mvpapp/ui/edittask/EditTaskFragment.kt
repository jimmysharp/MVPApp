package com.example.mvpapp.ui.edittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.example.mvpapp.R
import com.example.mvpapp.data.Task
import com.example.mvpapp.ui.ScrollChildSwipeRefreshLayout

class EditTaskFragment : Fragment(), EditTaskContract.View {

    // Fragmentに渡される引数
    private val args: EditTaskFragmentArgs by navArgs()

    // Presenter
    private lateinit var presenter: EditTaskContract.Presenter

    // Viewオブジェクト
    private lateinit var rootLayout: ViewGroup
    private lateinit var refreshLayout: ScrollChildSwipeRefreshLayout
    private lateinit var titleText: EditText
    private lateinit var descriptionText: EditText
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
        val root = inflater.inflate(R.layout.edit_task_fragment, container, false)

        // Viewオブジェクトの取得
        rootLayout = root.findViewById(R.id.edit_task_container)
        refreshLayout = root.findViewById(R.id.refresh_layout)
        titleText = root.findViewById(R.id.edit_task_title_edit_text)
        descriptionText = root.findViewById(R.id.edit_task_description_edit_text)
        fab = root.findViewById(R.id.save_task_fab)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = EditTaskInjection.providePresenter(this, this.requireContext())
        setupRefreshLayout()
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

    override fun setTaskDetail(task: Task) {
        // TextViewの書き換え
        titleText.setText(task.title, TextView.BufferType.NORMAL)
        descriptionText.setText(task.description, TextView.BufferType.NORMAL)
    }

    override fun showError() {
        // Snackbarにエラーメッセージを表示
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

    override fun navigateFinishEditTask() {
        // タスク一覧画面へ戻る
        val action = EditTaskFragmentDirections
            .actionEditTaskFragmentToTaskListFragment()

        findNavController().navigate(action)
    }

    //
    // privateメソッド
    //

    private fun setupRefreshLayout() {
        refreshLayout.scrollUpChild = null
    }

    private fun setupFab() {
        fab.setOnClickListener {
            presenter.saveTask(
                titleText.text.toString(),
                descriptionText.text.toString()
            )
        }
    }
}