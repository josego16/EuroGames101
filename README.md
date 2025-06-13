![EuroGames101](https://socialify.git.ci/josego16/EuroGames101/image?custom_language=Kotlin&language=1&name=1&owner=1&theme=Light)

# EuroGames101 - Aplicación Kotlin Multiplatform

> Aplicación multiplataforma que proporciona información sobre juegos, países y funciones de gestión de usuarios
> utilizando una base de código compartida con implementaciones específicas para cada plataforma cuando es necesario.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue.svg)](https://kotlinlang.org/)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.8.0-green.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![Koin](https://img.shields.io/badge/Koin-4.0.4-orange.svg)](https://insert-koin.io/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Arquitectura](#-arquitectura)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Tecnologías](#-tecnologías-utilizadas)
- [Instalación](#-instalación)
- [Más Información](#-más-información)

---

## 🚀 Características

| Funcionalidad                | Descripción                                                               |
|------------------------------|---------------------------------------------------------------------------|
| 🎮 **Información de Juegos** | Navega y visualiza detalles sobre varios juegos                           |
| 🌍 **Información de Países** | Consulta información sobre diferentes países                              |
| 👤 **Gestión de Usuarios**   | Autenticación de usuarios y gestión de perfiles                           |
| 📱 **UI Multiplataforma**    | Experiencia de usuario consistente en Android e iOS                       |
| 🔄 **Código Compartido**     | Base de código común que reduce la duplicación y mejora la mantenibilidad |

---

## 🏗️ Arquitectura

El proyecto sigue los principios de **Arquitectura Limpia (Clean Architecture)** con un patrón **MVVM** para la capa de
UI:

```
EuroGames101
├── Capa de Datos (Repositorios, Fuentes de Datos Remotas)
├── Capa de Dominio (Casos de Uso, Modelos de Dominio, Interfaces de Repositorio)
└── Capa de Presentación (UI, ViewModels, Gestión de Estado)
```

### Componentes Arquitectónicos Clave:

- **Arquitectura Limpia**: Separación de responsabilidades con capas independientes
- **Patrón MVVM**: Para actualizaciones reactivas de UI y gestión de estado
- **Patrón Repositorio**: Para abstracción de acceso a datos
- **Inyección de Dependencias**: Para código modular y testeable

---

## 📂 Estructura del Proyecto

### `/composeApp`

Contiene el código compartido para todas las plataformas:

#### `/src/commonMain`

Código compartido para todas las plataformas:

```
commonMain/
├── composeResources/          # Recursos para Compose Multiplatform
└── kotlin/
    └── com/
        └── eurogames/
            ├── App.kt                # Punto de entrada de la aplicación
            ├── Platform.kt           # Interfaz de plataforma
            ├── Result.kt             # Clase para manejo de resultados
            ├── data/                 # Capa de datos
            │   ├── mappers/          # Conversión entre DTOs y modelos de dominio
            │   ├── remote/           # Clientes API, DTOs remotos
            │   └── repository/       # Implementaciones de interfaces de repositorio
            ├── di/                   # Inyección de dependencias
            │   ├── DataModule.kt     # Dependencias de la capa de datos
            │   ├── DiConfig.kt       # Configuración de inyección de dependencias
            │   ├── DomainModule.kt   # Dependencias de la capa de dominio
            │   ├── NetworkModule.kt  # Dependencias de red
            │   └── UiModule.kt       # Dependencias de UI
            ├── domain/               # Capa de dominio
            │   ├── enums/            # Enumeraciones del dominio
            │   ├── model/            # Modelos de dominio
            │   ├── repository/       # Interfaces de repositorio
            │   ├── session/          # Gestión de sesión del usuario
            │   └── usecase/          # Casos de uso
            └── ui/                   # Capa de presentación
                ├── core/             # Componentes básicos de UI
                │   └── navigation/   # Sistema de navegación
                ├── screens/          # Pantallas
                │   ├── country/      # Pantallas de países
                │   ├── game/         # Pantallas de juegos
                │   ├── home/         # Pantalla de inicio
                │   ├── splash/       # Pantalla de bienvenida
                │   └── user/         # Pantallas de usuario
                ├── state/            # Estados de UI
                └── viewmodels/       # ViewModels
```

#### `/src/androidMain`

Implementaciones específicas para Android:

```
androidMain/
└── kotlin/
    └── com/
        └── eurogames/
            ├── EuroGamesApp.kt       # Clase de aplicación Android
            ├── MainActivity.kt       # Actividad principal
            ├── Platform.android.kt   # Implementación de plataforma para Android
            ├── di/                   # Inyección de dependencias específica para Android
            └── ui/                   # Componentes de UI específicos para Android
```

#### `/src/iosMain`

Implementaciones específicas para iOS:

```
iosMain/
└── kotlin/
    └── com/
        └── eurogames/
            ├── mainViewController.kt # Controlador de vista principal
            ├── Platform.ios.kt       # Implementación de plataforma para iOS
            ├── di/                   # Inyección de dependencias específica para iOS
            └── ui/                   # Componentes de UI específicos para iOS
```

### `/iosApp`

Contiene el punto de entrada de la aplicación iOS y código SwiftUI.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología                | Descripción                             |
|---------------------------|-----------------------------------------|
| **Kotlin Multiplatform**  | Para compartir código entre plataformas |
| **Compose Multiplatform** | Para UI multiplataforma                 |
| **Koin**                  | Para inyección de dependencias          |
| **Ktor**                  | Para comunicación en red                |
| **Kotlinx Serialization** | Para análisis JSON                      |
| **Kotlinx Coroutines**    | Para programación asíncrona             |

---

## 🔧 Instalación

Sigue estos pasos para poner en marcha el proyecto en tu entorno local:

### 📥 Requisitos previos

- Android Studio Arctic Fox o más reciente
- Xcode 13 o más reciente (para desarrollo iOS)
- JDK 11 o más reciente

### ⚙️ Pasos de instalación

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/josego16/EuroGames101.git
   ```

   Accede al directorio del proyecto:
   ```bash
   cd EuroGames101
   ```

2. **Abrir el proyecto en Android Studio**

3. **Para Android**:
    - Selecciona la configuración `composeApp` y ejecuta en un dispositivo o emulador Android

4. **Para iOS**:
    - Abre el archivo `iosApp/iosApp.xcodeproj` en Xcode
    - Ejecuta el proyecto en un dispositivo o simulador iOS

---

## 🛡️ Licencia

Este proyecto está licenciado bajo la **Licencia MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

## 📚 Más Información

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)

---

*© 2025 EuroGames101. Todos los derechos reservados.*
