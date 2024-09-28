package net.apw.codeaurora.gitclone;

import android.os.Bundle
import kotlinx.coroutines.delay
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.compose.foundation.*
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
import android.content.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.airbnb.lottie.compose.*
import androidx.compose.ui.text.input.*
import net.apw.codeaurora.gitclone.ui.theme.MyComposeApplicationTheme

@Composable
fun HomeMenu(intentActivity : () -> Unit){
    var expanded by remember {mutableStateOf(false)}
    val width by animateDpAsState(targetValue = if(expanded) 200.dp else 0.dp, animationSpec = tween(durationMillis = 300, easing = LinearEasing))
    val height by animateDpAsState(targetValue = if(expanded) 63.dp else 0.dp, animationSpec = tween(durationMillis = 300, easing = LinearEasing))
    Box(){
        IconButton(onClick = {expanded = true}){
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Settings"
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false},modifier = Modifier.height(height).width(width).align(Alignment.TopEnd)){
            DropdownMenuItem(
                text = {Text("Settings")},
                onClick = { intentActivity.invoke() },
                leadingIcon = { Icon(imageVector = Icons.Filled.Settings,contentDescription = null) }
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WelcomeScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally){
            displayAnimation(
                modifier = Modifier.size(290.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            AnimatedText(text = "Swipe Up")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GitCloneScreen(onCloneClicked: (String)-> Unit){
    var gitUrl by remember {mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(top = 75.dp, end = 16.dp, start = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Top
    ){
        TextField(
            value = gitUrl,
            onValueChange = {gitUrl = it},
            label = {Text("Enter the Repo Link")},
            modifier = Modifier.fillMaxWidth().height(50.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onCloneClicked(gitUrl) },modifier = Modifier.fillMaxWidth()){
            Text(text = "Clone Repo",maxLines = 1)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Make Sure",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "• Do not turn of the internet during the process of cloning.",
            style = TextStyle( fontWeight = FontWeight.Bold )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Make sure to give all necessary permissions for cloning.",
            style = TextStyle( fontWeight = FontWeight.Bold )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Don't remove the application from recents during cloning process.",
            style = TextStyle( fontWeight = FontWeight.Bold )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• If possible join the developer's telegram channel @AndroidPortWorld 😜.",
            style = TextStyle( fontWeight = FontWeight.Bold )
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun displayAnimation(modifier : Modifier){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome))
    val progress by animateLottieCompositionAsState(composition)
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp).padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = modifier
        )
    }
}

@Composable
fun AnimatedText(text: String) {
    var offsetY by remember { mutableStateOf(0f) }
    val animatableY = remember { Animatable(0f) }
    val density = LocalDensity.current
    LaunchedEffect(Unit) {
        while (true) {
            animatableY.animateTo(
                targetValue = offsetY - with(density) { 10.dp.toPx() },
                animationSpec = tween(durationMillis = 500)
            )
            delay(500)
            animatableY.animateTo(
                targetValue = offsetY,
                animationSpec = tween(durationMillis = 500)
            )
            delay(500)
        }
    }
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
        Text(
            text = text,
            modifier = Modifier.offset { IntOffset(0, animatableY.value.toInt()) },
            style = TextStyle(fontSize = 20.sp,fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive)
        )
    }
}