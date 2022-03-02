package students;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TeacherNotebook {
    private List<Student> students=new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void readFromFile(Path path){
        try(Scanner scanner = new Scanner(path)){
            String[] line;
            Student student;
            while(scanner.hasNext()){
                line= scanner.nextLine().split(";");
                student=new Student(line[0],line[1]);
                for(int i =2;i<line.length;++i){
                    student.addGrade(Integer.parseInt(line[i]));
                }
                students.add(student);
            }
        }catch (IOException e){
            throw new IllegalStateException("Can not read from file",e);
        }
    }

    public List<String> findFailingStudents(){
        List<String> result= new ArrayList<>();
        for (Student student:students) {
            int sum=0;
            int c = 0;
            for(int i:student.getGrades()){
                sum+=i;
                c++;
            }
            if (c==0){
                throw new IllegalArgumentException("No grades found");
            }
            if(2>(double)sum/c){
                result.add(student.getName());
            }
        }
        return result;
    }
}
