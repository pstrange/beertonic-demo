package app.fintonic.demo.presentation.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import app.fintonic.demo.R
import app.fintonic.demo.databinding.ActivitySplashBinding
import app.fintonic.demo.presentation.utils.openActivity
import app.fintonic.demo.presentation.viewmodel.SplashViewModel
import app.fintonic.demo.presentation.views.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash){

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewData.observe(this, ::countDownObserver)
        viewModel.startCountDown()
    }

    private fun countDownObserver(isSuccess: Boolean){
        if(isSuccess) openActivity<MainActivity>()
    }
}