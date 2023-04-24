package com.semdelion.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
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
import com.semdelion.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,
            MainViewModelFactory(requireContext().applicationContext)
        )[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.mainViewModel = viewModel

        val view = viewBinding.root

        val sendButton = view.findViewById<Button>(R.id.save_data_button)
        val receiveButton = view.findViewById<Button>(R.id.get_data_button)

        sendButton.setOnClickListener {
            viewModel.save()
        }

        receiveButton.setOnClickListener {
            viewModel.load()
        }

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