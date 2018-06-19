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
    public partial class NovaEncomenda : Form
    {
        SQLconn conn = new SQLconn();
        private Boolean bol = true;
        public NovaEncomenda()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void NovaEncomenda_Load(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            SqlDataAdapter sda;
            DataTable dt;
            SqlCommand sql;
            textBox3.Text = "1";

            comboBox1.Items.Clear();
            //adicionar todos os fornecedores à dataGridView
            conn.getConn();
            conn.con.Open();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_FORNECEDORES", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView3.DataSource = dt;
            conn.con.Close();
            conn.con.Open();
            //adicionar todos os funcionarios na comboBox    
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_FUNCIONARIOS_NIFnome", conn.con);
            sda = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            sda.Fill(ds);
            conn.con.Close();
            foreach (DataRow dr in ds.Tables[0].Rows)
            {
                comboBox1.Items.Add(dr["Funcionario"].ToString());
            }

            //adicionar todos os produtos à dataGridView
            //conn.getConn();
            conn.con.Open();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_PRODUTO_NAVENDA", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;

            if (bol)
            {
                bol = false;
                //adicionar colunas na datagridview lista de compras
                dataGridView2.Columns.Add("Column", "ID");
                dataGridView2.Columns.Add("Column", "Artigo");
                dataGridView2.Columns.Add("Column", "Quant.");
                dataGridView2.Columns.Add("Column", "Preço");
                dataGridView2.Columns.Add("Column", "IVA");
            }
            conn.con.Close();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            AdicionarFornecedor add = new AdicionarFornecedor();
            add.ShowDialog();
            loadData();
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(textBox2.Text))
            {
                loadData();
            }
            else
            {
                String nome = textBox2.Text;
                SqlDataAdapter sda;
                DataTable dt;
                conn.getConn();
                conn.con.Open();
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISAFORNECEDORESNOME(@nome)", conn.con);
                sql.Parameters.AddWithValue("@nome", nome);
                sda = new SqlDataAdapter(sql);
                dt = new DataTable();
                sda.Fill(dt);
                dataGridView3.DataSource = dt;
                conn.con.Close();
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(textBox1.Text))
            {
                loadData();
            }
            else
            {
                String nome = textBox1.Text;
                SqlDataAdapter sda;
                DataTable dt;
                conn.getConn();
                conn.con.Open();
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISARARTIGOSNOME(@nome)", conn.con);
                sql.Parameters.AddWithValue("@nome", nome);
                sda = new SqlDataAdapter(sql);
                dt = new DataTable();
                sda.Fill(dt);
                dataGridView1.DataSource = dt;
                conn.con.Close();
            }
        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            SqlDataAdapter sda;
            DataTable dt;
            SqlCommand sql;

            if (checkBox1.Checked)
            {
                conn.getConn();
                conn.con.Open();
                sql = new SqlCommand("SELECT * FROM grocery.VIEW_PRODUTO_STOCKMINIMO", conn.con);
                sda = new SqlDataAdapter(sql);
                dt = new DataTable();
                sda.Fill(dt);
                dataGridView1.DataSource = dt;
                conn.con.Close();
            }
            else
            {
                conn.getConn();
                conn.con.Open();
                sql = new SqlCommand("SELECT * FROM grocery.VIEW_PRODUTO_NAVENDA", conn.con);
                sda = new SqlDataAdapter(sql);
                dt = new DataTable();
                sda.Fill(dt);
                dataGridView1.DataSource = dt;
                conn.con.Close();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {

            Boolean adicionar = true;
            string id ="";
            string artigo ="";
            string preco ="";
            string iva ="";
            int quantidade = 1;
            try
            {

                quantidade = int.Parse(textBox3.Text.ToString());
            }
            catch (System.FormatException)
            {
                MessageBox.Show("Quantidade deve ser um numero inteiro!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            foreach (DataGridViewRow row in dataGridView1.SelectedRows)
            {
                id = row.Cells[0].Value.ToString();
                artigo = row.Cells[1].Value.ToString();
                iva = row.Cells[3].Value.ToString();
                preco = row.Cells[4].Value.ToString();
            }

            foreach (DataGridViewRow row in dataGridView2.Rows)
            {
                if (row.Cells[0].Value.ToString().Equals(id))
                {
                    row.Cells[2].Value = (int.Parse(row.Cells[2].Value.ToString()) + quantidade).ToString();
                    adicionar = false;
                    break;
                }
            }
            if (adicionar)
            {
                this.dataGridView2.Rows.Add(id, artigo, quantidade.ToString(), preco, iva);
            }

        }

        private void dataGridView3_CellMouseClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView3.Rows[e.RowIndex];
                label9.Text = row.Cells[0].Value.ToString();
                label10.Text = row.Cells[2].Value.ToString();
                label11.Text = row.Cells[1].Value.ToString();

            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            int idProdRem = 0;
            int quantidade = 1;
            try
            {

                quantidade = int.Parse(textBox3.Text.ToString());
            }
            catch (System.FormatException)
            {
                MessageBox.Show("Quantidade deve ser um numero inteiro!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            foreach (DataGridViewRow row in dataGridView2.SelectedRows)
            {
                idProdRem = int.Parse(row.Cells[0].Value.ToString());
                if (int.Parse(row.Cells[2].Value.ToString()) > 1 && (int.Parse(row.Cells[2].Value.ToString()) - quantidade) > 0)
                {
                    row.Cells[2].Value = (int.Parse(row.Cells[2].Value.ToString()) - quantidade).ToString();
                }
                else
                {
                    dataGridView2.Rows.RemoveAt(int.Parse(dataGridView2.CurrentCell.RowIndex.ToString()));
                }
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            string[] funcionario = this.comboBox1.GetItemText(this.comboBox1.SelectedItem).Split('-');
            int idEncomenda = 0;

            if (label9.Text == "---" || dataGridView2.Rows.Count == 0 || funcionario.Length == 0)
            {
                MessageBox.Show("Verifique se todos os campos estão preenchidos:" + Environment.NewLine + Environment.NewLine + "- Funcionario seleccionado;" + Environment.NewLine + "- Fornecedor seleccionado;" + Environment.NewLine + "- Lista de produtos a Encomendar não pode estar vazia;" + Environment.NewLine , "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            conn.getConn();
            SqlCommand sql = new SqlCommand("grocery.sp_CriarEncomenda", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("idForn", label9.Text);
            sql.Parameters.AddWithValue("idFunc", int.Parse(funcionario[0]));
            try
            {
                conn.con.Open();
                sql.ExecuteNonQuery();
            }
            catch (SqlException)
            {
                MessageBox.Show("Erro ao inserir, verifique", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            conn.con.Close();

            try
            {
                conn.getConn();
                conn.con.Open();
                sql = new SqlCommand("SELECT * FROM grocery.VIEW_ENCOMENDA_LAST_ID", conn.con);
                idEncomenda = int.Parse(Convert.ToString(sql.ExecuteScalar()));
            }
            catch (SqlException)
            {
            }
            conn.con.Close();
            foreach (DataGridViewRow row in dataGridView2.Rows)
            {
                conn.getConn();
                sql = new SqlCommand("grocery.sp_AdicionarProdutosEncomenda", conn.con);
                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.AddWithValue("idEncomenda", idEncomenda);
                sql.Parameters.AddWithValue("idProduto", row.Cells[0].Value);
                sql.Parameters.AddWithValue("quantidade", row.Cells[2].Value);
                try
                {
                    conn.con.Open();
                    sql.ExecuteNonQuery();
                }
                catch (SqlException)
                {
                    MessageBox.Show("Erro ao inserir produtos na Encomenda", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                conn.con.Close();
            }
            MessageBox.Show("Encomenda Efetuada com Sucesso", "Sucesso!", MessageBoxButtons.OK, MessageBoxIcon.Information);
            this.Close();
        }
    }
}
