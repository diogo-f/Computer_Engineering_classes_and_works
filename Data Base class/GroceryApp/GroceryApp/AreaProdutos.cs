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
    public partial class AreaProdutos : Form
    {
        SQLconn conn = new SQLconn();

        public AreaProdutos()
        {
            InitializeComponent();
            this.WindowState = FormWindowState.Maximized;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void AreaProdutos_Load_1(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            SqlDataAdapter sda;
            DataTable dt;
            conn.getConn();
            conn.con.Open();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_PRODUTO_IVA", conn.con);
            sda = new SqlDataAdapter(sql);
            dt = new DataTable();
            sda.Fill(dt);
            dataGridView1.DataSource = dt;
            conn.con.Close();
            conn.con.Open();
            sql = new SqlCommand("SELECT * FROM grocery.VIEW_IVAS_ACTIVOS", conn.con);
            SqlDataAdapter sqladap = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            sqladap.Fill(ds);
            conn.con.Close();
            foreach (DataRow dr in ds.Tables[0].Rows)
            {
                comboBox1.Items.Add(dr["IVA"].ToString());
            }
            checkBox1.Checked = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AdicionarProduto add = new AdicionarProduto();
            add.ShowDialog();
            loadData();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            string[] codIVA = this.comboBox1.GetItemText(this.comboBox1.SelectedItem).Split('-');

            conn.getConn();
            SqlCommand sql;
            if (checkBox1.Checked)
            {
                sql = new SqlCommand("grocery.sp_EditarProdutoComIVA", conn.con);
            }
            else
            {
                sql = new SqlCommand("grocery.sp_EditarProdutoSemIVA", conn.con);
            }
            sql.CommandType = CommandType.StoredProcedure;
            sql.Parameters.AddWithValue("@nome", textBox2.Text);
            sql.Parameters.AddWithValue("@percLucro", textBox3.Text);
            sql.Parameters.AddWithValue("@pvp", textBox6.Text);
            sql.Parameters.AddWithValue("@stockMin", textBox4.Text);
            sql.Parameters.AddWithValue("@stockatual", textBox5.Text);
            if (checkBox1.Checked)
            {
                sql.Parameters.AddWithValue("@codIva", codIVA[0]);
            }
            sql.Parameters.AddWithValue("@id", label10.Text);

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
            textBox3.Clear();
            textBox6.Clear();
            textBox4.Clear();
            textBox5.Clear();
            label10.Text = "--";
            comboBox1.Items.Clear();
            comboBox1.ResetText();
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
                SqlDataAdapter sda;
                DataTable dt;
                conn.getConn();
                conn.con.Open();
                SqlCommand sql = new SqlCommand("SELECT * FROM grocery.UDF_PESQUISARARTIGOSNOME(@Artigo)", conn.con);
                sql.Parameters.AddWithValue("@Artigo", textBox1.Text);
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
                label10.Text = row.Cells[0].Value.ToString();
                textBox2.Text = row.Cells[1].Value.ToString();
                textBox4.Text = row.Cells[2].Value.ToString();
                textBox5.Text = row.Cells[3].Value.ToString();
               // IVAlabel11.Text = row.Cells[4].Value.ToString();
                textBox6.Text = row.Cells[5].Value.ToString();
                
                conn.getConn();
                conn.con.Open();
                SqlCommand sql = new SqlCommand("SELECT grocery.UDF_GETLUCROPORARTIGO(@idArtigo)", conn.con);
                sql.Parameters.AddWithValue("@idArtigo", label10.Text);
                textBox3.Text = sql.ExecuteScalar().ToString();
                conn.con.Close();
            }

        }

    }
}
