using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Threading;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Assignment5.Sorting;

namespace Assignment5
{
    public partial class ProgressesForm : Form
    {
        private List<List<Button>> buttons;
        private Action<Button, Button> swap;
        private int completedCount;

        public Color GetProgressBarBackgroundColor(double percent)
        {
            return Color.FromArgb(0, (int)(percent / 100 * 255), 0);
        }

        public ProgressesForm(List<List<Button>> buttons, Action<Button, Button> swap)
        {
            InitializeComponent();
            this.buttons = buttons;
            this.swap = swap;

        }

        private void Run(Sort sort, ProgressBar pb, Label lb)
        {
            int count = 0;

            int stepCount = sort.StepCount;

            if (stepCount != 0)
            {
                sort.Swap = (i, j) =>
                {
                    count++;
                    Invoke(new Action(() =>
                    {
                        swap(sort.Buttons[i], sort.Buttons[j]);
                        double percent = (double)count / stepCount * 100;
                        pb.Value = (int)percent;
                        pb.BackColor = GetProgressBarBackgroundColor(percent);
                        lb.Text = $"{count}/{stepCount} {percent.ToString("0.##")}%";
                    }));
                    Thread.Sleep(300);

                };
                sort.SortButtons();
            } else
            {
                Invoke(new Action(() =>
                {
                    pb.Value = 100;
                    pb.BackColor = GetProgressBarBackgroundColor(100);
                    lb.Text = $"0/0 100%";
                }));

            }

            int result = Interlocked.Increment(ref completedCount);
            if (result == 3)
            {
                Thread.Sleep(500);
                Invoke(new Action(() =>
                {
                    Close();
                }));
            }
        }

        private void ProgressesForm_Load(object sender, EventArgs e)
        {
            // Start threads

            var bubbleThread = new Thread(() =>
            {
                Run(new BubbleSort(buttons[0]), pbBubble, lbBubble);

            });

            var selectionThread = new Thread(() =>
            {
                Run(new SelectionSort(buttons[1]), pbSelection, lbSelection);
            });

            var quickThread = new Thread(() =>
            {
                Run(new QuickSort(buttons[2]), pbQuick, lbQuick);
            });

            bubbleThread.Start();
            selectionThread.Start();
            quickThread.Start();
           
        }
    }
}
