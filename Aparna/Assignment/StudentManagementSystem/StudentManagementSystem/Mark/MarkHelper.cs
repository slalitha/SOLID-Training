using StudentManagementSystem.Mark;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem
{
    public class MarkHelper
    {
        internal static void GetMarkDetails(Subject subject)
        {
            Console.WriteLine("Enter mark details of subject {0}", subject.subjectId);
            subject.mark[0] = MarkFactory.GetMarkProvider(MarkTypeEnum.ATTENDANCE);
            subject.mark[1] = MarkFactory.GetMarkProvider(MarkTypeEnum.INTERNAL);
            subject.mark[2] = MarkFactory.GetMarkProvider(MarkTypeEnum.EXTERNAL);
        }
        internal static void CalculateMarks(Student[] students)
        {
            for (int i = 0; i < students.Length; i++)
            {
                for (int j = 0; j < students[i].subjects.Length; j++)
                {
                    for (int k = 0; k < 3; k++)
                    {
                        students[i].subjects[j].subjectScore += students[i].subjects[j].mark[k].CalculateMark();
                    }
                }
            }
        }
    }
}
