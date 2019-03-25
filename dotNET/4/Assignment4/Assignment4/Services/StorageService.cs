using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace Assignment4.Services
{
    public class StorageService
    {

        public void SaveToXml(DataSet dataSet, string path)
        {
            var xml = dataSet.GetXml();
            using (var writer = new StreamWriter(path, false))
            {
                writer.Write(xml);
            }
        }

        public void SerializeToFile(object data, string path)
        {
            using (var fileStream = File.Open(path, FileMode.OpenOrCreate, FileAccess.ReadWrite))
            {
                var binaryFormatter = new BinaryFormatter();
                binaryFormatter.Serialize(fileStream, data);
            }
        }

        public object ReadObjectFromFile(string path)
        {
            using (Stream stream = File.Open(path, FileMode.Open, FileAccess.Read))
            {
                var binaryFormatter = new BinaryFormatter();
                return binaryFormatter.Deserialize(stream);
            }
        }
    }
}
