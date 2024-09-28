package net.apw.codeaurora.gitclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.*
import android.view.accessibility.AccessibilityManager
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.input.nestedscroll.*
import android.app.Activity
import android.content.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import net.apw.codeaurora.gitclone.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Main(LocalContext.current)
                }
            }
        }
    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Main(context : Context){
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){ }
    val activity = (LocalContext.current as? Activity)
    var expanded by remember {mutableStateOf(false)}
    var showWelcomeScreen by remember {mutableStateOf(true)}
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("GitClone",maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = { activity?.finish() }){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    HomeMenu(intentActivity = {
                        val intent = Intent(context,SettingsActivity::class.java)
                        launcher.launch(intent)
                    })
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().pointerInput(Unit){
                detectVerticalDragGestures{ _, dragAmount->
                    if(dragAmount < 0){
                        showWelcomeScreen = false
                    }else if(dragAmount > 0){
                        showWelcomeScreen = true
                    }
                }
            }){
                AnimatedVisibility(
                    visible = showWelcomeScreen,
                    enter = slideInVertically(
                        initialOffsetY = {-it},
                        animationSpec = tween(durationMillis = 500)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = {-it},
                        animationSpec = tween(durationMillis = 500)
                    )
                ){
                    WelcomeScreen()
                }
                AnimatedVisibility(
                    visible = !showWelcomeScreen,
                    enter = slideInVertically(
                        initialOffsetY = {it},
                        animationSpec = tween(durationMillis = 500)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = {it},
                        animationSpec = tween(durationMillis = 500)
                    )
                ){
                    GitCloneScreen{ gitUrl->
                        cloneRepositoryToDownloads(gitUrl)
                    }
                }
            }
        }
    )
}