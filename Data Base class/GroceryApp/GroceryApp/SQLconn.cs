using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace GroceryApp
{
    class SQLconn
    {
        public SqlConnection con;

        public void getConn()
        {
            // Local DB
            //con = new SqlConnection("Data Source = DESKTOP-2M5P3FU; Initial Catalog = db_project_grocery; Integrated Security = True; ");
            // VM DB
            con = new SqlConnection("Data Source=tcp:193.136.175.33\\SQLSERVER2012, 8293;Initial Catalog=p5g9;User ID=p5g9;Password=expired");
        }
    }
}
