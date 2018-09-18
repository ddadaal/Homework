using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using TagCanvas.Services;
using TagCanvas.ViewModels;

namespace TagCanvas
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        private ILogger logger;

        public MainWindow(ILogger logger, MainWindowViewModel viewModel)
        {
            this.DataContext = viewModel;
;
            this.logger = logger;
            InitializeComponent();
            logger.Log("MainWindow Initialized");

            
        }
    }
}
