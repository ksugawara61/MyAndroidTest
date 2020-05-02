package net.listadoko.mytodomvp.room

class RepositoryLocalDataSource(val db: AppDatabase) {
    fun insertAll(vararg repositories: Repository) {
        db.repositoryDao().insertAll(*repositories)
    }

    fun findByOwner(owner: String): List<Repository> {
        return db.repositoryDao().findByOwner(owner)
    }
}
