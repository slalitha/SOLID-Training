

namespace Notepad.Billing
{
    class BillingWithSubscription : Billing
    {
        public double AddSubscriptionCost(double cost, double subscriptionCost)
        {
            return cost + subscriptionCost;
        }
    }
}
