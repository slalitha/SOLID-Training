
using System.ComponentModel;

namespace Notepad.ViewModel
{
    public class BaseViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;
        public void FirePropertyChange(string propertyName)
        {
            if(PropertyChanged!=null)
            PropertyChanged.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
