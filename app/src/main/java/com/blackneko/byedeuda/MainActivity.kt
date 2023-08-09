package com.blackneko.byedeuda

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blackneko.byedeuda.data.model.Deuda
import com.blackneko.byedeuda.ui.theme.ByeDeudaTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.Text as Text

@ExperimentalMaterial3Api
@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            contentApp()
        }

    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
@Preview
fun contentApp(){
    Scaffold(
        //topBar = {toolBar()},
        content = {contentRecycler()},
        floatingActionButton = {fab()},
        floatingActionButtonPosition = FabPosition.End)
}


@ExperimentalMaterial3Api
@Composable
fun toolBar(){
    TopAppBar( title = { Text(text = "Deuda Application", color = Color.DarkGray)},
        Modifier.fillMaxWidth(), navigationIcon = {Icons.Filled.AccountBox})
}
@Preview
@Composable
fun contentRecycler(){
    val data = listOf<Deuda>(Deuda("Julio","asdsa",233,false,"dsadas")
        ,Deuda("Antonio","asdsa",233,false,"dsadas"),
        Deuda("Miguel","asdsa",233,false,"dsadas"),
        Deuda("Caridad","asdsa",233,false,"dsadas"))

    LazyColumn(Modifier.fillMaxSize()) {
            items(data) {
                ListItem(it)

        }
    }
}

@Composable
fun ListItem(data: Deuda, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Text(text = data.name)

    }
}
@Composable
fun fab(){
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.Favorite, contentDescription = "favorite")

    }
}
