package com.arturomarmolejo.amiiboappamcompose.views.screens

import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ScrollingView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arturomarmolejo.amiiboappamcompose.R
import com.arturomarmolejo.amiiboappamcompose.viewmodel.AmiiboViewModel


@Preview
@Composable
fun DetailsScreen(
    amiiboViewModel: AmiiboViewModel? = null
) {

    val selectedAmiibo = amiiboViewModel?.selectedAmiibo

    Box(
      modifier = Modifier.fillMaxSize()
      ) {
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .verticalScroll(rememberScrollState())
                 .background(Color.White),
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             AsyncImage(
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(200.dp),
                 contentScale = ContentScale.None,
                 placeholder = painterResource(R.drawable.baseline_person_24),
                 error = painterResource(R.drawable.baseline_person_off_24),
                 model = ImageRequest.Builder(LocalContext.current).data((selectedAmiibo?.image)).crossfade(true).build(),
                 contentDescription = stringResource(R.string.app_name),
                 alignment = Alignment.BottomStart
             )
             Text(
                 text = "Name: ${selectedAmiibo?.name ?: "Name not available"}",
                 color = Color.Black,
                 fontWeight = FontWeight.Bold,
                 fontSize = 20.sp,
                 textAlign = TextAlign.Center,
                 modifier = Modifier.padding(bottom = 10.dp)
             )

             Text(
                 text = "Series: ${selectedAmiibo?.amiiboSeries ?: "Name not available"}",
                 color = Color.Black,
                 fontWeight = FontWeight.Bold,
                 fontSize = 15.sp,
                 textAlign = TextAlign.Center,
                 modifier = Modifier.padding(bottom = 5.dp)
             )


         }
      }

}