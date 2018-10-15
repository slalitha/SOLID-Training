using Microsoft.Win32;
using System.IO;

namespace Notepad.ViewModel
{
    public class OpenHelper:BaseViewModel
    {
        NotepadViewModel notepadViewModel;
        public OpenHelper(NotepadViewModel notepadViewModel)
        {
            this.notepadViewModel = notepadViewModel;
        }
        public void OpenFile(object obj)
        {
            var openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = FileTypeConstant.TEXT;
            if (openFileDialog.ShowDialog() == true)
            {
                notepadViewModel.FileName = openFileDialog.FileName;
                notepadViewModel.FilePath = Path.GetFullPath(notepadViewModel.FileName);
                if (File.Exists(notepadViewModel.FileName))
                    notepadViewModel.Text = File.ReadAllText(openFileDialog.FileName);
            }
        }
    }
}
