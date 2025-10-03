package br.com.safecorp.network

import br.com.safecorp.data.api.AssessmentApi
import br.com.safecorp.data.api.SelfCheckApi
import br.com.safecorp.data.api.SupportApi
import br.com.safecorp.models.TokenResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

object RetrofitClient {

    // Substitua pelo token JWT que você gerou no Postman
    private const val JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c3VhcmlvQW5vbmltbyI6InVzZXIxMjMiLCJpYXQiOjE3NTk1MjQzNTEsImV4cCI6MTc1OTUyNzk1MX0.xKBriuAwIuyYUfyvOuh1ROh1J_fmlrjPRlj4VNsaa6g"

    private const val BASE_URL = "http://10.0.2.2:3000/api/"

    // Interceptor para adicionar o token em todas as requisições
    private val authInterceptor = Interceptor { chain ->
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header("Authorization", "Bearer $JWT_TOKEN")
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // <-- cliente com token
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Serviços criados
    val assessmentApi: AssessmentApi by lazy { retrofit.create(AssessmentApi::class.java) }
    val selfCheckApi: SelfCheckApi by lazy { retrofit.create(SelfCheckApi::class.java) }
    val supportApi: SupportApi by lazy { retrofit.create(SupportApi::class.java) }

    val api: ApiService by lazy { retrofit.create(ApiService::class.java) }

    interface ApiService {
        @POST("auth")
        fun loginAnonimo(): Call<TokenResponse>
    }
}
