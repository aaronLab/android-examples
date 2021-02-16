package net.aaronlab.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_word_definition.*

class WordDefinitionActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)

        setupListeners()

        // 단어 뜻
        definition_text_view.text = intent.getStringExtra(KEY)

    }

    /*
    리스너 셋업
     */
    private fun setupListeners() {

        // 뒤로가기 버튼
        back_image_view.setOnClickListener {
            finish()
        }

    }

}