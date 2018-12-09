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
using TagCanvas.ViewModels;

namespace TagCanvas.Views
{
    /// <summary>
    /// Interaction logic for GraphEditDialog.xaml
    /// </summary>
    public partial class GraphEditDialog : Window
    {

        public delegate GraphEditDialog Factory(Graph graph);

        public event Action<Graph> OnConfirm;
        public event Action OnCancel;

        private ILogger logger;
        private ColorProfile colorProfile;
        private Graph graph;
        private GraphEditDialogViewModel viewModel;

        public GraphEditDialog(ILogger logger, ColorProfile colorProfile, Graph graph, GraphEditDialogViewModel viewModel)
        {

            DataContext = viewModel;
            this.logger = logger;
            this.colorProfile = colorProfile;
            this.graph = graph;
            this.viewModel = viewModel;

            // initialize viewmodel
            viewModel.Name = graph.Name;
            viewModel.Shape = new ShapeSelection() {
                Shape = graph.Shape,
                Color = colorProfile.GetColorFromShape(graph.Shape)
            };
            viewModel.Content = graph.Content;

            InitializeComponent();

        }

        private void btnConfirm_Click(object sender, RoutedEventArgs e)
        {
            Close();
            OnConfirm(new Graph(graph.Id, graph.Strokes, viewModel.Shape.Shape, viewModel.Name, viewModel.Content));
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
            OnCancel();
        }

        private void Window_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            DragMove();
        }

    }
}
