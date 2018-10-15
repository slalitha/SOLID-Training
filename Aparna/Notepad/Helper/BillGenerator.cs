using Notepad.Billing;
using System;

namespace Notepad.ViewModel
{
    public static class BillGenerator
    {
        
        static long totalBytes = 0;

        public static void UpdateUploadCost(string FilePath)
        {
            long length = new System.IO.FileInfo(FilePath).Length;
            totalBytes += length;
        }

        public static double GenerateBill(TimeSpan totalTime)
        {
            double totalBill = 0;
            totalBill += GetBilling(BillingTypeEnum.TIME, totalTime.TotalHours, 1, 0.1);
            totalBill += GetBilling(BillingTypeEnum.FEATURE, totalBytes / 1024, 1, 0.1);
            totalBill += GetBilling(BillingTypeEnum.DONATION, 1, 1, 0);
            totalBill += GetBilling(BillingTypeEnum.SUBSCRIPTION, 1, 1, 0);
            return totalBill;
        }

        private static double GetBilling(BillingTypeEnum billingType,double quantity,double rate,double tax,double discountPercent = 0)
        {
            IBilling b = BillingFactory.GetBillingProvider(billingType);
            if(b is BaseBillingWithOffer)
            {
                ((BaseBillingWithOffer)b).applyDiscount(discountPercent);
            }
            return b.CalculateBill(b.CalculateCost(quantity,rate),tax);
        }
    }
}
