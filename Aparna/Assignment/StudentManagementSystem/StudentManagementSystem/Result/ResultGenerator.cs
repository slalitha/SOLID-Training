using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem.Result
{
    public class ResultGenerator
    {
        internal static void DisplayResult(Student[] students)
        {
            for (int i = 0; i < students.Length; i++)
            {
                Console.WriteLine("STudent ID:{0}", students[i].studentId);
                Console.WriteLine("STudent Name:{0}", students[i].studentName);
                Console.WriteLine("Result:{0}", students[i].result == ResultEnum.PASS ? "Pass" : "Fail");
            }
        }

        internal static void CalculateResult(Student[] students)
        {
            for (int i = 0; i < students.Length; i++)
            {
                students[i].result = ResultEnum.PASS;
                for (int j = 0; j < students[i].subjects.Length; j++)
                {
                    if (students[i].subjects[j].subjectScore <= 50)
                    {
                        students[i].result = ResultEnum.FAIL;
                        break;
                    }
                }
            }

        }
    }
}
