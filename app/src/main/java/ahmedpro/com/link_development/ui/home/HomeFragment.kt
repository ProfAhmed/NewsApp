package ahmedpro.com.link_development.ui.home

import ahmedpro.com.link_development.*
import ahmedpro.com.link_development.models.NewsApiModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.merge

@ExperimentalCoroutinesApi
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        ).get(HomeViewModel::class.java)
        ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        getNewsApi()
        return root
    }

    private fun getNewsApi() {
        val map1 = HashMap<String, String>()
        map1["source"] = "associated-press"
        map1["apiKey"] = "355ba2159b7c467fb0b4ece42f10fc7e"
        val map2 = HashMap<String, String>()
        map2["source"] = "the-next-web"
        map2["apiKey"] = "355ba2159b7c467fb0b4ece42f10fc7e"
        val flow1 = homeViewModel.getNewsApi(map1)
        val flow2 = homeViewModel.getNewsApi(map2)
        val list = ArrayList<NewsApiModel.Article>()
        val adapter = NewsAdapter()
        CoroutineScope(Dispatchers.IO).launch {
            merge(flow1, flow2).collect {
                when (it) {
                    is Success<*> -> {
                        Log.i("NEWSDATA", (it.data as NewsApiModel).articles.size.toString())
                        withContext(Dispatchers.Main) {
                            list.addAll(it.data.articles)
                            adapter.setModels(list)
                            rv.adapter = adapter
                            adapter.setOnItemClickListener(object :
                                NewsAdapter.OnItemClickListener {
                                override fun onItemClick(article: NewsApiModel.Article?) {
                                    startActivity(
                                        Intent(
                                            requireActivity(),
                                            ArticleDetailsActivity::class.java
                                        ).putExtra("data", article)
                                    )
                                }
                            })
                        }
                    }
                    is Failed -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }
}