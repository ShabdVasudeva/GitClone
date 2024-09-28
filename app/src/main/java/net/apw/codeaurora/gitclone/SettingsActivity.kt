package net.apw.codeaurora.gitclone;

import android.os.Bundle
import kotlinx.coroutines.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.input.nestedscroll.*
import android.app.Activity
import android.net.Uri
import android.provider.Settings
import android.content.*
import androidx.appcompat.app.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.airbnb.lottie.compose.*
import androidx.compose.ui.text.input.*
import net.apw.codeaurora.gitclone.ui.theme.MyComposeApplicationTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Settings(LocalContext.current)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Settings(context: Context){
    val activity = LocalContext.current as? Activity
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scope = rememberCoroutineScope()
    val darkModeState by getDarkModeSetting(context).collectAsState(initial = false)
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Settings",maxLines = 1,overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = {activity?.finish()}){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                item{
                    Card(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        shape = RoundedCornerShape(16.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable{
                                val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://t.me/AndroidPortWorld"))
                                context.startActivity(intent)
                            }.padding(top = 9.dp,bottom = 16.dp,start = 16.dp,end = 16.dp)
                        ){
                            Column(modifier = Modifier.weight(1f)){
                                Text(
                                    text = "AndroidPortWorld",
                                    style = TextStyle(
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = "Tap here to join out official telegram channel @AndroidPortWorld.",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                )
                            }
                        }
                        Divider(color = Color.Gray, thickness = 1.dp)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable{
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://androidportworld.netlify.app/"))
                                context.startActivity(intent)
                            }.padding(top = 9.dp,bottom = 16.dp,start = 16.dp,end = 16.dp)
                        ){
                            Column(modifier = Modifier.weight(1f)){
                                Text(
                                    text = "Website",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                    )
                                )
                                Text(
                                    text = "Tap here to have a visit on our official website.",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                )
                            }
                        }
                        Divider(color = Color.Gray, thickness = 1.dp)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable{
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply{
                                    data = Uri.fromParts("package",context.packageName,null)
                                }
                                context.startActivity(intent)
                            }.padding(top = 9.dp,bottom = 16.dp,start = 16.dp,end = 16.dp)
                        ){
                            Column(modifier = Modifier.weight(1f)){
                                Text(
                                    text = "About",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                    )
                                )
                                Text(
                                    text = "Get Information about the current version of GitClone.",
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun toggleTheme(isDark: Boolean){
    if (isDark){
    } else {
    }
}