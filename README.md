# tareas-api

API REST de tareas hecha con Java 21, Spring Boot y PostgreSQL.
Proyecto de práctica de CI/CD con GitHub Actions.

## Endpoints

| Método | Ruta | Qué hace |
|---|---|---|
| GET | `/api/tareas` | Lista las tareas |
| POST | `/api/tareas` | Crea una tarea (`{"titulo": "..."}`) |
| PUT | `/api/tareas/{id}/completar` | Marca una tarea como completada |
| DELETE | `/api/tareas/{id}` | Borra una tarea |
| GET | `/actuator/health` | Estado de la aplicación |

## Ejecutar en local

```bash
docker run -d --name tareas-db \
  -e POSTGRES_DB=tareas -e POSTGRES_USER=tareas -e POSTGRES_PASSWORD=tareas \
  -p 5432:5432 postgres:16
./mvnw spring-boot:run
```

La conexión se configura con `DB_URL`, `DB_USER` y `DB_PASSWORD`; sin ellas usa valores de desarrollo local.

## Tests

- `./mvnw test`: tests unitarios, sin base de datos.
- `./mvnw verify`: unitarios y de integración (`*IT`), que necesitan PostgreSQL.
