## Краткое описание приложения
Java-приложение с интегрированной ИИ-моделью для анализа тональности текста, контейнеризованное в Docker и развёрнутое в Kubernetes.

## 💻 Технологии

Для реализации проекта использовалась:
- Java 17
- Spring Boot
- Docker
- Kubernetes
- Prometheus
- Grafana
- Maven


## Структура приложения
```
📦main
┣ 📂java
┃ ┗ 📂ru
┃ ┃ ┗ 📂aisystem
┃ ┃ ┃ ┣ 📂inference
┃ ┃ ┃ ┃ ┣ 📜AiModel.java
┃ ┃ ┃ ┃ ┗ 📜AiModelLoader.java
┃ ┃ ┃ ┣ 📂service
┃ ┃ ┃ ┃ ┣ 📜AiResult.java
┃ ┃ ┃ ┃ ┗ 📜AiService.java
┃ ┃ ┃ ┣ 📜AiApplication.java
┃ ┃ ┃ ┗ 📜AiController.java
┗ 📂resources
┃ ┣ 📂model
┃ ┃ ┗ 📜ai-model.json
┃ ┗ 📜application.yml
```

<h2 id="started">🚀 Запуск приложения</h2>
1. Первоначально необходимо склонировать репозиторий сервиса с помощью команды:

```bash
git clone https://github.com/pasom-creator/Ai
```
2. Сборка и запуск приложения/запуск тестов
```bash
# Сборка проекта
mvn clean install

# Запуск приложения
mvn spring-boot:run
```
3. Приложение запуститься по адресу http://localhost:8080

<h2 id="routes">📍 API Endpoints</h2>

|           API           |                                 Описание
|:-----------------------:|:-----------------------------------------------------:|
| GET /api/sentiment | Получить оценку AI
