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
    public partial class AreaIvas : Form
    {
        SQLconn conn = new SQLconn();
        public AreaIvas()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void AreaIvas_Load(object sender, EventArgs e)
        {
            LoadData();
        }

        private void LoadData()
        {
            SqlDataAdapter sda;
            DataTable dt;
            conn.getConn();
            conn.con.Open();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_IVAS", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
            conn.con.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text == "" || textBox2.Text == "")
            {
                MessageBox.Show("Preencha todos os Parametros", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                conn.getConn();
                SqlCommand sqlaux = new SqlCommand("grocery.sp_AreaIvas", conn.con);
                sqlaux.CommandType = CommandType.StoredProcedure;
                sqlaux.Parameters.AddWithValue("Nome", textBox1.Text);
                sqlaux.Parameters.AddWithValue("Percentagem", int.Parse(textBox2.Text));
                sqlaux.Parameters.AddWithValue("@Estado", checkBox1.Checked ? "1" : "0");
                try
                {
                    conn.con.Open();
                    sqlaux.ExecuteNonQuery();
                }
                catch (SqlException)
                {
                    MessageBox.Show("Erro ao Inserir", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }

                conn.con.Close();
                LoadData();
            }
        }
    }
}
