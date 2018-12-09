using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Windows.Controls;
using System.Windows.Ink;
using TagCanvas.Models;
using TagCanvas.Services;
using System.Collections.Generic;
using System.Windows.Media;
using TagCanvas.Extensions;
using System.Windows.Data;
using System.Globalization;
using System.Collections.Specialized;

namespace TagCanvas.ViewModels
{

    public enum Mode
    {
        Draw,
        Select,
        EraseByPoint,
        EraseByStroke,

    }

    public class GraphListItem
    {
        public Graph Graph { get; set; }
        public Brush Fill { get; set; }

        public GraphListItem(Graph graph, Color fill)
        {
            Graph = graph;
            Fill = new SolidColorBrush(fill);
        }
    }




    public class MainWindowViewModel : BaseViewModel
    {
        private ObservableCollection<Log> Logs = new ObservableCollection<Log>();
        private Mode mode = Mode.Draw;
        private StrokeCollection selectedStrokes = new StrokeCollection();
        private ObservableCollection<Graph> graphs = new ObservableCollection<Graph>();
        private ObservableCollection<GraphListItem> graphListItems = new ObservableCollection<GraphListItem>();
        private StrokeCollection strokes = new StrokeCollection();
        private GraphListItem selectedGraph = null;


        private ILogger logger;
        private ColorProfile colorProfile;


        public MainWindowViewModel(ILogger logger, ColorProfile colorProfile)
        {
            this.logger = logger;
            this.colorProfile = colorProfile;

            // events
            strokes.StrokesChanged += Strokes_StrokesChanged;
            graphs.CollectionChanged += Graphs_CollectionChanged;

            Log("MainWindowViewModel initiated.");
        }

        private void Graphs_CollectionChanged(object sender, NotifyCollectionChangedEventArgs e)
        {

            graphListItems.Clear();
            foreach (var graph in graphs)
            {
                graphListItems.Add(new GraphListItem(graph, colorProfile.GetColorFromShape(graph.Shape)));
                if (e.Action == NotifyCollectionChangedAction.Add)
                {
                    foreach (Graph g in e.NewItems)
                    {
                        foreach (var s in g.Strokes)
                        {
                            if (!strokes.Contains(s))
                            {
                                strokes.Add(s);
                            }
                        }
                    }
                }

            }

        }

        private void Strokes_StrokesChanged(object sender, StrokeCollectionChangedEventArgs e)
        {
            NotifyChange(nameof(BtnGraphRedrawAvailable));
        }

        public string LatestLog => Logs.Any() ? Logs.OrderByDescending(x => x.Time).First().ToString() : "Logs here";

        public GraphListItem SelectedGraph
        {
            get => selectedGraph;
            set
            {
                SetProperty(ref selectedGraph, value,
                    nameof(SelectedGraph),
                    nameof(BtnGraphDeleteAvailable),
                    nameof(BtnGraphEditAvailable),
                    nameof(BtnGraphRedrawAvailable)
                );
            }
        }

        public StrokeCollection Strokes
        {
            get => strokes;
        }

        public ObservableCollection<Graph> Graphs
        {
            get => graphs;
        }

        public ObservableCollection<GraphListItem> GraphListItems
        {
            get => graphListItems;
        }

        public Mode Mode
        {
            get => mode;
            set
            {
                SetProperty(ref mode, value, 
                    nameof(Mode),
                    nameof(EditingMode),
                    nameof(BtnDrawAvailable),
                    nameof(BtnSelectAvailable),
                    nameof(BtnEraseByPointAvailable),
                    nameof(BtnEraseByStrokeAvailable)

                );
                Log($"Switched to {mode} mode");
            }
        }

        public StrokeCollection SelectedStrokes
        {
            get => selectedStrokes;
            set {
                SetProperty(ref selectedStrokes, value, nameof(SelectedStrokes),
                    nameof(BtnRecognizeAvailable),
                    nameof(BtnSaveAvailable)
                );
                Log($"{selectedStrokes.Count()} strokes selected.");
            }
        }

        public GraphListItem ConvertGraphToGraphListItem(Graph graph)
        {
            return new GraphListItem(graph, colorProfile.GetColorFromShape(graph.Shape));
        }

        public bool BtnDrawAvailable => mode != Mode.Draw;

        public bool BtnSelectAvailable => mode != Mode.Select;

        public bool BtnEraseByPointAvailable => mode != Mode.EraseByPoint;

        public bool BtnEraseByStrokeAvailable => mode != Mode.EraseByStroke;

        public bool BtnRecognizeAvailable => selectedStrokes.Count() > 0;

        public bool BtnSaveAvailable => selectedStrokes.Count() > 0;

        public bool BtnGraphEditAvailable => selectedGraph != null;

        public bool BtnGraphDeleteAvailable => selectedGraph != null;

        public bool BtnGraphRedrawAvailable => selectedGraph != null && !selectedGraph.Graph.Strokes.All(x => strokes.Contains(x));


        public InkCanvasEditingMode EditingMode
        {
            get
            {
                switch (mode)
                {
                    case Mode.EraseByPoint:
                        return InkCanvasEditingMode.EraseByPoint;
                    case Mode.Draw:
                        return InkCanvasEditingMode.Ink;
                    case Mode.Select:
                        return InkCanvasEditingMode.Select;
                    case Mode.EraseByStroke:
                        return InkCanvasEditingMode.EraseByStroke;
                    default:
                        return InkCanvasEditingMode.None;
                }
            }
        }

        public void Log(string content, LogType type = LogType.Info, [CallerMemberName] string caller = "")
        {
            var log = new Log()
            {
                Time = DateTime.Now,
                Content = content,
                LogType = type,
                Initiater = caller
            };

            Logs.Add(log);
            logger.Log(content, type, caller);
            NotifyChange(nameof(LatestLog));
        }

    }
}
