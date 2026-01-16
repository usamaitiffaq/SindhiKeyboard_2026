package com.sindhi.urdu.english.keybad.sindhikeyboard.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.core.content.edit

class BillingManager private constructor(context: Context) {

    private val applicationContext = context.applicationContext
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _productDetailsList = MutableStateFlow<List<ProductDetails>>(emptyList())
    val productDetailsList = _productDetailsList.asStateFlow()

    private val _isPremium = MutableStateFlow(false)
    val isPremium = _isPremium.asStateFlow()

    companion object {
        private const val PRODUCT_ID = "sindhikeyboard_basic_offer"
        private const val LOG_TAG = "BillingManager"

        @Volatile
        private var INSTANCE: BillingManager? = null

        fun getInstance(context: Context): BillingManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BillingManager(context).also { INSTANCE = it }
            }
        }
    }

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.e(LOG_TAG, "User canceled purchase")
        } else {
            Log.e(LOG_TAG, "Purchase error: ${billingResult.debugMessage}")
        }
    }

    private val billingClient = BillingClient.newBuilder(applicationContext)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases(
            PendingPurchasesParams.newBuilder()
                .enableOneTimeProducts()
                .build()
        )
        .build()

    init {
        startConnection()
    }



    private fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.d(LOG_TAG, "Billing Setup Finished")
                    queryAvailableProducts()
                    checkActivePurchases()
                } else {
                    Log.e(LOG_TAG, "Billing Setup Failed: ${billingResult.debugMessage}")
                    // Optional: Retry after delay here too if needed
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.e(LOG_TAG, "Billing Disconnected. Retrying in 2 seconds...")
                // --- FIX: Add a delay to prevent infinite immediate loops ---
                scope.launch {
                    kotlinx.coroutines.delay(2000) // Wait 2 seconds
                    startConnection()
                }
            }
        })
    }

    fun queryAvailableProducts() {
        if (!billingClient.isReady) {
            Log.w(LOG_TAG, "Billing Client not ready yet. Skipping product query.")
            return
        }

        val productList = listOf(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(PRODUCT_ID)
                .setProductType(BillingClient.ProductType.SUBS)
                .build()
        )

        val params = QueryProductDetailsParams.newBuilder().setProductList(productList).build()

        scope.launch {
            val result = billingClient.queryProductDetails(params)

            if (result.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val detailsList = result.productDetailsList
                if (detailsList != null) {
                    _productDetailsList.emit(detailsList)
                }
            } else {
                Log.e(LOG_TAG, "Query Product Details Failed: ${result.billingResult.debugMessage}")
            }
        }
    }
    fun launchPurchaseFlow(activity: Activity, productDetails: ProductDetails, offerToken: String) {
        if (!billingClient.isReady) {
            Log.e(LOG_TAG, "Billing Client is not ready. Cannot launch purchase flow.")
            return
        }

        // --- FIX: Validate Input to prevent Crash ---
        if (offerToken.isEmpty()) {
            Log.e(LOG_TAG, "Invalid Offer Token: Cannot launch flow with empty token.")
            return
        }
        if (productDetails.productId.isEmpty()) {
            Log.e(LOG_TAG, "Invalid ProductDetails.")
            return
        }
        // --------------------------------------------

        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(productDetails)
                .setOfferToken(offerToken)
                .build()
        )

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

        billingClient.launchBillingFlow(activity, billingFlowParams)
    }


    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                scope.launch {
                    val result = billingClient.acknowledgePurchase(acknowledgePurchaseParams)
                    if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                        Log.e(LOG_TAG, "Purchase Acknowledged: ${purchase.orderId}")
                        setPremiumStatus(true)
                    } else {
                        Log.e(LOG_TAG, "Acknowledge Failed: ${result.debugMessage}")
                    }
                }
            } else {
                setPremiumStatus(true)
            }
        } else if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
            // Handle pending purchase scenario (e.g., payment requires external action)
            Log.e(LOG_TAG, "Purchase Pending: ${purchase.orderId}")
        }
    }

    fun checkActivePurchases() {
        if (!billingClient.isReady) {
            // Try to reconnect if client is lost
            startConnection()
            return
        }

        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(params) { billingResult, purchasesList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                // purchasesList only contains ACTIVE subscriptions.
                // If the list is empty, it means the subscription expired.

                val hasActivePurchase = purchasesList.any { it.purchaseState == Purchase.PurchaseState.PURCHASED }

                // This updates the Boolean Flow and SharedPrefs
                setPremiumStatus(hasActivePurchase)

                if (!hasActivePurchase) {
                    Log.e(BillingManager.Companion.LOG_TAG, "No active subscription found. Resetting to Free User.")
                }
            }
        }
    }

    private fun setPremiumStatus(isPremium: Boolean) {
        scope.launch {
            _isPremium.emit(isPremium)
            withContext(Dispatchers.Main) {
                val prefs = applicationContext.getSharedPreferences(RemoteConfigConst.REMOTE_CONFIG, Context.MODE_PRIVATE)
                prefs.edit { putBoolean(RemoteConfigConst.IS_PURCHASED, isPremium) }
            }
        }
    }
}