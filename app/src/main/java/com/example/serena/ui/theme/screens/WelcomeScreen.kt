package com.example.serena.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.serena.R // Certifique-se que o R seja o do seu projeto


val welcomeSwitchThumbColor = Color(0xFFA07BC7)
val welcomeSwitchTrackColor = Color(0xFF130B1F)
val welcomeButtonColor = Color(0xFFA07BC7)
val welcomeButtonTextColor = Color.White

@Composable
fun WelcomeScreen(navController: NavController) {
    var isToggleChecked by remember { mutableStateOf(false) }

    Box( // O Box principal agora controla o alinhamento de todos os itens
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF5F2B97),
                        Color(0xFF5F2B97),
                        Color(0xFF03020C),
                        Color(0xFF03020C)
                    )
                )
            )
    ) {
        // --- INÍCIO: Coluna para o conteúdo do topo ---
        // Esta coluna agora contém apenas o texto, switch e botão.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Linha do Topo: Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    checked = isToggleChecked,
                    onCheckedChange = { isToggleChecked = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = welcomeSwitchThumbColor,
                        checkedTrackColor = welcomeSwitchTrackColor.copy(alpha = 0.7f),
                        checkedBorderColor = welcomeSwitchThumbColor,
                        uncheckedThumbColor = Color(0xFF130B1F),
                        uncheckedTrackColor = Color(0xFFA07BC7).copy(alpha = 0.5f),
                        uncheckedBorderColor = Color(0xFF130B1F)
                    )
                )
            }

            // Título "SERENA"
            Text(
                text = "SERENA",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            // Texto descritivo
            Text(
                text = "Escolha um som relaxante com Serena!\nNavegue pelos diferentes tipos de som e encontre o que mais combina com você. Além de tudo, você também pode mudar o tema do app, entre a gatinha Lila e o doguinho Thor...",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(10.dp)
            )

            Spacer(modifier = Modifier.height(3.dp))

            // Botão COMEÇAR
            Button(
                onClick = {
                    navController.navigate("home")
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = welcomeButtonColor,
                    contentColor = welcomeButtonTextColor
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(47.dp)
            ) {
                Text(
                    text = "COMEÇAR",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

        }



        Image(
            painter = painterResource(
                id = if (isToggleChecked) R.drawable.dog_boas_vindas else R.drawable.cat_boas_vindas_
            ),
            contentDescription = if (isToggleChecked) "Cachorro com fones de ouvido" else "Gato branco com fones de ouvido",
            contentScale = ContentScale.Crop,
            // MODIFIER CONDICIONAL
            modifier = if (isToggleChecked) {
                // Se for o cachorro, alinhe no centro da parte de baixo
                Modifier
                    .height(350.dp) // Usando height para um visual melhor que size
                    .align(Alignment.BottomCenter)
            } else {
                // Se for o gato, alinhe no canto inferior esquerdo
                Modifier
                    .size(350.dp)
                    .align(Alignment.BottomStart)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}
