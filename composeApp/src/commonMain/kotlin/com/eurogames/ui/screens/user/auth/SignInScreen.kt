package com.eurogames.ui.screens.user.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthLabeledText
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField
import com.eurogames.ui.viewmodels.SignInViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val viewmodel = koinViewModel<SignInViewModel>()
    val state by viewmodel.state.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    LaunchedEffect(state.user) {
        if (state.user != null && state.error == null) {
            onLoginSuccess()
        }
    }

    AuthScreenContainer(title = "Sign In") {
        AuthTextField(
            label = "Username",
            value = username,
            onValueChange = {
                username = it
                usernameError = if (it.length < 3) "Username must be at least 3 characters" else ""
            },
            error = usernameError,
            leadingIcon = { Icon(Icons.Rounded.AccountCircle, contentDescription = "Username") }
        )
        AuthTextField(
            label = "Password",
            value = password,
            onValueChange = {
                password = it
                passwordError = if (it.length < 6) "Password must be at least 6 characters" else ""
            },
            error = passwordError,
            isPassword = true,
            leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = "Password") }
        )
        AuthButton(
            text = "Sign In",
            onClick = {
                usernameError =
                    if (username.isBlank() || username.length < 3) "Username must be at least 3 characters" else ""
                passwordError =
                    if (password.isBlank() || password.length < 6) "Password must be at least 6 characters" else ""

                if (usernameError.isEmpty() && passwordError.isEmpty()) {
                    viewmodel.signIn(username, password)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        AuthLabeledText("Forgot your password?", "Reset it here!", onClick = onForgotPassword)

        Spacer(modifier = Modifier.height(8.dp))
        AuthLabeledText("Don't have an account?", "Sign up now!", onClick = onSignUpClick)

        Spacer(modifier = Modifier.height(8.dp))
    }
}