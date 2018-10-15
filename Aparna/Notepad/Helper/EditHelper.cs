using GalaSoft.MvvmLight.Command;
using Notepad.ViewModel;
using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace Notepad.Helper
{
    public class EditHelper:BaseViewModel
    {
        private NotepadViewModel notepadViewModel;
        public string SelectedText { get; set; }
        string _searchText;
        public string SearchText
        {
            get { return _searchText; }
            set { _searchText = value; FirePropertyChange(nameof(SearchText)); }
        }

        string _replaceText;
        public string ReplaceText
        {
            get { return _replaceText; }
            set { _replaceText = value; FirePropertyChange(nameof(ReplaceText)); }
        }
        List<int> indexes;
        static int currentIndex = 0;
        public int SelectionStart { get; private set; }
        public int SelectionEnd { get; private set; }
        public ICommand CopyCommand { get; set; }
        public ICommand PasteCommand { get; set; }
        public ICommand CutCommand { get; set; }
        public ICommand SelectedTextCommand { get; set; }
        public ICommand SearchCommand { get; set; }
        public ICommand ReplaceCommand { get; set; }
        public EditHelper(NotepadViewModel notepadViewModel)
        {
            this.notepadViewModel = notepadViewModel;
            CopyCommand = new ViewModel.RelayCommand(Copy, CanCopyOrCut);
            PasteCommand = new ViewModel.RelayCommand(Paste, CanPaste);
            CutCommand = new ViewModel.RelayCommand(Cut, CanCopyOrCut);
            SelectedTextCommand = new RelayCommand<RoutedEventArgs>(OnSelectionChanged);
            SearchCommand = new ViewModel.RelayCommand(Search);
            ReplaceCommand = new ViewModel.RelayCommand(Replace);
        }
        private bool CanPaste()
        {
            return Clipboard.GetText() != null ? true : false;
        }

        private bool CanCopyOrCut()
        {
            return SelectedText != String.Empty ? true : false;
        }

        private void Cut(object obj)
        {
            Clipboard.SetText(SelectedText);
            notepadViewModel.Text = notepadViewModel.Text.Remove(SelectionStart, SelectionEnd);
        }

        private void Paste(object obj)
        {
            notepadViewModel.Text = notepadViewModel.Text.Insert(SelectionStart, Clipboard.GetText());
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

        private void Replace(object obj)
        {
            if (String.IsNullOrEmpty(ReplaceText) || String.IsNullOrEmpty(SearchText) || !notepadViewModel.Text.Contains(SearchText))
                return;
            Search(obj);
            if (currentIndex == 0)
                notepadViewModel.Text = notepadViewModel.Text.Remove(indexes[indexes.Count - 1], SearchText.Length).Insert(indexes[indexes.Count - 1], ReplaceText);
            else
                notepadViewModel.Text = notepadViewModel.Text.Remove(indexes[currentIndex - 1], SearchText.Length).Insert(indexes[currentIndex - 1], ReplaceText);

        }

        private void Search(object obj)
        {
            var textBox = obj as TextBox;
            if (String.IsNullOrEmpty(notepadViewModel.Text) || String.IsNullOrEmpty(SearchText))
                return;
            indexes = AllIndexesOf(notepadViewModel.Text, SearchText);
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
    }
}
