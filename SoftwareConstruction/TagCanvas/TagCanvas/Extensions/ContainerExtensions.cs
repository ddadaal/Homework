using Autofac;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TagCanvas.Extensions
{
    public static class ContainerExtensions
    {
        public static ContainerBuilder Register<I, Impl>(this ContainerBuilder builder, bool singleInstance = false) where Impl : I
        {
            var intermediate = builder.RegisterType<Impl>();
            if (singleInstance)
            {
                intermediate = intermediate.SingleInstance();
            }

            intermediate.As<I>();

            return builder;
        }
    }
}
