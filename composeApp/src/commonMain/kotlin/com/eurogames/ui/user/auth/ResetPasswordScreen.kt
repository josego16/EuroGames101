package com.eurogames.ui.user.auth

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.eurogames.domain.models.user.auth.ResetPassFormData
import com.eurogames.ui.user.auth.components.AuthButton
import com.eurogames.ui.user.auth.components.AuthScreenContainer
import com.eurogames.ui.user.auth.components.AuthTextField

@Composable
fun ResetPasswordScreen(
    token: String,
    onResetClick: (ResetPassFormData) -> Unit,
    onBackToSignIn: () -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    AuthScreenContainer(title = "Restablecer contraseña") {
        Text("Token: $token", style = MaterialTheme.typography.bodySmall)

        AuthTextField("Nueva contraseña", newPassword, { newPassword = it }, isPassword = true)
        AuthTextField("Confirmar contraseña", confirmPassword, { confirmPassword = it }, isPassword = true)

        AuthButton("Restablecer") {
            onResetClick(ResetPassFormData(newPassword, confirmPassword))
        }

        TextButton(onClick = onBackToSignIn) {
            Text("Volver al inicio de sesión")
        }
    }
}