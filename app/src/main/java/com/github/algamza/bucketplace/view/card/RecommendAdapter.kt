package com.github.algamza.bucketplace.view.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.bucketplace.BR
import com.github.algamza.bucketplace.databinding.RecyclerCardRecommendBinding
import javax.inject.Inject

class RecommendAdapter @Inject constructor(): RecyclerView.Adapter<RecommendAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerCardRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<CardViewModel.Recommend>) {
        differ.submitList(data)
    }

    inner class Holder constructor(private var binding: RecyclerCardRecommendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CardViewModel.Recommend) { binding.setVariable(BR.data, data) }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<CardViewModel.Recommend>() {
        override fun areItemsTheSame(oldItem: CardViewModel.Recommend, newItem: CardViewModel.Recommend): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CardViewModel.Recommend, newItem: CardViewModel.Recommend): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.description == newItem.description
                    && oldItem.img_url == newItem.img_url
                    && oldItem.user_id == newItem.user_id
        }
    }
}