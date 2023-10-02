package uz.gita.examfinal3.ui.screen.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.examfinal3.R

class AccountScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<AccountViewModel>()
        val uiState = viewModel.collectAsState().value

        ContentScreen(uiState, viewModel::eventDispatcher)

    }
}

@Composable
private fun ContentScreen(
    uiState: AccountContract.UiState,
    eventDispatcher: (AccountContract.Intent) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = null,
                Modifier
                    .size(20.dp)
                    .clickable { eventDispatcher(AccountContract.Intent.Back) }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Profile", fontSize = 16.sp, fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(300.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.img),
                contentDescription = null, alignment = Alignment.Center
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        ElevatedButton(
            onClick = { eventDispatcher(AccountContract.Intent.OpenSignIn) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF667080)),
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text(text = "SIGN IN", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = { eventDispatcher(AccountContract.Intent.OpenSignUp) }) {
            Text(text = "Create new account", color = Color.Black, fontSize = 18.sp)

        }
    }
}

@Preview
@Composable
fun Preview() {
    Surface {
        ContentScreen(AccountContract.UiState.Default) {}
    }
}