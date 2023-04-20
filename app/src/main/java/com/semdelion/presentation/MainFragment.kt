package com.semdelion.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.semdelion.R
import com.semdelion.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var viewBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.mainViewModel = viewModel

        val view = viewBinding.root

        val dataTextView = view.findViewById<TextView>(R.id.get_data_textview)
        val sendButton = view.findViewById<Button>(R.id.save_data_button)
        val receiveButton = view.findViewById<Button>(R.id.get_data_button)

        viewModel.loadedUserLive.observe(viewLifecycleOwner) {
            dataTextView.text = it
        }

        sendButton.setOnClickListener {
            viewModel.save()
        }

        receiveButton.setOnClickListener {
            viewModel.load()
        }

        return view;
    }
}