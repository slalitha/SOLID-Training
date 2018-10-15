

namespace Notepad.Billing
{
    public class BillingWithDonation:Billing
    {
      public double AddDonationCost(double cost,double donationCost)
        {
            return cost + donationCost;
        }
       
    }
}
