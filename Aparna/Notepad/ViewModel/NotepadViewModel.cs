using GalaSoft.MvvmLight.Command;
using iTextSharp.text;
using iTextSharp.text.pdf;
using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Xml;
using static Notepad.ViewModel.FileTypeEnum;
namespace Notepad.ViewModel
{
    public class NotepadViewModel : BaseViewModel
    {
        #region Commands
        public ICommand NewCommand { get; set; }
        public ICommand SaveCommand { get; set; }
        public ICommand OpenCommand { get; set; }
        public ICommand SaveAsCommand { get; set; }
        public ICommand CloseCommand { get; set; }
        public ICommand CopyCommand { get; set; }
        public ICommand PasteCommand { get; set; }
        public ICommand CutCommand { get; set; }
        public ICommand SelectedTextCommand { get; set; }
        public ICommand SearchCommand { get; set; }
        public ICommand ReplaceCommand { get; set; }
        public ICommand TrimCommand { get; set; }
        public ICommand TrimStartCommand { get; set; }
        public ICommand TrimEndCommand { get; set; }
        public ICommand SplitToLinesCommand { get; set; }
        public ICommand UploadToDriveCommand { get; set; }
        #endregion
        DateTime startDate;
        DateTime endDate;
        float costperhour = 1;
        float costperbyte = 0.0001f;
        long totalBytes =0;
        float totalUploadCost = 0.0f;
        List<int> indexes;
        static int currentIndex = 0;

        public NotepadViewModel()
        {
            startDate = DateTime.Now;
            NewCommand = new RelayCommand(CreateNewFile);
            SaveCommand = new RelayCommand(SaveFile, null);
            OpenCommand = new RelayCommand(OpenFile, null);
            CloseCommand = new RelayCommand<CancelEventArgs>(CloseFile);
            CopyCommand = new RelayCommand(Copy);
            PasteCommand = new RelayCommand(Paste);
            CutCommand = new RelayCommand(Cut);
            SelectedTextCommand = new RelayCommand<RoutedEventArgs>(OnSelectionChanged);
            SearchCommand = new RelayCommand(Search);
            ReplaceCommand = new RelayCommand(Replace);
            SaveAsCommand = new RelayCommand(SaveAs);
            TrimCommand = new RelayCommand(Trim);
            TrimStartCommand = new RelayCommand(TrimStart);
            TrimEndCommand = new RelayCommand(TrimEnd);
            SplitToLinesCommand = new RelayCommand(SplitToLines);
            UploadToDriveCommand = new RelayCommand(UploadToDrive);
        }

        private void UploadToDrive(object obj)
        {
            Drive d = new Drive();
            if (!IsSaved)
            {
                SaveFile();
            }
            d.UploadFile(FilePath,Path.GetExtension(FilePath));
            long length = new System.IO.FileInfo(FilePath).Length;
            totalBytes += length;
            totalUploadCost += length * costperbyte;
        }

        private void CreateNewFile(object obj)
        {            
            if (!IsSaved)
            {
                MessageBoxResult result = MessageBox.Show("Some contents have not been saved. Do you want to save", "Save", MessageBoxButton.YesNo);
                if (result == MessageBoxResult.Yes)
                {
                    SaveFile();
                }
            }
            Text = string.Empty;
            FileName = string.Empty;
        }

        private void SplitToLines(object obj)
        {
            string[] temp = Text.Split(null);
            string toBeReplaced = string.Empty;
            for (int i = 0; i < temp.Length; i++)
            {
                toBeReplaced += temp[i] + "\n";
            }
            Text = toBeReplaced;
        }

        private void TrimEnd(object obj)
        {
            Text = Text.TrimEnd();
        }

        private void TrimStart(object obj)
        {
            Text = Text.TrimStart();
        }

        private void Trim(object obj)
        {
            Text = Text.Trim();
        }

        #region Methods
        private void SaveAs(object obj)
        {
            FileName = string.Empty;
            SaveFile(obj);
        }

        private void Replace(object obj)
        {
            if (String.IsNullOrEmpty(ReplaceText) || String.IsNullOrEmpty(SearchText) || !Text.Contains(SearchText))
                return;
            Search(obj);
            if (currentIndex == 0)
                Text = Text.Remove(indexes[indexes.Count - 1], SearchText.Length).Insert(indexes[indexes.Count - 1], ReplaceText);
            else
                Text = Text.Remove(indexes[currentIndex - 1], SearchText.Length).Insert(indexes[currentIndex - 1], ReplaceText);

        }

        private void Search(object obj)
        {
            var textBox = obj as TextBox;
            if (String.IsNullOrEmpty(Text) || String.IsNullOrEmpty(SearchText))
                return;
            indexes = AllIndexesOf(Text, SearchText);
            if (indexes.Count == 0)
            {
                MessageBox.Show("Cannot find " + SearchText);
                return;
            }
            if (currentIndex == indexes.Count)
            {
                MessageBox.Show("Find reached starting point of search");
                currentIndex = 0;
            }
            textBox.Select(indexes[currentIndex++], SearchText.Length);
        }

        public static List<int> AllIndexesOf(string str, string value)
        {
            List<int> indexes = new List<int>();
            for (int index = 0; ; index += value.Length)
            {
                index = str.IndexOf(value, index);
                if (index == -1)
                    return indexes;
                indexes.Add(index);
            }
        }

        private void Cut(object obj)
        {
            Clipboard.SetText(SelectedText);
            Text = Text.Remove(SelectionStart, SelectionEnd);
        }

        private void Paste(object obj)
        {
            Text = Text.Insert(SelectionStart, Clipboard.GetText());
        }

        private void OnSelectionChanged(RoutedEventArgs e)
        {
            var textBox = e.OriginalSource as TextBox;
            SelectedText = textBox.SelectedText;
            SelectionStart = textBox.SelectionStart;
            SelectionEnd = textBox.SelectionLength;
        }

        private void Copy(object obj)
        {
            Clipboard.SetText(SelectedText);
        }


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
                        SaveFile();
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
                CalculateBill();
                e.Cancel = false;
            }
            else
            {

                e.Cancel = true;
            }
        }

        private void CalculateBill()
        {
            TimeSpan timeSpan = endDate - startDate;
            var hours = timeSpan.TotalHours;
            var cost = hours * costperhour;
            string displayText = $"Hours Used : {hours} hrs\n" +
                                 $"Cost : {cost} $\n";
            if (totalUploadCost != 0.0)
                displayText +=  $"Total Bytes Uploaded : {totalBytes * 0.001} KB\n" +
                                $"Upload Cost : {totalUploadCost} $\n" +
                                $"Total Cost : {cost + totalUploadCost} $";
            MessageBox.Show(displayText, "BILL");
        }

        private void OpenFile(object obj)
        {
            var openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Text file (*.txt)|*.txt";
            if (openFileDialog.ShowDialog() == true)
            {
                FileName = openFileDialog.FileName;
                if (File.Exists(FileName))
                    Text = File.ReadAllText(openFileDialog.FileName);
            }
        }

        private void SaveFile(object obj = null)
        {
            if (String.IsNullOrEmpty(FileName))
            {
                var saveFileDialog = new SaveFileDialog();
                saveFileDialog.Filter = "Text file (*.txt)|*.txt|Pdf File (*.pdf)|*.pdf|HTML file (*.html,*.htm))|*.html,*.htm|XML File (*.xml)|*.xml";
                if (saveFileDialog.ShowDialog() == true)
                {
                    FileName = saveFileDialog.FileName;
                    FilePath = Path.GetFullPath(FileName);
                    if (saveFileDialog.FilterIndex == 2)
                    {
                        ConvertToPDF();
                        return;
                    }
                    if (saveFileDialog.FilterIndex == 4)
                    {
                        ConvertToXml();
                        return;
                    }
                }

            }
            if (!IsSaved && !String.IsNullOrEmpty(FileName))
                File.WriteAllText(FileName, Text);

        }

        private void ConvertToXml()
        {
            XmlDocument xmlDocument = new XmlDocument();
            try
            {
                xmlDocument.Load(Text);
                xmlDocument.Save(FileName);
            }
            catch (Exception e)
            {
                MessageBox.Show("Unable to convert" + e.Message);
            }
        }

        private void ConvertToPDF()
        {
            Document document = new Document();
            PdfWriter.GetInstance(document, new FileStream(FileName, FileMode.Create));
            document.Open();
            document.Add(new iTextSharp.text.Paragraph(Text));
            document.Close();
        }
        #endregion

        #region Properties
        string _text;
        public string Text
        {
            get { return _text; }
            set { _text = value; FirePropertyChange(nameof(Text)); }
        }

        string _searchText;
        public string SearchText {
            get { return _searchText; }
            set { _searchText = value; FirePropertyChange(nameof(SearchText)); }
        }

        string _replaceText;
        public string ReplaceText
        {
            get { return _replaceText; }
            set { _replaceText = value; FirePropertyChange(nameof(ReplaceText)); }
        }

        public string FilePath { get; set; } = string.Empty;
        public string FileName { get; set; } = string.Empty;
        public string SelectedText { get; set; }
        public int SelectionStart { get; private set; }
        public int SelectionEnd { get; private set; }
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

