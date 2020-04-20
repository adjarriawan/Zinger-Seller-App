package com.food.ordering.zinger.seller.data.retofit

import android.content.Context
import com.food.ordering.zinger.seller.data.local.PreferencesHelper
import com.food.ordering.zinger.seller.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val context: Context, val preferences: PreferencesHelper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val whiteListedEndpoints = listOf(
            "/user/seller"
        )

        val request = if (!whiteListedEndpoints.contains(req.url().encodedPath())) {
            req.newBuilder()
                .addHeader("oauth_id", preferences.oauthId)
                .addHeader("id", preferences.id.toString())
                .addHeader("role", preferences.role)
                .build()
        } else {
            req.newBuilder().build()
        }
        val response = chain.proceed(request)
        return response
    }
}