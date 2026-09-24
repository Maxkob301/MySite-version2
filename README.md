# MyBlog

Веб-приложение на Spring Boot для ведения блога.

Проект создан как демонстрация навыков Java-разработки: работа с Spring Boot 3, Spring Security, JPA, PostgreSQL, Docker и CI/CD.

## Стек

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Lombok
- Docker
- GitHub Actions (CI/CD)

## Функционал

- Регистрация и авторизация пользователей
- Валидация форм
- Хеширование паролей (BCrypt)
- Роли (USER, ADMIN)
- CRUD для записей блога
- Обработка ошибок через `@ControllerAdvice`
- Кастомные страницы ошибок

## Как запустить

Приложение доступно по адресу: `http://localhost:8080`

### Локально

1. Создай базу данных:

```sql
CREATE DATABASE myblog;
```

2. Настрой `application.properties`.

3. Собери и запусти:

```bash
mvn clean package
java -jar target/*.jar
```

### Через Docker

```bash
docker compose up --build
```

## Docker

### Dockerfile

Многоэтапная сборка:
- Maven собирает `.jar`;
- JRE запускает `.jar`.

### docker-compose.yaml

Поднимает два сервиса:
- `postgres` — база данных;
- `app` — приложение.

### Остановка

```bash
docker compose down
```

## CI/CD

GitHub Actions автоматически:

- собирает проект через Maven;
- запускает тесты (JUnit + Mockito);
- собирает Docker-образ;
- публикует образ в GHCR.

## Автор

Максим Кобец

- GitHub: [@Maxkob301](https://github.com/Maxkob301)
