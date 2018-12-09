using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Ink;

namespace TagCanvas.Services
{
    public enum RecognizedShape
    {
        Triangle,
        Rectangle,
        Circle,
        Unrecognized
    }

    public interface IRecognizer
    {
        RecognizedShape Recognize(IEnumerable<Stroke> strokes);
    }
}
