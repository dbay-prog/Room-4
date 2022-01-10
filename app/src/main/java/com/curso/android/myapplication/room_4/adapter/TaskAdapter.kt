package com.curso.android.myapplication.room_4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.curso.android.myapplication.room_4.R
import com.curso.android.myapplication.room_4.database.TaskEntity
import com.curso.android.myapplication.room_4.databinding.ItemTaskBinding


class TaskAdapter(
    private val checkTask:(TaskEntity)->Unit,
    private val deleteTask:(TaskEntity)->Unit
): ListAdapter<TaskEntity, TaskAdapter.TaskViewHolder>(TasksComparator()){


    class TaskViewHolder(
        view:View,
        private val checkTask:(TaskEntity)->Unit,
        private val deleteTask:(TaskEntity)->Unit):RecyclerView.ViewHolder(view) {
        val binding = ItemTaskBinding.bind(view)

        private var currentTask:TaskEntity? = null
        init {
            view.setOnClickListener {
                currentTask?.let {
                    deleteTask(it)
                }
            }
        }

        private var currentCheck:TaskEntity?=null
        init {
            binding.cbIsDone.setOnClickListener {
                currentCheck?.let {
                    checkTask(it)
                }
            }
        }

        fun bind(task: TaskEntity){
            currentTask = task
            currentCheck = task
            binding.tvTask.text = task.name
            binding.cbIsDone.isChecked = task.isDOne
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_task,parent,false)
        return TaskViewHolder(view,checkTask,deleteTask)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

}

class TasksComparator:DiffUtil.ItemCallback<TaskEntity>() {
    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.name == newItem.name
    }

}





