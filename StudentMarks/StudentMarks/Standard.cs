using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    class Standard
    {
        List<Subject> subjects;
        bool pass;

        public Standard(int numberOfSubjects)
        {
            subjects = new List<Subject>();
            for (int i = 0; i < numberOfSubjects; i++)
            {
                subjects.Add(new Subject());
            }
        }

        public Subject getSubject(int pos)
        {
            return subjects[pos];
        }

        public bool passStatus()
        {
            bool flag = true;
            foreach (Subject subject in subjects)
            {
                flag = flag & subject.passStatus();
            }
            pass = flag;
            return pass;
        }
    }
}
