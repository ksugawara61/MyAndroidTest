package net.listadoko.mytodomvp.http

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import org.assertj.core.api.Assertions.assertThat

@RunWith(RobolectricTestRunner::class)
class GithubRemoteDataSourceTest {
    val mockWebService = MockWebServer()

    lateinit var githubRemoteDataSource: GithubRemoteDataSource

    @Before
    fun setUp() {
        mockWebService.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = request.path
                return when {
                    path == null -> MockResponse().setResponseCode(400)
                    path.matches(Regex("/users/[a-zA-Z0-9]+/repos")) -> {
                        MockResponse().setResponseCode(200).setBodyFromFileName("http/users_repos.json")
                    }
                    else -> MockResponse().setResponseCode(400)
                }
            }
        }
        mockWebService.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebService.url(""))
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GithubService::class.java)
        githubRemoteDataSource = GithubRemoteDataSource(githubService)
    }

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

    @Test
    fun listRepos_givenBlank_notComplete() {
        githubRemoteDataSource.listRepos("").test().await().assertNotComplete()
    }

    @Test
    fun listRepos_givenText_returnsNotEmpty() {
        val list = githubRemoteDataSource.listRepos("ksugawara61")
            .test()
            .await()
            .assertNoErrors()
            .assertComplete()
            .values()[0]
        assertThat(list).isNotEmpty
    }
}
