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
    public partial class ReceberEncomenda : Form
    {
        SQLconn conn = new SQLconn();
        public ReceberEncomenda()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void ReceberEncomenda_Load(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            SqlDataAdapter sda;
            SqlCommand sql;
            conn.getConn();
            conn.con.Open();
            comboBox1.Items.Clear();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_ID_ENCOMENDA_FORNECEDOR", conn.con);
            sda = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            sda.Fill(ds);
            conn.con.Close();
            foreach (DataRow dr in ds.Tables[0].Rows)
            {
                comboBox1.Items.Add(dr["Encomenda_fornecedor"].ToString());
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            string [] idenc = this.comboBox1.GetItemText(this.comboBox1.SelectedItem).Split('-');
            SqlDataAdapter sda;
            DataTable dt;
            //adicionar todos os produtos à dataGridView
            conn.getConn();
            conn.con.Open();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PRODUTOS_DAENCOMENDA(@idEncomenda)", conn.con);
            sql.Parameters.AddWithValue("@idEncomenda", int.Parse(idenc[0]));
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
            conn.con.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string[] idenc = this.comboBox1.GetItemText(this.comboBox1.SelectedItem).Split('-');
            SqlCommand sql;
            conn.getConn();
            sql = new SqlCommand("grocery.sp_AdicionarNumeroFatura", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("idEnc", int.Parse(idenc[0]));
            sql.Parameters.AddWithValue("numFat", textBox1.Text);
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

            foreach (DataGridViewRow row in dataGridView1.Rows)
            {
                conn.getConn();
                sql = new SqlCommand("grocery.sp_UpdateStockEncomendas", conn.con);
                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.AddWithValue("idProduto", row.Cells[0].Value);
                sql.Parameters.AddWithValue("quantidade", row.Cells[2].Value);

                try
                {
                    conn.con.Open();
                    sql.ExecuteNonQuery();
                }
                catch (SqlException)
                {
                    MessageBox.Show("Erro ao fazer UPDATE de produtos", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                conn.con.Close();
            }
            this.Close();
        }
    }
}

