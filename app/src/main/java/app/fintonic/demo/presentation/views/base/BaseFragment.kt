package app.fintonic.demo.presentation.views.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import app.fintonic.demo.presentation.views.dialogs.ProgressDialog

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment() {

    lateinit var binding: T

    companion object {
        @JvmStatic
        inline fun <reified T : Fragment> getInstance(vararg params: Pair<String, Any>): T =
            T::class.java.newInstance().apply { arguments = bundleOf(*params) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        setupView()
        return binding.root
    }

    protected abstract fun setupView()

    fun showProgressDialog(msg: String? = null) {
        try {
            ProgressDialog.show(childFragmentManager, msg)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun dismissProgressDialog() {
        ProgressDialog.dismiss()
    }

}