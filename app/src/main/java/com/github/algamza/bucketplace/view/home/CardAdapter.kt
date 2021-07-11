package com.github.algamza.bucketplace.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.bucketplace.BR
import com.github.algamza.bucketplace.databinding.RecyclerHomeCardBinding
import javax.inject.Inject

class CardAdapter @Inject constructor(): RecyclerView.Adapter<CardAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<CardData>) {
        differ.submitList(data)
    }

    inner class Holder constructor(private var binding: RecyclerHomeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CardData) { binding.setVariable(BR.data, data) }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<CardData>() {
        override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.description.equals(newItem.description)
                    && oldItem.img_url.equals(newItem.img_url)
                    && oldItem.user_id == newItem.user_id
        }
    }
}