package com.uilover.project2002.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uilover.project2002.Model.ItemsModel
import com.uilover.project2002.databinding.ViewholderRecommendedBinding

class RecommendedAdapter (val items:MutableList<ItemsModel>):RecyclerView.Adapter<RecommendedAdapter.Viewholder>(){

    class Viewholder(val binding: ViewholderRecommendedBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedAdapter.Viewholder {
        val binding=ViewholderRecommendedBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedAdapter.Viewholder, position: Int) {
        val item=items[position]

        with(holder.binding){
            tilteTxt.text=item.title
            priceTxt.text="$${item.price}"
            ratingTxt.text=item.rating.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(pic)

            root.setOnClickListener{

            }

        }
    }

    override fun getItemCount(): Int= items.size
}