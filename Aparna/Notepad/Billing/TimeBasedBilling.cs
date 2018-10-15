

namespace Notepad.Billing
{
    class TimeBasedBilling : BaseBillingWithOffer
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
