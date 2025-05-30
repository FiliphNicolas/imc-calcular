# Funcionalidades de la Aplicación de IMC

## 📱 Funcionalidades Principales

### 1. Calculadora de IMC

#### 📊 Entrada de Datos
- **Peso**:
  - Unidad: kilogramos (kg)
  - Campo: `TextInputEditText` con validación numérica
  - Rango válido: 20-300 kg
  - Mensaje de error: "Por favor ingrese un peso válido"

- **Altura**:
  - Unidad: centímetros (cm)
  - Conversión automática a metros (m)
  - Rango válido: 100-250 cm
  - Mensaje de error: "Por favor ingrese una altura válida"

#### 🧮 Algoritmo de Cálculo
```kotlin
fun calcularIMC(peso: Float, alturaCm: Float): Float {
    val alturaMetros = alturaCm / 100
    return peso / (alturaMetros * alturaMetros)
}
```

#### 📈 Clasificación del IMC
- **Desnutrición**: IMC < 18.5
- **Peso Normal**: IMC 18.5 - 24.9
- **Sobrepeso**: IMC 25 - 29.9
- **Obesidad I**: IMC 30 - 34.9
- **Obesidad II**: IMC 35 - 39.9
- **Obesidad III**: IMC ≥ 40

#### 🔄 Manejo de Errores
- Validación de campos vacíos
- Validación de entrada numérica
- Validación de rangos válidos
- Manejo de división por cero
- Mensajes de error claros y descriptivos

#### 🎨 Interfaz de Usuario
- **EditText**:
  - Campos para peso y altura
  - Validación en tiempo real
  - Mensajes de error visibles

- **Button**:
  - Botón "Calcular" principal
  - Estado deshabilitado cuando faltan datos
  - Animación de carga durante el cálculo

- **TextView**:
  - Resultado numérico del IMC
  - Categoría correspondiente
  - Recomendaciones basadas en el resultado

#### 📊 Resultados y Recomendaciones
- Mostrar IMC calculado con 2 decimales
- Indicar categoría de IMC
- Mostrar recomendaciones basadas en el resultado
- Opción para guardar resultados
- Historial de cálculos previos

#### 🔄 Funcionalidades Adicionales
- Conversión automática de unidades (kg/cm a lb/pulgadas)
- Guardado de resultados en historial
- Exportación de resultados
- Compartir resultados por correo o redes sociales
- Gráficos de evolución de IMC

## 🛠️ Características Técnicas

### 1. Validaciones
- Validación de entrada numérica
- Validación de rango de valores
- Manejo de errores de cálculo
- Validación de formato

### 2. Persistencia de Datos
- Almacenamiento local de historial
- Sincronización con la nube (opcional)
- Backup automático

### 3. Interfaz Responsiva
- Diseño adaptativo para diferentes tamaños de pantalla
- Soporte para modo oscuro
- Accesibilidad integrada
- Soporte para diferentes idiomas

## 📈 Tabla de Referencia IMC

| Categoría | IMC | Recomendación |
|-----------|-----|--------------|
| Desnutrición | < 18.5 | Consultar médico |
| Peso Normal | 18.5 - 24.9 | Mantener hábitos saludables |
| Sobrepeso | 25 - 29.9 | Considerar cambios en dieta y ejercicio |
| Obesidad I | 30 - 34.9 | Consultar médico y plan de pérdida de peso |
| Obesidad II | 35 - 39.9 | Programa de pérdida de peso supervisado |
| Obesidad III | ≥ 40 | Tratamiento médico urgente |

## 🏋️‍♂️ Recomendaciones de Ejercicio

### Basado en IMC

#### Desnutrición (IMC < 18.5)
- Ejercicios de bajo impacto
- Fortalecimiento muscular moderado
- Duración: 20-30 minutos
- Frecuencia: 3-4 veces por semana

#### Peso Normal (IMC 18.5-24.9)
- Ejercicios aeróbicos
- Entrenamiento de fuerza
- Duración: 30-45 minutos
- Frecuencia: 4-5 veces por semana

#### Sobrepeso (IMC 25-29.9)
- Ejercicios de bajo impacto
- Entrenamiento de fuerza con peso corporal
- Duración: 30-60 minutos
- Frecuencia: 4-5 veces por semana

#### Obesidad (IMC ≥ 30)
- Ejercicios adaptados
- Fortalecimiento muscular
- Duración: 20-40 minutos
- Frecuencia: 3-4 veces por semana

### Ejercicios Recomendados
- Caminata
- Natación
- Yoga
- Entrenamiento de fuerza
- Ejercicios de bajo impacto

## 🥗 Recomendaciones de Dieta

### Basado en IMC

#### Desnutrición
- Aumentar calorías saludables
- Proteínas magras
- Carbohidratos complejos
- Vitaminas y minerales

#### Peso Normal
- Dieta balanceada
- 5 comidas al día
- Porciones moderadas
- Variada en nutrientes

#### Sobrepeso
- Reducción de calorías
- Bajo en grasas saturadas
- Alto en fibra
- Control de porciones

#### Obesidad
- Plan de alimentación personalizado
- Reducción gradual de calorías
- Supervisión médica
- Control de líquidos

### Alimentos Recomendados
- Proteínas magras
- Frutas y verduras
- Granos integrales
- Lácteos bajos en grasa
- Agua y líquidos saludables

## 📊 Recomendaciones Nutricionales

### Macro y Micronutrientes
- Proteínas: 15-20% de calorías
- Carbohidratos: 45-60% de calorías
- Grasas: 20-30% de calorías
- Fibra: 25-30g diarios
- Vitamina D: 600-800 IU/día
- Calcio: 1000-1200mg/día

### Hidratación
- 2-3 litros de agua/día
- Evitar bebidas azucaradas
- Agua antes y después de ejercicios

### Suplementos (con supervisión médica)
- Multivitaminas
- Omega 3
- Calcio y vitamina D
- Proteínas

## 📊 Seguimiento y Monitoreo

### Parámetros a Monitorear
- Peso semanal
- IMC mensual
- Circunferencia de cintura
- Niveles de energía
- Calidad del sueño

### Herramientas de Monitoreo
- Registro de alimentos
- Contador de pasos
- Aplicación de seguimiento de ejercicio
- Registro de hidratación

### Consultas Médicas
- Evaluación inicial
- Seguimiento mensual
- Ajustes en el plan
- Control de indicadores de salud

## 📝 Plan de Acción Personalizado

### Elementos del Plan
- Objetivos específicos
- Plan de ejercicio adaptado
- Plan de alimentación
- Horarios
- Recompensas
- Ajustes según progreso

### Recursos Adicionales
- Videos de ejercicios
- Recetas saludables
- Guías nutricionales
- Comunidad de apoyo
- Programas de motivación

### Seguimiento y Evaluación
- Evaluación mensual
- Ajustes según resultados
- Motivación continua
- Soporte constante
- Celebración de logros
