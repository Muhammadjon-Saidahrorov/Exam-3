package uz.gita.examfinal3.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.checkerframework.common.subtyping.qual.Bottom
import uz.gita.examfinal3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsItem(category: String, cost: String, size: String, amount: String) {
    Surface {
        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.elevatedCardElevation(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Card(
                    modifier = Modifier
                        .size(80.dp),
                    colors = CardDefaults.cardColors(Color(0xFFEEF1F4))
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(
                            id =
                            R.drawable.ic_launcher_foreground
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }


                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = category, fontWeight = FontWeight.Bold)
                    Text(text = "$$cost", modifier = Modifier)
                    Text(text = "Size: $size", fontWeight = FontWeight.Bold)
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = null,
                    )

                }

                Spacer(modifier = Modifier.weight(1f))


                Row(verticalAlignment = Alignment.CenterVertically) {

                    Card(
                        modifier = Modifier
                            .size(30.dp),
                        colors = CardDefaults.cardColors(Color(0xFFEEF1F4))
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = R.drawable.img_4),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            )

                        }
                    }
                    Text(text = amount, modifier = Modifier.padding(horizontal = 16.dp))

                    Card(
                        modifier = Modifier
                            .size(30.dp),
                        colors = CardDefaults.cardColors(Color(0xFFEEF1F4))
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = R.drawable.img_5),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun ProductsItemPreview() {
    ProductsItem("Lorem", "225.00", "Us 7","1")
}