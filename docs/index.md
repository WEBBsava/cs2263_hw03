
# Courses Application

Small UI application using JavaFx that stores information about courses offered in six departments for CS2263 homework 
3 at Idaho State University.

## Design

### Class Diagram

![Class Diagram](https://github.com/WEBBsava/cs2263_hw03/blob/f74c1f5195c6595311e8d6abb620d72b5890752c/docs/images/ClassDiagram.png)

The class diagram for this application shows the two main classes and how the user can use the application. The two 
classes are: _CourseProcessor_ which contains the UI interface and _Course_ which stores information about a single 
course. Then there are additional classes to help create classes and return (_RetrieveCourses_), for navigation such as 
saving, loading, and exiting (_Navigation_), and a way to list courses (_ListCourses_) based on their choices.

### Simple UI Diagram

![Simple UI Diagram](https://github.com/WEBBsava/cs2263_hw03/blob/f74c1f5195c6595311e8d6abb620d72b5890752c/docs/images/SimpleUI.png)

The UI diagram shows what the user interface can look like. It shows the main UI with options to access courses, 
view all courses, save a course list, load a course list, and exit. The window below it shows a popup for when the user
hasn't filled in all text boxes. Lastly, to the right it shows a popup confirming they want to exit for when they select
the exit button.
