package com.example.playaroundwithgpt.utils

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.example.playaroundwithgpt.data.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow


fun <E> handleExceptions(errorBase: Any): MutableStateFlow<Resource<E>> {
    return MutableStateFlow<Resource<E>>(
        Resource.error(
            null,
            errorBase.toString()
        )
    )
}

val String.color
    get() = androidx.compose.ui.graphics.Color(android.graphics.Color.parseColor(this))


fun <E> handleSuccess(data: E?, message: String? = ""): MutableStateFlow<Resource<E>> {
    return MutableStateFlow(
        Resource.success(
            data,
            message = message
        )
    )
}


fun <E> handleExceptions(errorBase: Exception): MutableStateFlow<Resource<E>> {
    return MutableStateFlow(
        Resource.error(
            null,
            errorBase.message)
    )
}

object GsonUtil {

    val gson by lazy { Gson() }

    fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }
    inline fun <reified T> fromJson(json: String): T {
        return gson.fromJsonTypeToken(json)
    }


}

inline fun <reified T> Gson.fromJsonTypeToken(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)


fun Any.toJson() = GsonUtil.toJson(this)
inline fun <reified T> String.fromJson() = GsonUtil.fromJson<T>(this)


fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback.invoke()
        }
        false
    }
}