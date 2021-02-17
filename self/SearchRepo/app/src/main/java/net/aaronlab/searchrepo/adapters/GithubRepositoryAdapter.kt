package net.aaronlab.searchrepo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.aaronlab.searchrepo.R
import net.aaronlab.searchrepo.viewholders.GithubRepositoryItemHolder
import net.aaronlab.searchrepo.models.GithubRepositoryModel

/*
RecyclerView 를 위한 Adapter
DiffUtil 은 N^2이기 때문에
백그라운드 작업 후 메인 스레드 반영을 추천
 */
class GithubRepositoryAdapter(private var repositories: List<GithubRepositoryModel>) :
    RecyclerView.Adapter<GithubRepositoryItemHolder>() {

    interface OnGithubRepositoryClickListener {
        fun onItemClick(position: Int)
    }

    var listener: OnGithubRepositoryClickListener? = null

    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_cell, parent, false)
        return GithubRepositoryItemHolder(view, listener)
    }

    override fun onBindViewHolder(holder: GithubRepositoryItemHolder, position: Int) {
        holder.bind(repositories[position])
    }

    fun update(updated: List<GithubRepositoryModel>) {

        CoroutineScope(Dispatchers.Main).launch {

            val diffResult = async(Dispatchers.IO) {
                getDiffResult(updated)
            }

            repositories = updated
            diffResult.await().dispatchUpdatesTo(this@GithubRepositoryAdapter)

        }

    }

    private fun getDiffResult(updated: List<GithubRepositoryModel>): DiffUtil.DiffResult {
        val diffCallback =
            GithubRepositoryDiffCallback(
                repositories,
                updated
            )
        return DiffUtil.calculateDiff(diffCallback)
    }

    fun getItem(position: Int) = repositories[position]

}

/*
Adapter Update 를 위한
Diff Callback
 */
class GithubRepositoryDiffCallback(
    private val oldList: List<GithubRepositoryModel>,
    private val newList: List<GithubRepositoryModel>
) : DiffUtil.Callback() {

    override fun getNewListSize(): Int = newList.size

    override fun getOldListSize(): Int = oldList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

}