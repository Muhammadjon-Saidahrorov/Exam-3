package uz.gita.examfinal3.ui.screen.home.shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import uz.gita.examfinal3.ui.component.ProductsItem

object ShoppingScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.ShoppingCart)

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
        val viewModel = getViewModel<ShoppingViewModel>()
        val uiState = viewModel.collectAsState().value

        ShoppingScreenContent(uiState, viewModel::eventDispatcher)
    }
}

@Composable
private fun ShoppingScreenContent(
    uiState: ShoppingContract.UiState,
    eventDispatcher: (ShoppingContract.Intent) -> Unit
) {

        val products = arrayListOf<ProductData>()

    when(uiState){
        is ShoppingContract.UiState.LoadData->{
            products.addAll(uiState.products)
        }
        else->{

        }
    }

    eventDispatcher.invoke(ShoppingContract.Intent.LoadData)

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_6),
                    contentDescription = null,
                    Modifier
                        .padding(start = 16.dp)
                )
                Text(text = "MY PRODUCTS", fontSize = 16.sp, color = Color.LightGray)
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    Modifier
                        .padding(end = 16.dp)
                        .size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {
                items(products) {pro ->
                    ProductsItem(
                        pro.category,
                        pro.price,
                        pro.size,
                        pro.amount
                    )
                }
            }
        }

        ElevatedButton(
            onClick = {
                eventDispatcher(ShoppingContract.Intent.OpenAdd)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF667080)),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .width(250.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "ADD PRODUCT", color = Color.White, fontSize = 18.sp)
        }

        if(products.size == 0){
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
        ShoppingScreenContent(ShoppingContract.UiState.Default) {}
    }
}