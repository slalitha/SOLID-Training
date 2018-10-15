using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem.Mark
{
    class ExternalMark:IMark
    {
        private int externalConversionMark = 75;
        public int externalMark { get; set; }

        public ExternalMark()
        {
            Console.WriteLine("Enter external mark out of 100:");
            externalMark = Convert.ToInt32(Console.ReadLine());
        }
        public double CalculateMark()
        {
            return (externalMark * externalConversionMark) / 100;
        }
    }
}
