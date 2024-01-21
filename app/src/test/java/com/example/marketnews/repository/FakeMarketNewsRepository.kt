import com.example.marketnews.assets.Constants
import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.data.repository.MarketNewsRepositoryInterface
import com.example.marketnews.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMarketNewsRepository : MarketNewsRepositoryInterface {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getNews(): Flow<DataState<ApiModel>> = flow{
        if(shouldReturnNetworkError) {
            emit(DataState.Error(Constants.netWorkError))
        }else {
            emit(DataState.Success(Constants.apiModel))
        }

    }
}
