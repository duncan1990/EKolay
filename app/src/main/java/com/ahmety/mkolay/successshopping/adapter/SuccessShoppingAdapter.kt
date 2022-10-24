package com.ahmety.mkolay.successshopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.ItemOrderBinding
import com.ahmety.mkolay.model.Order
import com.bumptech.glide.Glide

class SuccessShoppingAdapter : ListAdapter<Order, SuccessShoppingAdapter.HomeViewHolder>(
    SuccessShoppingAdapterComparator
) {
    inner class HomeViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: Order) {
            binding.apply {
                Glide.with(imgPic).load(item.picUrl).error(R.drawable.ic_baseline_warning_24).into(imgPic)
                txtItemName.text = item.itemName
                txtItemAmount.text = item.itemAmount
                txtItemPrice.text = txtItemPrice.context.getString(R.string.price, item.itemPrice)
                txtItemQuantatiy.text = item.itemQuantatiy
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val mView = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(mView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val SuccessShoppingAdapterComparator = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem == newItem
            }
        }
    }
}