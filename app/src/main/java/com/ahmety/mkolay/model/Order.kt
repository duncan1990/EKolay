package com.ahmety.mkolay.model

import android.os.Parcelable
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val id: String = "",
    val picUrl: String = "",
    val itemName: String = "",
    val itemPrice: String = "",
    val itemAmount: String = "",
    val itemQuantatiy: Int = 0
) : Parcelable {
    companion object {
        fun DocumentSnapshot.toOrder(): Order? {
            return try {
                val id = getString("id")!!
                val picUrl = getString("picUrl")!!
                val itemName = getString("itemName")!!
                val itemPrice = getString("itemPrice")!!
                val itemAmount = getString("itemAmount")!!
                val itemQuantatiy = getLong("itemQuantatiy")?.toInt()!!
                Order(id, picUrl, itemName, itemPrice, itemAmount, itemQuantatiy)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting order", e)
                FirebaseCrashlytics.getInstance().log("Error converting order")
                FirebaseCrashlytics.getInstance().setCustomKey("id", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }
        private const val TAG = "Order"
    }
}