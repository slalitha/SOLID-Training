using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem
{
    public class Subject
    {
        public int subjectId { get; set; } = 0;
        public string subjectName { get; set; } = string.Empty;
        public IMark[] mark = new IMark[3];
        public double subjectScore { get; set; }
    }
}
