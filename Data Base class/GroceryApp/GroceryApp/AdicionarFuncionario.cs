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
    public partial class AdicionarFuncionario: Form
    {
        SQLconn conn = new SQLconn();
        public AdicionarFuncionario()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void SaveButton_Click(object sender, EventArgs e)
        {
            if (nomeTextbox.Text == "" || moradaTextbox.Text == "" || contactoTextbox.Text == "" || nifTextbox.Text == "" || ccTextBox.Text=="" || salarioTextBox.Text=="")
            {
                MessageBox.Show("Preencha todos os dados!", "Erro", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                conn.getConn();
                SqlCommand sql = new SqlCommand("grocery.sp_CriarFuncionario", conn.con);
                sql.CommandType = CommandType.StoredProcedure;
                sql.Parameters.AddWithValue("nif", int.Parse(nifTextbox.Text));
                sql.Parameters.AddWithValue("nome", nomeTextbox.Text);
                sql.Parameters.AddWithValue("morada", moradaTextbox.Text);
                sql.Parameters.AddWithValue("contacto", contactoTextbox.Text);
                sql.Parameters.AddWithValue("cc", ccTextBox.Text);
                sql.Parameters.AddWithValue("salario", salarioTextBox.Text);
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
                this.Close();
            }
        }
    }
}
