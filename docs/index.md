
# Courses Application

Small UI application using JavaFx that stores information about courses offered in six departments for CS2263 homework 
3 at Idaho State University.

## Design

### Class Diagram

<img alt="Class Diagram" src="D:\cs2263_hw03\docs\images\ClassDiagram.png" title="Class Diagram"/>

The class diagram for this application shows the two main classes and how the user can use the application. The two 
classes are: _CourseProcessor_ which contains the UI interface and _Course_ which stores information about a single 
course. Then there are additional classes to help create classes and return (_RetrieveCourses_), for navigation such as 
saving, loading, and exiting (_Navigation_), and a way to list courses (_ListCourses_) based on their choices.

### Simple UI Diagram

<img alt="UI Diagram" src="D:\cs2263_hw03\docs\images\Simple UI.png" title="Simple UI Diagram"/>

The UI diagram shows what the user interface can look like. It shows the main UI with options to access courses, 
view all courses, save a course list, load a course list, and exit. The window below it shows a popup for when the user
hasn't filled in all text boxes. Lastly, to the right it shows a popup confirming they want to exit for when they select
the exit button.