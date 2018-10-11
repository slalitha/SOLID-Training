using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    public abstract class BaseBilling : IBilling
    {
        public virtual double CalculateBill(double cost, double tax)
        {
            return cost + (cost * tax);
        }

        public double CalculateCost(double quantity, double rate)
        {
            return quantity * rate;
        }
    }
}
