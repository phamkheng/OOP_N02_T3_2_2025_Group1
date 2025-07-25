import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import Model.Student;
public class TestStudent {
    public static void test() {
        Student s1 = new Student("Hùng", 3.5f);
        Student s2 = new Student("Khang", 2.8f);
        Student s3 = new Student("Khoa", 3.9f);
        if (s3.compareTo(s2) < 0)
        System.out.println(s3.getName() + " has a lower gpa than " + s2.getName());
        Set<Student> studentSet = new TreeSet<>();
        studentSet.add(s1);
        studentSet.add(s2);
        studentSet.add(s3);
        Iterator i = studentSet.iterator();
        while (i.hasNext()) {
            System.out.println(((Student)i.next()).getName());
        }
    }
 }