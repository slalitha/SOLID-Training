using System.Diagnostics;
using System.IO;
using System.Threading;
using System.Windows;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Services;
using Google.Apis.Util.Store;

namespace Notepad
{
    public class Drive
    {
        UserCredential credential;
        string[] scopes = new string[] { Google.Apis.Drive.v3.DriveService.Scope.Drive,
            Google.Apis.Drive.v3.DriveService.Scope.DriveAppdata,
            Google.Apis.Drive.v3.DriveService.Scope.DriveFile,
            };
        string ApplicationName = "Notepad";
        Google.Apis.Drive.v3.DriveService service;
        public Drive()
        {
            using (var stream = new FileStream(@"C:\Users\aananth\source\repos\Notepad\Notepad\client_secret.json", FileMode.Open, FileAccess.Read))
            {
                string credPath = "token.json";
                credential = GoogleWebAuthorizationBroker.AuthorizeAsync(
                    GoogleClientSecrets.Load(stream).Secrets,
                    scopes,
                    "user", CancellationToken.None,
                    new FileDataStore(credPath, true)).Result;
            }
            CreateService();
        }

        public void CreateService()
        {
            service = new Google.Apis.Drive.v3.DriveService(new BaseClientService.Initializer()
            {
                HttpClientInitializer = credential,
                ApplicationName = ApplicationName,
            });
        }

        public void UploadFile(string filePath,string extension)
        {
            var fileMetadata = new Google.Apis.Drive.v3.Data.File();
            fileMetadata.Name = Path.GetFileName(filePath);
            if(extension.Equals("txt"))
                fileMetadata.MimeType = "text/plain";
            if (extension.Equals("pdf"))
                fileMetadata.MimeType = "application/pdf";
            if (extension.Equals("xml"))
                fileMetadata.MimeType = "text/xml";
            if (extension.Equals("doc"))
                fileMetadata.MimeType = "application/msword";
            if (extension.Equals("html"))
                fileMetadata.MimeType = "text/html";

            Google.Apis.Drive.v3.FilesResource.CreateMediaUpload request;
            using (var stream = new System.IO.FileStream(filePath,
                                    System.IO.FileMode.Open))
            {
                request = service.Files.Create(
                    fileMetadata, stream, fileMetadata.MimeType);
                request.Fields = "id";
                request.Upload();
            }
            var file1 = request.ResponseBody;
            if (file1 != null)
                MessageBox.Show("Uploaded Successfully");
            Debug.WriteLine("File ID: " + file1.Id);
        }
    }
}
