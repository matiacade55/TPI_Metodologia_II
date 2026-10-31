# Sistema de Gestión de Solicitudes Académicas - UTN

Este proyecto es el **Trabajo Práctico Integrador (TPI)** para la asignatura **Metodología de Sistemas II** (Tecnicatura Universitaria en Programación - UTN). 

El objetivo principal de este repositorio no es solo presentar un sistema funcional, sino demostrar empíricamente la evolución desde un código con **alta deuda técnica** hacia una arquitectura robusta, aplicando principios **SOLID**, **Clean Code** y técnicas avanzadas de **Refactoring**.

## 🚀 Funcionalidades
El sistema opera mediante una interfaz de consola (CLI) con validaciones robustas y permite:
1. Registrar nuevas solicitudes académicas (Beca, Equivalencia, Examen, etc.).
2. Reasignar el sector responsable de una solicitud (con validaciones de negocio).
3. Modificar el estado de la solicitud (Pendiente, En Proceso, Aprobado, Rechazado).
4. Generar un reporte detallado del estado de las solicitudes.

## 🛠️ Evolución Técnica y Refactoring

El proyecto cuenta con dos hitos (etiquetados en el historial de Git): el código base intencionalmente defectuoso y la versión final refactorizada. Durante el proceso se aplicaron las siguientes mejoras:

### 1. Refactorings Aplicados
* **Introduce Parameter Object:** Se eliminó la obsesión por primitivos (y el code smell *Long Parameter List*) encapsulando los datos crudos en las entidades `Estudiante` y `Solicitud` mediante el patrón Builder.
* **Extract Method & DRY:** Se extrajo lógica repetitiva de los menús hacia métodos genéricos reutilizables (`seleccionarOpcionEnum<T>`, `leerEntero`, `leerTexto`).
* **Move Method:** Se desmanteló la "Clase Gigante" original, reubicando la persistencia en `RepositorioEnMemoria` y la lógica de negocio en `GestorDeSolicitudes`.
* **Replace Conditional with Polymorphism:** Se eliminó una extensa cadena de `if-else` utilizando el patrón Factory (`TramiteFactory`) y la interfaz `TramiteAcademico`.

### 2. Principios SOLID Implementados
* **SRP (Single Responsibility Principle):** Separación estricta en capas: Presentación (`Main`), Lógica de Negocio (`GestorDeSolicitudes`) y Acceso a Datos (`RepositorioEnMemoria`).
* **OCP (Open/Closed Principle):** Implementación de la interfaz `TramiteAcademico` para la determinación dinámica de sectores responsables. El sistema está abierto a nuevos trámites sin modificar el gestor.
* **DIP (Dependency Inversion Principle):** El gestor de solicitudes depende de la abstracción `RepositorioSolicitudes`, permitiendo en el futuro inyectar una base de datos real (ej. JPA) sin alterar la capa de negocio.

### 3. Fiabilidad y Fail-Fast (Programación Defensiva)
Se erradicaron los retornos de error silenciosos y el uso de `null` directo.
* Uso de `Optional` en las búsquedas del repositorio.
* Implementación de **Fail-Fast** en la UI: el sistema verifica la existencia del ID antes de solicitar el resto de los datos al usuario.
* Lanzamiento de excepciones semánticas (`IllegalArgumentException`, `RuntimeException`) para casos de borde, capturadas de forma segura en la capa de presentación.

## 💻 Tecnologías Utilizadas
* **Java 25**
* **Maven** (Gestor de dependencias)
* **Lombok** (Reducción de boilerplate y patrón Builder)

## ⚙️ Instalación y Uso

1. Clonar el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   ```
2. Instalar dependencias mediante Maven:
   ```bash
   mvn clean install
   ```
3. Ejecutar la clase principal:
   ```bash
   java -cp target/classes org.example.Main
   ```