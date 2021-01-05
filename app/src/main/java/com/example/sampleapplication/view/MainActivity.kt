package com.example.sampleapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.sampleapplication.R
import com.example.sampleapplication.adapter.BottomPagerAdapter
import com.example.sampleapplication.adapter.UserAdapter
import com.example.sampleapplication.databinding.ActivityMainBinding
import com.example.sampleapplication.model.ResponseModel
import com.example.sampleapplication.model.UserResponseModel
import com.example.sampleapplication.viewmodel.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomPagerAdapter: BottomPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.background = null
        bottomPagerAdapter = BottomPagerAdapter(this@MainActivity)
        bottomPagerAdapter.addFragment(HomeFragment())
        bottomPagerAdapter.addFragment(UsersFragment())
        bottomPagerAdapter.addFragment(ProfileFragment())
        bottomPagerAdapter.addFragment(MusicFragment())
        binding.viewPager.adapter = bottomPagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    binding.viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.users -> {
                    binding.viewPager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    binding.viewPager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.music -> {
                    binding.viewPager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }

        }
    }



    companion object {
        private const val TAG = "MainActivity"
    }
}


