namespace Assignment5
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
            this.lbBubble = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lbSelection = new System.Windows.Forms.Label();
            this.lbQuick = new System.Windows.Forms.Label();
            this.btnStart = new System.Windows.Forms.Button();
            this.btnShuffle = new System.Windows.Forms.Button();
            this.btnReset = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lbBubble
            // 
            this.lbBubble.AutoSize = true;
            this.lbBubble.Location = new System.Drawing.Point(37, 28);
            this.lbBubble.Name = "lbBubble";
            this.lbBubble.Size = new System.Drawing.Size(69, 20);
            this.lbBubble.TabIndex = 0;
            this.lbBubble.Text = "冒泡排序";
            this.lbBubble.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(450, 69);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(0, 20);
            this.label2.TabIndex = 1;
            // 
            // lbSelection
            // 
            this.lbSelection.AutoSize = true;
            this.lbSelection.Location = new System.Drawing.Point(406, 28);
            this.lbSelection.Name = "lbSelection";
            this.lbSelection.Size = new System.Drawing.Size(69, 20);
            this.lbSelection.TabIndex = 2;
            this.lbSelection.Text = "选择排序";
            // 
            // lbQuick
            // 
            this.lbQuick.AutoSize = true;
            this.lbQuick.Location = new System.Drawing.Point(789, 28);
            this.lbQuick.Name = "lbQuick";
            this.lbQuick.Size = new System.Drawing.Size(69, 20);
            this.lbQuick.TabIndex = 3;
            this.lbQuick.Text = "快速排序";
            // 
            // btnStart
            // 
            this.btnStart.Location = new System.Drawing.Point(12, 497);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(383, 53);
            this.btnStart.TabIndex = 11;
            this.btnStart.Text = "开始排序";
            this.btnStart.UseVisualStyleBackColor = true;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            // 
            // btnShuffle
            // 
            this.btnShuffle.Location = new System.Drawing.Point(410, 497);
            this.btnShuffle.Name = "btnShuffle";
            this.btnShuffle.Size = new System.Drawing.Size(332, 53);
            this.btnShuffle.TabIndex = 12;
            this.btnShuffle.Text = "打乱";
            this.btnShuffle.UseVisualStyleBackColor = true;
            this.btnShuffle.Click += new System.EventHandler(this.btnShuffle_Click);
            // 
            // btnReset
            // 
            this.btnReset.Location = new System.Drawing.Point(774, 497);
            this.btnReset.Name = "btnReset";
            this.btnReset.Size = new System.Drawing.Size(311, 53);
            this.btnReset.TabIndex = 13;
            this.btnReset.Text = "重新生成";
            this.btnReset.UseVisualStyleBackColor = true;
            this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1106, 566);
            this.Controls.Add(this.btnReset);
            this.Controls.Add(this.btnShuffle);
            this.Controls.Add(this.btnStart);
            this.Controls.Add(this.lbQuick);
            this.Controls.Add(this.lbSelection);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lbBubble);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.Text = "Assignment5";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbBubble;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lbSelection;
        private System.Windows.Forms.Label lbQuick;
        private System.Windows.Forms.Button btnStart;
        private System.Windows.Forms.Button btnShuffle;
        private System.Windows.Forms.Button btnReset;
    }
}

