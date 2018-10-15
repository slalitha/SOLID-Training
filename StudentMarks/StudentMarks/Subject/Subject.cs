using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    class Subject : SubjectMarks
    {
        string subjectName;

        public Subject()
        {
        }

        public Subject(string name)
        {
            subjectName = name;
        }
        public string getSubjectName()
        {
            return subjectName;
        }
        public void setSubjectName (string name)
        {
            subjectName = name;
        }
    }
}
