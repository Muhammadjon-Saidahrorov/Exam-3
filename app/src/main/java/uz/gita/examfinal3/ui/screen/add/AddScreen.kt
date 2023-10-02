package uz.gita.examfinal3.ui.screen.add

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import uz.gita.examfinal3.data.model.CategoryData
import uz.gita.examfinal3.data.model.ProductData
import uz.gita.examfinal3.ui.component.CategoryItem
import uz.gita.examfinal3.util.navigation.AppScreen

class AddScreen : AppScreen() {
    @Composable
    override fun Content() {

        val viewModel = getViewModel<AddViewModel>()
        val uiState = viewModel.collectAsState().value
        val context = LocalContext.current

        viewModel.collectSideEffect {
            when (it) {
                is AddContract.SideEffect.HasError -> {
                    Toast.makeText(context, "Error : ${it.message}", Toast.LENGTH_SHORT).show()
                }
                is AddContract.SideEffect.HasSuccess -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        ContentScreen(uiState, viewModel::eventDispatcher)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentScreen(
    uiState: AddContract.UiState,
    eventDispatcher: (AddContract.Intent) -> Unit
) {
    val context = LocalContext.current

    var logic by remember {
        mutableStateOf(false)
    }

    var selectLogic by remember {
        mutableStateOf(false)
    }

    var color by remember { mutableStateOf(Color(0xFF979797)) }

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var size by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    val data = remember { mutableStateOf<List<String>>(arrayListOf()) }

    when (uiState) {
        is AddContract.UiState.LoadData -> {
            data.value = uiState.categoryList
        }
        else -> {}
    }

    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = null,
                    Modifier
                        .padding(start = 0.dp)
                        .size(20.dp)
                        .clickable { eventDispatcher(AddContract.Intent.Back) }
                )
                Text(text = "ADD PRODUCTS", fontSize = 16.sp, color = Color.LightGray)
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(Modifier.padding(16.dp)) {
                Text(text = "Name", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "e.g. Converse", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)

                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Price", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = price,
                    onValueChange = {
                        price = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "e.g. \$225.00", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(text = "Size", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = size,
                    onValueChange = {
                        size = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "US 7", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)

                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Amount", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(4.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = amount,
                    onValueChange = {
                        amount = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "e.g. 12", color = Color.Gray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )

            }
            Spacer(modifier = Modifier.weight(1f))

            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Category",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.img_5),
                    contentDescription = null,
                    Modifier
                        .padding(end = 16.dp)
                        .size(20.dp)
                        .clickable { logic = true }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (data.value.isEmpty()) {
                Text(text = "There isn't any categorys")
            }
            LazyRow(Modifier.padding(start = 16.dp)) {
                items(data.value) { str ->
                    color = if (category != str) {
                        Color(0xFF979797)
                    } else {
                        Color(0xFF29B95F)
                    }

                    CategoryItem(
                        str,
                        Modifier
                            .padding(horizontal = 8.dp),
                        color
                    ) {

                        category = str
                        selectLogic = true

                        color = Color(0xFF29B95F)

                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            ElevatedButton(
                onClick = {
                    if (name != "" && size != "" && amount != "" && price != "") {
                        if (!logic) {
                            if (selectLogic) {
                                eventDispatcher(
                                    AddContract.Intent.AddProduct(
                                        ProductData(
                                            id = "",
                                            name = name,
                                            size = size,
                                            amount = amount,
                                            price = price,
                                            category = category,
                                        )
                                    )
                                )
                            } else {
                                Toast.makeText(context, "SELECT CATEGORY", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Empty!", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF667080)),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(250.dp)
                    .height(50.dp)
            ) {
                Text(text = "ADD TO BAG", color = Color.White, fontSize = 18.sp)
            }
        }

        if (logic) {

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color(0xFF667080)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .padding(16.dp)
                    .align(Alignment.Center)
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
                        onClick = {
                            eventDispatcher(
                                AddContract.Intent.AddCategory(
                                    CategoryData(
                                        "",
                                        category
                                    )
                                )
                            )

                            logic = false
                            selectLogic = true
                        },
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
    }
}

@Preview
@Composable
private fun ContentScreenPreview() {
    Surface {
        ContentScreen(AddContract.UiState.Default) {}
    }
}