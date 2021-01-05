package com.example.sampleapplication.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.UserItemLayoutBinding
import com.example.sampleapplication.model.UserResponseModel



class UserAdapter(var activity: Activity, var userResponseModels: ArrayList<UserResponseModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return UserViewHolder(binding);
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userResponseModel = userResponseModels[getItemViewType(position)]

        holder.itemLayoutBinding.userNameTextView.text  = userResponseModel.name
        holder.itemLayoutBinding.userEmailTextView.text = userResponseModel.email
        holder.itemLayoutBinding.userGenderTextView.text = userResponseModel.gender
        holder.itemLayoutBinding.userStatusTextView.text = userResponseModel.status
        if (userResponseModel.status.equals("Inactive")) {
            holder.itemLayoutBinding.userStatusTextView.setTextColor(ResourcesCompat.getColor(activity.resources,
                R.color.red, activity.theme))
        }
        holder.itemLayoutBinding.createdAtTextView.text = userResponseModel.createdAt
        holder.itemLayoutBinding.updatedAtTextView.text = userResponseModel.updatedAt

    }

    override fun getItemCount(): Int {
        return userResponseModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class UserViewHolder(var itemLayoutBinding: UserItemLayoutBinding) : RecyclerView.ViewHolder(itemLayoutBinding.root)

}