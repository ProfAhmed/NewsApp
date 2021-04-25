package ahmedpro.com.link_development.ui.home

import ahmedpro.com.link_development.R
import ahmedpro.com.link_development.models.NewsApiModel
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item_list.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private var models: List<NewsApiModel.Article>? = null
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(article: NewsApiModel.Article?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_list, parent, false)
        return MyViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = models?.get(position)
        holder.itemView.tvTitle.text = model?.title
        holder.itemView.tvAuthor.text = "By ${model?.author}"
        model?.publishedAt.let { holder.itemView.tvDate.text = it }
        Picasso.get().load(model?.urlToImage).into(holder.itemView.imageView)
    }

    override fun getItemCount(): Int {
        return if (models?.size != null) models?.size!! else 0
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    fun setModels(clientTeamDetailsModel: List<NewsApiModel.Article>?) {
        this.models = clientTeamDetailsModel
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(models?.get(position))
                }
            }
        }
    }
}