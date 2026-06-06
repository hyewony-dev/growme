# GrowMe - My Activity Tracker

GrowMe is a backend REST API for tracking daily activities and personal growth.

Users can register, log in, view available activities, log completed activities, earn points, check their progress, update activity log notes, and delete activity logs.

## Features

- Register a new user
- Log in and receive a JWT token
- View available activities
- Log a completed activity
- Add activity points to the user's total points
- Calculate the user's level based on total points
- View activity log history
- View total points and current level
- Update an activity log note
- Delete an activity log and subtract its points

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- PostgreSQL
- Aiven
- Gradle
- IntelliJ IDEA
- Talend API Tester

## Database Structure

### app_user

| Field | Description |
|---|---|
| id | Primary key |
| user_name | User name |
| email | User email |
| password_hash | User password |
| total_points | User's total points |
| level | User's current level |

### activity_category

| Field | Description |
|---|---|
| id | Primary key |
| name | Category name |

### activity

| Field | Description |
|---|---|
| id | Primary key |
| name | Activity name |
| description | Activity description |
| category_id | Foreign key to activity_category |
| points | Points earned from the activity |

### activity_log

| Field | Description |
|---|---|
| id | Primary key |
| user_id | Foreign key to app_user |
| activity_id | Foreign key to activity |
| earned_points | Points earned when the activity was logged |
| note | User note |
| logged_at | Date and time when the activity was logged |

## API Endpoints

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/users` | Register a new user | No |
| POST | `/api/login` | Log in and receive a JWT token | Basic Auth |
| GET | `/activities` | Get all available activities | Bearer token |
| POST | `/activity-logs` | Log a completed activity | Bearer token |
| GET | `/users/{userId}/activity-logs` | Get activity log history | Bearer token |
| GET | `/users/{userId}/progress` | Get total points and level | Bearer token |
| PATCH | `/activity-logs/{id}` | Update an activity log note | Bearer token |
| DELETE | `/activity-logs/{id}` | Delete an activity log | Bearer token |

## Example Requests

### Register user

```json
POST /users

{
  "userName": "hyewon",
  "email": "hyewon@test.com",
  "password": "1234"
}
```

### Login

```text
POST /api/login

Authorization: Basic base64(email:password)
```

Example response:

```json
{
  "access_token": "JWT_TOKEN"
}
```

### Log an activity

```json
POST /activity-logs

Authorization: Bearer JWT_TOKEN
Content-Type: application/json

{
  "activityId": 1,
  "note": "I went to the gym today"
}
```

### Update an activity log note

```json
PATCH /activity-logs/{id}

Authorization: Bearer JWT_TOKEN
Content-Type: application/json

{
  "note": "Updated gym note"
}
```

## Setup

1. Clone the repository.

```bash
git clone https://github.com/hyewony-dev/growme.git
```

2. Open the project in IntelliJ IDEA.

3. Create an `application.properties` file inside `src/main/resources`.

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

4. Run the application.

5. Test the endpoints with Talend API Tester or Postman.

## Future Work

- Delete user account and related activity logs
- Create custom activities
- Edit or delete custom activities
- Set custom categories and point values
- View statistics by category
- View growth with a radar chart
- Add badges or achievements
- Improve password security with hashing

## Links

- GitHub: https://github.com/hyewony-dev/growme
- Project documentation: [GrowMe_Hyewon.pdf](GrowMe_Hyewon .pdf)
- Presentation slides: To be added

## Author

Hyewon Yoo
