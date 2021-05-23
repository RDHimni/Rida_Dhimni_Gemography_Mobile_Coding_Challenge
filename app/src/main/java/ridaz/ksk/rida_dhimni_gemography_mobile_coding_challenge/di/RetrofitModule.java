package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.network.ApiService;

/**
 * Rida Dhimni
 * 11/01/2021
 **/

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    public static ApiService provideServiceApi() {

        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

}
