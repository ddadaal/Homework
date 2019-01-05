using AxWMPLib;
using System;
using System.Windows.Forms;
using WMPLib;

namespace Assignment2
{
    public partial class Form1 : Form
    {

        public Form1()
        {
            InitializeComponent();

        }

        public void OnFileChanged(string path)
        {
            this.wmpPlayer.Ctlcontrols.stop();
            this.wmpPlayer.URL = path;
            this.wmpPlayer.Ctlcontrols.play();
        }

        private void Player_MediaError(object sender, _WMPOCXEvents_MediaErrorEvent e)
        {
            try
            {
                IWMPMedia2 errSource = e.pMediaObject as IWMPMedia2;
                IWMPErrorItem errorItem = errSource.Error;
                MessageBox.Show("Error " + errorItem.errorCode.ToString("X")
                                + " in " + errSource.sourceURL);
            }
            catch (InvalidCastException)
            {
                MessageBox.Show("Error.");
            }

        }



    }
}
