using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem
{
    public static class SubjectHelper
    {
        public static void GetSubjectDetails(Subject[] subjects)
        {
            for(int i = 0; i < subjects.Length; i++)
            {
                subjects[i] = new Subject();
                subjects[i].subjectId = i + 1;
                Console.Write("Enter subject name of subject id{0}:",subjects[i].subjectId);
                subjects[i].subjectName = Convert.ToString(Console.ReadLine());
                MarkHelper.GetMarkDetails(subjects[i]);
            }
        }
    }
}
