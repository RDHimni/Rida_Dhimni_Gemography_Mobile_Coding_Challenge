package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.ui.trending;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Respanse;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.repo.Repository;

@HiltViewModel
public class TrendingViewModel extends ViewModel {
    private static final String TAG = "TrendingViewModel";

    private Repository repository;
    private MutableLiveData<Respanse> result = new MutableLiveData<>();


    @Inject
    public TrendingViewModel(Repository repository) {
        this.repository = repository;
    }



    public void getReps(String date, String sort, String order, int pageNumber,int per_page){

        repository.getReps(date,sort,order,pageNumber,per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        res -> result.setValue(res),
                        err -> Log.d(TAG, "InsertUserInDb: " + err)
                );
    }


    public MutableLiveData<Respanse> getResult() {
        return result;
    }
}