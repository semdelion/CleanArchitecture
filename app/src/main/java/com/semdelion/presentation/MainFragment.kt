package com.semdelion.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.semdelion.R
import com.semdelion.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var viewBinding: FragmentMainBinding

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

        return view;
    }
}