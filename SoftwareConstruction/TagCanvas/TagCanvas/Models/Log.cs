using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TagCanvas.Services;

namespace TagCanvas.Models
{
    public class Log
    {
        public DateTime Time { get; set; }
        public LogType LogType { get; set; }
        public string Content { get; set; }
        public string Initiater { get; set; }

        public override string ToString()
        {
            return $"{Time} [{LogType}] ({Initiater}): {Content}";
        }
    }
}
