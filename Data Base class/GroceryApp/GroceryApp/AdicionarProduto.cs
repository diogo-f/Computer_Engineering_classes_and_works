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
    public partial class AdicionarProduto: Form
    {
        SQLconn conn = new SQLconn();
        public AdicionarProduto()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void SaveButton_Click(object sender, EventArgs e)
        {
            int a = 0;
            string[] codIVA = this.comboBox1.GetItemText(this.comboBox1.SelectedItem).Split('-');

            if (nomeTextbox.Text == "" || lucroTextbox.Text == "" || pvpTextBox.Text == "" || stockMINTextbox.Text == "" || stockAtualTextbox.Text == "" || codIVA.Length==0)
            {
                MessageBox.Show("Preencha todos os dados!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else if (pvpTextBox.Text.Split(',').Length > 1)
            {
                MessageBox.Show("Preço mal preenchido!" + Environment.NewLine + Environment.NewLine + "Exemplo:" + Environment.NewLine + "1.50 e não 1,50!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {

                conn.getConn();
                SqlCommand sql = new SqlCommand("grocery.sp_CriarProduto", conn.con);
                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.AddWithValue("Nome", nomeTextbox.Text);
                sql.Parameters.AddWithValue("PercLucro", int.Parse(lucroTextbox.Text));
                sql.Parameters.AddWithValue("PVP", pvpTextBox.Text);
                sql.Parameters.AddWithValue("stockMIN", int.Parse(stockMINTextbox.Text));
                sql.Parameters.AddWithValue("stockATUAL", int.Parse(stockAtualTextbox.Text));
                sql.Parameters.AddWithValue("codIVA", codIVA[0]);
                try
                {
                    conn.con.Open();
                    sql.ExecuteNonQuery();
                }
                catch (SqlException)
                {
                    a = 1;
                    MessageBox.Show("Erro ao inserir, verifique", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                conn.con.Close();
                if (a!=1)
                {
                    this.Close();
                }
            }
        }

        private void AdicionarProduto_Load(object sender, EventArgs e)
        {
            
            conn.getConn();
            SqlCommand sql = new SqlCommand("SELECT * FROM grocery.VIEW_IVAS_ACTIVOS", conn.con);
            SqlDataAdapter sqladap = new SqlDataAdapter(sql);
            DataSet ds = new DataSet();
            sqladap.Fill(ds);

            foreach (DataRow dr in ds.Tables[0].Rows)
            {
                comboBox1.Items.Add(dr["IVA"].ToString());
            }
        }
    }
}
