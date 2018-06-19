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
    public partial class AreaClientes : Form
    {
        SQLconn conn = new SQLconn();

        public AreaClientes()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void AreaClientes_Load_1(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            SqlDataAdapter sda;
            DataTable dt;
            conn.getConn();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_CLIENTES", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AdicionarCliente add = new AdicionarCliente();
            add.ShowDialog();
            loadData();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            conn.getConn();
            SqlCommand sql = new SqlCommand("grocery.sp_EditarCliente", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("@nome", textBox2.Text);
            sql.Parameters.AddWithValue("@nif",label9.Text);
            sql.Parameters.AddWithValue("@morada", textBox3.Text);
            sql.Parameters.AddWithValue("@contacto", textBox4.Text);
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
                sql.Parameters.AddWithValue("@nome",nome);
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
                textBox2.Text = row.Cells[0].Value.ToString();
                label9.Text = row.Cells[1].Value.ToString();
                textBox3.Text = row.Cells[2].Value.ToString();
                textBox4.Text = row.Cells[3].Value.ToString();
                try
                {
                    conn.getConn();
                    conn.con.Open();
                    SqlCommand sql = new SqlCommand("SELECT grocery.UDF_PESQUISACONTACORRENTENIF(@nif)", conn.con);
                    sql.Parameters.AddWithValue("@nif", label9.Text);
                    label5.Text = Convert.ToString(sql.ExecuteScalar());
                    conn.con.Close();
                }
                catch (SqlException)
                {
                }
            }
        }
    }
}
