

namespace Notepad.Billing
{
    class FeatureBasedBilling : BaseBillingWithOffer
    {
        public override void applyDiscount(double discountPercentage)
        {
            base.discountPercentage = discountPercentage;
        }
    }
}
