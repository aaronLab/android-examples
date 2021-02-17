package net.aaronlab.searchrepo.itemholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repo_cell.view.*
import net.aaronlab.searchrepo.GithubRepositoryAdapter
import net.aaronlab.searchrepo.models.GithubRepositoryModel

/*
RecyclerView 에서 사용할
ViewHolder
 */
class GithubRepositoryItemHolder(
    view: View,
    listener: GithubRepositoryAdapter.OnGithubRepositoryClickListener?
) : RecyclerView.ViewHolder(view) {

    private val avatarView: ImageView = view.img_repo_avatar
    private val fullNameView: TextView = view.text_full_name
    private val descriptionView: TextView = view.text_description
    private val starCountView: TextView = view.text_star_count

    init {
        view.setOnClickListener {
            listener?.onItemClick(adapterPosition)
        }
    }

    fun bind(model: GithubRepositoryModel) {
        model.run {
            Glide.with(itemView)
                .load(owner?.avatarURL)
                .into(avatarView)
            fullNameView.text = fullName
            descriptionView.text = description
            starCountView.text = "Stars: $starGazersCount"
        }
    }

}