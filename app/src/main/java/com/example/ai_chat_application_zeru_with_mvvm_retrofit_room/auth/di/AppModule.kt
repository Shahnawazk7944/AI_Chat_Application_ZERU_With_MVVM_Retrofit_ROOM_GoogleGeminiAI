package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserLoginRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserSignUpRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.UserLogin
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.model.UserSignUp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.NavigationGraph
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    @Provides
    fun provideUserLoginRepository(
        auth: FirebaseAuth,
        //dataStore: DataStore<UserLogin>
    ): UserLoginRepository {
        return UserLoginRepositoryImpl(auth,
            //dataStore
        )
    }


    @Provides
    fun provideUserSignUpRepository(
        auth: FirebaseAuth,
        //dataStore: DataStore<UserSignUp>
    ): UserSignUpRepository {
        return UserSignUpRepositoryImpl(auth,
            //dataStore
        )
    }

    @Provides
    @ApplicationContext
    fun provideContext(application: Application): Context = application.applicationContext

}
@Module
@InstallIn(ViewModelComponent::class)
object NavControllerModule {
    @Provides
    fun provideNavController(
        context: Context
    ): NavHostController = NavHostController(context)
}