package com.ahmety.mkolay.shoppinghistory.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.ahmety.mkolay.R
import com.ahmety.mkolay.databinding.ItemHeaderBinding
import com.ahmety.mkolay.databinding.ItemShoppingHistoryDetailBinding
import com.ahmety.mkolay.model.Order
import com.ahmety.mkolay.model.enum.GroupItemsViewType
import com.bumptech.glide.Glide

class ShoppingHistoryDetailAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var orderList : MutableList<Order> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: MutableList<Order>){
        orderList.clear()
        orderList.addAll(list)
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind() {
            binding.apply {}
        }
    }

    inner class BodyViewHolder(private val binding: ItemShoppingHistoryDetailBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(item: Order) {
            binding.apply {
                viewBottom.isInvisible = absoluteAdapterPosition == orderList.size
                Glide.with(imgPic).load(item.picUrl).error(R.drawable.ic_baseline_warning_24).into(imgPic)
                txtItemName.text = item.itemName
                txtItemAmount.text = item.itemAmount
                txtItemPrice.text = txtItemPrice.context.getString(R.string.price_with_symbol, item.itemPrice)
                txtItemQuantatiy.isInvisible = item.itemQuantatiy == 1
                imgBg.isInvisible= item.itemQuantatiy == 1
                txtItemQuantatiy.text = txtItemQuantatiy.context.getString(R.string.quantatiy, item.itemQuantatiy)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GroupItemsViewType.Header.ordinal -> {
                val headerView = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(headerView)
            }
            else -> {
                val bodyView = ItemShoppingHistoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return BodyViewHolder(bodyView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            GroupItemsViewType.Header.ordinal -> (holder as? HeaderViewHolder)?.bind()
            else -> (holder as? BodyViewHolder)?.bind(orderList[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {GroupItemsViewType.Header.ordinal} else {GroupItemsViewType.Body.ordinal}
    }

    override fun getItemCount(): Int  = orderList.size + 1
}