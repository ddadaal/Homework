using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Autofac;
using Autofac.Core;
using TagCanvas.Services;
using TagCanvas.Services.Log;
using TagCanvas.Services.Recognizer;
using TagCanvas.ViewModels;
using TagCanvas.Views;
using TagCanvas.Extensions;
using TagCanvas.Models;
using TagCanvas.Services.Storage;

namespace TagCanvas
{
    public static class RootContainer
    {


        private static IContainer container;

        public static void Initialize()
        {
            var builder = new ContainerBuilder();

            // Register services and single instances
            builder.Register<ILogger, MemoryLogger>(true);
            builder.Register<IRecognizer, GestureRecognizer>(true);
            builder.Register<ColorProfile, ColorProfile>(true);
            builder.Register<IStorage, FileStorage>(true);

            // Register views and corresponding view models
            builder.Register<MainWindow, MainWindow>();
            builder.Register<MainWindowViewModel, MainWindowViewModel>();
            builder.Register<LogViewer, LogViewer>();
            builder.Register<GraphEditDialog, GraphEditDialog>();
            builder.Register<GraphEditDialogViewModel, GraphEditDialogViewModel>();

            container = builder.Build();
        }

        public static T Resolve<T>()
        {
            return container.Resolve<T>();

        }
    }
}
