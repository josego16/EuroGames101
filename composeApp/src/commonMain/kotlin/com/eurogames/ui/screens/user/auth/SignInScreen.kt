package com.eurogames.ui.screens.user.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eurogames.domain.models.user.auth.SignInFormData
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthLabeledText
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField

@Composable
fun SignInScreen(
    onSignInClick: (SignInFormData) -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

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
                usernameError = if (username.isBlank() || username.length < 3) "Username must be at least 3 characters" else ""
                passwordError = if (password.isBlank() || password.length < 6) "Password must be at least 6 characters" else ""

                if (usernameError.isEmpty() && passwordError.isEmpty()) {
                    onSignInClick(SignInFormData(username, password))
                }
            }
        )
        Text(
            text = "Forgot Password?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(8.dp)
                .clickable { onForgotPasswordClick() }
        )
        Spacer(modifier = Modifier.height(32.dp))
        AuthLabeledText("Don't have an account?", "Sign up now!", onClick = onSignUpClick)
    }
}