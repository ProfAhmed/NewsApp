package ahmedpro.com.link_development

import ahmedpro.com.link_development.models.NewsApiModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("v1/articles")
    suspend fun getNewsApi(@QueryMap param: HashMap<String, String>): NewsApiModel
}