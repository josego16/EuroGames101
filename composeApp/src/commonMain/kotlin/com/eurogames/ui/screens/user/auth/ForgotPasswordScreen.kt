package com.eurogames.ui.screens.user.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField
import com.eurogames.ui.viewmodels.ForgotPasswordViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ForgotPasswordScreen() {
    koinViewModel<ForgotPasswordViewModel>()
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var sent by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    AuthScreenContainer(title = "Recuperar contraseña") {
        Text("Introduce tu email y, si existe en el sistema, recibirás instrucciones para restablecer tu contraseña.")
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(
            label = "Email",
            value = email,
            onValueChange = {
                email = it
                emailError =
                    if (!it.contains("@") || !it.contains(".")) "Formato de email inválido" else ""
            },
            error = emailError,
            leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = "Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AuthButton(
            text = "Enviar",
            onClick = {
                emailError =
                    if (email.isBlank() || !email.contains("@") || !email.contains(".")) "Formato de email inválido" else ""
                if (emailError.isEmpty()) {
                    //viewmodel.forgotPassword(email)
                    scope.launch {
                        snackbarHostState.showSnackbar("La recuperación de contraseña estará disponible próximamente. Si necesitas ayuda, contacta con soporte.")
                    }
                    sent = true
                }
            }
        )
        SnackbarHost(hostState = snackbarHostState)
        if (sent) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Si el email existe, recibirás instrucciones para restablecer tu contraseña.")
        }
    }
}