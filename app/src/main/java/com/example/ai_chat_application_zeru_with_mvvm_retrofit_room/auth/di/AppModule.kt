package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserLoginRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserSignUpRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val PREFERENCES_NAME = "my_app_prefs"
    private val KEY_SETTING_1 = booleanPreferencesKey("isFirstTime")
    private val KEY_SETTING_2 = booleanPreferencesKey("rememberMe")

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): File {
        return context.dataStoreFile(
            fileName = PREFERENCES_NAME
        )
    }
    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return getInstance(File) get(dataStore)
    }
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    @Provides
    fun provideUserLoginRepository(
        auth: FirebaseAuth,
        dataStore: DataStore<Preferences>
    ): UserLoginRepository {
        return UserLoginRepositoryImpl(
            auth,
            
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
            //dataStore
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