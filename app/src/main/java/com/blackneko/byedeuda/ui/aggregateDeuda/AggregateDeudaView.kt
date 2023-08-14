package com.blackneko.byedeuda.ui.aggregateDeuda

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blackneko.byedeuda.ui.initPage.MainViewModel

@ExperimentalMaterial3Api
@Composable
fun aggregateDeudaView() {
    Box {
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            textEditName()
            Spacer(modifier = Modifier.padding(4.dp))
            textEditDescription()
            Spacer(modifier = Modifier.padding(4.dp))
            textEditAmmount()
            Spacer(modifier = Modifier.padding(10.dp))
            myCalendar()
            Spacer(modifier = Modifier.padding(5.dp))
            switchType()
            Spacer(modifier = Modifier.padding(6.dp))
            buttonAggregate()
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun textEditName(viewModel: AggregateDeudaViewModel = hiltViewModel()) {
    val name by viewModel.name.collectAsState()

    OutlinedTextField(
        value = name,
        onValueChange = { viewModel.setName(it) },
        Modifier
            .padding(5.dp)
            .width(350.dp)
            .background(Color.Transparent),
        label = { Text(text = "Nombre") },
        placeholder = { Text(text = "Escriba su nombre") },

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1
    )
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun textEditDescription(viewModel: AggregateDeudaViewModel = hiltViewModel()) {
    val description by viewModel.description.collectAsState()

    OutlinedTextField(
        value = description,
        onValueChange = { viewModel.setDescription(it) },
        Modifier
            .padding(5.dp)
            .height(100.dp)
            .background(Color.Transparent)
            .width(350.dp),
        label = { Text(text = "Descripción") },
        placeholder = { Text(text = "Escriba una descripción") },

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = false,
        maxLines = 3
    )
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun textEditAmmount(viewModel: AggregateDeudaViewModel = hiltViewModel()) {

    val amount by viewModel.amount.collectAsState()

    OutlinedTextField(
        value = amount,
        onValueChange = { viewModel.setAmount(it) },
        Modifier
            .padding(5.dp)
            .width(350.dp)
            .background(Color.Transparent),
        label = { Text(text = "Monto") },
        placeholder = { Text(text = "Escriba la cantidad") },

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun myCalendar(mainViewModel: AggregateDeudaViewModel = hiltViewModel()) {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()

    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mYear = mCalendar.get(Calendar.YEAR)
    val mDate by mainViewModel.date.collectAsState()
    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
            mainViewModel.setDate(mDay, mMonth, mYear)
        }, mYear, mMonth, mDay
    )
    Row {
        Text(
            text = mDate, modifier = Modifier.padding(PaddingValues(top = 8.dp)), fontSize = (17.sp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { mDatePickerDialog.show() }) {
            Text(text = "Modificar Fecha")
        }


    }
}

@Composable
fun switchType(mainViewModel: AggregateDeudaViewModel = hiltViewModel()) {
    val switch by mainViewModel.switchState.collectAsState()
    Row {
        Text(
            text = "Préstamo/Deuda",
            modifier = Modifier.padding(PaddingValues(top = 8.dp)),
            fontSize = (17.sp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Switch(checked = switch, onCheckedChange = { mainViewModel.switchChangeState() })
    }
}

@Composable
fun buttonAggregate(viewModel: AggregateDeudaViewModel = hiltViewModel(), mainViewModel: MainViewModel= hiltViewModel()) {
    val buttonState by viewModel.isCorrect.collectAsState()
    Button(
        onClick = { viewModel.addDeuda()
                  mainViewModel.changeAggregateState()},

        enabled = buttonState
    ) {
        Icon(Icons.Filled.Check, "Completar")
        Text(text = "Agregar", fontSize = 19.sp)

    }
}

@ExperimentalMaterial3Api
@Composable
fun toolBarAggregateDeuda(mainViewModel: MainViewModel = hiltViewModel()) {
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
        IconButton(onClick = { mainViewModel.changeAggregateState() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
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

