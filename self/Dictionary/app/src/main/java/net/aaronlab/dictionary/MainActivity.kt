package net.aaronlab.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        find_button.setOnClickListener {
            getDefinition()
        }
    }

    /*
    사전 정보 호출
     */
    private fun getDefinition() {

        val queue = Volley.newRequestQueue(this)

        val apiKey = API.KEY
        val word = word_edit_text.text
        val url = "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    // Success
                    Log.d("!!!!!", response)
                },
                Response.ErrorListener { error ->
                    // Error
                    Log.d("!!!!!", error.toString())
                }
        )

        // Request
        queue.add(stringRequest)

    }
}