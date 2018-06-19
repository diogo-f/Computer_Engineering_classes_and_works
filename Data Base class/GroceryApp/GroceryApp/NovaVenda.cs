using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GroceryApp
{
    public partial class NovaVenda : Form
    {
        SQLconn conn = new SQLconn();
        private Boolean bol = true;
        public NovaVenda()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void NovaVenda_Load(object sender, EventArgs e)
        {
            loadData();
        }
        private void loadData()
        {
            dataGridView1.DataSource = null;
            comboBox1.Items.Clear();
            comboBox2.Items.Clear();
            //adicionar todos os clientes à dataGridView
            SqlDataAdapter sda;
            DataTable dt;
            conn.getConn();
            conn.con.Open();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_CLIENTES", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
            conn.con.Close();
            //adicionar todos os produtos à dataGridView
            conn.getConn();
            conn.con.Open();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_PRODUTO_NAVENDA", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView2.DataSource = dt;
            conn.con.Close();
            //adicionar todos os funcionarios na comboBox
            conn.con.Open();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_FUNCIONARIOS_NIFnome", conn.con);
            sda = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            sda.Fill(ds);
            conn.con.Close();
            foreach (DataRow dr in ds.Tables[0].Rows)
            {
                comboBox1.Items.Add(dr["Funcionario"].ToString());
            }
            //adicionar os tipos de pagamento na combobox
            conn.con.Open();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_TIPO_PAGAMENTO", conn.con);
            sda = new SqlDataAdapter(sql);
            ds = new DataSet();
            sda.Fill(ds);
            conn.con.Close();
            foreach (DataRow dr in ds.Tables[0].Rows)
            {
                comboBox2.Items.Add(dr["TipoPagam"].ToString());
            }
            if (bol)
            {
                bol = false;
                //adicionar colunas na datagridview lista de compras
                dataGridView3.Columns.Add("Column", "ID");
                dataGridView3.Columns.Add("Column", "Artigo");
                dataGridView3.Columns.Add("Column", "Quant.");
                dataGridView3.Columns.Add("Column", "Preço");
                dataGridView3.Columns.Add("Column", "IVA");
                dataGridView3.Columns.Add("Column", "Desconto");
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AdicionarCliente add = new AdicionarCliente();
            add.ShowDialog();
            loadData();
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
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISACLIENTESNOME(@nome)", conn.con);
                sql.Parameters.AddWithValue("@nome", nome);
                sda = new SqlDataAdapter(sql);
                dt = new DataTable();
                sda.Fill(dt);
                dataGridView1.DataSource = dt;
                conn.con.Close();
            }
        }

        private void dataGridView1_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = this.dataGridView1.Rows[e.RowIndex];
                label5.Text = row.Cells[1].Value.ToString();

                try
                {
                    conn.getConn();
                    conn.con.Open();
                    SqlCommand sql = new SqlCommand("SELECT grocery.UDF_PESQUISACONTACORRENTENIF(@nif)", conn.con);
                    sql.Parameters.AddWithValue("@nif", label5.Text);
                    label13.Text = Convert.ToString(sql.ExecuteScalar());
                }
                catch (SqlException)
                {
                }
                conn.con.Close();
            }
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(textBox2.Text))
            {
                loadData();
            }
            else
            {
                SqlDataAdapter sda;
                DataTable dt;
                conn.getConn();
                conn.con.Open();
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISARARTIGOSNOMENAVENDA(@artigo)", conn.con);
                sql.Parameters.AddWithValue("@artigo", textBox2.Text);
                sda = new SqlDataAdapter(sql);
                dt = new DataTable();
                sda.Fill(dt);
                dataGridView2.DataSource = dt;
                conn.con.Close();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Boolean adicionar = true;
            //Boolean decrementar = false;
            string id="";
            string artigo="";
            int quantidade = 1;
            string preco="";
            string iva="";
            int quantMaxVenda = 0;

            foreach (DataGridViewRow row in dataGridView2.SelectedRows)
            {
                id = row.Cells[0].Value.ToString();
                artigo = row.Cells[1].Value.ToString();
                iva = row.Cells[3].Value.ToString();
                preco = row.Cells[4].Value.ToString();
                //quantidade maxima que pode ser vendida pelo stock atual disponivel
                quantMaxVenda = int.Parse(row.Cells[2].Value.ToString());
            }
            //procurar o index da row pelo id 
            foreach (DataGridViewRow row in dataGridView3.Rows)
            {
                if (row.Cells[0].Value.ToString().Equals(id))
                {
                    if (row.Cells[2].Value.ToString().Equals(quantMaxVenda.ToString()))
                    {
                        MessageBox.Show("Stock não disponivel!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        adicionar = false;
                    }
                    else
                    {
                        row.Cells[2].Value = (int.Parse(row.Cells[2].Value.ToString()) + 1).ToString();
                        adicionar = false;
                        break;
                    }
                }
            }
            
            if (adicionar && quantMaxVenda > 0)
            {
                this.dataGridView3.Rows.Add(id, artigo, quantidade.ToString(), preco, iva, 0);
            }
            else if (quantMaxVenda==0)
            {
                MessageBox.Show("Stock não disponivel!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            label9.Text = (int.Parse(label9.Text)+1).ToString();
            label11.Text = (double.Parse(label11.Text.Replace(",", "."), CultureInfo.InvariantCulture) + double.Parse(preco, CultureInfo.InvariantCulture)).ToString();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            int idProdRem = 0;
            foreach (DataGridViewRow row in dataGridView3.SelectedRows)
            {
                idProdRem = int.Parse(row.Cells[0].Value.ToString());
                if (int.Parse(row.Cells[2].Value.ToString()) > 1)
                {
                    row.Cells[2].Value = (int.Parse(row.Cells[2].Value.ToString()) - 1).ToString();
                }
                else
                {
                    dataGridView3.Rows.RemoveAt(int.Parse(dataGridView3.CurrentCell.RowIndex.ToString()));
                }
             }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            int idVenda = 0;
            string[] funcionario = this.comboBox1.GetItemText(this.comboBox1.SelectedItem).Split('-');
            string[] tipoPagm = this.comboBox2.GetItemText(this.comboBox2.SelectedItem).Split('-');


            if (tipoPagm.Length == 0 || label5.Text == "---" || dataGridView3.Rows.Count == 0 || funcionario.Length == 0)
            {
                MessageBox.Show("Verifique se todos os campos estão preenchidos:" + Environment.NewLine + Environment.NewLine + "- Funcionario seleccionado;" + Environment.NewLine + "- Cliente seleccionado;" + Environment.NewLine + "- Lista de produtos a vender não pode estar vazia;" + Environment.NewLine + "- Tipo de pagamento seleccionado.", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            conn.getConn();
            SqlCommand sql = new SqlCommand("grocery.sp_CRIARNOVAVENDA", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("nifCliente", label5.Text);
            sql.Parameters.AddWithValue("nifFunc", funcionario[0]);
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
                sql = new SqlCommand("SELECT * FROM grocery.VIEW_VENDA_LAST_ID", conn.con);
                idVenda = int.Parse(Convert.ToString(sql.ExecuteScalar()));
            }
            catch (SqlException)
            {
            }
            conn.con.Close();
            foreach (DataGridViewRow row in dataGridView3.Rows)
            {
                conn.getConn();
                sql = new SqlCommand("grocery.sp_AdicionarArtigoITEMvenda", conn.con);
                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.AddWithValue("idVenda", idVenda);
                sql.Parameters.AddWithValue("idProduto", row.Cells[0].Value);
                sql.Parameters.AddWithValue("quantidade", row.Cells[2].Value);
                sql.Parameters.AddWithValue("desconto", row.Cells[5].Value);
                try
                {
                    conn.con.Open();
                    sql.ExecuteNonQuery();
                }
                catch (SqlException)
                {
                    MessageBox.Show("Erro ao inserir produtos na venda", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                conn.con.Close();
            }

            conn.getConn();
            sql = new SqlCommand("grocery.sp_PagamentoTotalValorVenda", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("idVenda", idVenda);
            sql.Parameters.AddWithValue("tipoPagamento", tipoPagm[0]);

            try
            {
                conn.con.Open();
                sql.ExecuteNonQuery();
            }
            catch (SqlException)
            {
                MessageBox.Show("blah verifique", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            conn.con.Close();
            MessageBox.Show("Venda efectuada!", "sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
            this.Close();
        }
    }
}