using Google.Apis.Drive.v3;
using Google.Apis.Drive.v3.Data;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Services;
using Google.Apis.Util.Store;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;
using System.Diagnostics;
//using File = Google.Apis.Drive.v3.Data.File;
//using File2 = System.IO.File;

namespace WFTextEditor
{
    public partial class Form1 : Form
    {
        public SaveFileDialog sfd;
        public OpenFileDialog ofd;
        public DateTime startTime;
        public DateTime endTime;
        public TimeSpan UsedTime { get; set; }
        public Form1()
        {
            InitializeComponent();
            startTime = DateTime.Now;
            sfd = new SaveFileDialog();
            ofd = new OpenFileDialog();
            this.Text = "Untitled";
            this.Closed += new EventHandler(MainWindow_Closed);
        }
        void MainWindow_Closed(object sender, EventArgs e)
        {
            endTime = DateTime.Now;
            UsedTime = endTime - startTime;
            MessageBox.Show("Application use time : "+UsedTime.Minutes+" minutes\nCost of usage : $"+Math.Round(UsedTime.TotalHours,3));
        }
        private void richTextBox1_TextChanged_1(object sender, EventArgs e)
        {

        }
        private void OpenFile()
        {
            ofd.Title = "Open Document";
            ofd.Filter = "Text Files|*.txt";
            ofd.FileName = string.Empty;
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                if (ofd.FileName == String.Empty)
                {
                    return;
                }
                else
                {
                    string str = ofd.FileName;
                    richTextBox1.LoadFile(str, RichTextBoxStreamType.PlainText);
                    this.Text = ofd.FileName;
                }
            }
        }
        private void SaveAsFile()
        {
            sfd.Title = "Save As";
            sfd.Filter = "Text Document|*.txt|PDF|*.pdf|HTML|*.htm|XML|.xml|Mirosoft Document (.doc)|*.doc";
            sfd.DefaultExt = "txt";
            if (sfd.ShowDialog() == DialogResult.OK)
            {
                richTextBox1.SaveFile(sfd.FileName, RichTextBoxStreamType.PlainText);
                this.Text = sfd.FileName;
            }
        }
        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (richTextBox1.Modified == true)
            {
                DialogResult dr = MessageBox.Show("Do you want to save changes to the opened file?", "Save File?", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                if (dr == DialogResult.No)
                {
                    richTextBox1.Modified = false;
                    OpenFile();
                }
                else
                {
                    if (this.Text == "Untitled")
                    {
                        SaveAsFile();
                        OpenFile();
                    }
                    else
                    {
                        richTextBox1.SaveFile(this.Text);
                        OpenFile();
                    }
                }
            }
            else
            {
                OpenFile();
            }
        }
        private void saveAsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveAsFile();
        }
        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (this.Text != "Untitled")
            {
                richTextBox1.SaveFile(this.Text, RichTextBoxStreamType.PlainText);
                richTextBox1.Modified = false;
            }
            else
            {
                SaveAsFile();
            }
        }
        public static string SearchDialog(string text, string caption)
        {
            Form prompt = new Form()
            {
                Width = 500,
                Height = 150,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                Text = caption,
                StartPosition = FormStartPosition.CenterScreen
            };
            Label textLabel = new Label() { Left = 50, Top = 20, Text = text, Width = 400 };
            TextBox textBox = new TextBox() { Left = 50, Top = 50, Width = 400 };
            Button confirmation = new Button() { Text = "Ok", Left = 350, Width = 100, Top = 70, DialogResult = DialogResult.OK };
            confirmation.Click += (sender, e) => { prompt.Close(); };
            prompt.Controls.Add(textBox);
            prompt.Controls.Add(confirmation);
            prompt.Controls.Add(textLabel);
            prompt.AcceptButton = confirmation;
            return prompt.ShowDialog() == DialogResult.OK ? textBox.Text : "";
        }
        public static string[] ReplaceDialog(string caption, string text,string text2)
        {
            Form prompt = new Form()
            {
                Width = 500,
                Height = 250,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                Text = caption,
                StartPosition = FormStartPosition.CenterScreen
            };
            Label textLabel = new Label() { Left = 50, Top = 20, Text = text, Width = 400 };
            TextBox textBox = new TextBox() { Left = 50, Top = 50, Width = 400 };
            Label textLabel2 = new Label() { Left = 50, Top = 80, Text = text2, Width = 400 };
            TextBox textBox2 = new TextBox() { Left = 50, Top = 110, Width = 400 };
            Button confirmation = new Button() { Text = "Ok", Left = 350, Width = 100, Top = 140, DialogResult = DialogResult.OK };
            confirmation.Click += (sender, e) => { prompt.Close(); };
            prompt.Controls.Add(textBox);
            prompt.Controls.Add(textBox2);
            prompt.Controls.Add(confirmation);
            prompt.Controls.Add(textLabel);
            prompt.Controls.Add(textLabel2);
            prompt.AcceptButton = confirmation;
            DialogResult dialogResult = prompt.ShowDialog();
            string[] replaceTexts = { textBox.Text, textBox2.Text };
            return dialogResult == DialogResult.OK ? replaceTexts : null;
        }
        private void searchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            int index = 0;
            string searchText = SearchDialog("Search", "Enter text to be searched: ");
            while(index < richTextBox1.Text.LastIndexOf(searchText))
            {
                richTextBox1.Find(searchText, index, richTextBox1.TextLength, RichTextBoxFinds.None);
                richTextBox1.SelectionBackColor = Color.FromArgb(0xB4,0xD5,0xFE);
                index = richTextBox1.Text.IndexOf(searchText, index) + 1;
            }
            
        }
        private void replaceToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string[] replaceTexts = ReplaceDialog("Replace", "Enter text to be replaced : ", "Enter the replacing word : ");
            int lengthDiff = 0;
            MatchCollection matches = Regex.Matches(richTextBox1.Text, replaceTexts[0]);
            foreach(Match match in matches)
            {
                richTextBox1.Select(match.Index+lengthDiff, match.Length);
                richTextBox1.SelectedText = replaceTexts[1];
                lengthDiff += replaceTexts[1].Length - replaceTexts[0].Length;
            }
        }
        private void copyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            richTextBox1.Copy();
        }
        private void pasteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            richTextBox1.Paste();
        }
        private void cleanToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string text = richTextBox1.Text;
            text.Trim();
            Regex trimmer = new Regex(@"\n\s+");
            text = trimmer.Replace(text, "\n");
            trimmer = new Regex(@"\s\s+");
            text = trimmer.Replace(text, " ");
            richTextBox1.Text = text;
            richTextBox1.Text = richTextBox1.Text.Trim();
        }
        private void splitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string[] words = richTextBox1.Text.Split();
            string text = string.Empty;
            foreach (string word in words)
                text += (word + "\n");
            richTextBox1.Text = text;
        }
        #region Drive
        static string[] Scopes = { DriveService.Scope.Drive, DriveService.Scope.DriveAppdata, DriveService.Scope.DriveFile };
        static string ApplicationName = "Drive API .NET Quickstart";
        #endregion
        private void saveToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            UserCredential credential;

            using (var stream =
                new FileStream("credentials.json", FileMode.Open, FileAccess.Read))
            {
                string credPath = "token.json";
                credential = GoogleWebAuthorizationBroker.AuthorizeAsync(
                    GoogleClientSecrets.Load(stream).Secrets,
                    Scopes,
                    "user",
                    CancellationToken.None,
                    new FileDataStore(credPath, true)).Result;
                Debug.WriteLine("Credential file saved to: " + credPath);
            }

            // Create Drive API service.
            var service = new DriveService(new BaseClientService.Initializer()
            {
                HttpClientInitializer = credential,
                ApplicationName = ApplicationName,
            });
            var fileMetadata = new Google.Apis.Drive.v3.Data.File()
            {
                Name = richTextBox1.Name
            };
            FilesResource.CreateMediaUpload request;
            using (var stream = new System.IO.FileStream(this.Text,
                                    System.IO.FileMode.Open))
            {
                request = service.Files.Create(
                    fileMetadata, stream, "text/plain");
                request.Fields = "id";
                request.Upload();
            }
            var file = request.ResponseBody;
            if (file != null)
            {
                if(file.Id!=null)
                MessageBox.Show("Upload success");
            }
        }
    }
}
