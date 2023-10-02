package uz.gita.examfinal3.ui.screen.home.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.examfinal3.R
import uz.gita.examfinal3.data.model.ProductData
import uz.gita.examfinal3.ui.component.Square_Product_Item
import uz.gita.examfinal3.ui.screen.add.AddContract
import uz.gita.examfinal3.util.myLog

object ShopScreen : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Menu)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "",
                    icon = icon
                )
            }
        }


    @Composable
    override fun Content() {
        val viewModel: ShopContract.ViewModel = getViewModel<ShopViewModel>()
        val uiState = viewModel.collectAsState().value


        ShopScreenContent(uiState, viewModel::onEventDispatcher)
    }


}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ShopScreenContent(
    uiState: ShopContract.UiState,
    onEventDispatcher: (ShopContract.Intent) -> Unit,
) {
    val categorydata = remember { mutableStateOf<List<String>>(arrayListOf()) }
    val productdata = remember { mutableStateOf<List<ProductData>>(arrayListOf()) }

    var searchInput by remember { mutableStateOf("") }


    when (uiState) {
        is ShopContract.UiState.Data -> {
            categorydata.value = uiState.categories
            productdata.value = uiState.products
            myLog("kirdi")
        }
        else -> {

        }
    }

    onEventDispatcher.invoke(ShopContract.Intent.LoadData)

    myLog("tashqari")
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_6),
                    contentDescription = null,
                    Modifier
                        .padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    Modifier
                        .padding(end = 16.dp)
                        .size(24.dp)
                )
            }


            Spacer(modifier = Modifier.height(40.dp))

            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp), value = searchInput, onValueChange = {
                searchInput = it
            },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = { Text(text = "Search") },
                singleLine = false,
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                })

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(categorydata.value) { categoryData ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = categoryData,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 16.dp),
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.img_7),
                            contentDescription = null,
                            Modifier
                                .padding(end = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow {
                        items(productdata.value) {
                            if (categoryData == it.category) {
                                Square_Product_Item(
                                    R.drawable.ic_launcher_foreground,
                                    it.price,
                                    it.name
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                }

            }
        }

        if (categorydata.value.isEmpty()) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text(text = "There isn't any products")
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    Surface {
        ShopScreenContent(ShopContract.UiState.LoadingData) {}
    }
}