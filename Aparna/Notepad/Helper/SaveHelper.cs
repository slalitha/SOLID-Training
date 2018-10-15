
using Microsoft.Win32;
using Notepad.Saving;
using System;
using System.IO;
using System.Windows;
using System.Windows.Input;
namespace Notepad.ViewModel
{
    public class SaveHelper
    {
        private NotepadViewModel notepadViewModel;
        public ICommand SaveCommand { get; set; }
        public ICommand SaveAsCommand { get; set; }
        public ICommand UploadToDriveCommand { get; set; }


        public SaveHelper(NotepadViewModel notepadViewModel)
        {
            this.notepadViewModel = notepadViewModel;
            SaveCommand = new RelayCommand(SaveFile, null);
            SaveAsCommand = new RelayCommand(SaveAs);
            UploadToDriveCommand = new RelayCommand(UploadToDrive);
        }
        private void SaveAs(object obj)
        {
            notepadViewModel.FileName = string.Empty;
            SaveFile(obj);
        }
        public void SaveFile(object obj = null)
        {
            //Save for the first time Opens a dialog
            if (String.IsNullOrEmpty(notepadViewModel.FileName))
            {
                try
                {
                    ISaver saver =null;
                    var saveFileDialog = new SaveFileDialog();
                    saveFileDialog.Filter = $"{ FileTypeConstant.TEXT}|{FileTypeConstant.PDF}|{FileTypeConstant.HTML}|{FileTypeConstant.XML}";
                    if (saveFileDialog.ShowDialog() == true)
                    {
                        SetFileNameAndFilePath(saveFileDialog.FileName);
                        saver  = SaveFactory.GetSaveProvider(saveFileDialog.FilterIndex, notepadViewModel.FileName, notepadViewModel.Text);
                        if (saver != null)
                        {
                            saver.Save();
                            return;
                        }
                    }
                }
                catch (Exception e)
                {
                    MessageBox.Show(e.Message);
                }

            }
            //Save Every time
            if (!notepadViewModel.IsSaved && !String.IsNullOrEmpty(notepadViewModel.FileName))
                File.WriteAllText(notepadViewModel.FileName, notepadViewModel.Text);

        }

        private void SetFileNameAndFilePath(string fileName)
        {
            if (fileName != null)
            {
                notepadViewModel.FileName = fileName;
                notepadViewModel.FilePath = Path.GetFullPath(fileName);
            }
        }
        private void UploadToDrive(object obj)
        {
            if (!notepadViewModel.IsSaved)
            {
                SaveFile();
            }
            ISaver saver = new SaveToGoogleDrive(notepadViewModel.FilePath);
            saver.Save();
            BillGenerator.UpdateUploadCost(notepadViewModel.FilePath);
        }
    }
}
