package com.example.cmpstudy.login.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cmpstudy.composeapp.generated.resources.Res
import cmpstudy.composeapp.generated.resources.authentication
import com.example.cmpstudy.login.domain.User
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    isProcessing: Boolean,
    isButtonEnabled: Boolean,
    currentUser: User?,
    isError: Boolean,
    onSignOut: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val width = this.maxWidth
        val finalModifier = if (width >= 780.dp) modifier.width(400.dp) else modifier.fillMaxWidth()
        Column(
            modifier = finalModifier
                .padding(16.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.authentication),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = uiState.email,
                label = { Text("Email") },
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.password,
                visualTransformation = PasswordVisualTransformation(),
                label = { Text("Password") },
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isProcessing) {
                CircularProgressIndicator()
            } else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isButtonEnabled,
                    onClick = onSignInClick
                ) {
                    Text("SIGN IN")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(currentUser != null && !currentUser.isAnonymous) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start)
                {
                    Text("Login Successful", color = Color.Green.copy(alpha = 0.5f))
                    Text("Logged In auth.User ID:")
                    Text("${currentUser?.id}")
                    TextButton(
                        contentPadding = PaddingValues(0.dp),
                        onClick = { onSignOut() }
                    ) {
                        Text("Log Out")
                    }
                }
            }

            AnimatedVisibility(isError) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Error in email or password!", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }

}
