package com.example.playaroundwithgpt.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates


class Adapter<T> constructor(
    @LayoutRes private val layoutRes: Int,
     val onClick: ((Int, T) -> Unit)?,
    private val onBind: (Int, T, View, binding: ViewDataBinding) -> Unit,
    ) : RecyclerView.Adapter<Adapter<T>.ViewHolder>() {

    var items = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    fun addAll(list: MutableList<T>) {
        items.addAll(list)
        notifyItemRangeInserted(items.size-1,items.size)
    }
    fun add(item:T) {
        items.add(item)
        notifyItemInserted(itemCount-1)
    }

    fun update(position:Int,item:T) {
        items[position] = item
        notifyItemChanged(position)
    }


    // This keeps track of the currently selected position
    var selectedPosition by Delegates.observable(-1) { _, oldPos, newPos ->
        if (newPos in items.indices) {
            if(oldPos!=-1)
            notifyItemChanged(oldPos,"null")
            notifyItemChanged(newPos,"null")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      // don't forget to make your layout resource as a data binding <layout><layout/>
        val layoutInflater = LayoutInflater.from(parent.getContext())
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutRes,
            parent,
            false
        )
        return ViewHolder(binding)

    }
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        items[position]?.let { item ->
            holder.bind(position, item, holder.itemViewBinding)

            holder.itemView.setOnClickListener {
                selectedPosition = position
               // notifyDataSetChanged()
                    onClick?.let { it1 -> it1(position, item) }

            }
        }
    }

    fun delete(dummyList: MutableList<T>) {
        dummyList.removeAt(selectedPosition)
        items = dummyList
    }

    inner class ViewHolder(val itemViewBinding: ViewDataBinding) : RecyclerView.ViewHolder(
        itemViewBinding.root
    ) {
        fun bind(position: Int, item: T, itemViewBinding: ViewDataBinding) = onBind(
            position,
            item,
            itemView,
            itemViewBinding
        )
    }
}