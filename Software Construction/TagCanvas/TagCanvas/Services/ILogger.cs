using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace TagCanvas.Services
{
    public enum LogType
    {
        Info,
        Warning,
        Error
    }

    public interface ILogger
    {
        void Log(string content, LogType type = LogType.Info, [CallerMemberName] string caller = "");

        IEnumerable<Models.Log> Logs { get; }

        void RemoveWhere(Predicate<Models.Log> predicate);
    }
}
