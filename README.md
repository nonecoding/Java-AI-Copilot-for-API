
---

## 📄 README.md (English)

```markdown
# 🚀 Java AI Copilot for API

An AI-powered Java API auto-generator based on OpenAI GPT API. It generates complete Java Spring Boot APIs, entities, services, controllers, unit tests, and API documentation — eliminating tedious CRUD work and boosting your productivity!

---

## 📦 Features

- 📖 Generate full Java API code by providing entity name and fields
- 🤖 Integrated OpenAI GPT model for intelligent code generation
- 💻 Frontend-console built with React + Vite + Tailwind + Shadcn UI
- 📡 One-click deployment with Docker Compose
- 📚 Supports API code, unit tests, API documentation generation
- ⚙️ Clean RESTful API design

---

## 🖥️ Project Structure

```
Java AI Copilot for API
├── backend (Spring Boot + OpenAI integration)
├── frontend (React + Vite web console)
└── docker-compose.yml (one-click deployment)
```

---

## 📦 Tech Stack

| Module      | Tech                        |
|-------------|-----------------------------|
| Backend     | Java 21 / Spring Boot 3 / OpenAI API |
| Frontend    | React 18 / Vite / Tailwind / Shadcn UI |
| Deployment  | Docker Compose               |

---

## 🚀 Getting Started

### 1️⃣ Clone the Project

```bash
git clone https://github.com/nonecoding/Java-AI-Copilot-for-API.git
cd Java-AI-Copilot-for-API
```

### 2️⃣ Configure OpenAI API Key

Update your `docker-compose.yml`:

```yml
environment:
  - OPENAI_API_KEY=your_api_key_here
```

Or set an environment variable:

```bash
export OPENAI_API_KEY=your_api_key_here
```

### 3️⃣ Run the Project

```bash
docker compose up --build
```

- Frontend: http://localhost:3000  
- Backend: http://localhost:8080  

---

## 📌 Example API Request

**Endpoint:**

```
POST /api/codegen/generate?entityName=User&fields=id:Long,name:String
```

**Response Example:**

```java
@RestController
@RequestMapping("/user")
public class UserController {
    // ...
}
```

---

## 📊 Web Console

A modern web-based console built with React + Vite + Tailwind + Shadcn UI.  
Simply input your entity name and fields, then click **"Generate API"** to instantly generate full Java API code.

---

## 🌈 Contributing

Contributions, issues and feature requests are welcome!

1. Fork this repository
2. Create a new branch: `feature/your-feature`
3. Commit your changes
4. Push to the branch
5. Submit a Pull Request

---

## 📄 License

MIT License

---

## ✨ Author

Project by [nonecoding](https://github.com/nonecoding)

If you like this project, please consider giving it a ⭐️ Star!
```

---


