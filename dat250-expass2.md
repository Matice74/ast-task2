# DAT250 Experiment Assignment 2 – PollApp REST API

## Introduction
The goal of this assignment was to implement a simple REST API for a Poll application using **Spring Boot**.  
The API provides CRUD functionality for `Users`, `Polls`, `VoteOptions`, and `Votes`.  
Special attention was required to handle the cyclic associations in the domain model and to correctly serialize the resources to JSON.

---

## Project Steps and Implementation

### Step 1 – Project Setup
- Installed and configured **Bruno** as HTTP client for testing.
- Created a **new GitHub repository** to keep this week’s work separate from the previous experiment.
- Uploaded the existing Spring Boot application as a clean starting point.

### Step 2 – Domain Model
- Created a new `domain` package and implemented Java Bean classes for:
  - `User`
  - `Poll`
  - `VoteOption`
  - `Vote`
- For now, only the attributes from the UML diagram were included.
- Implemented a `PollManager` class annotated with `@Component` to manage all objects in memory using `HashMap` collections.

### Step 3 – Test Scenarios
- Designed test scenarios following a **test-driven approach**, including:
  1. Create a new user and list all users.
  2. Create another user and list again.
  3. User 1 creates a new poll.
  4. User 2 votes and later updates the vote.
  5. Delete the poll and verify votes are removed.

### Step 4 – Controller Implementation
- Implemented controllers for this resources:
  - `UserController`
  - `PollController`
  - `VoteController`
- Added handlers with `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` as appropriate.


### Step 5 – Automated Testing
- Automated the above test scenarios in **Bruno**, allowing quick execution of the entire sequence.
- Encountered and fixed several issues:
  - Missing `VoteController` initially caused errors.
  - Update functionality was not implemented at first (cause I forogt it).
  - Votes were not automatically removed when a poll was deleted.

After fixing these problems, all tests ran successfully.

### Step 6 – API Documentation (Optional)
- Added the following dependencies to `build.gradle.kts`:
  ```kotlin
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
- Found all of the Controllers on the site
