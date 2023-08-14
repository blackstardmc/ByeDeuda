package com.blackneko.byedeuda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.blackneko.byedeuda.ui.aggregateDeuda.aggregateDeudaView
import com.blackneko.byedeuda.ui.aggregateDeuda.toolBarAggregateDeuda
import com.blackneko.byedeuda.ui.initPage.MainViewModel
import com.blackneko.byedeuda.ui.initPage.contentRecycler
import com.blackneko.byedeuda.ui.initPage.fab
import com.blackneko.byedeuda.ui.initPage.toolBar
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
fun contentApp(mainViewModel: MainViewModel = hiltViewModel()) {
    val aggregateState: Boolean by mainViewModel.aggregateState.collectAsState()
    if (!aggregateState) {
        Scaffold(topBar = { toolBar() },

            content = { contentPadding ->

                Box(modifier = Modifier.padding(contentPadding)) { contentRecycler() }
            }, floatingActionButton = { fab() }, floatingActionButtonPosition = FabPosition.End
        )
    } else {
        Scaffold(topBar = { toolBarAggregateDeuda() },

            content = { contentPadding ->

                Box(modifier = Modifier.padding(contentPadding)) { aggregateDeudaView() }
            }
        )
    }
}