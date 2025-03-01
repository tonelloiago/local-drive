# LocalDrive

### Project Goal

The primary objective of this project is to explore and implement recent Java features, including virtual threads and records, alongside modern development and deployment practices. This includes utilizing GitHub Actions for CI/CD, Docker and Kubernetes for containerization and orchestration, integrating with third-party services, and examining concurrency in distributed systems.

### Key Concepts Studied

The following key concepts have been studied and implemented in this project:

* **Java Recent Features:**
    * **Virtual Threads:** This project leverages virtual threads to monitor local user folders and Google Drive. When a virtual thread detects a file system change (e.g., creation, modification, deletion) within a monitored folder, it processes the change and initiates synchronization with other monitored folders. This approach enhances concurrency and responsiveness by allowing the application to handle multiple file system events concurrently without blocking operating system threads.
    * **Records**: coming up next
* **CI/CD:**
    * **GitHub Actions**: coming up next
* **Containerization & Orchestration:**
    * **Docker**: coming up next
    * **Kubernetes**: coming up next
* **Integration:**
    * **Third-party service integrations**: coming up next
* **Distributed Systems:**
    * **Concurrency in distributed environments**: coming up next
 

### Phase 1
The initial idea of this project is described below:
![image](https://github.com/user-attachments/assets/e990785e-d45e-4e37-a748-107a99db4119)

1. A virtual thread monitor the local folder waiting for changes.
2. If a change is detected in item 1, another virtual thread starts to update Google Drive equivalent folder.
3. The same that happens in item 1 now it`s applied to Google Drive folder.
4. If a change is detected in item 3, another virtual thread starts to update equivalent local folder.

* **Steps:**
     1. Configurate application using Spring Boot. The application must read application file and create a local folder if doesn`t exists.
     2. Create Watchers base code(virtual threads that will be used to monitor folders)
     3. Configure integration with Google Drive.
     4. Adapt Google Drive Watcher created on item b to integrate with Google Drive.

* **Attention Points:**
   * How to avoid update folder looping?
   * How to identify updated items?

 ### Parallel Topics
 * **Unit Tests:**
    1. Implement pipeline in Github Actions to run tests when a PR is open to main branch.
    2. Create a virtual machine in GCP to run Sonarqube and analyze code quality.
    3. Integrate Github Actions workflow to VM created on item 2.

