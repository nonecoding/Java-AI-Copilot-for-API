# AI Agent Project

This is an open-source project based on **Java** and a **Multi-Agent AI** architecture, designed to provide developers with a flexible, highly extensible intelligent code generation and workflow orchestration platform.

## Core Concepts
- **Multi-Agent Architecture**: The system is divided into lightweight agents, each responsible for a single task (e.g., LLM integration, business logic execution, data fetching, result formatting), collaborating via an **asynchronous message bus**.
- **Web-Driven Interface**: A front-end dashboard allows visual process configuration and monitoring, while the back end exposes unified REST APIs to define, execute, and track workflows in real time.
- **Plugin-Based Extensibility**: Agents are registered as plugins. Implement a common interface to add new capabilities such as novel models, data sources, or post-processing logic.

## Features
1. **Workflow Orchestration**: Define business requirements as a DAG (Directed Acyclic Graph) and automatically schedule the execution of each node in sequence.
2. **Dynamic Agent Management**: Start, stop, or scale agents on the fly according to configuration to handle high concurrency.
3. **LLM Integration**: Support multiple models (OpenAI, local LLMs like llama.cpp) through a unified interface.
4. **Real-Time Monitoring**: Visualize task logs, agent execution times, and queue statuses on the dashboard.
5. **Security and Access Control**: Built-in token-based authentication and RBAC ensure secure inter-service communication.

## Project Structure
```
root
├── backend                # Spring Boot backend service
│   ├── src/main/java/...
│   ├── src/main/resources/application.yml
│   └── Dockerfile
├── orchestrator           # Workflow orchestration service
├── agents                 # Agent plugin implementations
│   ├── llm-agent          # LLM integration agent
│   ├── tool-agent         # External tool orchestration agent
│   └── ...
├── frontend               # Web dashboard (React + Ant Design)
├── docker-compose.yml     # One-step deployment configuration
└── README.md              # This file
```

## Quick Start

1. **Clone the Repository**
   ```bash
   git clone https://github.com/nonecoding/AI-Agent-Platform.git
   cd AI-Agent-Platform
   ```

2. **Set Environment Variables**
   ```bash
   export OPENAI_API_KEY=your_openai_api_key
   ```

3. **Launch with Docker Compose**
   ```bash
   docker-compose up --build -d
   ```
   - Front end: http://localhost:3000
   - Back end API: http://localhost:8080

4. **Open the Dashboard**
   Navigate to http://localhost:3000 in your browser to configure and run workflows using the visual editor.

## Usage Example

1. **Create a Workflow**: Drag and drop nodes in the front end to connect **User Input → LLM Generation → Formatting**.
2. **Execute**: Click the **Run** button to start, and view live logs and results.
3. **Download Outputs**: After completion, download generated code as a ZIP file or export a report document.

## Extending the Platform

- **Add a New Agent**: Create a module under `agents/`, implement the `AgentInterface`, and register it in `agent-registration.yml`.
- **Configure the Orchestrator**: Define workflow nodes and dependencies in YAML files under `orchestrator/config/flows/`.
- **Customize Front-End Components**: Modify or add views in `frontend/src/components/` to support new features.

## Architecture Diagram

![Architecture Diagram](docs/architecture.png)

## Community & Contribution

Contributions are welcome! Join the conversation:
- GitHub Issues: https://github.com/nonecoding/AI-Agent-Platform/issues
- QQ Group: 123456789

## License

This project is licensed under the **Apache 2.0** License. See [LICENSE](LICENSE) for details.
