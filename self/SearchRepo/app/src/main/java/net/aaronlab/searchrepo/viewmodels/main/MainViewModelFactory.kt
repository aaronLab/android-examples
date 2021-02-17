package net.aaronlab.searchrepo.viewmodels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.aaronlab.searchrepo.repositories.GithubRepository

/*
생성자에 파라미터가 있는 ViewModel 초기화를 위한 Factory
 */
class MainViewModelFactory(private val githubRepository: GithubRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GithubRepository::class.java).newInstance(githubRepository)
    }

}