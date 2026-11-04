# Task Tracker CLI

A simple command-line task tracker built with Java. It allows users to create, update, delete, and manage tasks directly from the terminal.

Tasks are stored locally in a JSON file using Jackson.

## Features

- Add new tasks with automatically generated IDs.
- Update task descriptions.
- Delete tasks by ID.
- List all tasks or filter them by status.
- Mark tasks as `in-progress` or `done`.
- Track task creation and update timestamps.
- Persist tasks between application runs using JSON.

## Technologies

- Java 21
- Maven
- Jackson (JSON serialization and deserialization)
- Windows Batch Script

## Project Structure

```text
Task-Tracker/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Main.java
│       │   └── tasks/
│       │       ├── TaskManager.java
│       │       └── TaskStatus.java
│       └── resources/
├── tasks.json
├── pom.xml
├── task-cli.bat
├── README.md
└── .gitignore
```

## Requirements

- Java 21 or newer.
- Windows (for the provided `.bat` launcher).

Maven is used to manage dependencies and build the executable JAR. You can run Maven goals directly through IntelliJ IDEA without installing Maven separately.

## Running the application

### Option 1: Windows CLI

The project includes a Windows batch launcher called `task-cli.bat`.

First, build the application using IntelliJ:

1. Open the project in IntelliJ IDEA.
2. Open the Maven panel.
3. Expand `Lifecycle`.
4. Run `clean`, followed by `package`.

This generates the executable JAR inside the `target` directory.

Open PowerShell in the project root and execute:

```powershell
.\task-cli.bat add "Buy groceries"
```

You can now use the CLI commands described below.

**Note:** Run the launcher from the project root so the application finds `tasks.json` in the expected location.

### Option 2: Run directly from IntelliJ IDEA

You can also run the application without building the JAR.

1. Open `Main.java`.
2. Select **Run → Edit Configurations**.
3. Create an Application configuration.
4. Set `Main` as the Main class.
5. Set the working directory to the project root.
6. Enter your desired command in Program arguments.

For example:

```text
add "Buy groceries"
```

Click Run to execute the command.

### Option 3: Run the JAR manually

After building the application, you can also execute it directly:

```powershell
java -jar target/task-tracker-1.0-SNAPSHOT.jar add "Buy groceries"
```

## Available Commands

| Command | Description |
|---|---|
| `add "description"` | Create a new task |
| `update <id> "description"` | Update a task description |
| `delete <id>` | Delete a task |
| `list` | Display all tasks |
| `list todo` | Display TODO tasks |
| `list in-progress` | Display tasks in progress |
| `list done` | Display completed tasks |
| `mark-in-progress <id>` | Change task status to in-progress |
| `mark-done <id>` | Mark a task as done |

### Examples

Add a task:

```powershell
.\task-cli.bat add "Learn Java"
```

List all tasks:

```powershell
.\task-cli.bat list
```

Update a task:

```powershell
.\task-cli.bat update 1 "Learn Java and Maven"
```

Mark a task as completed:

```powershell
.\task-cli.bat mark-done 1
```

Delete a task:

```powershell
.\task-cli.bat delete 1
```

## Data Storage

All tasks are stored in `tasks.json` in the application's working directory.

The application initializes the JSON file with an empty array (`[]`) if the file does not exist.

Tasks are persisted between application runs, so existing tasks are preserved when new tasks are added or updated.


*This project was developed as a Java learning exercise to practise object-oriented programming, collections, file handling, JSON processing, and command-line applications.*

*RoadMap.sh URL - https://roadmap.sh/projects/task-tracker*