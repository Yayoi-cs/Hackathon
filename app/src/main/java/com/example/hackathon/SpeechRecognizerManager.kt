package com.example.soundinput

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView

class SpeechRecognizerManager(
    private val recognize_start_button: Button,
    private val recognize_text_view: TextView
) {
    private var speechRecognizer : SpeechRecognizer? = null
    var text_box : String = ""

    init {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(recognize_text_view.context)
        speechRecognizer?.setRecognitionListener(createRecognitionListenerStringStream { recognize_text_view.text = it })

        recognize_start_button.setOnClickListener {
            speechRecognizer?.startListening(
                Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH
                )
            )
        }
    }

    private fun createRecognitionListenerStringStream(onResult : (String)-> Unit) : RecognitionListener {
        return object : RecognitionListener {
            override fun onRmsChanged(rmsdB: Float) { /** 今回は特に利用しない */ }
            override fun onReadyForSpeech(params: Bundle) { onResult("onReadyForSpeech") }
            override fun onBufferReceived(buffer: ByteArray) { onResult("onBufferReceived") }
            override fun onPartialResults(partialResults: Bundle) { onResult("onPartialResults") }
            override fun onEvent(eventType: Int, params: Bundle) { onResult("onEvent") }
            override fun onBeginningOfSpeech() { onResult("onBeginningOfSpeech") }
            override fun onEndOfSpeech() {
                speechRecognizer?.stopListening()
                onResult("onEndOfSpeech")
            }
            override fun onError(error: Int) {
                speechRecognizer?.stopListening()
                onResult("onError")
            }
            override fun onResults(results: Bundle) {
                val stringArray = results.getStringArrayList(android.speech.SpeechRecognizer.RESULTS_RECOGNITION);
                if(stringArray!!.size > 0){
                    text_box = stringArray[0]
                }

                onResult("onResults " + stringArray.toString())
            }
        }
    }

    fun destroy() {
        speechRecognizer?.destroy()
    }

    public fun getRecognizedText() : String {
        return text_box
    }
}