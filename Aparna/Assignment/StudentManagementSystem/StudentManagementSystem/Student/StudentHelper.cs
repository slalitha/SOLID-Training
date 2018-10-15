using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem
{
    public class StudentHelper
    {
        public static void GetStudentDetails(Student[] students)
        {
            for(int i = 0; i < students.Length; i++)
            {
                students[i] = new Student();
                students[i].studentId = i+1;
                Console.Write("Enter student Name of student id {0}:",students[i].studentId);
                students[i].studentName =Convert.ToString( Console.ReadLine());
                Console.Write("Enter no of subjects for student {0}:", students[i].studentId);
                int noOfSubjects = Convert.ToInt32(Console.ReadLine());
                students[i].subjects = new Subject[noOfSubjects];

                SubjectHelper.GetSubjectDetails(students[i].subjects);
            }
        }
    }
}
