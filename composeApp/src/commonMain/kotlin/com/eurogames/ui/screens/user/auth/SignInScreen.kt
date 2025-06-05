package com.eurogames.ui.screens.user.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthLabeledText
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField
import com.eurogames.ui.viewmodels.auth.SignInViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val signInViewModel = koinViewModel<SignInViewModel>()
    val state by signInViewModel.state.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    LaunchedEffect(state.user) {
        if (state.user != null && state.errorUsername == null && state.errorPassword == null) {
            onLoginSuccess()
        }
    }
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF232526),
                            Color(0xFF414345)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            AuthScreenContainer(
                title = "Iniciar sesión",
                icon = Icons.Rounded.AccountCircle
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                AuthTextField(
                    label = "Usuario",
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError =
                            if (it.length < 3) "El usuario debe tener al menos 3 caracteres" else ""
                    },
                    error = if (usernameError.isNotEmpty()) usernameError else (state.errorUsername
                        ?: ""),
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.AccountCircle,
                            contentDescription = "Usuario",
                            tint = Color(0xFF0072FF)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                AuthTextField(
                    label = "Contraseña",
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError =
                            if (it.length < 6) "La contraseña debe tener al menos 6 caracteres" else ""
                    },
                    error = if (passwordError.isNotEmpty()) passwordError else (state.errorPassword
                        ?: ""),
                    isPassword = true,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Lock,
                            contentDescription = "Contraseña",
                            tint = Color(0xFF0072FF)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(18.dp))
                AuthButton(
                    text = "Entrar",
                    onClick = {
                        usernameError =
                            if (username.isBlank() || username.length < 3) "El usuario debe tener al menos 3 caracteres" else ""
                        passwordError =
                            if (password.isBlank() || password.length < 6) "La contraseña debe tener al menos 6 caracteres" else ""
                        if (usernameError.isEmpty() && passwordError.isEmpty()) {
                            signInViewModel.signIn(username, password)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                AuthLabeledText(
                    text = "¿No tienes cuenta?",
                    clickableText = "Regístrate aquí",
                    onClick = onSignUpClick
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}