# MIE353 Assignment #2  
**Analyzing the Course Management System**  
**University of Toronto – Fall 2024**  

## 📦 Overview  
This assignment involves extending and analyzing a course management system (CMS) using Java, SQL, and Hibernate/JPA. Students are tasked with modifying the data model, fixing software issues, and writing queries to extract insights from a university's course database.

---

## ⚙️ Setup Instructions  
To ensure consistency across environments, a development container has been provided.

### Prerequisites
- [Docker](https://www.docker.com/)
- [Visual Studio Code](https://code.visualstudio.com/)
- Dev Containers Extension (within VS Code)

### Getting Started
1. Clone the GitHub Classroom repository.
2. Open the folder in VS Code.
3. Reopen in the dev container (you must have Docker running).
4. Use the terminal or built-in tools to run and modify the code.

---

## 📂 Project Structure  
- `src/` - Java source code (uses Hibernate/JPA)
- `submit/` - Submit your `.pdf` write-up, `.csv` data files, and additional deliverables here
- `database.mdb` - MS Access database file
- `hibernate.cfg.xml` - Hibernate configuration file

---

## ✅ Submission Guidelines  
- Submit all modified code via your GitHub Classroom repository.
- Include your `.pdf` with written answers in the `submit/` folder.
- Save data outputs as `.csv` files, also in the `submit/` folder.
- Cite any external code (multi-line) with proper comments above.

---

## 🧪 Grading Criteria  
1. **Functionality & Code Quality**: Original, readable, efficient, and well-documented code.
2. **Write-up**: Clear, accurate answers in the `.pdf`.
3. **Data Results**: Correct and properly formatted `.csv` files.

---

## 📘 Assignment Tasks

### 1. Exploring the CMS  
Familiarize yourself with the existing Java-Hibernate setup.

#### Questions:
- How many classes are in the CMS data model?
- What’s the relationship between `Person`, `Student`, and `Professor`?
- How many relations are in `CrsSystem.mdb`?
- How are those classes mapped in the database?
- Do Professors and Students satisfy disjoint/covering conditions?
- What is the format of the database?
- What is the role of `hibernate.cfg.xml`?
- Which features of the Lombok library are used?

---

### 2. Fixing Software Problems  
Implement support for the missing **Teaches** table by modifying relevant model classes and database mappings.

---

### 3. Extending the Data Model  
Add support for a new class: `TeachingAssistant.java`.

#### Requirements:
- All teaching assistants are students.
- All teaching assistants assist at least one course.
- Add functionality to retrieve and display all TAs in memory.
- Populate the TA table with at least 4 entries using SQL, through `DataUtil.java`.

---

### 4. Writing Queries  
Implement the following SQL-based queries as functions in `DataUtil.java`. Output the results to `.csv` files named `query1.csv`, `query2.csv`, etc.

#### Queries:
1. Highest grade in `mie302h1s`
2. Names of all first-year courses
3. Full names of professors teaching only one course
4. Students who took a course taught by **Patrick Ward**
5. Students who have **never** taken a course taught by **Kelsey Lewis**
6. Is **Jason Martinez’s** average higher than other students who took the same courses?
7. Students who took **Hum.Cent.Syst.Design** and failed at least one other course (grade ≤ 49)

---

## 💡 Notes
- Java and SQL integration is handled via Hibernate/JPA.
- Queries should be testable from the `main()` method.
- Ensure your CSVs include headers and follow the naming convention strictly.

---

## 🔗 References
- [Dev Containers](https://code.visualstudio.com/docs/devcontainers/containers)
- [Hibernate/JPA Guide](https://www.baeldung.com/learn-jpa-hibernate)

---


