package net.aaronlab.searchrepo

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.aaronlab.searchrepo.adapters.GithubRepositoryAdapter
import net.aaronlab.searchrepo.models.GithubRepositoryModel
import net.aaronlab.searchrepo.repositories.GithubRepository
import net.aaronlab.searchrepo.viewholders.GithubRepositoryItemDecoration
import net.aaronlab.searchrepo.viewmodels.main.MainViewModel
import net.aaronlab.searchrepo.viewmodels.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var mGithubRepositoryAdapter: GithubRepositoryAdapter

    // 뷰 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButton()
        initKeyboard()

        initViewModel()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // EditText 이외 터치 시 키보드 닫기
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

    // 키보드 숨기기
    private fun hideKeyboard() {
        currentFocus?.run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    // 버튼 리스너 초기화
    private fun initButton() {
        btn_search.setOnClickListener {
            search()
        }
    }

    // EditText 리스너 초기화
    private fun initKeyboard() {
        edit_text_search_query.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                search()

                return@setOnKeyListener true
            }
            false
        }
    }

    // 검색
    private fun search() {
        edit_text_search_query.run {
            viewModel.requestGithubRepositories(edit_text_search_query.text.toString())
            text.clear()
            clearFocus()
            hideKeyboard()
        }
    }

    // 뷰모델 초기화
    private fun initViewModel() {
        viewModelFactory = MainViewModelFactory(GithubRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.githubRepositories.observe(this) {

            updateRepositories(it)

        }
    }

    // Fetch + 뷰 업데이트
    private fun updateRepositories(repos: List<GithubRepositoryModel>) {

        if (::mGithubRepositoryAdapter.isInitialized) {
            mGithubRepositoryAdapter.update(repos)
        } else {
            mGithubRepositoryAdapter = GithubRepositoryAdapter(repos).apply {

                listener = object : GithubRepositoryAdapter.OnGithubRepositoryClickListener {

                    override fun onItemClick(position: Int) {
                        mGithubRepositoryAdapter.getItem(position).run {

                            htmlURL?.let { openGithub(it) }

                        }
                    }

                }

            }

            recycler_repo_cell.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mGithubRepositoryAdapter
                addItemDecoration(GithubRepositoryItemDecoration(6, 6))
            }

        }

    }

    // GitHub 열기
    private fun openGithub(url: String) {
        try {
            val uri = Uri.parse(url)
            Intent(Intent.ACTION_VIEW, uri).run {
                startActivity(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}