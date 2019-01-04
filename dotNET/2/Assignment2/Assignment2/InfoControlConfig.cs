using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Assignment2
{
    public static class InfoControlConfig
    {
        private static XmlDocument config = new XmlDocument();

        static InfoControlConfig() {
            config.Load("InfoControlConfig.xml");
        }

        public static string Version => config.GetElementsByTagName("Version")[0].InnerText;

    }
}
