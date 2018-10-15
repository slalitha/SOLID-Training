using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WFTextEditor
{
    class BillingProvider
    {
        public static IBilling GetBillingProvider(string purpose)
        {
            if (purpose == "subscription")
                return new BillingWithSubscription();
            else if (purpose == "donation")
                return new BillingWithDonation();
            else if (purpose == "feature")
                return new BillingWithFeature();
            else if (purpose == "timebased")
                return new BillingWithTimeBased();
            return null;
        }
    }
}
