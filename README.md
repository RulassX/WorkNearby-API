WorkNearby

Aplicación móvil para conectar clientes con profesionales locales según proximidad y categoría de servicio.

📌 Descripción

WorkNearby es una app móvil desarrollada en Android Studio con Kotlin que permite a los usuarios registrarse como clientes o trabajadores. Los trabajadores pueden publicar sus servicios, establecer el precio y el radio de actuación, mientras que los clientes pueden buscar profesionales cercanos, contratarlos y valorarlos mediante reseñas y puntuaciones.

Objetivo principal: Facilitar que pequeños profesionales o autónomos puedan publicitar sus servicios de manera asequible, y que los clientes encuentren fácilmente profesionales confiables en su área.

⚙️ Funcionalidades

-Registro e inicio de sesión de usuarios (clientes y trabajadores).

-Gestión de perfiles de clientes y trabajadores.

-Creación y consulta de servicios y contrataciones.

-Búsqueda por proximidad utilizando coordenadas GPS y radio de servicio del trabajador.

-Valoraciones y reseñas de clientes a trabajadores.

-Multimedia: subida y visualización de imágenes de perfiles y trabajos.

-Multi-idioma: soporte castellano / gallego.

-Interfaz responsiva y usable, adaptable a diferentes pantallas.

🛠️ Tecnologías utilizadas

Frontend (móvil): Android Studio, Kotlin, Jetpack Compose, RecyclerView, Room (SQLite), Retrofit, Coil.

Backend (servidor): Kotlin con Spring Boot, API REST, JWT para autenticación.

Base de datos: MySQL (servidor), SQLite (local).

Control de versiones: Git / GitHub.

🗂️ Estructura del proyecto

[Aun en proceso]

    WorkNearby-API/
    ├─ src/
    │  ├─ main/
    │  │  ├─ kotlin/
    │  │  │  └─ com/
    │  │  │     └─ raul_fernandez_garcia/
    │  │  │        └─ worknearby_api/
    │  │  │           ├─ controller/
    │  │  │           │  ├─ MaestrosController.kt
    │  │  │           │  ├─ OfertaController.kt
    │  │  │           │  ├─ RecursosController.kt
    │  │  │           │  ├─ ServicioController.kt
    │  │  │           │  ├─ TrabajadorController.kt
    │  │  │           │  └─ UsuarioController.kt
    │  │  │           ├─ modelo/
    │  │  │           │  ├─ Categoria.kt
    │  │  │           │  ├─ Cliente.kt
    │  │  │           │  ├─ Oferta.kt
    │  │  │           │  ├─ Resena.kt
    │  │  │           │  ├─ Servicio.kt
    │  │  │           │  ├─ Trabajador.kt
    │  │  │           │  ├─ Trabajador_Categoria.kt
    │  │  │           │  └─ Usuario.kt
    │  │  │           ├─ modeloDTO/
    │  │  │           │  ├─ CategoriaDTO.kt
    │  │  │           │  ├─ ClienteDTO.kt
    │  │  │           │  ├─ CrearOferta.kt
    │  │  │           │  ├─ LoginRequest.kt
    │  │  │           │  ├─ OfertaDTO.kt
    │  │  │           │  ├─ RegistroDTO.kt
    │  │  │           │  ├─ ResenaDTO.kt
    │  │  │           │  ├─ ServicioDTO.kt
    │  │  │           │  ├─ SolicitarServicioDTO.kt
    │  │  │           │  ├─ TrabajadorCategoriaDTO.kt
    │  │  │           │  ├─ TrabajadorDTO.kt
    │  │  │           │  └─ UsuarioDTO.kt
    │  │  │           ├─ repository/
    │  │  │           │  ├─ CategoriaRepository.kt
    │  │  │           │  ├─ ClienteRepository.kt
    │  │  │           │  ├─ OfertaRepository.kt
    │  │  │           │  ├─ ResenaRepository.kt
    │  │  │           │  ├─ ServicioRepository.kt
    │  │  │           │  ├─ TrabajadorRepository.kt
    │  │  │           │  ├─ TrabCatgRepository.kt
    │  │  │           │  └─ UsuarioRepository.kt
    │  │  │           ├─ service/
    │  │  │           │  ├─ ClienteService.kt
    │  │  │           │  ├─ OfertaService.kt
    │  │  │           │  ├─ ResenaService.kt
    │  │  │           │  ├─ ServicioService.kt
    │  │  │           │  ├─ TrabajadorService.kt
    │  │  │           │  └─ UsuarioService.kt
    │  │  │           └─ WorkNearbyApiApplication.kt
    │  │  └─ resources/
    │  │     ├─ static/
    │  │     ├─ templates/
    │  │     └─ application.properties
    │  └─ test/
    │     └─ kotlin/
    │        └─ com/raul_fernandez_garcia/WorkNearby_API
    │           └─ WorkNearbyApiApplicationTests.kt
    └─ README.md


📊 Base de datos

-Usuarios: información común a todos los usuarios (login, email, rol).

-Clientes: información específica de clientes.

-Trabajadores: información específica de trabajadores (categorías, radio de servicio, ubicación).

-Categorías: lista de sectores de trabajo.

-Trabajador_Categoria: relación N:M entre trabajadores y categorías.

-Servicios: registros de contrataciones entre clientes y trabajadores.

-Reseñas: valoraciones de clientes a trabajadores.

🚀 Instalación y ejecución

1.Clonar el repositorio:

    git clone https://github.com/usuario/WorkNearby.git

2.Abrir el proyecto en Android Studio.

3.Configurar la base de datos local y servidor remoto (MySQL/PostgreSQL).

4.Ejecutar la app en un emulador o dispositivo real.

5.Probar la comunicación con el servidor usando Postman para verificar endpoints REST.

📆 Estado del proyecto

-En desarrollo: implementación de autenticación, servicios y comunicación con servidor en progreso.

-Base de datos y diseño de vistas completados parcialmente.

-Próximos pasos: finalización de funcionalidades de contratación, reseñas y búsqueda por proximidad.

📜 Motivación

El proyecto surge para facilitar el acceso a trabajos locales de profesionales y autónomos que no cuentan con grandes recursos de publicidad, promoviendo la visibilidad de servicios de calidad en el entorno cercano de los clientes.

👤 Autor

-Nombre: Raúl Fernández Gracía.

-Ciclo Formativo: Desarrollo de Aplicaciones Multiplataforma (DAM)

-Proyecto de fin de grado
