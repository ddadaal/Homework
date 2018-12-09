using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Ink;

namespace TagCanvas.Services.Recognizer
{
    public class GestureRecognizer : IRecognizer
    {


        private readonly System.Windows.Ink.GestureRecognizer recognizer = new System.Windows.Ink.GestureRecognizer(
            new ApplicationGesture[] {
            ApplicationGesture.Triangle,
            ApplicationGesture.Square,
            ApplicationGesture.Circle
            });

        public RecognizedShape Recognize(IEnumerable<Stroke> strokes)
        {
            // GestureRecognizer requires a maximum of 2 strokes
            if (strokes.Count() > 2)
            {
                return RecognizedShape.Unrecognized;
            }
            var results = recognizer.Recognize(new StrokeCollection(strokes));
            var topSelection = results.First();
            if (topSelection == null)
            {
                return RecognizedShape.Unrecognized;
            }

            switch (topSelection.ApplicationGesture)
            {
                case ApplicationGesture.Triangle:
                    return RecognizedShape.Triangle;
                case ApplicationGesture.Square:
                    return RecognizedShape.Rectangle;
                case ApplicationGesture.Circle:
                    return RecognizedShape.Circle;
                default:
                    return RecognizedShape.Unrecognized;
            }
        }
    }
}
