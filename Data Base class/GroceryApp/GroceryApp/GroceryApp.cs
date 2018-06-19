using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GroceryApp
{
    public partial class GroceryApp : Form
    {
        public GroceryApp()
        {
            InitializeComponent();
        }
        
        private void GroceryApp_Load(object sender, EventArgs e)
        {

        }

        private void areaClientesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AreaClientes newMDIChild = new AreaClientes();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void areaFuncionariosToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AreaFuncionarios newMDIChild = new AreaFuncionarios();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void areaProdutosToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AreaProdutos newMDIChild = new AreaProdutos();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void areaFornecedoresToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AreaFornecedores newMDIChild = new AreaFornecedores();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void vendasToolStripMenuItem_Click(object sender, EventArgs e)
        {
            NovaVenda newMDIChild = new NovaVenda();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void editarIVAToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AreaIvas newMDIChild = new AreaIvas();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void novaEncomendaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            NovaEncomenda newMDIChild = new NovaEncomenda();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void receberEncomendasToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ReceberEncomenda newMDIChild = new ReceberEncomenda();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }

        private void gestãoDeContasToolStripMenuItem_Click(object sender, EventArgs e)
        {
            GestaoContas newMDIChild = new GestaoContas();
            newMDIChild.MdiParent = this;
            newMDIChild.Show();
        }
    }
}
