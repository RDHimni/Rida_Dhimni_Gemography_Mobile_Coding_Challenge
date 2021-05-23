package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.network;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Respanse;


/**
 * Rida Dhimni
 * 22/03/2021
 **/

public interface ApiService {

    @GET("repositories")
    Observable<Respanse> getReps(@Query("q") String date,@Query("sort") String  sort,@Query("order") String  order,@Query("page") int pageNumber,@Query("per_page") int per_page);


}
