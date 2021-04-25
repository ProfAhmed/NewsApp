package ahmedpro.com.link_development

import ahmedpro.com.link_development.models.NewsApiModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        val model = intent.getSerializableExtra("data") as NewsApiModel.Article
        Picasso.get().load(model.urlToImage).into(imageView)
        tvTitle.text = model.title
        tvAuthor.text = "By ${model.author}"
        tvDescription.text = model.description
        tvDate.text = model.publishedAt
    }
}