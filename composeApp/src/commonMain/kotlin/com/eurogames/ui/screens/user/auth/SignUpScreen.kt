package com.eurogames.ui.screens.user.auth

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.eurogames.domain.models.user.auth.SignUpFormData
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField

@Composable
fun SignUpScreen(
    onSignUp: (SignUpFormData) -> Unit,
    onBackToSignIn: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AuthScreenContainer(title = "Crear cuenta") {
        AuthTextField(
            label = "Nombre",
            value = fullName,
            onValueChange = { fullName = it }
        )

        AuthTextField(
            label = "Nombre de usuario",
            value = username,
            onValueChange = { username = it }
        )

        AuthTextField(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it }
        )

        AuthTextField(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            isPassword = true
        )

        AuthTextField(
            label = "Confirmar contraseña",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            isPassword = true
        )

        AuthButton(text = "Registrarse") {
            onSignUp(
                SignUpFormData(fullName, username, email, password, confirmPassword)
            )
        }

        TextButton(onClick = onBackToSignIn) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}