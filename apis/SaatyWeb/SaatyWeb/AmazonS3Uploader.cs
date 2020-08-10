using System;
using Amazon.S3;
using Amazon.S3.Model;

namespace AmazonS3Demo
{
    public class AmazonS3Uploader
    {
        private string bucketName = "saaty";
        //private string keyName = "Key2.txt";
        private string filePath = @"C:\\Users\\wolfa\\Pictures\\Key2.png";


        public void UploadFile(string body, string keyName)
        {
            var client = new AmazonS3Client(Amazon.RegionEndpoint.USEast1);

            try
            {
                PutObjectRequest putRequest = new PutObjectRequest
                {
                    BucketName = bucketName,
                    Key = keyName,
                    ContentBody = body,

                    ContentType = "text/plain"
                };

                PutObjectResponse response = client.PutObject(putRequest);
            }
            catch (AmazonS3Exception amazonS3Exception)
            {
                if (amazonS3Exception.ErrorCode != null &&
                    (amazonS3Exception.ErrorCode.Equals("InvalidAccessKeyId")
                    ||
                    amazonS3Exception.ErrorCode.Equals("InvalidSecurity")))
                {
                    throw new Exception("Check the provided AWS Credentials.");
                }
                else
                {
                    throw new Exception("Error occurred: " + amazonS3Exception.Message);
                }
            }
        }
    }
}