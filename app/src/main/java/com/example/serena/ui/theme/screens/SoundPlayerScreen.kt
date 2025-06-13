package com.example.serena.ui.theme.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.serena.R
import com.example.serena.ui.theme.viewModel.PlayerViewModel // Import do pacote correto


val playerScreenBackgroundColor = Color(0xFF1E122B)
val playerControlsBackgroundColor = Color(0xFF130B1F)
val playerTextColor = Color.White
val playerPlaceholderBackgroundColor = Color(0xFF3C2F4E)
val playerPlaceholderIconColor = Color(0xFFA095B7)
val playerSliderColor = Color.White
val playerInactiveSliderColor = Color.White.copy(alpha = 0.3f)

@Composable
fun SoundPlayerScreen(
    envName: String,
    navController: NavController,
    playerViewModel: PlayerViewModel = viewModel()
) {
    val context = LocalContext.current

    // Observa os estados do ViewModel
    val isPlaying by playerViewModel.isPlaying.collectAsState()
    val currentSound by playerViewModel.currentSound.collectAsState()
    val currentPosition by playerViewModel.currentPosition.collectAsState()
    val totalDuration by playerViewModel.totalDuration.collectAsState()
    val isFirstSong = playerViewModel.isFirstSong
    val isLastSong = playerViewModel.isLastSong

    val playPauseSize by animateDpAsState(
        targetValue = if (isPlaying) 90.dp else 60.dp,
        animationSpec = tween(durationMillis = 200), label = ""
    )

    // Carrega os sons para o ambiente apenas uma vez quando a tela é iniciada
    LaunchedEffect(envName) {
        playerViewModel.loadSounds(envName, context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(playerScreenBackgroundColor)
            .padding(top = 32.dp, bottom = 0.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SERENA",
            style = MaterialTheme.typography.headlineLarge,
            color = playerTextColor,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .clickable {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true } // Melhora a navegação
                    }
                }
        )

        Spacer(modifier = Modifier.weight(0.4f))

        Text(
            text = envName.uppercase(),
            style = MaterialTheme.typography.headlineMedium,
            color = playerTextColor,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(1f),
            colors = CardDefaults.cardColors(containerColor = playerPlaceholderBackgroundColor)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                currentSound?.imageRes?.let { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = currentSound?.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } ?: Image( // Imagem placeholder caso currentSound seja nulo
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "Placeholder",
                    modifier = Modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(playerPlaceholderIconColor)
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = currentSound?.name ?: "Carregando...",
            style = MaterialTheme.typography.headlineSmall,
            color = playerTextColor,
        )

        Spacer(modifier = Modifier.weight(3f))

        // --- PAINEL DE CONTROLES ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    playerControlsBackgroundColor,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(horizontal = 24.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                value = currentPosition.toFloat(),
                onValueChange = { newValue -> playerViewModel.seekTo(newValue.toInt()) },
                valueRange = 0f..(if (totalDuration > 0) totalDuration.toFloat() else 100f),
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = playerSliderColor,
                    activeTrackColor = playerSliderColor,
                    inactiveTrackColor = playerInactiveSliderColor
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = playerViewModel.formatTime(currentPosition), color = playerTextColor, fontSize = 15.sp)
                Text(text = playerViewModel.formatTime(totalDuration), color = playerTextColor, fontSize = 15.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botão "Anterior" agora funciona
                IconButton(onClick = { playerViewModel.previousSound(context) }, enabled = !isFirstSong) {
                    Image(
                        painter = painterResource(id = R.drawable.voltar),
                        contentDescription = "Voltar",
                        modifier = Modifier.size(50.dp),
                        colorFilter = ColorFilter.tint(if (!isFirstSong) playerTextColor else playerInactiveSliderColor)
                    )
                }

                // Botão "Play/Pause" agora funciona
                IconButton(onClick = { playerViewModel.togglePlayPause(context) }) {
                    Image(
                        painter = painterResource(id = if (isPlaying) R.drawable.pause else R.drawable.play),
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        modifier = Modifier.size(playPauseSize),
                        colorFilter = ColorFilter.tint(playerTextColor)
                    )
                }

                // Botão "Próximo" agora funciona
                IconButton(onClick = { playerViewModel.nextSound(context) }, enabled = !isLastSong) {
                    Image(
                        painter = painterResource(id = R.drawable.proximo),
                        contentDescription = "Próximo",
                        modifier = Modifier.size(50.dp),
                        colorFilter = ColorFilter.tint(if (!isLastSong) playerTextColor else playerInactiveSliderColor)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SoundPlayerScreenPreview() {
    // O preview pode não funcionar perfeitamente com ViewModel, mas a estrutura está aqui
    SoundPlayerScreen(envName = "LO-FI", navController = rememberNavController())
}