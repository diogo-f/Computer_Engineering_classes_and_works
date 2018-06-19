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
    public partial class AreaFornecedores : Form
    {
        SQLconn conn = new SQLconn();

        public AreaFornecedores()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void AreaFornecedores_Load_1(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            SqlDataAdapter sda;
            DataTable dt;
            conn.getConn();
            conn.con.Open();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_FORNECEDORES", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
            conn.con.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AdicionarFornecedor add = new AdicionarFornecedor();
            add.ShowDialog();
            loadData();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            conn.getConn();
            SqlCommand sql = new SqlCommand("grocery.sp_EditarFornecedor", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("@nome", textBox2.Text);
            sql.Parameters.AddWithValue("@contacto", textBox4.Text);
            sql.Parameters.AddWithValue("@nif", label4.Text);

            try
            {
                conn.con.Open();
                sql.ExecuteNonQuery();
            }
            catch (SqlException)
            {
                MessageBox.Show("Erro ao inserir", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            conn.con.Close();

            textBox2.Clear();
            textBox4.Clear();
            label4.Text = "--";
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
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISAFORNECEDORESNOME(@nome)", conn.con);
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
                //gets a collection that contains all the rows
                DataGridViewRow row = this.dataGridView1.Rows[e.RowIndex];
                //populate the textbox from specific value of the coordinates of column and row.
                label4.Text = row.Cells[0].Value.ToString();
                textBox4.Text = row.Cells[1].Value.ToString();
                textBox2.Text = row.Cells[2].Value.ToString();
                
                conn.getConn();
            }

        }
    }
}
