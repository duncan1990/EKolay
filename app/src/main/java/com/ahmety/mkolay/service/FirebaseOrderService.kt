package com.ahmety.mkolay.service

import android.util.Log
import com.ahmety.mkolay.model.Order
import com.ahmety.mkolay.model.Order.Companion.toOrder
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseOrderService {
    private const val TAG = "FirebaseOrderService"
    suspend fun getOrderData(): List<Order>? {
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection("Order").get().await()
                .documents.mapNotNull { it.toOrder() }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting order details", e)
            FirebaseCrashlytics.getInstance().log("Error getting order details")
            FirebaseCrashlytics.getInstance().setCustomKey("id", "1")
            FirebaseCrashlytics.getInstance().recordException(e)
            null
        }
    }

/*    fun getOrder(id: String): Flow<List<Post>> {
        val db = FirebaseFirestore.getInstance()
        return callbackFlow {
            val listenerRegistration = db.collection("users")
                .document(userId)
                .collection("posts")
                .addSnapshotListener { querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
                    if (firebaseFirestoreException != null) {
                        cancel(message = "Error fetching posts",
                               cause = firebaseFirestoreException)
                        return@addSnapshotListener
                    }
                    val map = querySnapshot.documents.
                        .mapNotNull { it.toPost() }
                    offer(map)
                }
            awaitClose {
                Log.d(TAG, "Cancelling posts listener")
                listenerRegistration.remove()
            }
        }
    }*/

}