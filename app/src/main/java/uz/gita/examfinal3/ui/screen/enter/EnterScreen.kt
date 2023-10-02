package uz.gita.examfinal3.ui.screen.enter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.examfinal3.R
import uz.gita.examfinal3.ui.screen.account.AccountScreen
import uz.gita.examfinal3.util.navigation.AppScreen

class EnterScreen : AppScreen() {
    @Composable
    override fun Content() {
        ContentScreen()
    }
}


@Composable
private fun ContentScreen() {

    val navigator = LocalNavigator.currentOrThrow

    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Text(
                    text = "Lorem ipsum dolor \nsit, consectetur",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "Lorem ipsum dolor sit amet, \nconsectetur adipiscin adipiscing elit.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 30.dp)
                )
                ElevatedButton(
                    onClick = { navigator.push(AccountScreen()) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF667080)),
                    modifier = Modifier
                        .padding(bottom = 100.dp)
                        .width(200.dp)
                        .height(50.dp)
                ) {
                    Text(text = "START", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContentScreenPreview() {
    ContentScreen()
}