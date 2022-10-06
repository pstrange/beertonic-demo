package app.fintonic.demo.presentation.views.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.fintonic.demo.presentation.views.dialogs.ProgressDialog

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    fun showProgressDialog(msg: String? = null) {
        try {
            ProgressDialog.show(supportFragmentManager, msg)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun dismissProgressDialog() {
        ProgressDialog.dismiss()
    }

}