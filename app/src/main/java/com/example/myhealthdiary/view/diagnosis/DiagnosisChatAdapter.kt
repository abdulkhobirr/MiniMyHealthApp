package com.example.myhealthdiary.view.diagnosis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthdiary.data.diagnosischat.model.Message
import com.example.myhealthdiary.databinding.ItemMyMessageBinding
import com.example.myhealthdiary.databinding.ItemOtherMessageBinding

class DiagnosisChatAdapter(): RecyclerView.Adapter<DiagnosisChatAdapter.ViewHolder>() {
    private val messages: ArrayList<Message> = ArrayList()

    companion object {
        private const val VIEW_TYPE_MY_MESSAGE = 1
        private const val VIEW_TYPE_OTHER_MESSAGE = 2
    }

    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        return if(message.sender == "user") {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            val binding = ItemMyMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    //            MyMessageViewHolder(
    //                    LayoutInflater.from(parent.context).inflate(R.layout.item_my_message, parent, false)
    //            )
            MyMessageViewHolder(binding)
        } else {
            val binding = ItemOtherMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    //            OtherMessageViewHolder(
    //                    LayoutInflater.from(parent.context).inflate(R.layout.item_other_message, parent, false)
    //            )
            OtherMessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]

        holder.bind(message)
    }

    open class ViewHolder (@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bind(message: Message) {}
    }

    inner class MyMessageViewHolder (private val binding: ItemMyMessageBinding) : ViewHolder(binding.root) {
        override fun bind(message: Message) {
            binding.txtMyMessage.text = message.message
        }
    }

    inner class OtherMessageViewHolder (private val binding: ItemOtherMessageBinding) : ViewHolder(binding.root) {
        override fun bind(message: Message) {
            binding.txtOtherMessage.text = message.message
        }
    }
}