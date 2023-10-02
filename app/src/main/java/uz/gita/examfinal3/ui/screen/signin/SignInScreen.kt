package uz.gita.examfinal3.ui.screen.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.examfinal3.R
import uz.gita.examfinal3.util.navigation.AppScreen

class SignInScreen : AppScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SignInViewModel>()
        val uiState = viewModel.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect {
            when (it) {
                is SignInContract.SideEffect.HasError -> {
                    Toast.makeText(context, "Error : ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ContentScreen(uiState, viewModel::eventDispatcher)
    }
}

@Composable
private fun ContentScreen(
    uiState: SignInContract.UiState,
    eventDispatcher: (SignInContract.Intent) -> Unit
) {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = null,
                Modifier
                    .clickable { eventDispatcher(SignInContract.Intent.Back) }.size(20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column {
            Text(text = "Email", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(4.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailInput,
                onValueChange = {
                    emailInput = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "examle.user@gmail.com", color = Color.Gray) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)

            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "Password", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(4.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordInput,
                onValueChange = {
                    passwordInput = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "email password", color = Color.Gray) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)


            )
        }

        Spacer(modifier = Modifier.weight(2f))

        ElevatedButton(
            onClick = {
                eventDispatcher(
                    SignInContract.Intent.SignInUser(
                        emailInput,
                        passwordInput
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF667080)),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .width(250.dp)
                .height(50.dp)
        ) {
            Text(text = "SIGN IN", color = Color.White, fontSize = 18.sp)
        }

    }
}

@Preview
@Composable
private fun ContentScreenPreview() {
    Surface() {
        ContentScreen(SignInContract.UiState.Default) {}
    }
}