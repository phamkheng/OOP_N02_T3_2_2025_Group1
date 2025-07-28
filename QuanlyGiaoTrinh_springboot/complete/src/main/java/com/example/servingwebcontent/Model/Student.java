package com.example.servingwebcontent.Model;

public class Student implements Comparable<Student> {
    private String name;
    private float gpa;

    public Student(String name, float gpa) {
        this.name = name;
        this.gpa  = gpa;
    }

    public Student() {}


    public int compareTo(Student other) {
      
            if (other.gpa < this.gpa)
                return 1;
            else if (other.gpa > this.gpa)
                return -1;
            else
                return 0;
        }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getGpa() { return gpa; }
    public void setGpa(float gpa) { this.gpa = gpa; }
    }  
    