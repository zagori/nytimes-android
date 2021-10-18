package io.github.zagori.nytimes

import io.github.zagori.nytimes.models.State
import org.junit.Test

class ArticlesRepositoryTest : TestSetup(){

    @Test
    fun `verify search articles from remote and store to local is successful`(){
        onSearchArticles_fromApi_success()
        onClearAllSearchArticles_fromLocal_success()
        onInsertSearchArticles_toLocal_success()

        articlesRepository.loadAndSaveBySearch("query", 1).test().assertComplete()
    }

    @Test
    fun `verify load searched articles from local is successful`(){
        onLoadSearchArticles_fromLocal_success()

        articlesRepository.getLocalSearched().test().assertValue {
            it is State.Success
        }
    }
}