using GalaSoft.MvvmLight.Command;
using Notepad.Helper;
using System;
using System.ComponentModel;
using System.IO;
using System.Windows;
using System.Windows.Input;

namespace Notepad.ViewModel
{
    public class NotepadViewModel : BaseViewModel
    {
        #region Commands
        public ICommand NewCommand { get; set; }
        public ICommand OpenCommand { get; set; }
        public ICommand CloseCommand { get; set; }

        public FormatHelper FormatHelper { get;  set; }
        public EditHelper EditHelper { get;  set; }
        public SaveHelper SaveHelper { get;  set; }
        
        #endregion

        DateTime startDate;
        DateTime endDate;
        

        public NotepadViewModel()
        {
            startDate = DateTime.Now;
            NewCommand = new RelayCommand(CreateNewFile);
            OpenCommand = new RelayCommand(OpenFile, null);
            CloseCommand = new RelayCommand<CancelEventArgs>(CloseFile);
            
            FormatHelper = new FormatHelper(this);
            EditHelper = new EditHelper(this);
            SaveHelper = new SaveHelper(this);
        }
        private void CreateNewFile(object obj)
        {
            if (!IsSaved)
            {
                MessageBoxResult result = MessageBox.Show("Some contents have not been saved. Do you want to save", "Save", MessageBoxButton.YesNo);
                if (result == MessageBoxResult.Yes)
                {
                    SaveHelper.SaveFile();
                }
            }
            Text = string.Empty;
            FileName = string.Empty;
        }

        #region Methods
        


        public bool CanClose()
        {
            bool canclose = false;
            if (String.IsNullOrEmpty(Text))
            {
                canclose = true;
                return true;
            }
            MessageBoxResult result = MessageBox.Show("Are you sure you want to close", "Close", MessageBoxButton.YesNo);
            if (result == MessageBoxResult.Yes)
            {
                if (!IsSaved)
                {
                    result = MessageBox.Show("Some contents have not been saved. Do you want to save", "Save", MessageBoxButton.YesNo);
                    if (result == MessageBoxResult.Yes)
                    {
                        SaveHelper.SaveFile();
                    }
                    else
                    {
                        canclose = false;
                    }
                }
                canclose = true;
            }
            else
            {
                canclose = false;
            }
            return canclose;

        }

        public void CloseFile(CancelEventArgs e)
        {
            if (CanClose())
            {
                endDate = DateTime.Now;
                double bill =BillGenerator.GenerateBill(endDate - startDate);
                MessageBox.Show(String.Format("Total Bill:{0:0.00} $",bill));
                e.Cancel = false;
            }
            else
            {

                e.Cancel = true;
            }
        }

        private OpenHelper OpenHelper;
        private void OpenFile(object obj)
        {
            OpenHelper = new OpenHelper(this);
            OpenHelper.OpenFile(obj);
        }
        
        #endregion
        #region Properties
      
        string _text = string.Empty;
        public string Text
        {
            get { return _text; }
            set { _text = value; FirePropertyChange(nameof(Text)); }
        }

        

        public string FilePath { get; set; } = string.Empty;
        public string FileName { get; set; } = string.Empty;
        public bool IsSaved
        {
            get {
                if (String.IsNullOrEmpty(FileName) || !File.Exists(FileName) || !File.ReadAllText(FileName).Equals(Text))
                    return false;
                return true;
            }
        }
        #endregion
    }
}

