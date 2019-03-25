namespace Assignment5
{
    partial class ProgressesForm
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
            this.lbQuick = new System.Windows.Forms.Label();
            this.lbSelection = new System.Windows.Forms.Label();
            this.lbBubble = new System.Windows.Forms.Label();
            this.pbQuick = new Assignment5.NewProgressBar();
            this.pbSelection = new Assignment5.NewProgressBar();
            this.pbBubble = new Assignment5.NewProgressBar();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // lbQuick
            // 
            this.lbQuick.AutoSize = true;
            this.lbQuick.Location = new System.Drawing.Point(699, 149);
            this.lbQuick.Name = "lbQuick";
            this.lbQuick.Size = new System.Drawing.Size(26, 17);
            this.lbQuick.TabIndex = 19;
            this.lbQuick.Text = "0%";
            // 
            // lbSelection
            // 
            this.lbSelection.AutoSize = true;
            this.lbSelection.Location = new System.Drawing.Point(372, 149);
            this.lbSelection.Name = "lbSelection";
            this.lbSelection.Size = new System.Drawing.Size(26, 17);
            this.lbSelection.TabIndex = 18;
            this.lbSelection.Text = "0%";
            // 
            // lbBubble
            // 
            this.lbBubble.AutoSize = true;
            this.lbBubble.Location = new System.Drawing.Point(39, 149);
            this.lbBubble.Name = "lbBubble";
            this.lbBubble.Size = new System.Drawing.Size(26, 17);
            this.lbBubble.TabIndex = 17;
            this.lbBubble.Text = "0%";
            // 
            // pbQuick
            // 
            this.pbQuick.Location = new System.Drawing.Point(664, 111);
            this.pbQuick.Name = "pbQuick";
            this.pbQuick.Size = new System.Drawing.Size(172, 22);
            this.pbQuick.TabIndex = 16;
            // 
            // pbSelection
            // 
            this.pbSelection.Location = new System.Drawing.Point(358, 111);
            this.pbSelection.Name = "pbSelection";
            this.pbSelection.Size = new System.Drawing.Size(172, 22);
            this.pbSelection.TabIndex = 15;
            // 
            // pbBubble
            // 
            this.pbBubble.Location = new System.Drawing.Point(29, 111);
            this.pbBubble.Name = "pbBubble";
            this.pbBubble.Size = new System.Drawing.Size(172, 22);
            this.pbBubble.TabIndex = 14;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(717, 69);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(56, 17);
            this.label4.TabIndex = 13;
            this.label4.Text = "快速排序";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(414, 69);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(56, 17);
            this.label3.TabIndex = 12;
            this.label3.Text = "选择排序";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(89, 69);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 17);
            this.label1.TabIndex = 11;
            this.label1.Text = "冒泡排序";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft YaHei", 21.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(273, 18);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(365, 38);
            this.label2.TabIndex = 20;
            this.label2.Text = "进度以交换次数为衡量标准";
            // 
            // ProgressesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(911, 196);
            this.ControlBox = false;
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lbQuick);
            this.Controls.Add(this.lbSelection);
            this.Controls.Add(this.lbBubble);
            this.Controls.Add(this.pbQuick);
            this.Controls.Add(this.pbSelection);
            this.Controls.Add(this.pbBubble);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label1);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ForeColor = System.Drawing.SystemColors.ControlText;
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "ProgressesForm";
            this.Text = "ProgressesForm";
            this.Load += new System.EventHandler(this.ProgressesForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbQuick;
        private System.Windows.Forms.Label lbSelection;
        private System.Windows.Forms.Label lbBubble;
        private NewProgressBar pbQuick;
        private NewProgressBar pbSelection;
        private NewProgressBar pbBubble;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}