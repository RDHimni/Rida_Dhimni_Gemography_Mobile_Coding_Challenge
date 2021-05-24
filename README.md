# Rida_Dhimni_Gemography_Mobile_Coding_Challenge
That's my version app for Gemography mobile-coding-challenge ( Implementing a small app that will list the most starred Github repos that were created in the last 30 days. And fetching the sorted JSON data directly from the Github API )


## Features Achieved
* As a User I should be able to list the most starred Github repos that were created in the last 30 days. 
* As a User I should see the results as a list. One repository per row. 
* As a User I should be able to see for each repo/row the following details :
  * Repository name
  * Repository description 
  * Numbers of stars for the repo. 
  * Username and avatar of the owner. 
* As a User I should be able to keep scrolling and new results should appear (pagination).

## Technologie used
   * Andoid Java

## Architecture Pattern used
   * MvvM
      *  In Android, MVC refers to the default pattern where an Activity acts as a controller and XML files are views. MVVM treats both Activity classes and XML files as views, and ViewModel classes are where you write your business logic. It completely separates an app's UI from its logic.
  

## Framework used
### Retrofit
   Retrofit is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp. This library makes downloading JSON or XML data from a web API fairly straightforward.
   
    https://square.github.io/retrofit/
 
#### Implementation:
##### Retrofit with CallBack
  * Step1: dépendances:
   
                  implementation "com.squareup.retrofit2:retrofit:2.9.0"
                  implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
   
   * Step2: packages (pojo or ui ;data)
                     
                     add two package (pojo or ui) and (data).
                     
   * Step3: setUp (ApiInterface.java ; ClientRetrofit.java)
                    
        * ApiInterface.java it is ana interface java that we define our methode https like that: 
        
                          public interface ApiInterface {
                              @GET("posts")
                              public Call<List<ModelName>> getPost();
                             }
          
        * ClientRetrofit.java it is ana class java that we povide  an instance from ApiIterface.java 
         
                         public class ClientRetrofit {

                          private static final String BASE_URL = "";
                          private ApiInterface apiInterface;
                          private static ClientRetrofit INSTANCE;

                           public ClientRetrofit() {

                               Retrofit retrofit = new Retrofit.Builder()
                                       .baseUrl(BASE_URL)
                                       .addConverterFactory(GsonConverterFactory.create())
                                       .build();

                               apiInterface = retrofit.create(ApiInterface.class);

                           }

                             public static ClientRetrofit getInstance() {
                               if (INSTANCE == null) INSTANCE = new ClientRetrofit();
                               return INSTANCE;
                            }

                          }


### Rxjava
RxJava It's one of the most discussed libraries for enabling Reactive Programming in Android development. It's touted as the go-to framework for simplifying concurrency/asynchronous tasks inherent in mobile programming.
   
    https://github.com/ReactiveX/RxJava
   
##### Retrofit with Rxjava

  * Step1: dépendances:
   
          // Retrofit
          implementation 'com.squareup.retrofit2:retrofit:2.9.0'
          implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
          implementation "com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0"

          //RxJava
          implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
          implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
   
   * Step2: packages (pojo or ui ; network)
                     
                     add two package (pojo or ui) and (data).
                     
   * Step3: setUp (ApiInterface.java ; ClientRetrofit.java)
                    
        * ApiInterface.java becoms : 
        
                          public interface ApiInterface {
                              @GET("posts")
                              public Obsevale<List<ModelName>> getPost();
                             }
          
        * ClientRetrofit.java with Rxjava becoms.
         
                         public class ClientRetrofit {

                          private static final String BASE_URL = "";
                          private ApiInterface apiInterface;
                          private static ClientRetrofit INSTANCE;

                           public ClientRetrofit() {

                               Retrofit retrofit = new Retrofit.Builder()
                                       .baseUrl(BASE_URL)
                                       .addConverterFactory(GsonConverterFactory.create())
                                       .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                                       .build();

                               apiInterface = retrofit.create(ApiInterface.class);

                           }

                             public static ClientRetrofit getInstance() {
                               if (INSTANCE == null) INSTANCE = new ClientRetrofit();
                               return INSTANCE;
                            }

                          }
                          
  for more...
        
    https://www.baeldung.com/retrofit-rxjava


### DaggerHilt
Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides.

  * Step1: dépendances:
   
        //Dagger hilt
        implementation "com.google.dagger:hilt-android:2.31-alpha"
        annotationProcessor 'com.google.dagger:hilt-android-compiler:2.31.2-alpha'
        implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03'
        annotationProcessor 'androidx.hilt:hilt-compiler:1.0.0'
        
   * Step2: add plugin in buil.gradle:
        
         classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'

   
   * Step3: packages (di)
                     
                     add one package (di).
                     
   * Step4: setUp (BaseApplication.java)
                     
          @HiltAndroidApp
          public class BaseApplication extends Application {}
   

   * Step5: update AndroidManifest.xml by adding
   
           android:name=".BaseApplication"
 
   for more... 
   
     https://developer.android.com/training/dependency-injection/hilt-android
     
 ### LiveData
 LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
 
    https://developer.android.com/topic/libraries/architecture/livedata
   
  ### Navigation Components
 Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app. Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
 
    https://developer.android.com/guide/navigation/navigation-getting-started
 
### Glide
Glide is an Image Loader Library for Android developed by bumptech and is a library that is recommended by Google. It has been used in many Google open source projects including Google I/O 2014 official application. It provides animated GIF support and handles image loading/caching.

    https://github.com/bumptech/glide
    
### SDP
An android lib that provides a new size unit - sdp (scalable dp). This size unit scales with the screen size. It can help Android developers with supporting multiple screens. for text views please refer to ssp which is based on the sp size unit for texts.

    https://github.com/intuit/sdp
    
### lottie
Lottie is a mobile library for Android and iOS that parses Adobe After Effects animations exported as json with Bodymovin and renders them natively on mobile!

    https://github.com/airbnb/lottie-android
    
    
 ## Result
 
![Screenshot_20210524-010123](https://user-images.githubusercontent.com/50507265/119281502-4235a580-bc36-11eb-8172-e2bfc3ae0814.png)
![Screenshot_20210524-010202](https://user-images.githubusercontent.com/50507265/119281511-4661c300-bc36-11eb-8f25-ec8dad0213de.png)
![Screenshot_20210524-010212](https://user-images.githubusercontent.com/50507265/119281516-495cb380-bc36-11eb-8a0b-24153dc2eb53.png)




