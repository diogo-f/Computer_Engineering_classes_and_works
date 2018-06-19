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
    public partial class AdicionarFornecedor: Form
    {
        SQLconn conn = new SQLconn();
        public AdicionarFornecedor()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void SaveButton_Click(object sender, EventArgs e)
        {

            if (nifTextbox.Text == "" || contactoTextbox.Text == "" || nomeTextbox.Text == "")
            {
                MessageBox.Show("Preencha todos os Parametros", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                conn.getConn();
                SqlCommand sql = new SqlCommand("grocery.sp_AdicionarFornecedores", conn.con);
                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.AddWithValue("@nif", nifTextbox.Text);
                sql.Parameters.AddWithValue("@contacto", contactoTextbox.Text);
                sql.Parameters.AddWithValue("@nome", nomeTextbox.Text);
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
                this.Close();
            }
        }
    }
}
