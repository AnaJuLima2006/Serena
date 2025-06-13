# Serena: Aplicativo de Sons Relaxantes 🎶

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-Android-brightgreen.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.6-blue)

Um aplicativo Android nativo, construído com as tecnologias mais modernas do ecossistema Kotlin, para ouvir sons ambientes e relaxantes, ideal para momentos de foco, meditação ou simplesmente para relaxar.

## 📱 Telas Principais

| Splash Screen | Tela de Boas-vindas | Tela Inicial | Player de Música |
| :-----------: | :------------------: | :------------: | :--------------: |
| *[Insira a screenshot da SplashScreen aqui]* | *[Insira a screenshot da WelcomeScreen aqui]* | *[Insira a screenshot da HomeScreen aqui]* | *[Insira a screenshot da SoundPlayerScreen aqui]* |

## ✨ Funcionalidades

-   **Player de Áudio Completo**: Controles de Play/Pause, avançar e retroceder faixas.
-   **Barra de Progresso**: Slider interativo para navegar pela música.
-   **Navegação por Categorias**: Explore 4 categorias de sons: LO-FI, Instrumental, Clássico e Natureza.
-   **Reprodução Contínua**: Ao final de uma música, a próxima da lista é iniciada automaticamente.
-   **Persistência de Áudio**: A música continua tocando mesmo ao navegar de volta para a tela inicial, graças à arquitetura com ViewModel.
-   **Interface Dinâmica**: Animações sutis nos controles e carregamento de GIFs nas categorias.
-   **Tela de Boas-vindas Personalizável**: Permite ao usuário escolher um "mascote" (gato ou cachorro) que altera a imagem na tela.
-   **Interface Moderna**: Construído 100% com Jetpack Compose e seguindo as diretrizes do Material 3.

## 🛠️ Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e bibliotecas:

-   **Linguagem**: [Kotlin](https://kotlinlang.org/)
-   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose) para uma interface declarativa e moderna.
-   **Arquitetura**: **MVVM (Model-View-ViewModel)**, separando a lógica de negócios da interface do usuário.
-   **Gerenciamento de Estado**: `StateFlow` e `ViewModel` para gerenciar o estado da UI de forma reativa e consciente do ciclo de vida.
-   **Navegação**: [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation) para gerenciar a navegação entre as telas do aplicativo.
-   **Assincronia**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) (`viewModelScope`) para operações assíncronas, como a atualização do progresso da música.
-   **Player de Mídia**: `MediaPlayer` nativo do Android, encapsulado no `PlayerViewModel` para controle robusto.
-   **Carregamento de Imagens**: [Coil](https://coil-kt.github.io/coil/) para carregar e exibir os GIFs das categorias de forma eficiente.

## 🏗️ Arquitetura do Projeto

O app segue o padrão de arquitetura **MVVM**, garantindo um código desacoplado, testável e de fácil manutenção.

-   **Model**: Representado pelo `SoundItem.kt`, que define a estrutura de dados das músicas e fornece uma fonte de dados local (`getSoundsForEnvironment`).
-   **View**: As telas (`Composable functions` como `HomeScreen.kt` e `SoundPlayerScreen.kt`), que são responsáveis apenas por exibir o estado e delegar as ações do usuário ao ViewModel.
-   **ViewModel**: O `PlayerViewModel.kt` atua como o cérebro da lógica de reprodução. Ele gerencia a instância do `MediaPlayer`, controla o estado da música (play/pause, progresso), e expõe esses dados para a View através de `StateFlow`, sobrevivendo a mudanças de configuração e navegação.

## 🚀 Como Executar o Projeto

Para executar este projeto, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/serena.git](https://github.com/seu-usuario/serena.git)
    ```
2.  **Abra no Android Studio:**
    -   Abra o Android Studio (versão Flamingo ou superior é recomendada).
    -   Clique em `File > Open` e selecione a pasta do projeto clonado.
3.  **Sincronize o Gradle:**
    -   Aguarde o Android Studio sincronizar e baixar todas as dependências do projeto.
4.  **Execute o aplicativo:**
    -   Selecione um emulador ou conecte um dispositivo físico.
    -   Clique no botão 'Run' (▶️).

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes.

---

*Desenvolvido com ❤️ por Naju*
