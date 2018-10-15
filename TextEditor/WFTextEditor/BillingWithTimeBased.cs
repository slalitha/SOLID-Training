using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    class BillingWithTimeBased : BaseBillingWithOffer
    {
        bool canApplyDiscount = false;
        public override void applyDiscount(double discountPercentage)
        {
            if (canApplyDiscount)
            {
                base.discountPercentage = discountPercentage;
            }
        }
    }
}
