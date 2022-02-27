/*
 * MIT License
 *
 * Copyright (c) 2022 Savannah Webb
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cs2263_hw03;

import java.util.Objects;

/**
 * creates and hold a course for CourseProcessor
 * @author Savannah Webb
 */

public class Course {
    private String department;
    private String deptCode;
    private String courseName;
    private String courseNumber;
    private String courseCredits;

    /**
     * Accepts data given from CourseProcessor and returns the information.
     * @param department the department chosen by the user
     * @param deptCode automatically inputted by using courseCode
     * @param courseName the name the user entered
     * @param courseNumber the course number the user entered
     * @param courseCredits the number of credits the user entered
     */
    public Course(String department, String deptCode, String courseName, String courseNumber, String courseCredits) {
        this.department = department;
        this.deptCode = deptCode;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseCredits = courseCredits;
    }

    /**
     * ArrayList of all the departments for the combobox
     * @return the available departments
     */
    public static String[] getDepartmentsArray() {
        String[] departments = {"Biology", "Chemistry", "Computer Science",
                "Electrical Engineering", "Mathematics", "Physics"};

        return departments;
    }

    /**
     * Matches the course department selected to its course code
     * @param deptCode the department given by the user
     * @return the appropriate code for the department given
     */
    public static String courseCode(String deptCode) {
        String course;
        if (Objects.equals(deptCode, "Computer Science")) {
            course = "CS";
        } else if (Objects.equals(deptCode, "Mathematics")) {
            course = "MATH";
        } else if (Objects.equals(deptCode, "Chemistry")) {
            course = "CHEM";
        } else if (Objects.equals(deptCode, "Physics")) {
            course = "PHYS";
        } else if (Objects.equals(deptCode, "Biology")) {
            course = "BIOL";
        } else if (Objects.equals(deptCode, "Electrical Engineering")) {
            course = "EE";
        } else {
            course = "Course Not Found!";
        }

        return course;
    }
}
