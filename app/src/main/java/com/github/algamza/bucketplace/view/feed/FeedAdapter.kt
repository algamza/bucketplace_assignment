package com.github.algamza.bucketplace.view.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.bucketplace.BR
import com.github.algamza.bucketplace.databinding.RecyclerFeedBinding
import javax.inject.Inject

class FeedAdapter @Inject constructor(): RecyclerView.Adapter<FeedAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<FeedData>) {
        differ.submitList(data)
    }

    inner class Holder constructor(private var binding: RecyclerFeedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FeedData) { binding.setVariable(BR.data, data) }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<FeedData>() {
        override fun areItemsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeedData, newItem: FeedData): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.img.equals(newItem.img)
                    && oldItem.user == newItem.user
        }
    }
}