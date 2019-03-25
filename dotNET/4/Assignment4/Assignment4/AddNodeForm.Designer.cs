namespace Assignment4
{
    partial class AddNodeForm
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
            this.btnConfirm = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnChangeValue = new System.Windows.Forms.Button();
            this.lvAttributes = new System.Windows.Forms.ListView();
            this.colKey = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colValue = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.label2 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btnConfirm
            // 
            this.btnConfirm.Location = new System.Drawing.Point(21, 303);
            this.btnConfirm.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnConfirm.Name = "btnConfirm";
            this.btnConfirm.Size = new System.Drawing.Size(106, 33);
            this.btnConfirm.TabIndex = 0;
            this.btnConfirm.Text = "确定";
            this.btnConfirm.UseVisualStyleBackColor = true;
            this.btnConfirm.Click += new System.EventHandler(this.btnConfirm_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(236, 303);
            this.btnCancel.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(111, 33);
            this.btnCancel.TabIndex = 1;
            this.btnCancel.Text = "取消";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnChangeValue
            // 
            this.btnChangeValue.Location = new System.Drawing.Point(21, 244);
            this.btnChangeValue.Name = "btnChangeValue";
            this.btnChangeValue.Size = new System.Drawing.Size(326, 40);
            this.btnChangeValue.TabIndex = 18;
            this.btnChangeValue.Text = "修改值";
            this.btnChangeValue.UseVisualStyleBackColor = true;
            this.btnChangeValue.Click += new System.EventHandler(this.btnChangeValue_Click);
            // 
            // lvAttributes
            // 
            this.lvAttributes.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colKey,
            this.colValue});
            this.lvAttributes.Location = new System.Drawing.Point(21, 35);
            this.lvAttributes.MultiSelect = false;
            this.lvAttributes.Name = "lvAttributes";
            this.lvAttributes.Size = new System.Drawing.Size(326, 188);
            this.lvAttributes.TabIndex = 14;
            this.lvAttributes.UseCompatibleStateImageBehavior = false;
            this.lvAttributes.View = System.Windows.Forms.View.Details;
            // 
            // colKey
            // 
            this.colKey.Text = "属性名";
            this.colKey.Width = 150;
            // 
            // colValue
            // 
            this.colValue.Text = "属性值";
            this.colValue.Width = 150;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(21, 15);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(56, 17);
            this.label2.TabIndex = 13;
            this.label2.Text = "属性列表";
            // 
            // AddNodeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(366, 360);
            this.Controls.Add(this.btnChangeValue);
            this.Controls.Add(this.lvAttributes);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnConfirm);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "AddNodeForm";
            this.Text = "AddNodeForm";
            this.Load += new System.EventHandler(this.AddNodeForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnConfirm;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnChangeValue;
        private System.Windows.Forms.ListView lvAttributes;
        private System.Windows.Forms.ColumnHeader colKey;
        private System.Windows.Forms.ColumnHeader colValue;
        private System.Windows.Forms.Label label2;
    }
}