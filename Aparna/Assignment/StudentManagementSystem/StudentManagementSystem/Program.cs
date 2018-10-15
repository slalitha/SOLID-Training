using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter no of students");
            int noOfStudents = Convert.ToInt32(Console.ReadLine());
            Student[] students = new Student[noOfStudents];
            StudentHelper.GetStudentDetails(students);
            StudentHelper.CalculateMarks(students);
            StudentHelper.CalculateResult(students);
            StudentHelper.DisplayResult(students);
            Console.ReadKey();
        }
    }
}
