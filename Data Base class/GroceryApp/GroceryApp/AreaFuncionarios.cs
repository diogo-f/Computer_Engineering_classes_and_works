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
    public partial class AreaFuncionarios : Form
    {
        SQLconn conn = new SQLconn();

        public AreaFuncionarios()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }


        private void AreaFuncionarios_Load_1(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            SqlDataAdapter sda;
            DataTable dt;
            conn.getConn();
            conn.con.Open();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_FUNCIONARIOS", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
            conn.con.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AdicionarFuncionario add = new AdicionarFuncionario();
            add.ShowDialog();
            loadData();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            conn.getConn();
            SqlCommand sql = new SqlCommand("grocery.sp_EditarFuncionario", conn.con);
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("@Nif", label9.Text);
            sql.Parameters.AddWithValue("@Nome", textBox2.Text);
            sql.Parameters.AddWithValue("@Morada", textBox3.Text);
            sql.Parameters.AddWithValue("@Contacto", textBox4.Text);
            sql.Parameters.AddWithValue("@Salario", textBox5.Text);
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
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISAFUNCIONARIOSNOME(@nome)", conn.con);
                sql.Parameters.AddWithValue("@Nome", nome);
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
                textBox2.Text = row.Cells[0].Value.ToString();
                label9.Text = row.Cells[1].Value.ToString();
                textBox3.Text = row.Cells[2].Value.ToString();
                textBox4.Text = row.Cells[3].Value.ToString();
                label11.Text = row.Cells[4].Value.ToString();
                textBox5.Text = row.Cells[5].Value.ToString();
            }

        }
    }
}
