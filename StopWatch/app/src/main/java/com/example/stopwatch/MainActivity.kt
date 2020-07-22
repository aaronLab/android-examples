package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0                    // 시간 변수 초기화
    private var isRunning = false           // 작동 여부 초기화
    private var timerTask: Timer? = null    // 타이머 초기화 -> null 허용
    private var lap = 1                     // 타임랩 초기화

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 시작 / 일시정지 버튼 클릭 리스너
        fab.setOnClickListener {
            // 작동 여부 반전
            isRunning = !isRunning

            // 작동 여부 검사
            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        // 랩타임 버튼 클릭 리스너
        lapButton.setOnClickListener {
            recordLapTime()
        }

        // 리셋 버튼 클릭 리스너
        resetFab.setOnClickListener {
            reset()
        }
    }

    private fun pause() {
        // 일시 정지 시 아이콘 시작으로 변경
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)

        // 타이머 중단
        timerTask?.cancel()
    }

    private fun start() {
        // 시작 시 아이콘 일시 정지로 변경
        fab.setImageResource(R.drawable.ic_baseline_pause_24)

        // 타이머 갱신 간격 0.01초
        timerTask = timer(period = 10){
            time++

            val sec = time / 60         // 초 포매팅
            val milli = time % 100      // 밀리초 포매팅

            runOnUiThread {     // UI 조작 및 갱신
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun recordLapTime() {
        val lapTime = this.time     // 시간 변수 저장
        val textView = TextView(this)       // TextView 생성
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        // 랩 레이아웃의 맨 위에 랩타임 추가
        lapLayout.addView(textView, 0)
        lap++
    }

    private fun reset() {
        // 타이머 중지
        timerTask?.cancel()

        // 각 변수 및 아이콘 초기화
        fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        time = 0
        isRunning = false
        secTextView.text = "0"
        milliTextView.text = "00"
        lap = 1

        // 랩 레이아웃 내의 모든 객체 삭제
        lapLayout.removeAllViews()
    }

}