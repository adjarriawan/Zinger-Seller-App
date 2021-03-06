package com.food.ordering.zinger.seller.data.local

import com.food.ordering.zinger.seller.data.model.ShopConfigurationModel
import com.food.ordering.zinger.seller.data.model.UserModel

interface AppPreferencesHelper {

    val name: String?
    val email: String?
    val mobile: String?
    val role: String?
    val oauthId: String?
    val shop: String?
    val currentShop: Int?
    val id: Int?
    val fcmToken: String?
    val tempMobile: String?
    val tempOauthId: String?


    var isFCMTokenUpdated: Boolean?
    var isFCMTopicSubScribed: Boolean?
    var orderStatusChanged: Boolean


    var updateItemRequest: String
    var deleteItemRequest: Int


    fun saveUser(id: Int?,name: String?,email: String?, mobile: String?, role: String?, oauthId: String?, shop: String?)

    fun clearPreferences()

    fun getShop():List<ShopConfigurationModel>?

    fun getUser(): UserModel?
}