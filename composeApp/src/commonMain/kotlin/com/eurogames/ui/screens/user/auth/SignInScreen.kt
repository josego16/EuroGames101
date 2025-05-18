package com.eurogames.ui.screens.user.auth

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.eurogames.domain.models.user.auth.SignInFormData
import com.eurogames.ui.screens.user.auth.components.AuthButton
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

    AuthScreenContainer(title = "Iniciar Sesión") {
        AuthTextField("Usuario", username, { username = it })
        AuthTextField("Contraseña", password, { password = it }, isPassword = true)

        AuthButton("Entrar") {
            onSignInClick(SignInFormData(username, password))
        }

        TextButton(onClick = onForgotPasswordClick) {
            Text("¿Olvidaste tu contraseña?")
        }

        TextButton(onClick = onSignUpClick) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}