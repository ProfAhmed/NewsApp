package ahmedpro.com.link_development

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.util.*

// data repository is responsible to fetch data from remote or local cash
class DataRepository private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        @Synchronized
        fun getInstance(apiService: ApiService): DataRepository =
            instance ?: DataRepository(apiService).also { instance = it }
    }

    fun getNewsApi(data: HashMap<String, String>) = flow {
        emit(Loading)
        try {
            emit(Success(data = apiService.getNewsApi(data)))
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    emit(
                        Failed(
                            //parse error response
                            message = "Unexpected error"
                        )
                    )
                }
                else -> {
                    emit(
                        Failed(
                            message = "Connection error"
                        )
                    )
                }
            }
        }
    }
}