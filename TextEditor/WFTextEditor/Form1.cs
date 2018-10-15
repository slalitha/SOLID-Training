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

namespace WFTextEditor
{
    public partial class Form1 : Form
    {
        public SaveFileDialog sfd;
        public DateTime startTime;
        public DateTime endTime;
        public long totalUpload;
        public Form1()
        {
            InitializeComponent();
            startTime = DateTime.Now;
            totalUpload = 0;
            sfd = new SaveFileDialog();
            this.Text = "Untitled";
            this.Closed += new EventHandler(MainWindow_Closed);
        }
        void MainWindow_Closed(object sender, EventArgs e)
        {
            endTime = DateTime.Now;
            TimeSpan totalTime = endTime - startTime;
            BillGenerator bill = new BillGenerator();
            MessageBox.Show("Total Bill : " + bill.generateBill(totalTime,totalUpload));
            /*BillingAmt timeAmt = new BillingAmt(startTime, endTime, 1);
            double timeCost = timeAmt.TimeBill();
            BillingAmt uploadAmt = new BillingAmt(totalUpload, 1);
            double uploadCost = uploadAmt.UploadBill();
            MessageBox.Show("Time Cost : " + timeAmt + "\nUpload Cost : " + uploadCost);*/
        }
        private void richTextBox1_TextChanged_1(object sender, EventArgs e)
        {

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
                if (dr == DialogResult.Yes)
                {
                    if (this.Text == "Untitled")
                    {
                        SaveAsFile();
                    }
                    else
                    {
                        richTextBox1.SaveFile(this.Text);
                    }
                }
            }
            Dialogs.OpenFile(richTextBox1,this);
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
        private void searchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            int index = 0;
            string searchText = Dialogs.SearchDialog("Search", "Enter text to be searched: ");
            while(index < richTextBox1.Text.LastIndexOf(searchText))
            {
                richTextBox1.Find(searchText, index, richTextBox1.TextLength, RichTextBoxFinds.None);
                richTextBox1.SelectionBackColor = Color.FromArgb(0xB4,0xD5,0xFE);
                index = richTextBox1.Text.IndexOf(searchText, index) + 1;
            }
            
        }
        private void replaceToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string[] replaceTexts = Dialogs.ReplaceDialog("Replace", "Enter text to be replaced : ", "Enter the replacing word : ");
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
        private void saveToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            DriveUpload driveUpload = new DriveUpload();
            bool uploadStatus = driveUpload.UploadToDrive(Path.GetFileName(this.Text),this.Text);
            if (uploadStatus)
            {
                FileInfo fInfo = new FileInfo(this.Text);
                totalUpload += fInfo.Length;
            }
        }

        private void cloudStorageToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }
    }
}
