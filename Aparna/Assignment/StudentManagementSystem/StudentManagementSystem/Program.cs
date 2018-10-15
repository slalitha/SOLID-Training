using StudentManagementSystem.Result;
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
            MarkHelper.CalculateMarks(students);
            ResultGenerator.CalculateResult(students);
            ResultGenerator.DisplayResult(students);
            Console.ReadKey();
        }
    }
}
