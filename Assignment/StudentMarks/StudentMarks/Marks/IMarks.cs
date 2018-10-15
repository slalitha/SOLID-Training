using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentMarks
{
    interface IMarks
    {
        double GetTotalMarks();
        bool SetTotalMarks(double marks);
        bool ValidateMarks(double marks);
    }
}
