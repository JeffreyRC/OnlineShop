package com.uilover.project2002.Adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.collection.R
import com.uilover.project2002.Model.CategoryModel
import com.uilover.project2002.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class Viewholder(val binding: ViewholderCategoryBinding) :RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
                val position=adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    lastSelectedPosition=selectedPosition
                    selectedPosition=position
                    notifyItemChanged(lastSelectedPosition)
                    notifyItemChanged(selectedPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item=items[position]
        holder.binding.titleTxt.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUInt)
            .into(holder.binding.pic)

        if(selectedPosition==position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(com.uilover.project2002.R.drawable.green_button_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,com.uilover.project2002.R.color.white))
            )
            holder.binding.titleTxt.visibility=View.VISIBLE
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    com.uilover.project2002.R.color.white
                )
            )
        }else{
            holder.binding.pic.setBackgroundResource(com.uilover.project2002.R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(ContextCompat.getColor(
                    holder.itemView.context,
                    com.uilover.project2002.R.color.black))
            )
            holder.binding.titleTxt.visibility=View.GONE
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    com.uilover.project2002.R.color.black
                )
            )
        }

    }

    override fun getItemCount(): Int=items.size
}