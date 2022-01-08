package com.curso.android.myapplication.room_4.ui.dashboard


import android.content.ClipData
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.android.myapplication.room_4.adapter.TaskAdapter
import com.curso.android.myapplication.room_4.database.TaskEntity
import com.curso.android.myapplication.room_4.databinding.FragmentDashboardBinding
import com.curso.android.myapplication.room_4.ui.home.HomeViewModel
import com.curso.android.myapplication.room_4.utils.InjectorUtils

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var dashboardViewModel: HomeViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: TaskAdapter
    private var miTasks = listOf<TaskEntity>()

    private val itemTouchHelperCallback = object : ItemTouchHelper.Callback(){
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val swipeDrag = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(0,swipeDrag)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val posicion = viewHolder.adapterPosition
            val selectTask = miTasks[posicion]
            Log.d("TAG","Delete: $selectTask")
            delteTask(selectTask)





        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = InjectorUtils.inyectarHomeViewModelFactory(requireContext())
        dashboardViewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root




        dashboardViewModel.allTasks.observe(viewLifecycleOwner){tasks->
            tasks.let {
                miTasks = tasks
                mAdapter.submitList(tasks) }
        }


        mRecyclerView = binding.rvTask
        mAdapter = TaskAdapter({ checkItemTask(it) }, { delteTask(it) })
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)


        binding.btnAddTask.setOnClickListener {
            val nuevaTarea = binding.etTask.editText?.text
            dashboardViewModel.insertTask(TaskEntity(name = nuevaTarea.toString(), isDOne = false))
            clearFocus()
            hideKeyboard()
        }
        return root
    }

    private fun delteTask(task: TaskEntity) {
        Log.d("TAG","delete: $task")
        dashboardViewModel.deleteTask(task)

    }

    private fun checkItemTask(task: TaskEntity) {
        Log.d("TAG","check: $task")
        task.isDOne = !task.isDOne
        dashboardViewModel.upDateTask(task)

    }


    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken,0)
    }

    private fun clearFocus() {
        binding.etTask.editText?.setText("")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}