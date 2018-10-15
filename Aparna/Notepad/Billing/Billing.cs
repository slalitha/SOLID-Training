

namespace Notepad.Billing
{
    public abstract class Billing : IBilling
    {

        public virtual double CalculateBill(double cost, double tax)
        {
            return cost * (1 + tax);
        }


        public double CalculateCost(double quantity, double rate)
        {
            return quantity * rate;
        }
    }
}
