package com.kents.snuh.di

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import com.kents.datatest.MockOpenLibraryApi
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
class DataTestModule : DataModule() {

    override val baseUrl = "localhost"

    override fun internalHttpClientEngine(): HttpClientEngineFactory<*> =
        object : HttpClientEngineFactory<HttpClientEngineConfig> {
            override fun create(block: HttpClientEngineConfig.() -> Unit) = MockOpenLibraryApi.engine
        }
}
