using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    interface IBilling
    {
        double CalculateBill(double cost, double tax);

        double CalculateCost(double quantity, double rate);
    }
}
