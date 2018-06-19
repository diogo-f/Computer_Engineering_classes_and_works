namespace GroceryApp
{
    partial class AdicionarFuncionario
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
            this.nifTextbox = new System.Windows.Forms.TextBox();
            this.moradaTextbox = new System.Windows.Forms.TextBox();
            this.contactoTextbox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.SaveButton = new System.Windows.Forms.Button();
            this.CancelarButton = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.ccTextBox = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.salarioTextBox = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // nomeTextbox
            // 
            this.nomeTextbox.Location = new System.Drawing.Point(75, 49);
            this.nomeTextbox.MaxLength = 49;
            this.nomeTextbox.Name = "nomeTextbox";
            this.nomeTextbox.Size = new System.Drawing.Size(243, 20);
            this.nomeTextbox.TabIndex = 0;
            // 
            // nifTextbox
            // 
            this.nifTextbox.Location = new System.Drawing.Point(75, 84);
            this.nifTextbox.MaxLength = 20;
            this.nifTextbox.Name = "nifTextbox";
            this.nifTextbox.Size = new System.Drawing.Size(118, 20);
            this.nifTextbox.TabIndex = 1;
            // 
            // moradaTextbox
            // 
            this.moradaTextbox.Location = new System.Drawing.Point(75, 121);
            this.moradaTextbox.MaxLength = 49;
            this.moradaTextbox.Name = "moradaTextbox";
            this.moradaTextbox.Size = new System.Drawing.Size(243, 20);
            this.moradaTextbox.TabIndex = 2;
            // 
            // contactoTextbox
            // 
            this.contactoTextbox.Location = new System.Drawing.Point(75, 158);
            this.contactoTextbox.MaxLength = 49;
            this.contactoTextbox.Name = "contactoTextbox";
            this.contactoTextbox.Size = new System.Drawing.Size(118, 20);
            this.contactoTextbox.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(31, 19);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(133, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "Adicionar novo funcionario";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(31, 52);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(38, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Nome:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(42, 87);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(27, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "NIF:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(23, 124);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(46, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Morada:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(16, 161);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(53, 13);
            this.label5.TabIndex = 8;
            this.label5.Text = "Contacto:";
            // 
            // SaveButton
            // 
            this.SaveButton.Location = new System.Drawing.Point(75, 248);
            this.SaveButton.Name = "SaveButton";
            this.SaveButton.Size = new System.Drawing.Size(75, 23);
            this.SaveButton.TabIndex = 9;
            this.SaveButton.Text = "Guardar";
            this.SaveButton.UseVisualStyleBackColor = true;
            this.SaveButton.Click += new System.EventHandler(this.SaveButton_Click);
            // 
            // CancelarButton
            // 
            this.CancelarButton.Location = new System.Drawing.Point(243, 248);
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
            this.label6.Location = new System.Drawing.Point(42, 198);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(24, 13);
            this.label6.TabIndex = 12;
            this.label6.Text = "CC:";
            // 
            // ccTextBox
            // 
            this.ccTextBox.Location = new System.Drawing.Point(75, 195);
            this.ccTextBox.MaxLength = 49;
            this.ccTextBox.Name = "ccTextBox";
            this.ccTextBox.Size = new System.Drawing.Size(118, 20);
            this.ccTextBox.TabIndex = 11;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(254, 171);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(42, 13);
            this.label7.TabIndex = 14;
            this.label7.Text = "Salario:";
            // 
            // salarioTextBox
            // 
            this.salarioTextBox.Location = new System.Drawing.Point(218, 195);
            this.salarioTextBox.MaxLength = 49;
            this.salarioTextBox.Name = "salarioTextBox";
            this.salarioTextBox.Size = new System.Drawing.Size(118, 20);
            this.salarioTextBox.TabIndex = 13;
            // 
            // AdicionarFuncionario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(379, 309);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.salarioTextBox);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.ccTextBox);
            this.Controls.Add(this.CancelarButton);
            this.Controls.Add(this.SaveButton);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.contactoTextbox);
            this.Controls.Add(this.moradaTextbox);
            this.Controls.Add(this.nifTextbox);
            this.Controls.Add(this.nomeTextbox);
            this.Name = "AdicionarFuncionario";
            this.Text = "AdicionarFuncionario";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox nomeTextbox;
        private System.Windows.Forms.TextBox nifTextbox;
        private System.Windows.Forms.TextBox moradaTextbox;
        private System.Windows.Forms.TextBox contactoTextbox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button SaveButton;
        private System.Windows.Forms.Button CancelarButton;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox ccTextBox;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox salarioTextBox;
    }
}