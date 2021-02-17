package net.aaronlab.searchrepo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.aaronlab.searchrepo.models.GithubRepositoryModel
import net.aaronlab.searchrepo.repositories.GithubRepository

class MainViewModel(private val githubRepository: GithubRepository): ViewModel() {

    private val _githubRepositories = MutableLiveData<List<GithubRepositoryModel>>()
    val githubRepositories = _githubRepositories

    fun requestGithubRepositories(query: String) {
        CoroutineScope(Dispatchers.IO).launch {

            githubRepository.getRepositories(query)?.let { response ->

                if (response.isSuccessful) {

                    response.body()?.let {

                        it.items?.let { items ->

                            _githubRepositories.postValue(items)
                        }

                    }
                }

            }

        }
    }

}