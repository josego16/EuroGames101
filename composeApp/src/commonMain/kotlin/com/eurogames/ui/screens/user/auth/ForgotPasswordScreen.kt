package com.eurogames.ui.screens.user.auth

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.eurogames.domain.models.user.auth.ForgotPassFormData
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField

@Composable
fun ForgotPasswordScreen(
    onSendEmailClick: (ForgotPassFormData) -> Unit,
    onBackToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    AuthScreenContainer(title = "Recuperar contraseña") {
        AuthTextField("Correo electrónico", email, { email = it })

        AuthButton("Enviar correo") {
            onSendEmailClick(ForgotPassFormData(email))
        }

        TextButton(onClick = onBackToLogin) {
            Text("Volver al inicio de sesión")
        }
    }
}