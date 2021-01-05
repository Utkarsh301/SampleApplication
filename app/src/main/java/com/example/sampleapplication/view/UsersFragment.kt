package com.example.sampleapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.adapter.UserAdapter
import com.example.sampleapplication.databinding.FragmentUsersBinding
import com.example.sampleapplication.model.UserResponseModel
import com.example.sampleapplication.viewmodel.UserViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentUsersBinding

    lateinit var userViewModel: UserViewModel

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var userAdapter: UserAdapter
    var userResponseModels = ArrayList<UserResponseModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(activity!!)[UserViewModel :: class.java]
        layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false);
        userAdapter = UserAdapter(activity!!, userResponseModels)
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
        return binding.root
    }

    private fun getUsers() {
        binding.loadingTextView.text = "Loading please wait..."
        var getUsersLiveData = userViewModel.getUsers(activity!!)
        getUsersLiveData.observe(activity!!,
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
                getUsersLiveData.removeObservers(activity!!)
            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}