package net.aaronlab.dictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"

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

                    extractDefinition(response)

                },

                Response.ErrorListener { error ->

                    // Network Error
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()

                }
        )

        // Request
        queue.add(stringRequest)
    }

    /*
    shortdef 추출 후 결과 뷰로 이동
     */
    private fun extractDefinition(response: String) {

        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val shortDef = firstIndex.getJSONArray("shortdef")
        val firstDef = shortDef.get(0)

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(KEY, firstDef.toString())
        startActivity(intent)

    }

}