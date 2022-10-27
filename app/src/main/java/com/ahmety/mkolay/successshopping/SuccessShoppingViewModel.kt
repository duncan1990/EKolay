package com.ahmety.mkolay.successshopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmety.mkolay.model.Order
import com.ahmety.mkolay.service.FirebaseOrderService
import kotlinx.coroutines.launch

class SuccessShoppingViewModel : ViewModel() {
    private val _orderList = MutableLiveData<Order>()
    val order: LiveData<Order> = _orderList
    private val _liveText = MutableLiveData<String>()
    val liveText: LiveData<String> = _liveText
    var total = ""

    init {
        viewModelScope.launch {
            _orderList.value = FirebaseOrderService.getOrderData()
            val price = _orderList.value?.itemPrice?.toDoubleOrNull()
            if (price != null) {
                _orderList.value?.itemQuantatiy?.let {
                    val priceTimesQuantatiy = price * it
                    total = priceTimesQuantatiy.toString()
                    _liveText.value = total
                }
            }
        }
    }

}