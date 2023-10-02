package uz.gita.examfinal3.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import uz.gita.examfinal3.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Square_Product_Item(image: Int, price: String, name: String) {
    Column(
        Modifier
            .padding(16.dp),
    ) {
        Card(
            modifier = Modifier
                .size(140.dp), elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .width(200.dp), contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = image), contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$$price", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, color = Color.LightGray, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun P() {
    Surface {
        Square_Product_Item(image = R.drawable.ic_launcher_foreground, price = "763", name = "hdah")
    }
}


