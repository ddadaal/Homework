using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TagCanvas.Models;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

namespace TagCanvas.Services.Storage
{
    public class FileStorage : IStorage
    {
        public IEnumerable<Graph> LoadGraph(string path)
        {
            var stream = new FileStream(path, FileMode.Open, FileAccess.Read);
            var formatter = new BinaryFormatter();
            var list = (List<Graph>)formatter.Deserialize(stream);
            stream.Close();
            return list;
        }


        public void Save(List<Graph> graphs, string path)
        {
            var formatter = new BinaryFormatter();
            using (var stream = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite))
            {
                formatter.Serialize(stream, graphs);
            }
        }
    }
}
