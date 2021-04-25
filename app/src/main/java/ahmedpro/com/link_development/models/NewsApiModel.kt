package ahmedpro.com.link_development.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsApiModel(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("sortBy")
    val sortBy: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("status")
    val status: String
) {
    data class Article(
        @SerializedName("author")
        val author: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("urlToImage")
        val urlToImage: String
    ) : Serializable
}