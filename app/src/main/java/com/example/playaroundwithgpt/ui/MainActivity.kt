package com.example.playaroundwithgpt.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.playaroundwithgpt.R
import com.example.playaroundwithgpt.data.Resource
import com.example.playaroundwithgpt.data.Status
import com.example.playaroundwithgpt.databinding.ActivityMainBinding
import com.example.playaroundwithgpt.ui.base.Adapter
import com.example.playaroundwithgpt.utils.Event
import com.example.playaroundwithgpt.utils.onDone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter<MessageItem>
    private val viewModel by viewModels<GptViewModel>()
    var messagesList = mutableListOf<MessageItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            subscribeObservers()
            adapter = initMessagesList(listItem) {}
            messageEt.onDone {
                if (messageEt.text.toString().isEmpty().not()) {
                    messagesList.add(MessageItem(messageEt.text.toString(), false))
                    messagesList.forEach {
                        it.isAnimte = false
                    }
                    adapter.items = messagesList
                    viewModel.sendMessage(messageEt.text.toString())
                    messageEt.text.clear()
                }
            }
        }

    }


    private fun subscribeObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.response.collect {
                    handleResponse(it)
                }
            }
        }
    }

    private fun handleResponse(it: Event<Resource<String>>) {
        val resp = it.peekContent()
        when (resp.status) {
            Status.LOADING -> {
                binding.loadingView.visibility = View.VISIBLE
            }

            Status.SUCCESS -> {
                resp.data.let { message ->
                    binding.loadingView.visibility = View.GONE
                    adapter.items.forEach {
                        it.isAnimte = false
                    }
                    messagesList.add(MessageItem(message ?: "", true))
                    adapter.items = messagesList
                }
            }

            Status.ERROR -> {

            }
        }
    }


}