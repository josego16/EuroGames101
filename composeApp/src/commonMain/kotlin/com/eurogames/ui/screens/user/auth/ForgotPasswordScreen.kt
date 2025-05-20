package com.eurogames.ui.screens.user.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField

@Composable
fun ForgotPasswordScreen(onSubmit: (String) -> Unit, onBackToSignIn: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf("") }

    AuthScreenContainer(title = "Forgot Password") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                label = emailError.value.ifEmpty { "Email" },
                value = email.value,
                onValueChange = { email.value = it }
            )

            AuthButton(
                text = "Submit",
                onClick = {
                    if (email.value.isEmpty()) {
                        emailError.value = "Email is required"
                    } else {
                        emailError.value = ""
                        onSubmit(email.value)
                    }
                }
            )

            AuthButton(
                text = "Back to Sign In",
                onClick = { onBackToSignIn() }
            )
        }
    }
}