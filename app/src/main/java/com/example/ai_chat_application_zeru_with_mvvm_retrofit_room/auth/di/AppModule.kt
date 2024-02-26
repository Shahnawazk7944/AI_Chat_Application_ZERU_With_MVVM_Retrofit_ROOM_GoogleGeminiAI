package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserLoginRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserSignUpRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val PREFERENCES_NAME = "my_app_prefs"
    // private val KEY_SETTING_1 = booleanPreferencesKey("isFirstTime")
    // private val KEY_SETTING_2 = booleanPreferencesKey("rememberMe")
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
    @Provides
    @Singleton
    fun provideDataStore(context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_NAME) }
        )
    }

    //    @Provides
//    @Singleton
//    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
//        return getInstance(dataStore)
//    }
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideUserLoginRepository(
        auth: FirebaseAuth,
        dataStore: DataStore<Preferences>
    ): UserLoginRepository {
        return UserLoginRepositoryImpl(
            auth,
            dataStore
            //dataStore
        )
    }

    @Provides
    fun provideUserSignUpRepository(
        auth: FirebaseAuth,
        dataStore: DataStore<Preferences>
    ): UserSignUpRepository {
        return UserSignUpRepositoryImpl(
            auth,
            dataStore
        )
    }


//    @Provides
//    @ApplicationContext
//    fun provideContext(application: Application): Context = application.applicationContext

}
//@Module
//@InstallIn(ViewModelComponent::class)
//object NavControllerModule {
//    @Provides
//    fun provideNavController(
//        context: Context
//    ): NavHostController = NavHostController(context)
//}

