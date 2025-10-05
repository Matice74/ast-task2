# Task 4 Project Setup and Test Notes

- Added dependencies for **Hibernate** and **H2 Database** to `build.gradle(.kts)`.  
- Created package `no.hvl.dat250.jpa.polls` and added Java class `PollsTest` with the provided code.  
- Implemented missing methods in `Polls` and `User`.  
- First test run threw some exceptions.  
- Needed to declare **@Id** in every Java Bean.  
- After fixing the above, all tests ran successfully.  
- Added `runtimeOnly("com.h2database:h2:2.3.232")` to `build.gradle.kts` dependencies.  
- Added the following code to the `setUp()` function to list tables:
- Added an extra function that prints all tables in the console.
