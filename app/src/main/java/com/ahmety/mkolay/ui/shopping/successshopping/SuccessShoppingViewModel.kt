package com.ahmety.mkolay.ui.shopping.successshopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmety.mkolay.model.Order
import com.ahmety.mkolay.service.FirebaseOrderService
import kotlinx.coroutines.launch

class SuccessShoppingViewModel : ViewModel() {
    private val _orderList = MutableLiveData<List<Order>>()
    val order: LiveData<List<Order>> = _orderList
    private val _liveText = MutableLiveData<String>()
    val liveText: LiveData<String> = _liveText

    init {
        viewModelScope.launch {
            var totalFee = 00.00
            _orderList.value = FirebaseOrderService.getOrderData()
            _orderList.value?.let { orderL ->
                for (i in orderL.indices) {
                    val price = _orderList.value?.get(i)?.itemPrice?.toDoubleOrNull()
                    if (price != null) {
                        _orderList.value?.get(i)?.itemQuantatiy?.let {
                            val priceTimesQuantatiy = price * it
                            totalFee += priceTimesQuantatiy
                        }
                    }
                }
            }

            _liveText.value = totalFee.toString().checkMoneyDigit()
        }
    }

    private fun String.checkMoneyDigit(): String {
        val digitAfterDot = this.substringAfterLast(".")
        return if (digitAfterDot.length == 1) {
            this + "0"
        } else {
            this
        }
    }

}