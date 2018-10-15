using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    class BillingWithFeature : BaseBillingWithOffer
    {
        public override void applyDiscount(double discountPercentage)
        {
            base.discountPercentage = discountPercentage;
        }
    }
}
