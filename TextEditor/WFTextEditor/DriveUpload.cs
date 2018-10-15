using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Google.Apis.Drive.v3;
using Google.Apis.Drive.v3.Data;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Services;
using Google.Apis.Util.Store;
using System.IO;
using System.Diagnostics;
using System.Windows.Forms;
using System.Threading;

namespace WFTextEditor
{
    class DriveUpload
    {
        static string[] Scopes = { DriveService.Scope.Drive, DriveService.Scope.DriveAppdata, DriveService.Scope.DriveFile };
        static string ApplicationName = "Quickstart";

        private Google.Apis.Drive.v3.Data.File fileMetadata;
        private DriveService service;

        public void createService()
        {
            UserCredential credential;

            using (var stream =
                new FileStream(@"C:\Users\schandh\source\repos\WFTextEditor\WFTextEditor\credential.json", FileMode.Open, FileAccess.Read))
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
            service = new DriveService(new BaseClientService.Initializer()
            {
                HttpClientInitializer = credential,
                ApplicationName = ApplicationName,
            });
        }
        public bool UploadToDrive(string name, string fileName)
        {
            createService();
            fileMetadata = new Google.Apis.Drive.v3.Data.File()
            {
                Name = name
            };
            FilesResource.CreateMediaUpload request;
            using (var stream = new System.IO.FileStream(fileName,
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
                if (file.Id != null)
                    return true;
            }
            else
            {
                return false;
            }
            return false;
        }


    }
}
