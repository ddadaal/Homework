using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Windows.Forms;

namespace Assignment4.Services
{
    public class DatabaseService: IDisposable
    {

        private SqlConnection conn;

        public DatabaseService()
        {
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["default"].ConnectionString);
            conn.Open();
        }
        public IEnumerable<string> GetDatabases()
        {
            UseDatabase("master");
            var databases = conn.GetSchema("Databases");

            return databases.AsEnumerable()
                .Select(r => r.Field<string>("database_name"));


        }

        private void UseDatabase(string database)
        {
            ExecuteNonQuery($"USE [{database}];");
        }

        public IEnumerable<string> GetTables(string database)
        {
            UseDatabase(database);
            var tables = conn.GetSchema("Tables");
            return tables.AsEnumerable()
                .Select(r => r.Field<string>("table_name"));

        }

        public void ExecuteNonQuery(string command)
        {
            var sqlCommand = new SqlCommand(command, conn);
            sqlCommand.ExecuteNonQuery();
        }

        public void RenameDatabase(string name, string newName)
        {
            UseDatabase("master");
            ExecuteNonQuery($"ALTER DATABASE [{name}] MODIFY NAME = [{newName}];");
        }

        public void DeleteDatabase(string database)
        {
            UseDatabase("master");
            ExecuteNonQuery($"DROP DATABASE [{database}];");
        }

        public void CreateDatabase(string database)
        {
            UseDatabase("master");
            ExecuteNonQuery($"CREATE DATABASE [{database}];");
        }

        public void DeleteTable(string database, string table)
        {
            UseDatabase(database);
            ExecuteNonQuery($"DROP TABLE [{table}];");
        }

        public void RenameTable(string database, string tableName, string newTableName)
        {
            UseDatabase(database);
            ExecuteNonQuery($"sp_rename '{tableName}', '{newTableName}'");
        }

        public void DeleteAll(string database, string tableName)
        {
            UseDatabase(database);
            ExecuteNonQuery($"DELETE FROM [{tableName}]");
        }

        public DataSet GetDataSet(string database, string table)
        {
            UseDatabase(database);
            var adapter = GetDataAdapter(database, table);

            return FillDataSet(adapter);
        }

        public Dictionary<string, string> GetTableColumnDefs(string database, string table)
        {
            UseDatabase(database);
            var columnsTable = conn.GetSchema("Columns", new[] { null, null, table, null });

            var dict = new Dictionary<string, string>();

            foreach (DataRow row in columnsTable.Rows)
            {
                dict.Add(row["COLUMN_NAME"] as string, row["DATA_TYPE"] as string);
            }

            return dict;
        }

        public void AddRow(string database, string table, Dictionary<string, string> values)
        {
            UseDatabase(database);
            ExecuteNonQuery($"INSERT INTO [{table}] ({string.Join(",",values.Keys.Select((x) => $"[{x}]"))}) VALUES ({string.Join(",", values.Values)});");
        }

        public void AddTable(string database, string table, Dictionary<string, string> columns)
        {
            UseDatabase(database);

            ExecuteNonQuery($"CREATE TABLE [{table}] ({string.Join(",", columns.Select(x => $"{x.Key} {x.Value}"))});");

        }

        public SqlDataAdapter GetDataAdapter(string database, string table)
        {
            UseDatabase(database);
            var adapter = new SqlDataAdapter($"SELECT * FROM [{table}]", conn);
            SqlCommandBuilder scb = new SqlCommandBuilder(adapter); 
            return adapter;
        }

        public DataSet FillDataSet(SqlDataAdapter adapter)
        {
            var dataSet = new DataSet();
            adapter.Fill(dataSet);
            adapter.FillSchema(dataSet, SchemaType.Source);
            dataSet.EnforceConstraints = false;
            return dataSet;
        }


        public void Dispose()
        {
            conn.Dispose();
        }
    }
}
