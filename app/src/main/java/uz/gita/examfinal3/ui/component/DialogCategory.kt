package uz.gita.examfinal3.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogCategory() {

    var category by remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color(0xFF667080)),
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .padding(16.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "CATEGORY NAME", color = Color.White)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                value = category,
                onValueChange = {
                    category = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            ElevatedButton(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(text = "Submit", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun DialogCategoryPreview() {
    Surface() {
        DialogCategory()
    }
}