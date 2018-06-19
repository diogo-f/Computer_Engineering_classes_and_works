namespace GroceryApp
{
    partial class AdicionarProduto
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.nomeTextbox = new System.Windows.Forms.TextBox();
            this.stockMINTextbox = new System.Windows.Forms.TextBox();
            this.stockAtualTextbox = new System.Windows.Forms.TextBox();
            this.lucroTextbox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.SaveButton = new System.Windows.Forms.Button();
            this.CancelarButton = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.pvpTextBox = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // nomeTextbox
            // 
            this.nomeTextbox.Location = new System.Drawing.Point(110, 49);
            this.nomeTextbox.MaxLength = 49;
            this.nomeTextbox.Name = "nomeTextbox";
            this.nomeTextbox.Size = new System.Drawing.Size(295, 20);
            this.nomeTextbox.TabIndex = 0;
            // 
            // stockMINTextbox
            // 
            this.stockMINTextbox.Location = new System.Drawing.Point(110, 82);
            this.stockMINTextbox.MaxLength = 20;
            this.stockMINTextbox.Name = "stockMINTextbox";
            this.stockMINTextbox.Size = new System.Drawing.Size(99, 20);
            this.stockMINTextbox.TabIndex = 1;
            // 
            // stockAtualTextbox
            // 
            this.stockAtualTextbox.Location = new System.Drawing.Point(306, 82);
            this.stockAtualTextbox.MaxLength = 49;
            this.stockAtualTextbox.Name = "stockAtualTextbox";
            this.stockAtualTextbox.Size = new System.Drawing.Size(99, 20);
            this.stockAtualTextbox.TabIndex = 2;
            // 
            // lucroTextbox
            // 
            this.lucroTextbox.Location = new System.Drawing.Point(110, 116);
            this.lucroTextbox.MaxLength = 49;
            this.lucroTextbox.Name = "lucroTextbox";
            this.lucroTextbox.Size = new System.Drawing.Size(99, 20);
            this.lucroTextbox.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(31, 19);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(117, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Adicionar novo produto";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(66, 52);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(38, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Nome:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(31, 85);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(73, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Stock minimo:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(235, 85);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(65, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Stock Atual:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(5, 119);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(99, 13);
            this.label5.TabIndex = 8;
            this.label5.Text = "Percentagem lucro:";
            // 
            // SaveButton
            // 
            this.SaveButton.Location = new System.Drawing.Point(73, 215);
            this.SaveButton.Name = "SaveButton";
            this.SaveButton.Size = new System.Drawing.Size(75, 23);
            this.SaveButton.TabIndex = 9;
            this.SaveButton.Text = "Guardar";
            this.SaveButton.UseVisualStyleBackColor = true;
            this.SaveButton.Click += new System.EventHandler(this.SaveButton_Click);
            // 
            // CancelarButton
            // 
            this.CancelarButton.Location = new System.Drawing.Point(273, 215);
            this.CancelarButton.Name = "CancelarButton";
            this.CancelarButton.Size = new System.Drawing.Size(75, 23);
            this.CancelarButton.TabIndex = 10;
            this.CancelarButton.Text = "Cancelar";
            this.CancelarButton.UseVisualStyleBackColor = true;
            this.CancelarButton.Click += new System.EventHandler(this.button2_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(270, 119);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(30, 13);
            this.label6.TabIndex = 12;
            this.label6.Text = "PvP:";
            // 
            // pvpTextBox
            // 
            this.pvpTextBox.Location = new System.Drawing.Point(306, 116);
            this.pvpTextBox.MaxLength = 49;
            this.pvpTextBox.Name = "pvpTextBox";
            this.pvpTextBox.Size = new System.Drawing.Size(99, 20);
            this.pvpTextBox.TabIndex = 11;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(80, 155);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(27, 13);
            this.label7.TabIndex = 14;
            this.label7.Text = "IVA:";
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Location = new System.Drawing.Point(110, 151);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(190, 21);
            this.comboBox1.TabIndex = 15;
            // 
            // AdicionarProduto
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(429, 266);
            this.Controls.Add(this.comboBox1);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.pvpTextBox);
            this.Controls.Add(this.CancelarButton);
            this.Controls.Add(this.SaveButton);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lucroTextbox);
            this.Controls.Add(this.stockAtualTextbox);
            this.Controls.Add(this.stockMINTextbox);
            this.Controls.Add(this.nomeTextbox);
            this.Name = "AdicionarProduto";
            this.Text = "AdicionarProduto";
            this.Load += new System.EventHandler(this.AdicionarProduto_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox nomeTextbox;
        private System.Windows.Forms.TextBox stockMINTextbox;
        private System.Windows.Forms.TextBox stockAtualTextbox;
        private System.Windows.Forms.TextBox lucroTextbox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button SaveButton;
        private System.Windows.Forms.Button CancelarButton;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox pvpTextBox;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.ComboBox comboBox1;
    }
}