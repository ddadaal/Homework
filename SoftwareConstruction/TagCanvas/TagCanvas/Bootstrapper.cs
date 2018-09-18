using Autofac;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using TagCanvas.Services;
using TagCanvas.ViewModels;

namespace TagCanvas
{
    class Bootstrapper
    {
        private static ILifetimeScope rootScope;

        public static void Start()
        {
            var builder = new ContainerBuilder();
            var assemblies = new[] { Assembly.GetExecutingAssembly() };

            // scan assemblies to register all services
            builder.RegisterAssemblyTypes(assemblies)
                  .Where(t => typeof(IService).IsAssignableFrom(t))
                  .SingleInstance()
                  .AsImplementedInterfaces();

            // scan assemblies to register ViewModels

            builder.RegisterAssemblyTypes(assemblies)
                .Where(t => typeof(IViewModel).IsAssignableFrom(t) && !typeof(ITransientViewModel).IsAssignableFrom(t))
                .AsImplementedInterfaces();

            // several view model instances are transitory and created on the fly, if these are tracked by the container then they
            // won't be disposed of in a timely manner

            builder.RegisterAssemblyTypes(assemblies)
                .Where(t => typeof(ITransientViewModel).IsAssignableFrom(t))
                .AsImplementedInterfaces()
                .ExternallyOwned();

            rootScope = builder.Build();
        }

        public static T Resolve<T>()
        {
            return rootScope.Resolve<T>();
        }
    }
}
