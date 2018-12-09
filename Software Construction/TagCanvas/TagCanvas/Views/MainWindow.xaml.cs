using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using TagCanvas.Models;
using TagCanvas.Services;
using TagCanvas.ViewModels;
using TagCanvas.Views;
using TagCanvas.Extensions;

namespace TagCanvas
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private MainWindowViewModel viewModel;
        private IRecognizer recognizer;
        private IStorage storage;

        // Sub windows
        private LogViewer.Factory logViewerFactory;
        private GraphEditDialog.Factory graphEditDialogFactory;
        private ColorProfile colorProfile;

        public MainWindow(MainWindowViewModel viewModel, 
            IRecognizer recognizer,
            LogViewer.Factory logViewerFactory, 
            GraphEditDialog.Factory graphEditDialogFactory,
            ColorProfile colorProfile,
            IStorage storage
            )
        {


            // Set DataContext to ViewModel
            DataContext = viewModel;
            this.viewModel = viewModel;

            // Set other dependencies
            this.recognizer = recognizer;
            this.logViewerFactory = logViewerFactory;
            this.graphEditDialogFactory = graphEditDialogFactory;
            this.colorProfile = colorProfile;
            this.storage = storage;

            InitializeComponent();
        }

        private void btnDraw_Click(object sender, RoutedEventArgs e)
        {
            viewModel.Mode = Mode.Draw;

        }

        private void btnSelect_Click(object sender, RoutedEventArgs e)
        {
            viewModel.Mode = Mode.Select;
        }

        private void btnEraseByPoint_Click(object sender, RoutedEventArgs e)
        {
            viewModel.Mode = Mode.EraseByPoint;
        }

        private void btnEraseByStroke_Click(object sender, RoutedEventArgs e)
        {
            viewModel.Mode = Mode.EraseByStroke;
        }

        private void canvas_SelectionChanged(object sender, EventArgs e)
        {
            var selectedStrokes = canvas.GetSelectedStrokes();
            viewModel.SelectedStrokes = selectedStrokes;

            // if the selection contains one and only one graph, select it.
            var selectedGraphs = viewModel.GraphListItems.Where(x => selectedStrokes.All(y => x.Graph.Strokes.Contains(y)));
            if (selectedGraphs.Count() == 1)
            {
                var graph = selectedGraphs.First();
                if (graph != viewModel.SelectedGraph)
                {
                    viewModel.SelectedGraph = graph;
                }
            }
        }

        private void btnRecognize_Click(object sender, RoutedEventArgs e)
        {
            var recognizedResult = recognizer.Recognize(viewModel.SelectedStrokes);
            MessageBox.Show($"机器认为被选中的图形是 {recognizedResult}");

        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            var strokes = viewModel.SelectedStrokes;
            var recognizedShape = recognizer.Recognize(strokes);
            var graph = new Graph(strokes, new Shape(recognizedShape.ToString()));

            viewModel.Graphs.Add(graph);
            viewModel.SelectedGraph = viewModel.ConvertGraphToGraphListItem(graph);
            ProcessColor(graph);
            OpenEditDialog();
        }

        private void StatusBar_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            var window = logViewerFactory.Invoke();
            window.Show();
        }

        private void btnGraphEdit_Click(object sender, RoutedEventArgs e)
        {
            OpenEditDialog();
        }

        private void OpenEditDialog()
        {

            var editDialog = graphEditDialogFactory.Invoke(viewModel.SelectedGraph.Graph);
            editDialog.OnConfirm += EditDialogConfirm;
            editDialog.OnCancel += EditDialogCancel;
            editDialog.Show();
        }

        private void EditDialogCancel()
        {
            
        }

        private void EditDialogConfirm(Graph obj)
        {
            for (int i =0; i < viewModel.Graphs.Count(); i++)
            {
                if (viewModel.Graphs[i].Id == obj.Id)
                {
                    viewModel.Graphs[i] = obj;
                    ProcessColor(obj);
                    break;
                }
            }
        }

        private void btnGraphDelete_Click(object sender, RoutedEventArgs e)
        {
            var selectedGraph = viewModel.SelectedGraph;

            if (selectedGraph == null)
            {
                return;
            }

            foreach (var stroke in selectedGraph.Graph.Strokes)
            {
                if (viewModel.Strokes.Contains(stroke))
                {
                    viewModel.Strokes.Remove(stroke);
                }
            }
            viewModel.Graphs.Remove(selectedGraph.Graph);
        }

        private void list_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (viewModel.SelectedGraph != null)
            {
                canvas.Select(viewModel.SelectedGraph.Graph.Strokes);
            }

        }

        private void ProcessColor(Graph graph)
        {

            SetColor(graph.Strokes, colorProfile.GetColorFromShape(graph.Shape));

        }


        public void SetColor(IEnumerable<Stroke> strokes, Color color)
        {
            foreach (var stroke in strokes)
            {
                stroke.DrawingAttributes.Color = color;
            }
        }



        private void btnGraphRedraw_Click(object sender, RoutedEventArgs e)
        {
            var canvasStrokes = viewModel.Strokes;
            var selectedStrokes = viewModel.SelectedGraph.Graph.Strokes;
            canvasStrokes.Remove(new StrokeCollection(canvasStrokes.Where(x => selectedStrokes.Contains(x))));
            canvasStrokes.Add(selectedStrokes);
        }

        private void btnDownload_Click(object sender, RoutedEventArgs e)
        {
            var dialog = new SaveFileDialog()
            {
                DefaultExt = ".graph",
                FileName = $"Graphs"
            };

            var result = dialog.ShowDialog();

            if (result ?? false)
            {
                var fileName = dialog.FileName;
                storage.Save(viewModel.Graphs.ToList(), fileName);
            }

        }

        private void btnLoad_Click(object sender, RoutedEventArgs e)
        {
            var dialog = new OpenFileDialog()
            {
                DefaultExt = ".graph"
            };

            var result = dialog.ShowDialog();

            if (result ?? false)
            {
                var fileName = dialog.FileName;
                try
                {
                    var graphs = storage.LoadGraph(fileName);
                    graphs.ForEach(viewModel.Graphs.Add);
                } catch (Exception)
                {
                    MessageBox.Show("选取的文件已经损坏。");
                }

            }
        }
    }
}
