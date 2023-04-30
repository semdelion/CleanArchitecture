package com.semdelion.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.semdelion.R
import com.semdelion.app.App
import com.semdelion.databinding.FragmentMainBinding
import com.semdelion.extensions.appComponent
import javax.inject.Inject
import com.semdelion.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var viewBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.mainViewModel = viewModel

        val view = viewBinding.root

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.useCaseState.collectLatest {
                    Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view;
    }
}