using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TagCanvas.Models;

namespace TagCanvas.Services
{
    public interface IStorage
    {
        void Save(List<Graph> graphs, string path);
        IEnumerable<Graph> LoadGraph(string path);
    }
}
