using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    class Program
    {
        const int No_Of_Subjects = 5;
        static void Main(string[] args)
        {
            //Creating standard
            Standard standard1 = new Standard(No_Of_Subjects);

            //Creating list of subjects
            standard1.getSubject(0).setSubjectName("English");
            standard1.getSubject(1).setSubjectName("Computer Science");
            standard1.getSubject(2).setSubjectName("Maths");
            standard1.getSubject(3).setSubjectName("Physics");
            standard1.getSubject(4).setSubjectName("Chemistry");

            //Creating Student
            Student student1 = new Student("Alisha", standard1);

            //Creating standard
            Standard standard2 = new Standard(No_Of_Subjects);

            //Creating list of subjects
            standard2.getSubject(0).setSubjectName("English");
            standard2.getSubject(1).setSubjectName("Computer Science");
            standard2.getSubject(2).setSubjectName("Maths");
            standard2.getSubject(3).setSubjectName("Physics");
            standard2.getSubject(4).setSubjectName("Chemistry");
            Student student2 = new Student("Bob", standard2);

            student1.setSubjectMarks(0, 20, 70, 5);
            student1.setSubjectMarks(1, 10, 40, 3);
            student1.setSubjectMarks(2, 12, 65, 1);
            student1.setSubjectMarks(3, 25, 69, 2);
            student1.setSubjectMarks(4, 14, 55, 4);

            student2.setSubjectMarks(0, 10, 10, 2);
            student2.setSubjectMarks(1, 20, 20, 3);
            student2.setSubjectMarks(2, 12, 19, 1);
            student2.setSubjectMarks(3, 14, 69, 2);
            student2.setSubjectMarks(4, 14, 55, 4);

            if (student1.passStatus())
            {
                Console.WriteLine("Student 1 passed");
            }
            else
            {
                Console.WriteLine("Student 1 failed");
            }
            if (student2.passStatus())
            {
                Console.WriteLine("Student 2 passed");
            }
            else
            {
                Console.WriteLine("Student 2 failed");
            }
            Console.ReadKey();
        }
    }
}
