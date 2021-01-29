package com.example.myhealthdiary.view.diagnosis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthdiary.databinding.ItemPayloadMessageBinding

class PayloadMessageAdapter(
        private var data: MutableList<String> = mutableListOf(),
        private val listener: OnClickPayload
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setPayloadData(payloadData: List<String>) {
        data = payloadData as MutableList<String>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPayloadMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayloadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val answer: String = data[position]
        val payloadViewHolder = holder as PayloadViewHolder
        payloadViewHolder.bindPayloadMessage(answer)
    }

    open class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class PayloadViewHolder(private val binding: ItemPayloadMessageBinding): ViewHolder(binding.root){
        fun bindPayloadMessage(ans: String){
            with(itemView){
                binding.btnPayloadMessage.text = ans

                binding.btnPayloadMessage.setOnClickListener {
                    listener.postMessage(ans)
                    Toast.makeText(context, ans, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

interface OnClickPayload{
    fun postMessage(ans: String)
}