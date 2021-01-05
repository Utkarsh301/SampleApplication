package com.example.sampleapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sampleapplication.R
import com.example.sampleapplication.adapter.UserAdapter
import com.example.sampleapplication.databinding.ActivityMainBinding
import com.example.sampleapplication.model.ResponseModel
import com.example.sampleapplication.model.UserResponseModel
import com.example.sampleapplication.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var userViewModel: UserViewModel

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var userAdapter: UserAdapter
    var userResponseModels = ArrayList<UserResponseModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = ViewModelProvider(this@MainActivity)[UserViewModel :: class.java]
        layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false);
        userAdapter = UserAdapter(this@MainActivity, userResponseModels)
        binding.usersRecyclerView.layoutManager = layoutManager
        binding.usersRecyclerView.adapter = userAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (binding.usersRecyclerView.visibility == View.VISIBLE) {
                binding.usersRecyclerView.visibility = View.GONE
                binding.loadingTextView.visibility = View.VISIBLE
            }
            getUsers()
        }
        getUsers()

    }

    private fun getUsers() {
        binding.loadingTextView.text = "Loading please wait..."
        var getUsersLiveData = userViewModel.getUsers(this@MainActivity)
        getUsersLiveData.observe(this@MainActivity,
            {
                if (binding.swipeRefreshLayout.isRefreshing) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                if (it != null && !it.data.isNullOrEmpty()) {
                    userResponseModels.addAll(it.data)
                    userAdapter.notifyDataSetChanged()
                    binding.loadingTextView.visibility = View.GONE
                    binding.usersRecyclerView.visibility = View.VISIBLE
                } else {
                    binding.loadingTextView.text = "No Data Available"
                }
            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}