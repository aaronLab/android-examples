package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이전에 입력한 값을 읽어오기
        loadData()

        // result 버튼 클릭 리스너
        resultButton.setOnClickListener {
            // 텍스트 필드 한개라도 비어있다면
            if (TextUtils.isEmpty(weightEditText.text) ||
                    TextUtils.isEmpty(heightEditText.text)) {
                // toast 호출 후 리턴
                toast("값을 입력해주세요.")
                return@setOnClickListener
            }

            // 마지막 입력 내용 저장
            saveData(heightEditText.text.toString().toInt(),
            weightEditText.text.toString().toInt())

            // ResultActivity 로 전환
            startActivity<ResultActivity>(
                // 데이터 전달
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    private fun saveData(height: Int, weight: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    private fun loadData() {
        // Preference 객체 호출
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        // 없으면 default = 0
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        // 0이 아니면 setText
        if (height != 0 && weight != 0) {
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}