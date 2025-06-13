package com.example.serena.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.serena.R
import androidx.compose.ui.platform.LocalContext // Import para Coil
import coil.ImageLoader // Import para Coil
import coil.compose.AsyncImage // Import para Coil
import coil.decode.ImageDecoderDecoder // Import para Coil (para GIFs em APIs mais recentes)
import coil.decode.GifDecoder // Import para Coil (para GIFs em APIs mais antigas, se necessário, mas ImageDecoderDecoder geralmente cobre)


val sereneTextColor = Color.White

@Composable
fun HomeScreen(navController: NavController) {
    var isToggleChecked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF421C62))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 18.dp, bottom = 18.dp), // Ajuste de padding
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TOPO
            Spacer(modifier = Modifier.height(15.dp)) // Mais espaço no topo
            Text(
                text = "SERENA",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Spacer(modifier = Modifier.height(15.dp)) // Espaço após o título

            // BOTÕES DE CATEGORIA - Organizados Verticalmente
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Permite que esta coluna ocupe o espaço vertical disponível
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp) // Espaço entre os botões
            ) {
                // Estes são placeholders.
                CategoryButton(
                    title = "LO-FI",
                    gifRes = R.drawable.lofi_gif2, // Ex: Use a imagem Lo-Fi do protótipo
                    onClick = { navController.navigate("sound/LO-FI") }
                )
                CategoryButton(
                    title = "INSTRUMENTAL",
                    gifRes = R.drawable.instrumental_gif2, // Ex: Use a imagem Instrumental do protótipo
                    onClick = { navController.navigate("sound/INSTRUMENTAL") }
                )
                CategoryButton(
                    title = "CLASSICO", // Texto corrigido
                    gifRes = R.drawable.classico_gif, // Ex: Use a imagem Clássico do protótipo
                    onClick = { navController.navigate("sound/CLÁSSICO") }
                )
                CategoryButton(
                    title = "NATUREZA",
                    gifRes = R.drawable.natureza_gif, // Ex: Use a imagem Natureza do protótipo
                    onClick = { navController.navigate("sound/NATUREZA") }
                )
            }

            // RODAPÉ
            Spacer(modifier = Modifier.height(24.dp)) // Espaço antes do rodapé
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    // Supondo que R.drawable.logo_s é o logo de gatinho do rodapé do protótipo
                    painter = painterResource(id = R.drawable.logo_s),
                    contentDescription = "Serena Logo",
                    modifier = Modifier
                        .size(90.dp) // Tamanho do logo no rodapé
                        .clip(CircleShape)
                        .border(width = 1.5.dp, color = sereneTextColor, shape = CircleShape) // Borda mais fina
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "@ 2025 SERENA", // Texto do protótipo
                    style = MaterialTheme.typography.bodyMedium, // Estilo para texto menor de rodapé
                )
                Spacer(modifier = Modifier.height(16.dp)) // Padding na parte inferior
            }
        }
    }
}

@Composable
fun CategoryButton(title: String, gifRes: Int, onClick: () -> Unit) { // Parâmetro renomeado para gifRes
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Usando AsyncImage da Coil para carregar o GIF
            // Usando AsyncImage da Coil para carregar o GIF
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .build() // A Coil usará o GifDecoder automaticamente para APIs < 28
            // se a dependência coil-gif estiver presente.

            AsyncImage(
                model = gifRes, // ID do recurso GIF (ex: R.drawable.meu_gif)
                contentDescription = title,
                imageLoader = imageLoader, // Passando o ImageLoader configurado para GIFs
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Overlay para o texto (mesmo de antes)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
                    .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 8.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())

}