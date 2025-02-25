package com.example.lpg.android.ui.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lpg.android.R
import com.example.lpg.android.data.model.Address
import com.example.lpg.android.ui.theme.LpgGasAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillInAddressInfo(
    modifier: Modifier = Modifier,
    addressInfo: Address? = null,
    onClose: () -> Unit = {},
    onSave: (Address) -> Unit = {},
) {

    val (location, setLocation) = remember { mutableStateOf(addressInfo?.locationName ?: "") }
    val (address, setAddress) = remember { mutableStateOf(addressInfo?.addressCode ?: "") }
    val snackBarState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onClose
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Address info")
                        Text(
                            text = "Fill in your pickup address",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (location.isNotEmpty() || address.isNotEmpty())
                            onSave(
                                Address(
                                    locationName = location,
                                    addressCode = address
                                )
                            )
//                    else
//                        Toast.make
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.save_24dp),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackBarState)
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = location,
                onValueChange = setLocation,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                placeholder = { Text(text = "e.g 21 street") },
                label = { Text(text = "Location") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )


            OutlinedTextField(
                value = address,
                onValueChange = setAddress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                placeholder = { Text(text = "e.g 21 street") },
                label = { Text(text = "Address") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    scope.launch {
                        focusManager.clearFocus(true)
                        keyboardController?.hide()
                    }
                })
            )

        }
    }


}


@Preview(showBackground = true)
@Composable
private fun FillInAddressInfoPrev() {
    LpgGasAppTheme {
        FillInAddressInfo()
    }
}