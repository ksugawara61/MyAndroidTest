package net.listadoko.mytodomvp.http

import io.reactivex.Single

class GithubRemoteDataSource(val githubService: GithubService) {
    fun listRepos(user: String): Single<List<Repo>> {
        return githubService.listRepos(user)
    }
}
