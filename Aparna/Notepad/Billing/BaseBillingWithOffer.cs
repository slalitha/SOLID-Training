

namespace Notepad.Billing
{
    abstract class BaseBillingWithOffer : Billing
    {
        protected double discountPercentage { get; set; }
        public abstract void applyDiscount(double discountPercentage);

        public override double CalculateBill(double cost, double tax)
        {
            return cost + (cost * tax) - (cost * discountPercentage);
        }
    }
}
