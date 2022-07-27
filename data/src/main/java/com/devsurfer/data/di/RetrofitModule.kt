package com.devsurfer.data.di

import com.devsurfer.data.BuildConfig
import com.devsurfer.data.manager.PreferenceManager
import com.devsurfer.data.utils.GithubApiInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
    }

    private val gson = GsonBuilder().setLenient().create()

    private const val GITHUB_AUTH_URL = "https://github.com/"
    private const val GITHUB_API_URL = "https://api.github.com/"

    /**
     * Retrofit 객체를 2개 사용 할 예정
     * Auth = github.com
     * Api = api.github,com 으로
     * BaseUrl 도 다르고 헤더, 인터셉터가 다르기 때문
     *
     * 따라서 같은 타입이지만 서로 다른 Retrofit을 구분하기 위해 Annotation 추가
     */

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Auth

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Api

    @Singleton
    @Provides
    @Auth
    fun provideOkhttpForAuth(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor( Interceptor{ chain ->
                chain.proceed(chain.request()
                    .newBuilder()
                    .addHeader("Accept","application/json")
                    .build())
            }).build()

    @Singleton
    @Provides
    @Auth
    fun provideRetrofitForAuth(@Auth okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(GITHUB_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    @Singleton
    @Provides
    fun provideGithubApiInterceptor(preferenceManager: PreferenceManager): GithubApiInterceptor =
        GithubApiInterceptor(preferenceManager)

    @Singleton
    @Provides
    @Api
    fun provideOkhttpForAPi(githubApiInterceptor: GithubApiInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(githubApiInterceptor)
            .build()

    @Singleton
    @Provides
    @Api
    fun provideRetrofitForAPi(@Api okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(GITHUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    
}