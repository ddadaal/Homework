using System;
using System.IO;
using System.Windows.Forms;

namespace InfoControl
{

    public delegate void FileChangedHandler(string path);
    public partial class InfoControl : UserControl
    {
        public event FileChangedHandler FileChanged;

        public InfoControl()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog dialog = new FolderBrowserDialog();
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                var path = dialog.SelectedPath;
                ListDirectory(path);
            }

        }

        private void TreeViewDirectory_NodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            var file = e.Node.Name;
            FileChanged(file);
        }



        private void ListDirectory(string path)
        {
            treeViewDirectory.Nodes.Clear();
            var rootDirectoryInfo = new DirectoryInfo(path);
            treeViewDirectory.Nodes.Add(CreateDirectoryNode(rootDirectoryInfo));
        }

        private TreeNode CreateDirectoryNode(DirectoryInfo directoryInfo)
        {
            var directoryNode = new TreeNode(directoryInfo.Name)
            {
                Name = directoryInfo.FullName
            };
            foreach (var directory in directoryInfo.GetDirectories())
            {
                directoryNode.Nodes.Add(CreateDirectoryNode(directory));
            }
            foreach (var file in directoryInfo.GetFiles())
            {

                directoryNode.Nodes.Add(new TreeNode(file.Name)
                {
                    Name = file.FullName
                });
            }

            return directoryNode;
        }

        private void InfoControl_Load(object sender, EventArgs e)
        {

        }
    }
}
