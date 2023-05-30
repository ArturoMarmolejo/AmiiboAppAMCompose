package com.arturomarmolejo.amiiboappamcompose.views.screens

import android.widget.SearchView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arturomarmolejo.amiiboappamcompose.R
import com.arturomarmolejo.amiiboappamcompose.model.domain.AmiiboDomain
import com.arturomarmolejo.amiiboappamcompose.utils.UIState
import com.arturomarmolejo.amiiboappamcompose.viewmodel.AmiiboViewModel
import com.arturomarmolejo.amiiboappamcompose.views.navgraph.Routes
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(amiiboViewModel: AmiiboViewModel, navController: NavController) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    val filteredItems = remember {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                amiiboViewModel.amiiboList.value
            } else {
                amiiboViewModel.amiiboList.value
            }
        }
    }
    Column(Modifier.fillMaxSize()) {
   
        Text(
            text = "Amiibo Catalog",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .wrapContentHeight(),
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Search for a specific Amiibo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .wrapContentHeight()
                .padding(2.dp),
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        //--------SEARCHVIEW HERE ---------------
        TextField(
            value = searchQuery, 
            onValueChange = {searchQuery = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(text = "Search")},
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        when(val state = amiiboViewModel.amiiboList.collectAsState(UIState.LOADING).value) {
            is UIState.LOADING -> {}
            is UIState.SUCCESS -> {
                FilteredAmiiboList(items = state.response, selectedItem = {
                    amiiboViewModel.selectedAmiibo = it
                }, navController = navController, searchQuery = searchQuery)
            }
            is UIState.ERROR -> {
                Toast.makeText(LocalContext.current, "Item not found", Toast.LENGTH_LONG).show()
            }
        }
    
    }

}

@Composable
fun AmiiboList(
    items: List<AmiiboDomain>,
    navController: NavController? = null,
    selectedItem: ((AmiiboDomain) -> Unit)? = null,
) {
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxSize()
        ) {
            LazyColumn(
                content = {
                    itemsIndexed(items = items) { _, item ->
                        AmiiboItem(item = item, navController, selectedItem )

                    }
                }
            )
        }
}

@Composable
fun FilteredAmiiboList(
    items: List<AmiiboDomain>,
    searchQuery: String,
    selectedItem: ((AmiiboDomain) -> Unit)? = null,
    navController: NavController? = null,
) {
    val filteredItemsState = remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                items
            } else {
                items.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }
    
    val filteredItems = filteredItemsState.value

    AmiiboList(items = filteredItems, navController = navController, selectedItem = selectedItem)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmiiboItem(
    item: AmiiboDomain,
    navController: NavController? = null,
    selectedItem: ((AmiiboDomain) -> Unit)? = null
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            selectedItem?.invoke(item)
            navController?.navigate(Routes.DetailsScreen.route)
        }

    ) {

        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.None,
                placeholder = painterResource(R.drawable.baseline_person_24),
                error = painterResource(R.drawable.baseline_person_off_24),
                model = ImageRequest.Builder(LocalContext.current).data((item?.image)).crossfade(true).build(),
                contentDescription = stringResource(R.string.app_name),
                alignment = Alignment.Center
            )

            Text(
                color = Color.Black,
                text = "Name: ${item.name}" ?: "Name not available",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

    }
}