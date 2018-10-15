

namespace Notepad.Billing
{
    class BillingFactory
    {
        
        public static IBilling GetBillingProvider(BillingTypeEnum billingType)
        {
            switch (billingType)
            {
                case BillingTypeEnum.TIME:
                    return new TimeBasedBilling();
                case BillingTypeEnum.FEATURE:
                    return new FeatureBasedBilling();
                case BillingTypeEnum.DONATION:
                    return new BillingWithDonation();
                case BillingTypeEnum.SUBSCRIPTION:
                    return new BillingWithSubscription();
                default: return null;
            }
        }
    }
}
