package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MyTopAppBar
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.poppins
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, dataStore: DataStore<Preferences>) {
    val activity = (LocalContext.current as? Activity)
    val KEY_REMEMBER_ME = booleanPreferencesKey("rememberMe")
    val scop = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar(
                onClick = {
                    activity?.finish()
                }, title = {
                    Text(
                        "Zeru",
                        fontFamily = ubuntu,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                },
                action = {
                    IconButton(onClick = {
                        scop.launch {
                            dataStore.edit { preferences ->
                                preferences[KEY_REMEMBER_ME] = false
                            }
                        }
                        navController.navigate(Screen.GoogleSignIn.route) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = "logout Icon",
                            modifier = Modifier.size(25.dp)
                        )

                    }
                })
        },
        bottomBar = {
            MainButton(
                onClick = { navController.navigate(Screen.ChatDetails.route) },
                eventText = "Start Chat with Zeru",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 30.dp)
            )
        }

        ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground)
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ai_intro_1),
                contentDescription = "intro images",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),

                )

            Spacer(modifier = Modifier.height(0.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    "Welcome, Shahnawaz!",
                    fontFamily = poppins,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryFontColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.hello),
                    contentDescription = "hand",
                    Modifier
                        .size(25.dp)
                        .rotate(320f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                "Let's Have Fun with Zeru!",
                fontFamily = ubuntu,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 40.sp,
                color = PrimaryFontColor,
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                "Start a conversation with Zeru right now!",
                fontFamily = poppins,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 40.sp,
                color = PrimaryFontColor,

            )

//            Spacer(modifier = Modifier.height(20.dp))
//            MainButton(
//                onClick = { /*TODO*/ },
//                eventText = "Start Chat with Zeru",
//                modifier = Modifier
//            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //it just for preview after ill comment it
    fun provideDataStore(context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { context.preferencesDataStoreFile("akk") }
        )
    }

    val context = LocalContext.current
    Home(navController = rememberNavController(), dataStore = provideDataStore(context = context))
}