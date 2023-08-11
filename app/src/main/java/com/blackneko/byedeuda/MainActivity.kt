package com.blackneko.byedeuda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blackneko.byedeuda.data.model.Deuda
import com.blackneko.byedeuda.ui.initPage.mainViewModel
import dagger.hilt.android.AndroidEntryPoint

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

@ExperimentalMaterial3Api
@Composable
@Preview
fun contentApp() {
    Scaffold(
        topBar = { toolBar() },

        content = { contentPadding ->
            // Screen content
            Box(modifier = Modifier.padding(contentPadding)) { contentRecycler() }
        },
        floatingActionButton = { fab() },
        floatingActionButtonPosition = FabPosition.End
    )
}


@ExperimentalMaterial3Api
@Composable
fun toolBar() {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                text = "Préstamos - Deudas",
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                fontSize = (27.sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )

            }
        }
    )
}


@Composable
fun contentRecycler() {

    val data = listOf(
        Deuda("Julio", "Prestado para motorina", 1570, false, "dsadas"),
        Deuda("Antonio", "Merienda", 233, false, "dsadas"),
        Deuda("Miguel", "Jamonada", 2300, true, "dsadas"),
        Deuda("Caridad", "Viaje a la Habana", 8887, false, "dsadas")
    )
    Box {

        LazyColumn(Modifier.fillMaxSize()) {

            items(data) {
                ListItem(it)

            }
        }
    }
}


@Composable
fun ListItem(data: Deuda, modifier: Modifier = Modifier) {
    Card(
        modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row {


            if (data.type) {
                Image(
                    painter = painterResource(
                        id = R.drawable.round_account_balance_wallet_24
                    ), contentDescription = null
                )
            } else {
                Image(
                    painter = painterResource(
                        id = R.drawable.baseline_money_off_24
                    ), contentDescription = null
                )
            }
            Column(modifier.width(250.dp)) {

                Text(
                    text = data.name, fontFamily = FontFamily.Default, fontSize = (20.sp)
                )
                Text(text = data.description)

            }
            Text(
                modifier = Modifier.padding(10.dp),
                text = data.amount.toString(),
                fontFamily = FontFamily.Monospace,
                fontSize = (19.sp)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun fab() {
    ExtendedFloatingActionButton(onClick = {
    /*    mainViewModel.addDeuda(
            Deuda(
                "miguel",
                "sada",
                23,
                true,
                "asda"
            )
        )*/
    }) {
        Icon(Icons.Filled.Add, contentDescription = "add")
        Text(text = "Agregar")

    }
}

@Composable
fun agregateView() {
    Box(modifier = Modifier.fillMaxSize())
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun agregaqeteDeuda() {
    Box(Modifier.fillMaxSize()) {
        Column {
            val textState = remember { mutableStateOf(TextFieldValue()) }
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(value = textState.value, onValueChange = { textState.value = it })
        }
    }
}
