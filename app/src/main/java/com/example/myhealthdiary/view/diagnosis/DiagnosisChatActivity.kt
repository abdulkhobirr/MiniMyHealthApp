package com.example.myhealthdiary.view.diagnosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhealthdiary.data.diagnosischat.model.Message
import com.example.myhealthdiary.data.diagnosischat.model.PostChatBody
import com.example.myhealthdiary.databinding.ActivityDiagnosisChatBinding
import com.example.myhealthdiary.utils.hideKeyboard
import com.example.myhealthdiary.viewmodel.DiagnosisChatViewModel
import com.example.myhealthdiary.utils.viewmodel.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiagnosisChatActivity : AppCompatActivity(), OnClickPayload {
    private lateinit var binding: ActivityDiagnosisChatBinding
    private lateinit var chatAdapter: DiagnosisChatAdapter
    private lateinit var payloadAdapter: PayloadMessageAdapter
    private val chatViewModel: DiagnosisChatViewModel by viewModel()
    private var payloadMessage = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiagnosisChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRV()

        initActions()

        initObservables()
    }

    private fun initActions(){
        binding.btnSendMessage.setOnClickListener {
            if (binding.txtMessage.text.isNotEmpty()){
                chatViewModel.postChat(PostChatBody("kandidatKhobir", binding.txtMessage.text.toString()))
                chatAdapter.addMessage(Message("user", binding.txtMessage.text.toString()))
                scrollToBottom()
                binding.txtMessage.text.clear()
            }
        }
    }

    private fun setupRV(){
        chatAdapter = DiagnosisChatAdapter()

        binding.rvChat.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            setHasFixedSize(true)
            adapter = chatAdapter
        }

        chatAdapter.addMessage(Message("bot", "Halo, selamat datang pada fitur diagnosis pintar"))

        payloadAdapter = PayloadMessageAdapter(listener = this)

        binding.rvPayload.apply {
            layoutManager =
                    GridLayoutManager(
                            context,
                            3
                    )
            setHasFixedSize(true)
            adapter = payloadAdapter
        }
    }

    private fun initObservables(){
        chatViewModel.chat.observe(this, Observer {
            when (it) {
                is Result.Empty -> {}
                is Result.Failure -> {}
                is Result.Success -> {
                    if (it.data.jawaban != "-" && it.data.jawaban != null){
                        this.hideKeyboard()
                        payloadMessage.clear()
                        payloadMessage.addAll(it.data.jawaban.split(","))
                        payloadAdapter.setPayloadData(payloadMessage)
                        binding.layoutMessageArea.visibility = View.GONE
                        binding.rvPayload.visibility = View.VISIBLE
                    }
                    if (it.data.pertanyaan != "-" && it.data.pertanyaan != null){
                        chatAdapter.addMessage(Message("bot", it.data.pertanyaan))
                        scrollToBottom()
                    }
                    if (it.data.type == "freetext"){
                        binding.layoutMessageArea.visibility = View.VISIBLE
                    } else {
                        binding.rvPayload.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun scrollToBottom(){
        binding.rvChat.smoothScrollToPosition(chatAdapter.itemCount)
    }

    override fun postMessage(ans: String) {
        chatViewModel.postChat(PostChatBody("kandidatKhobir", ans))
        chatAdapter.addMessage(Message("user", ans))
        scrollToBottom()
        binding.rvPayload.visibility = View.GONE
    }
}