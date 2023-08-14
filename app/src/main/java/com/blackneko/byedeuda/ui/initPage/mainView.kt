@file:OptIn(ExperimentalMaterial3Api::class)

package com.blackneko.byedeuda.ui.initPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blackneko.byedeuda.R
import com.blackneko.byedeuda.data.model.Deuda
import com.blackneko.byedeuda.ui.aggregateDeuda.aggregateDeudaView




@ExperimentalMaterial3Api
@Composable
fun toolBar() {
    TopAppBar(title = {
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
    }, navigationIcon = {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
        }
    }, actions = {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search",
                tint = Color.White
            )

        }
    })
}

@Composable
fun contentRecycler(mainViewModel: MainViewModel = hiltViewModel()) {

    mainViewModel.actualizarLiveData()
    Box {
        //     aggregateDeudaView()
        val data = mainViewModel.listDeuda.collectAsState()
        LazyColumn(Modifier.fillMaxSize()) {
            items(data.value) {
                ListItem(it)

            }
        }
    }
}


@Composable
fun ListItem(data: Deuda) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(80.dp)
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
            Column {

                Text(
                    text = data.name,
                    Modifier.padding(PaddingValues(top = 8.dp)),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = data.description, style = MaterialTheme.typography.labelMedium)

            }
            Column {
                Modifier.fillMaxWidth()
                Text(
                    text = data.date,
                    Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(end = 5.dp, top = 8.dp)),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    text = data.amount.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.End
                )
            }

        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun fab(mainViewModel: MainViewModel = hiltViewModel()) {
    ExtendedFloatingActionButton(onClick = {
mainViewModel.changeAggregateState()
    }) {
        Icon(Icons.Filled.Add, contentDescription = "add")
        Text(text = "Agregar")

    }
}


