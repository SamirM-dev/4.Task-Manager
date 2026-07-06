## Task Manager

REST API, для управления задачами , построенный на Spring Boot.

---

## Описание

Spring Boot приложение,реализующее  CRUD операции над задачами . Поддерживает создание,обновление,получение и удаление задачи,а так же фильтрацию по статусу . Данные хранятся в H2(in-memory) базе данных . 

**Стек:** Java 21 , Spring Boot 3.5 , Spring Data JPA , Lombok , H2 , Spring Actuator

---


## Требования

- Java 21+
- Maven 3.8+ 

---

## Как Запустить?

**Клонировать репозиторий:**
```bash
git clone https://github.com/SamirM-dev/4.Task-Manager.git
cd 4.Task-Manager
```
**Запустить с dev профилем (H2, порт 8080):**
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

**Или собрать и запустить jar:**
```bash
./mvnw package -DskipTests
java -jar target/bootfinal-0.0.1-SNAPSHOT.jar
```

После запуска приложение автоматически создаёт 3 тестовые задачи.

---

## Профили
| Профили | Порт | Бд | Sql логи |
|:-------:|:----:|:--:|:--------:|
|*DEV*|8080| H2 in-memory |Включены|
|*PROD*|8001| H2 in-memory |Выключены|

**H2 Console (только dev):** `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:taskdb`
- Username: `sa`
- Password: *(пусто)*

---

## Эндпоинты

| Метод | Путь | Описание |
|-------|------|----------|
| GET | `/api/tasks` | Получить все задачи |
| GET | `/api/tasks?status=NEW` | Фильтрация по статусу |
| GET | `/api/tasks/{id}` | Получить задачу по id |
| POST | `/api/tasks` | Создать задачу |
| PUT | `/api/tasks/{id}` | Обновить задачу |
| DELETE | `/api/tasks/{id}` | Удалить задачу |

**Статусы задачи:** `NEW`, `IN_PROGRESS`, `DONE`

**Создать задачу (POST /api/tasks):**
```json
{
  "title": "Название задачи",
  "description": "Описание задачи"
}
```

**Обновить задачу (PUT /api/tasks/{id}):**
```json
{
  "title": "Новое название",
  "description": "Новое описание",
  "status": "IN_PROGRESS"
}
```

**Пример ответа:**
```json
{
  "id": 1,
  "title": "Task #1",
  "description": "Description for Task #1",
  "status": "NEW",
  "createdAt": "2024-01-15T10:30:00"
}
```

---

## Обработка Ошибок
Все ошибки возвращаются в едином формате:

```json
{
  "statusCode": 404,
  "error": "NOT FOUND",
  "message": "Задачи с таким id нет!",
  "path": "/api/tasks/99",
  "timestamp": "2024-01-15 10:30:00"
}
```

При ошибках валидации добавляется список `fieldErrors`:
```json
{
  "statusCode": 400,
  "error": "BAD REQUEST",
  "message": "Ошибки валидации",
  "path": "/api/tasks",
  "timestamp": "2024-01-15 10:30:00",
  "fieldErrors": [
    {"field": "title", "message": "Необходимо заполнить имя!"}
  ]
}
```

---


## Мониторинг 
| Эндпоинт | Описание |
|----------|----------|
| `GET /actuator/health` | Состояние приложения |
| `GET /actuator/info` | Информация о приложении |
| `GET /actuator/metrics` | Метрики |

---


## Структура Проекта


```
src/main/java/com/example/bootfinal/
├── controller/       # REST контроллеры
├── service/          # Бизнес-логика
├── repository/       # Работа с БД (Spring Data JPA)
├── entity/           # JPA сущности
├── dtorequest/       # DTO для входящих запросов
├── enums/            # Перечисления (TaskStatus)
├── responsehandler/  # Глобальная обработка ошибок
└── starter/          # CommandLineRunner — тестовые данные
```

