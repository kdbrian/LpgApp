package com.example.lpg.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.example.lpg.android.ui.nav.MainNavigation
import com.example.lpg.android.ui.theme.LpgGasAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LpgGasAppTheme {
                App {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit = {}, ) {
    CompositionLocalProvider {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LpgGasAppTheme {
    }
}