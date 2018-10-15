

namespace Notepad.Billing
{
    public interface IBilling
    {
       
        double CalculateBill(double cost,double tax);
        double CalculateCost(double quantity,double rate);
    }
}
