using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem.Mark
{
    public class AttendanceMark : IMark
    {
        private int attendanceConversionMark = 5;
        public int totalDays { get; set; } = 100;
        public int attendedDays { get; set; }
        public AttendanceMark()
        {
            Console.WriteLine("Enter no of attended days out of {0}:",totalDays);
            attendedDays = Convert.ToInt32(Console.ReadLine());
        }
        public double CalculateMark()
        {
            return (attendedDays / totalDays) * attendanceConversionMark;
        }
    }
}
