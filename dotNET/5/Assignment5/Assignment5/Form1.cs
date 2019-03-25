using Assignment5.Sorting;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading;
using System.Windows.Forms;

namespace Assignment5
{
    public partial class Form1 : Form
    {
        private Random random = new Random();

        private Label[] labelAnchors;
        private List<List<Button>> buttons = new List<List<Button>>();

        public Form1()
        {
            InitializeComponent();
            labelAnchors = new Label[] { lbBubble, lbSelection, lbQuick };
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void CreateButtons(List<int> widths)
        {

            for (int col = 0; col < 3; col++)
            {
                var btns = new List<Button>();


                for (int index = 0; index < 10; index++)
                {
                    btns.Add(CreateButton(widths[index], col, index));
                }
                LocateButtons(btns, col);
                Controls.AddRange(btns.ToArray());
                buttons.Add(btns);
            }
        }

        
        private void RandomlyCreateButtons()
        {
            CreateButtons(GetRandomWidths(10));
        }


        private void Form1_Load(object sender, EventArgs e)
        {
            RandomlyCreateButtons();
        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            var form = new ProgressesForm(buttons, SwapButtons);
            form.Show();
        }

        private void btnShuffle_Click(object sender, EventArgs e)
        {
            var widths = buttons.First().Select(x => x.Width).OrderBy(x => random.Next()).ToList();
            RemoveAllButtons();
            CreateButtons(widths);
        }

        private void btnReset_Click(object sender, EventArgs e)
        {
            RemoveAllButtons();
            RandomlyCreateButtons();
        }
    }
}
