using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using TagCanvas.Services;
using TagCanvas.ViewModels;

namespace TagCanvas
{
    /// <summary>
    /// Interaction logic for App.xaml
    /// </summary>
    public partial class App : Application
    {
        protected override void OnStartup(StartupEventArgs e)
        {
            base.OnStartup(e);

            Bootstrapper.Start();

            var window = new MainWindow(
                Bootstrapper.Resolve<ILogger>(),
                Bootstrapper.Resolve<MainWindowViewModel>()
            );
            window.Show();
        }
    }
}
