package com.example.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InterestsAdapter (
    private var interests: List<Interest>
) : RecyclerView.Adapter<InterestsAdapter.InterestsViewHolder>() {
    private lateinit var tvTitle: TextView
    private lateinit var ivLogo: ImageView
    inner class InterestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InterestsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_interests, parent, false)
        return InterestsViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        holder.itemView.apply {
            tvTitle = this.findViewById<TextView>(R.id.tvTitle)
            ivLogo = this.findViewById<ImageView>(R.id.ivLogo)
            tvTitle.text = interests[position].title
            ivLogo.setImageResource(interests[position].image)
        }
    }

    override fun getItemCount(): Int = interests.size
}