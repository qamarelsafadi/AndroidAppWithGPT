package com.example.playaroundwithgpt.ui

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.playaroundwithgpt.R
import com.example.playaroundwithgpt.databinding.SimpleListItem1Binding
import com.example.playaroundwithgpt.ui.base.Adapter


@BindingAdapter(value = ["list", "onClick"])
fun initMessagesList(
    view: RecyclerView,
    list: List<MessageItem> = listOf(),
    onClick: () -> Unit,
): Adapter<MessageItem> {
    var adapter: Adapter<MessageItem>? = null
    adapter = Adapter(
        R.layout.simple_list_item_1,
        onClick = { position: Int, item: Any ->

        },
        onBind = { position: Int, item: MessageItem, _: View, bind: ViewDataBinding ->
            val binding = bind as SimpleListItem1Binding
            with(binding) {
               Log.e("QMR","${ item.isAnimte}")
                if (position % 2 == 0) {
                    textView.text = item.message
                    rootLt.setBackgroundColor(Color.parseColor("#14000000"))
                } else {
                    rootLt.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    if (item.isAnimte == true) {
                        textView.setCharacterDelay(50);
                        textView.animateText(item.message)
                    } else {
                        textView.text = item.message

                    }
                }
            }
        })
    view.adapter = adapter
    adapter.items = list.toMutableList()
    return adapter
}