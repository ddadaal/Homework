using AxWMPLib;
using System;
using System.Reflection;
using System.Windows.Forms;
using WMPLib;

namespace Assignment2
{
    public partial class Form1 : Form
    {

        Assembly myControlAssembly;
        UserControl infoControl;
        MethodInfo setPathMethodInfo;

        public Form1()
        {
            InitializeComponent();

            myControlAssembly = Assembly.Load($"InfoControl, Version={InfoControlConfig.Version}.0.0.0, Culture=neutral, PublicKeyToken=ff57dd7195e544df");
            infoControl = (UserControl)myControlAssembly.CreateInstance("InfoControl.InfoControl");
            infoControl.Enabled = true;
            infoControl.Location = new System.Drawing.Point(580, 12);
            infoControl.Size = new System.Drawing.Size(350, 550);

            setPathMethodInfo = infoControl.GetType().GetMethod("SetFilePath");

            Controls.Add(infoControl);
            tbSpeed.KeyDown += TbSpeed_KeyDown;

        }

        private void TbSpeed_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                SetPlaySpeed();
            }
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

        public void SetFilePath(string path)
        {
            tbPath.Text = path;
            wmpPlayer.Ctlcontrols.stop();
            wmpPlayer.URL = path;
            wmpPlayer.Ctlcontrols.play();
            setPathMethodInfo.Invoke(infoControl, new object[] { path });

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            var dialog = new OpenFileDialog();
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                string path = dialog.FileName;
                SetFilePath(path);
            }
        }

        private void SetPlaySpeed()
        {
            if (double.TryParse(tbSpeed.Text, out double result))
            {
                if (result == 0)
                {
                    MessageBox.Show("请输入非0！");
                    return;
                }
                wmpPlayer.settings.rate = result;
            }
            else
            {
                MessageBox.Show("请输入正确的数字！");
            }
        }

        private void btnSpeedConfirm_Click(object sender, EventArgs e)
        {
            SetPlaySpeed();
        }
    }
}
