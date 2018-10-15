using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    abstract class SubjectMarks
    {
        Internal internalMarks;
        External externalMarks;
        Attendance attendanceMarks;
        bool pass;
        double totalMarks;

        public SubjectMarks()
        {
            internalMarks = new Internal();
            externalMarks = new External();
            attendanceMarks = new Attendance();
        }

        public bool SetMarks(double i, double e, double a)
        {

            if (internalMarks.SetTotalMarks(i) &&
            externalMarks.SetTotalMarks(e) &&
            attendanceMarks.SetTotalMarks(a))
            {
                return true;
            }
            return false;
        }

        public double getTotalMarks()
        {
            totalMarks = internalMarks.GetTotalMarks() + externalMarks.GetTotalMarks() + attendanceMarks.GetTotalMarks();
            return totalMarks;
        }

        public bool passStatus()
        {
            getTotalMarks();
            if (totalMarks > 50)
            {
                pass = true;
                return pass;
            }
            pass = false;
            return pass;
        }
    }
}
