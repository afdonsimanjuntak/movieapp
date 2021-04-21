package io.afdon.movie.di.module

import dagger.Module
import dagger.Provides
import io.afdon.movie.BuildConfig
import io.afdon.movie.service.TmdbService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideTmdbService(retrofit: Retrofit): TmdbService {
        return retrofit.create(TmdbService::class.java)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TmdbService.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        interceptors.forEach { clientBuilder.addInterceptor(it) }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }
}