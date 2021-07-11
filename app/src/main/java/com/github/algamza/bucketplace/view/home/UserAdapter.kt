package com.github.algamza.bucketplace.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.bucketplace.BR
import com.github.algamza.bucketplace.databinding.RecyclerHomeUserBinding
import javax.inject.Inject

class UserAdapter @Inject constructor(): RecyclerView.Adapter<UserAdapter.Holder>() {

    private val differ = AsyncListDiffer(this, DiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerHomeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<UserData>) {
        differ.submitList(data)
    }

    inner class Holder constructor(private var binding: RecyclerHomeUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserData) { binding.setVariable(BR.data, data) }
    }

    inner class DiffCallback : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.introduction.equals(newItem.introduction)
                    && oldItem.nickname.equals(newItem.nickname)
        }
    }
}