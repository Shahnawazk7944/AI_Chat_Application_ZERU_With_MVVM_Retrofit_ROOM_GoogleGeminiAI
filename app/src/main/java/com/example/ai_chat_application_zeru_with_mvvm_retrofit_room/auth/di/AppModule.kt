package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.di

import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserLoginRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.data.repository.UserSignUpRepositoryImpl
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserLoginRepository
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.auth.domain.repository.UserSignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        return UserLoginRepositoryImpl(
            auth,
            //dataStore
        )
    }


    @Provides
    fun provideUserSignUpRepository(
        auth: FirebaseAuth,
        //dataStore: DataStore<UserSignUp>
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