package com.example.testapp.network

import com.example.testapp.Helpers.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    @Named("httpClient")
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val apiKeyInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("appid", Constants.API_KEY)
                .build()
            val requestBuilder = chain.request().newBuilder().url(url)
            chain.proceed(requestBuilder.build())
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(apiKeyInterceptor).build()
    }

    @Provides
    @Singleton
    @Named("retrofit")
    fun provideRetrofit(@Named("httpClient") httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    @Singleton
    fun provideWeatherApi(@Named("retrofit") retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}