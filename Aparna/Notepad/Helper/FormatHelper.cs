using Notepad.ViewModel;
using System.Windows.Input;

namespace Notepad.Helper
{
    public class FormatHelper:BaseViewModel
    {
        private NotepadViewModel notepadViewModel;
        public ICommand TrimCommand { get; set; }
        public ICommand TrimStartCommand { get; set; }
        public ICommand TrimEndCommand { get; set; }
        public ICommand SplitToLinesCommand { get; set; }
        public FormatHelper(NotepadViewModel notepadViewModel)
        {
            this.notepadViewModel = notepadViewModel;
            TrimCommand = new RelayCommand(Trim);
            TrimStartCommand = new RelayCommand(TrimStart);
            TrimEndCommand = new RelayCommand(TrimEnd);
            SplitToLinesCommand = new RelayCommand(SplitToLines);
        }
        private void SplitToLines(object obj)
        {
            string[] temp = notepadViewModel.Text.Split(null);
            string toBeReplaced = string.Empty;
            for (int i = 0; i < temp.Length; i++)
            {
                toBeReplaced += temp[i] + "\n";
            }
            notepadViewModel.Text = toBeReplaced;
        }

        private void TrimEnd(object obj)
        {
            notepadViewModel.Text = notepadViewModel.Text.TrimEnd();
        }

        private void TrimStart(object obj)
        {
            notepadViewModel.Text = notepadViewModel.Text.TrimStart();
        }

        private void Trim(object obj)
        {
            notepadViewModel.Text = notepadViewModel.Text.Trim();
        }
    }
}
