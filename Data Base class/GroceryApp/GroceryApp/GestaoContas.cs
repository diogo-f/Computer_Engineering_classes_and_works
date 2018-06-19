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
    public partial class GestaoContas : Form
    {
        SQLconn conn = new SQLconn();
        public GestaoContas()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
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

        private void GestãoContas_Load(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
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
            label4.Text = "---";
            label7.Text = "---";
            label5.Text = "---";
            textBox3.Text = "";
        }

        private void dataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                //gets a collection that contains all the rows
                DataGridViewRow row = this.dataGridView1.Rows[e.RowIndex];
                //populate the textbox from specific value of the coordinates of column and row.
                label4.Text = row.Cells[0].Value.ToString();
                label7.Text = row.Cells[1].Value.ToString();
                

                try
                {
                    conn.getConn();
                    conn.con.Open();
                    SqlCommand sql = new SqlCommand("SELECT grocery.UDF_PESQUISACONTACORRENTENIF(@nif)", conn.con);
                    sql.Parameters.AddWithValue("@nif", label7.Text);
                    label5.Text = Convert.ToString(sql.ExecuteScalar());
                }
                catch (SqlException)
                {
                }
                conn.con.Close();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Boolean done = true;
            conn.getConn();
            SqlCommand sql = new SqlCommand("grocery.sp_InsertINContaCorrente", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("idCliente", label7.Text);
            sql.Parameters.AddWithValue("valor", textBox3.Text);
            try
            {
                conn.con.Open();
                sql.ExecuteNonQuery();
            }
            catch (SqlException)
            {
                done = false;
                MessageBox.Show("Erro ao inserir", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            conn.con.Close();
            if (done)
            {
                MessageBox.Show("Pagamento recebido", "sucesso", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            loadData();
            this.Close();
        }

    }

}
