# Funcionalidades de la Aplicación

## 1. Calculadora de IMC

### Entrada de Datos
- El usuario ingresa:
  - Peso en kilogramos.
  - Altura en centímetros.

### Cálculo del IMC
- La altura ingresada en cm se convierte a metros (`altura / 100`).
- Se usa la fórmula: `IMC = peso / (altura * altura)`.

### Clasificación del IMC
- El resultado se compara con la tabla de categorías de imc:
  - Desnutrición 18 omenos
  - Peso Ideal 
  - Sobrepeso
  - Obesidad I
  - Obesidad II
  - Obesidad III
  - Obesidad IV

### Manejo de Errores
- Se validan entradas vacías.
- Se evita que el usuario ingrese valores no numéricos.
- Se muestra un mensaje de error en caso de datos inválidos.

### Interfaz de Usuario
- **EditText**: Para entrada de datos.
- **Button**: Para calcular.
- **TextView**: Para mostrar el resultado y la categoría.
# Aplicación de Cálculo de IMC - Documentación Técnica

## 📱 Funcionalidades Principales

### 1. Calculadora de IMC

#### 📊 Entrada de Datos
- **Peso**: 
  - Unidad: kilogramos (kg)
  - Campo: `TextInputEditText` con validación numérica
- **Altura**: 
  - Unidad: centímetros (cm)
  - Conversión automática a metros (m)

#### 🧮 Algoritmo de Cálculo
```kotlin
fun calcularIMC(peso: Float, alturaCm: Float): Float {
    val alturaMetros = alturaCm / 100
    return peso / (alturaMetros * alturaMetros)
}
