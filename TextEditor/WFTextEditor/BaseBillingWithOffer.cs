using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    public abstract class BaseBillingWithOffer : BaseBilling
    {
        protected double discountPercentage { get; set; }
        public abstract void applyDiscount(double discountPercentage);

        public override double CalculateBill(double cost, double tax)
        {
            return cost + (cost * tax) - (cost * discountPercentage);
        }

    }
}
