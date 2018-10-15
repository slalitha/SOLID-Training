using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace Notepad.ViewModel
{
    public class RelayCommand : ICommand
    {
        Action<object> _executeMethod;
        Func<bool> _canexecuteMethod;

        public RelayCommand(Action<object> executeMethod, Func<bool> canexecuteMethod = null)
        {
            _executeMethod = executeMethod;
            _canexecuteMethod = canexecuteMethod;
        }
        public bool CanExecute(object parameter)
        {
           return _canexecuteMethod == null ? true : _canexecuteMethod();
        }

        public void Execute(object parameter)
        {
            _executeMethod.Invoke(parameter);
        }
        public event EventHandler CanExecuteChanged
        {
            add { CommandManager.RequerySuggested += value; }
            remove { CommandManager.RequerySuggested -= value; }
        }
    }
}
