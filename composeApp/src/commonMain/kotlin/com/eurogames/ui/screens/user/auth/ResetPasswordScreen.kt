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
fun ResetPasswordScreen(onSubmit: (String, String) -> Unit, onBackToSignIn: () -> Unit) {
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }
    val confirmPasswordError = remember { mutableStateOf("") }

    AuthScreenContainer(title = "Reset Password") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                label = passwordError.value.ifEmpty { "New Password" },
                value = password.value,
                onValueChange = { password.value = it },
                isPassword = true
            )

            AuthTextField(
                label = confirmPasswordError.value.ifEmpty { "Confirm Password" },
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                isPassword = true
            )

            AuthButton(
                text = "Submit",
                onClick = {
                    if (password.value.isEmpty()) {
                        passwordError.value = "Password is required"
                    } else {
                        passwordError.value = ""
                    }

                    if (confirmPassword.value.isEmpty()) {
                        confirmPasswordError.value = "Confirm Password is required"
                    } else if (password.value != confirmPassword.value) {
                        confirmPasswordError.value = "Passwords do not match"
                    } else {
                        confirmPasswordError.value = ""
                    }

                    if (passwordError.value.isEmpty() && confirmPasswordError.value.isEmpty()) {
                        onSubmit(password.value, confirmPassword.value)
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
