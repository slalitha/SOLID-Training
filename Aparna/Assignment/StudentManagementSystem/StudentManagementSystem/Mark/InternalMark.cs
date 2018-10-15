using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem.Mark
{
    class InternalMark : IMark
    {
        private int internalConversionMark = 25;
        public int internalMark { get; set; }
        
        public InternalMark()
        {
            Console.WriteLine("Enter internal mark out of 100:");
            internalMark = Convert.ToInt32(Console.ReadLine());
        }
        public double CalculateMark()
        {
            return (internalMark* internalConversionMark) / 100;
        }
    }
}
