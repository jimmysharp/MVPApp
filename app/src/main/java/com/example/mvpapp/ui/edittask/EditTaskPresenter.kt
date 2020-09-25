package com.example.mvpapp.ui.edittask

import com.example.mvpapp.data.Result
import com.example.mvpapp.data.Task
import com.example.mvpapp.data.source.TasksDataSource

class EditTaskPresenter(
    private val view: EditTaskContract.View,
    private val dataSource: TasksDataSource
): EditTaskContract.Presenter {

    private var taskId: String? = null

    override fun start(taskId: String?) {
        this.taskId = taskId

        if (taskId != null) {
            view.showLoadingIndicator()

            dataSource.getTask(taskId, { result ->
                when (result) {
                    is Result.Success -> view.setTaskDetail(result.data)
                    is Result.Error -> view.showError()
                }
                view.hideLoadingIndicator()
            })
        }
    }

    override fun saveTask(title: String, description: String) {
        val taskIdCopy = taskId

        view.showLoadingIndicator()

        if (taskIdCopy != null) {
            val task = Task(taskIdCopy, title, description)
            dataSource.updateTask(task, { result ->
                view.hideLoadingIndicator()
                when(result) {
                    is Result.Success -> view.navigateFinishEditTask()
                    is Result.Error -> view.showError()
                }
            })
        } else {
            dataSource.createTask(title, description, { result ->
                view.hideLoadingIndicator()
                when(result) {
                    is Result.Success -> view.navigateFinishEditTask()
                    is Result.Error -> view.showError()
                }
            })
        }
    }
}