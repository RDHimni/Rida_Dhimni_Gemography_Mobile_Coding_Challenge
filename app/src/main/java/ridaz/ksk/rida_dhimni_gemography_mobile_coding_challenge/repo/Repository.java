package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.repo;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Respanse;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.network.ApiService;

/**
 * Rida Dhimni
 * 22/05/2021
 **/

public class Repository {

    private ApiService serviceApi;


    @Inject
    public Repository(ApiService serviceApi) {
        this.serviceApi = serviceApi;
    }

    //get Repos from Api
    public Observable<Respanse> getReps(String date, String sort, String order, int pageNumber, int per_page) {

        return serviceApi.getReps(date, sort, order, pageNumber, per_page);

    }
}