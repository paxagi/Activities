package com.example.activities

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InterestsAdapter (
    private var interests: List<Interest>
) : RecyclerView.Adapter<InterestsAdapter.InterestsViewHolder>() {
    class InterestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        val logo: ImageView = itemView.findViewById<ImageView>(R.id.ivLogo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterestsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_interests, parent, false)
        Log.d("recycle", "onCreateViewHolder")
        return InterestsViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        Log.d("recycle", "onBindViewHolder")
        holder.apply {
            this.title.text = interests[position].title
            this.logo.setImageResource(interests[position].image)
        }
    }

    override fun getItemCount(): Int = interests.size
}