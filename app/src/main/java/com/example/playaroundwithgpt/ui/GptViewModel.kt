package com.example.playaroundwithgpt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playaroundwithgpt.data.Resource
import com.example.playaroundwithgpt.data.Status
import com.example.playaroundwithgpt.data.model.DataResponse
import com.example.playaroundwithgpt.data.model.GPTChatRequest
import com.example.playaroundwithgpt.data.model.GPTContent
import com.example.playaroundwithgpt.data.model.GPTMessage
import com.example.playaroundwithgpt.data.repository.GptRepository
import com.example.playaroundwithgpt.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class GptViewModel @Inject constructor(
    private val repository: GptRepository
) : ViewModel() {

    private val _response = MutableSharedFlow<Event<Resource<String>>>(replay = 0)
    val response: SharedFlow<Event<Resource<String>>> = _response


    fun sendMessage(message:String) {
        viewModelScope.launch {
            _response.emit(Event(Resource.loading(null)))
            val request = GPTChatRequest(
                messages = listOf(
                    GPTMessage(
                        id = UUID.randomUUID().toString(),
                        content = GPTContent(parts = listOf(message))
                    )
                ),
                conversation_id = "d7e2b289-67c1-4497-a87b-c693c5ec6822",
                parent_message_id = "cbd6bbea-8dd7-426b-883c-a3ae0b956539"
            )
            val flowResult = repository.sendMessages(request)
            flowResult.collect { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        _response.emit(
                            Event(
                                Resource.success(
                                    result.data!!,
                                    result.message
                                )
                            )
                        )
                    }
                    Status.ERROR -> {
                        _response.emit(
                            Event(
                                Resource.error(
                                    null,
                                    result.message)
                            )
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}


