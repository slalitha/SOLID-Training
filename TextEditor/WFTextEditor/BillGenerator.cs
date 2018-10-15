using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    class BillGenerator
    {
        public double generateBill(TimeSpan totalTime, double totalUpload)
        {
            double totalBill = 0;


            totalBill += GetBilling("timebased", totalTime.Hours, 1, 10, 12);
            totalBill += GetBilling("feature", totalUpload/1024, 1, 10, 12);
            totalBill += GetBilling("donation", 1, 1, 0);
            totalBill += GetBilling("subscription", 1, 1, 0);

            return totalBill;
        }

        private double GetBilling(string purpose, double quantity, double rate, double tax, double discountPercent = 0)
        {
            IBilling billing = BillingProvider.GetBillingProvider(purpose);
            if (billing is BaseBillingWithOffer)
            {
                ((BaseBillingWithOffer)billing).applyDiscount(discountPercent);
            }

            return billing.CalculateBill(billing.CalculateCost(quantity, rate), tax);

        }

    }
}
