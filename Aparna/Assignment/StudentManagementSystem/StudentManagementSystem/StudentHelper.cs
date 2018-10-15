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

        internal static void DisplayResult(Student[] students)
        {
            for(int i = 0; i < students.Length; i++)
            {
                Console.WriteLine("STudent ID:{0}",students[i].studentId);
                Console.WriteLine("STudent Name:{0}", students[i].studentName);
                Console.WriteLine("Result:{0}", students[i].result == ResultEnum.PASS?"Pass":"Fail");
            }
        }

        internal static void CalculateResult(Student[] students)
        {
            for (int i = 0; i < students.Length; i++)
            {
                students[i].result = ResultEnum.PASS;
                for (int j = 0; j < students[i].subjects.Length; j++)
                {
                    if(students[i].subjects[j].subjectScore <= 50)
                    {
                        students[i].result = ResultEnum.FAIL;
                        break;
                    }
                }
            }
            
        }

        internal static void CalculateMarks(Student[] students)
        {
            for(int i = 0; i < students.Length; i++)
            {
                for(int j = 0; j < students[i].subjects.Length; j++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        students[i].subjects[j].subjectScore += students[i].subjects[j].mark[k].CalculateMark();
                    }
                }
            }
        }
    }
}
