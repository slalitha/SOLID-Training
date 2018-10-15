using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem
{
    public class Student
    {
        public int studentId { get; set; } = 0;
        public string studentName { get; set; } = string.Empty;
        public Subject[] subjects;
        public IMark[] marks;
        public ResultEnum result;
    }
}
