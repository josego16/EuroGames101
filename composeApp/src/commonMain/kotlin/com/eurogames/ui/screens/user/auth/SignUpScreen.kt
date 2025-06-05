package com.eurogames.ui.screens.user.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
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
import com.eurogames.domain.model.auth.SignUpFormData
import com.eurogames.ui.core.utils.AppTheme
import com.eurogames.ui.screens.user.auth.components.AuthButton
import com.eurogames.ui.screens.user.auth.components.AuthLabeledText
import com.eurogames.ui.screens.user.auth.components.AuthScreenContainer
import com.eurogames.ui.screens.user.auth.components.AuthTextField
import com.eurogames.ui.viewmodels.auth.SignUpViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(onBackToSignIn: () -> Unit) {
    val signUpViewModel = koinViewModel<SignUpViewModel>()
    val state by signUpViewModel.state.collectAsState()

    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

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
                title = "Crear cuenta",
                icon = Icons.Rounded.Person
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                AuthTextField(
                    label = "Nombre completo",
                    value = fullName,
                    onValueChange = {
                        fullName = it
                        fullNameError =
                            if (it.length < 3) "El nombre debe tener al menos 3 caracteres" else ""
                    },
                    error = fullNameError,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = "Nombre completo",
                            tint = Color(0xFF0072FF)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                AuthTextField(
                    label = "Usuario",
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError =
                            if (it.length < 3) "El usuario debe tener al menos 3 caracteres" else ""
                    },
                    error = usernameError,
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
                    label = "Correo electrónico",
                    value = email,
                    onValueChange = {
                        email = it
                        emailError =
                            if (!it.contains("@") || !it.contains(".")) "Introduce un correo válido" else ""
                    },
                    error = emailError,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Email,
                            contentDescription = "Correo electrónico",
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
                    error = passwordError,
                    isPassword = true,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Lock,
                            contentDescription = "Contraseña",
                            tint = Color(0xFF0072FF)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                AuthTextField(
                    label = "Repite la contraseña",
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        confirmPasswordError =
                            if (it != password) "Las contraseñas no coinciden" else ""
                    },
                    error = confirmPasswordError,
                    isPassword = true,
                    leadingIcon = {
                        Icon(
                            Icons.Rounded.Lock,
                            contentDescription = "Repite la contraseña",
                            tint = Color(0xFF0072FF)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(18.dp))
                AuthButton(
                    text = "Registrarme",
                    onClick = {
                        fullNameError =
                            if (fullName.isBlank() || fullName.length < 3) "El nombre debe tener al menos 3 caracteres" else ""
                        usernameError =
                            if (username.isBlank() || username.length < 3) "El usuario debe tener al menos 3 caracteres" else ""
                        emailError =
                            if (email.isBlank() || !email.contains("@") || !email.contains(".")) "Introduce un correo válido" else ""
                        passwordError =
                            if (password.isBlank() || password.length < 6) "La contraseña debe tener al menos 6 caracteres" else ""
                        confirmPasswordError = when {
                            confirmPassword.isBlank() -> "Repite la contraseña"
                            confirmPassword != password -> "Las contraseñas no coinciden"
                            else -> ""
                        }
                        if (listOf(
                                fullNameError,
                                usernameError,
                                emailError,
                                passwordError,
                                confirmPasswordError
                            ).all { it.isEmpty() }
                        ) {
                            signUpViewModel.signUp(
                                SignUpFormData(
                                    fullName,
                                    username,
                                    email,
                                    password,
                                    confirmPassword
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                AuthLabeledText(
                    text = "¿Ya tienes cuenta?",
                    clickableText = "Inicia sesión",
                    onClick = onBackToSignIn
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
        LaunchedEffect(state.isSuccess) {
            if (state.isSuccess) {
                onBackToSignIn()
            }
        }
    }
}