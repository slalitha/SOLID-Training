using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    class Student
    {
        Standard standard;
        string name;

        public Student(string studentName, Standard standardInstance)
        {
            name = studentName;
            standard = standardInstance;
        }

        public void setSubjectMarks(int pos, double iMarks, double eMarks, double aMarks)
        {
            standard.getSubject(pos).SetMarks(iMarks, eMarks, aMarks);
        }

        public bool passStatus()
        {
            return standard.passStatus();
        }
    }
}
