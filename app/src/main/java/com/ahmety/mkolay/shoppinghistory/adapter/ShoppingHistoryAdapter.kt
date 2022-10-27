package com.ahmety.mkolay.shoppinghistory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.ItemShoppingHistoryBinding
import com.ahmety.mkolay.extension.toString
import com.ahmety.mkolay.model.ShoppingHistory


class ShoppingHistoryAdapter(private val onClickItem: (ShoppingHistory) -> Unit): ListAdapter<ShoppingHistory, ShoppingHistoryAdapter.HomeViewHolder>(
    ShoppingHistoryAdapterComparator
) {
    inner class HomeViewHolder(private val binding: ItemShoppingHistoryBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: ShoppingHistory, onClickItem: (ShoppingHistory) -> Unit) {
            binding.apply {
                txtMarketName.text = item.marketName
                txtFee.text = txtFee.context.getString(R.string.price_with_turkish_lira, item.price).replace(".",",")
                txtDate.text = item.date.toString("dd MMMM yyyy")

                root.setOnClickListener {
                    onClickItem.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val mView = ItemShoppingHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(mView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), onClickItem)
    }

    companion object {
        private val ShoppingHistoryAdapterComparator = object : DiffUtil.ItemCallback<ShoppingHistory>() {
            override fun areItemsTheSame(oldItem: ShoppingHistory, newItem: ShoppingHistory): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShoppingHistory, newItem: ShoppingHistory): Boolean {
                return oldItem == newItem
            }
        }
    }
}