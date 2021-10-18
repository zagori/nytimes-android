package io.github.zagori.nytimes

import org.junit.Test
import org.mockito.exceptions.base.MockitoException

class EndpointsTest: TestSetup() {

    @Test
    fun `load searched articles returns success`(){
        onSearchArticles_fromApi_success()

        endpoints.searchArticles("query", 1).test().assertValue {
            it.status ==  "OK" && it.response != null
        }
    }

    @Test
    fun `load searched articles api returns server error`(){
        onSearchArticles_fromApi_serverError()

        endpoints.searchArticles("query", 1).test().assertValue {
            it.status != "OK"
        }
    }

    @Test(expected = MockitoException::class)
    fun `load images api throw exception`(){
        onSearchArticles_fromApi_exception()

        endpoints.searchArticles("query", 1).test().assertError {
            it.message == "Something went wrong"
        }
    }
}