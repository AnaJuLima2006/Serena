package com.example.serena.ui.theme.model

import com.example.serena.R

data class SoundItem(
    val name: String,
    val imageRes: Int,
    val soundRes: Int
)

fun getSoundsForEnvironment(envName: String): List<SoundItem> {
    return when (envName.uppercase()) {
        "LO-FI" -> listOf(
            SoundItem("Toque Suave", R.drawable.lofi_capa1, R.raw.lofi_1),
            SoundItem("Notas da Madrugada", R.drawable.lofi_capa2, R.raw.lofi_2),
            SoundItem("Pensando na Vida", R.drawable.lofi_capa3, R.raw.lofi_3),
            SoundItem("Reflexos Noturnos", R.drawable.lofi_capa4, R.raw.lofi_4),
            SoundItem("Café & Pensamentos", R.drawable.lofi_capa5, R.raw.lofi_5),
            SoundItem("Dias Cinzentos", R.drawable.lofi_capa6, R.raw.lofi_6),
            SoundItem("Memórias em Fita", R.drawable.lofi_capa7, R.raw.lofi_7),
            SoundItem("Entre Livros", R.drawable.lofi_capa8, R.raw.lofi_8),
            SoundItem("Cidade ao Longe", R.drawable.lofi_capa9, R.raw.lofi_9),
            SoundItem("Sonhos em Loop", R.drawable.lofi_capa10, R.raw.lofi_10),
            SoundItem("Amanhecer Lento", R.drawable.lofi_capa11, R.raw.lofi_11)
        )

        "INSTRUMENTAL" -> listOf(
            SoundItem("Pop", R.drawable.instrumental_c1, R.raw.instrumental_pop),
            SoundItem("Gentle", R.drawable.instrumental_c2, R.raw.instrumental_gentle),
            SoundItem("Ambient", R.drawable.instrumental_c3, R.raw.instrumental_ambient),
            SoundItem("Calming", R.drawable.instrumental_c4, R.raw.instrumental_calming),
            SoundItem("Complicated", R.drawable.instrumental_c5, R.raw.instrumental_complicated),
            SoundItem("Caves of dawn", R.drawable.instrumental_c6, R.raw.instrumental_caves_of_dawn),
            SoundItem("Guitare Emotion", R.drawable.instrumental_c7, R.raw.instrumental_guitare_emotion),
            SoundItem("Impact", R.drawable.instrumental_c8, R.raw.instrumental_undertone) ,
            SoundItem("Undertone", R.drawable.instrumental_c9, R.raw.instrumental_rock),
            SoundItem("Rock", R.drawable.instrumental_c10, R.raw.instrumental_piano),
            SoundItem("Metal", R.drawable.instrumental_c10, R.raw.instrumental_metal)

        )
        "CLÁSSICO" -> listOf(
            SoundItem("Air on the G String", R.drawable.classico_capa1, R.raw.classico_air_string),
            SoundItem("Sicilienne", R.drawable.classico_capa2, R.raw.classico_sicilienne),
            SoundItem("Für Elise", R.drawable.classico_capa3, R.raw.classico_fur_elise),
            SoundItem("Waltz in A-Flat Major", R.drawable.classico_capa4, R.raw.classico_waltz_flat),
            SoundItem("Cello Suite", R.drawable.classico_capa5, R.raw.classico_cello_suite),
            SoundItem("The Spectrum Boys", R.drawable.classico_capa6, R.raw.classico_the_spectrum_boys),
            SoundItem("Morning Lilly", R.drawable.classico_capa7, R.raw.classico_morning_lilly),
            SoundItem("The Last Road Trip", R.drawable.classico_capa8, R.raw.classico_the_last_road_trip),
            SoundItem("Ferris Wheel", R.drawable.classico_capa9, R.raw.classico_ferris_wheel),
            SoundItem("What Tears ", R.drawable.classico_capa10, R.raw.classico_damp_monastery),
            SoundItem("Time Will Only Tell", R.drawable.classico_capa11, R.raw.classico_only_tell)
        )
        "NATUREZA" -> listOf(
            SoundItem("Floresta Serena", R.drawable.natureza_capa1, R.raw.natureza_floresta),
            SoundItem("Sicilienne Clássico", R.drawable.natureza_capa2, R.raw.classico_sicilienne),
            SoundItem("Chuva na Mata", R.drawable.natureza_capa3, R.raw.natureza_chuva),
            SoundItem("Trovoada Distante", R.drawable.natureza_capa4, R.raw.natureza_trovao),
            SoundItem("Tempestade Selvagem", R.drawable.natureza_capa5, R.raw.natureza_tempestade),
            SoundItem("Águas Correntes", R.drawable.natureza_capa6, R.raw.natureza_agua_corrente),
            SoundItem("Passos na Floresta", R.drawable.natureza_capa7, R.raw.natureza_passos),
            SoundItem("Gotas Suaves", R.drawable.natureza_capa8, R.raw.natureza_gotas),
            SoundItem("Voo dos Falcões", R.drawable.natureza_capa9, R.raw.natureza_falcoes),
            SoundItem("Chuva na Janela", R.drawable.natureza_capa10, R.raw.natureza_chuva_janela),
            SoundItem("Cachoeira Mística", R.drawable.natureza_capa11, R.raw.natureza_cachoeira)
        )


        else -> emptyList()
    }
}
