package com.esaudev.gamesgarden.ui.recycler_view_animations

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.esaudev.gamesgarden.R
import com.esaudev.gamesgarden.model.Sample

class InfiniteRecyclerAdapter (originalList: List<Sample>)
    : RecyclerView.Adapter<InfiniteRecyclerAdapter.InfiniteRecyclerViewHolder>() {

    private val newList: List<Sample> =
        listOf(originalList.last()) + originalList + listOf(originalList.first())

    class InfiniteRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(sample: Sample) {
            val pagerTextView: TextView = itemView.findViewById(R.id.pagerTextView)
            val pagerCardView: CardView = itemView.findViewById(R.id.pagerCardView)

            pagerTextView.text = sample.number.toString()
            pagerCardView.setBackgroundColor(Color.parseColor(sample.color))
        }

        companion object {
            fun from(parent: ViewGroup) : InfiniteRecyclerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.item_infinite_viewpager,
                    parent, false)
                return InfiniteRecyclerViewHolder(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfiniteRecyclerViewHolder {
        return InfiniteRecyclerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: InfiniteRecyclerViewHolder, position: Int) {
        holder.bind(newList[position])
    }

    override fun getItemCount(): Int {
        return newList.size
    }

}