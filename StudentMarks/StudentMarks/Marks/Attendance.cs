using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    class Attendance : IMarks
    {
        double totalMarks;

        public double GetTotalMarks()
        {
            return totalMarks;
        }

        public bool SetTotalMarks(double marks)
        {
            if (ValidateMarks(marks))
            {
                totalMarks = marks;
                return true;
            }
            else
            {
                Console.WriteLine("Attendance Marks not set as it is not valid!");
                return false;
            }
        }

        public bool ValidateMarks(double marks)
        {
            if (marks > 5 || marks < 0)
            {
                return false;
            }
            return true;
        }
    }
}
