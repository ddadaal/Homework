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
using System.Windows.Shapes;
using TagCanvas.Models;
using TagCanvas.Services;

namespace TagCanvas.Views
{
    /// <summary>
    /// Interaction logic for LogViewer.xaml
    /// </summary>
    public partial class LogViewer : Window
    {
        public delegate LogViewer Factory();

        private readonly ILogger logger;
        public LogViewer(ILogger logger)
        {
            InitializeComponent();

            this.logger = logger;
        }

        public IEnumerable<Log> Logs => logger.Logs.OrderByDescending(x => x.Time);

    
    }
}
