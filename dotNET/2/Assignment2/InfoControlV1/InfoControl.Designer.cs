namespace InfoControl
{
    partial class InfoControl
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

        #region Component Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.treeViewDirectory = new System.Windows.Forms.TreeView();
            this.SuspendLayout();
            // 
            // treeViewDirectory
            // 
            this.treeViewDirectory.Location = new System.Drawing.Point(0, 0);
            this.treeViewDirectory.Name = "treeViewDirectory";
            this.treeViewDirectory.Size = new System.Drawing.Size(350, 250);
            this.treeViewDirectory.TabIndex = 2;
            // 
            // InfoControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.treeViewDirectory);
            this.Name = "InfoControl";
            this.Size = new System.Drawing.Size(350, 550);
            this.Load += new System.EventHandler(this.InfoControl_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TreeView treeViewDirectory;
    }
}
