using System;
using System.Reflection;
using System.Windows.Forms;

namespace Assignment2
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.wmpPlayer = new AxWMPLib.AxWindowsMediaPlayer();

            Assembly myControlAssembly = Assembly.Load($"InfoControl, Version={InfoControlConfig.Version}.0.0.0, Culture=neutral, PublicKeyToken=ff57dd7195e544df");
            this.infoControl = (UserControl)myControlAssembly.CreateInstance("InfoControl.InfoControl");

            
            ((System.ComponentModel.ISupportInitialize)(this.wmpPlayer)).BeginInit();
            this.SuspendLayout();
            // 
            // wmpPlayer
            // 
            this.wmpPlayer.Enabled = true;
            this.wmpPlayer.Location = new System.Drawing.Point(12, 12);
            this.wmpPlayer.Name = "wmpPlayer";
            this.wmpPlayer.OcxState = ((System.Windows.Forms.AxHost.State)(resources.GetObject("wmpPlayer.OcxState")));
            this.wmpPlayer.Size = new System.Drawing.Size(550, 550);
            this.wmpPlayer.TabIndex = 0;
            this.wmpPlayer.MediaError += Player_MediaError;
            //
            // InfoControl
            //
            this.infoControl.Enabled = true;
            this.infoControl.Location = new System.Drawing.Point(580, 12);
            this.infoControl.Size = new System.Drawing.Size(350, 550);
            // Add Event
            var fileChangedEventInfo = myControlAssembly.GetType("InfoControl.InfoControl").GetEvent("FileChanged");
            Delegate handler = Delegate.CreateDelegate(fileChangedEventInfo.EventHandlerType, this, GetType().GetMethod(nameof(this.OnFileChanged)));
            fileChangedEventInfo.AddEventHandler(this.infoControl, handler);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(934, 571);
            //this.Font = new System.Drawing.Font("Microsoft YaHei", 8F);
            this.Controls.Add(this.wmpPlayer);
            this.Controls.Add(this.infoControl);
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.wmpPlayer)).EndInit();
            this.ResumeLayout(false);

        }

        private void WmpPlayer_MediaError(object sender, AxWMPLib._WMPOCXEvents_MediaErrorEvent e)
        {
            throw new NotImplementedException();
        }

        #endregion

        private AxWMPLib.AxWindowsMediaPlayer wmpPlayer;
        private UserControl infoControl;
    }
}

