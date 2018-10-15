using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WFTextEditor
{
    /*public interface IBilling
    {
        double CalculateBillWithAdditionalCost(double cost, double additionalCost);
        double CalculateBillWithPercent(double cost, double percentValue, bool type);

    }*/
    class BillingAmt 
    {
        

        private double costPerUnit;
        private DateTime startTime;
        private DateTime endTime;
        private long bytesSize;

        public BillingAmt(DateTime start, DateTime end, double unitCost)
        {
            startTime = start;
            endTime = end;
            costPerUnit = unitCost;
        }
        public BillingAmt(long bytes, double unitCost)
        {
            bytesSize = bytes;
            costPerUnit = unitCost;
        }

        public double TimeBill()
        {
            TimeSpan UsedTime = endTime - startTime;
            double TimeCost = Math.Round(UsedTime.TotalHours * costPerUnit, 3);
            return TimeCost;
            //MessageBox.Show("Application use time : " + UsedTime.Minutes + " minutes\nCost of usage : $" + Math.Round(UsedTime.TotalHours*costPerUnit, 3));
        }

        public double UploadBill()
        {
            double SizeInKB = (double)bytesSize / 1024;
            double UploadCost = Math.Round(SizeInKB * costPerUnit, 3);
            return UploadCost;
            //MessageBox.Show("Total upload size : " + SizeInKB + " KB\nCost of usage : $" + Math.Round(SizeInKB*costPerUnit, 3));
        }

        /*double IBilling.CalculateBillWithAdditionalCost(double cost, double additionalCost)
        {
            return cost + additionalCost;
        }

        double IBilling.CalculateBillWithPercent(double cost, double percentValue, bool type)
        {
            if (type)
            {
                return cost * (1 + percentValue);
            }
            return cost * (1 - percentValue);
        }*/
        //ADD discount - without gsd
        //ADD donation cost - no gsd and offer
        //ADD subscription cost - with gsd
    }
}
