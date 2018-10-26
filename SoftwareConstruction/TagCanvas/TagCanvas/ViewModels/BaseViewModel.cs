using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace TagCanvas.ViewModels
{
    public class BaseViewModel : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        public void SetProperty<T>(ref T content, T value, [CallerMemberName] string caller = "", params string[] otherProperties)
        {
            content = value;
            NotifyChange(caller);
            NotifyChange(otherProperties);

        }

        public void NotifyChange(params string[] propertyNames)
        {
            foreach (var property in propertyNames)
            {
                PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(property));
            }
        }
    }
}
