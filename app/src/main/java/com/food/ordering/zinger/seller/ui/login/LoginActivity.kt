package com.food.ordering.zinger.seller.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.food.ordering.zinger.seller.R
import com.food.ordering.zinger.seller.data.local.PreferencesHelper
import com.food.ordering.zinger.seller.databinding.ActivityLoginBinding
import com.food.ordering.zinger.seller.ui.home.HomeActivity
import com.food.ordering.zinger.seller.ui.otp.OTPActivity
import com.food.ordering.zinger.seller.utils.AppConstants
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val preferencesHelper: PreferencesHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        setListener()

        if (!preferencesHelper.oauthId.isNullOrEmpty() && preferencesHelper.id!=-1) {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun setListener() {
        binding.buttonLogin.setOnClickListener {
            val phoneNo = binding.editPhone.text.toString()
            if (phoneNo.isNotEmpty() && phoneNo.length==10 && phoneNo.matches(Regex("\\d+"))) {
                val intent = Intent(applicationContext, OTPActivity::class.java)
                intent.putExtra(AppConstants.PREFS_SELLER_MOBILE, "+91"+phoneNo)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Phone number is incorrect!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
