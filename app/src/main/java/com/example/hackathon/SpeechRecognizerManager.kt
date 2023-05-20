package com.example.soundinput

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Boolean.FALSE

class SpeechRecognizerManager(
    private val recognize_start_button: FloatingActionButton,
    private val recognize_text_view: TextView
) {
    private var speechRecognizer : SpeechRecognizer? = null
    var text_box : String = ""
    private var isButtonOn = FALSE


    init {
//        val backgroundDrawable: Drawable? = recognize_start_button.background
//        var buttonBackground: Int = 0
//        if (backgroundDrawable is ColorDrawable) {
//            buttonBackground = backgroundDrawable.color
//        }

        recognize_start_button.setOnClickListener {
            isButtonOn = !isButtonOn
            if(isButtonOn){
                startSpeechListening()
//                recognize_start_button.drawable.setTint(buttonBackground)
//                recognize_start_button.background.setTint(Color.parseColor("#dddddd"))
            }else{
                speechRecognizer?.stopListening()
                speechRecognizer?.destroy()
//                recognize_start_button.drawable.setTint(buttonBackground)
//                recognize_start_button.background.setTint(buttonBackground)
            }
            Toast.makeText(recognize_start_button.context, isButtonOn.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun createRecognitionListenerStringStream(onResult : (String)-> Unit) : RecognitionListener {
        return object : RecognitionListener {
            override fun onRmsChanged(rmsdB: Float) {  }
            override fun onReadyForSpeech(params: Bundle) { /*onResult("onReadyForSpeech")*/ }
            override fun onBufferReceived(buffer: ByteArray) { /*onResult("onBufferReceived")*/ }
            override fun onPartialResults(partialResults: Bundle) { /*onResult("onPartialResults")*/ }
            override fun onEvent(eventType: Int, params: Bundle) { /*onResult("onEvent")*/ }
            override fun onBeginningOfSpeech() { /*onResult("onBeginningOfSpeech")*/ }
            override fun onEndOfSpeech() {
                speechRecognizer?.stopListening()
                /*onResult("onEndOfSpeech")*/
                startSpeechListening()
            }
            override fun onError(error: Int) {
                speechRecognizer?.stopListening()
                /*onResult("onError")*/
//                startSpeechListening()
            }
            override fun onResults(results: Bundle) {
                val stringArray = results.getStringArrayList(android.speech.SpeechRecognizer.RESULTS_RECOGNITION);
                if(stringArray!!.size > 0){
                    text_box = stringArray[0]
                }

                onResult(text_box)
                startSpeechListening()
            }
        }
    }

    fun startSpeechListening(){
        speechRecognizer?.destroy()
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(recognize_start_button.context)
        speechRecognizer?.setRecognitionListener(createRecognitionListenerStringStream {
            val currentText = recognize_text_view.text.toString() + it
            recognize_text_view.text = currentText
        })
        speechRecognizer?.startListening(
            Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            )
        )
    }
}