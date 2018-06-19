package Aula02;



import java.util.Scanner;
 
public class POO_aula02_ex07{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int ano, mes,dias=0;
         
        System.out.println("Insira o ano: ");
        ano = sc.nextInt();
        System.out.println("Insira o mês(1 a 12): ");
        mes = sc.nextInt();
         
        if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
        dias = 31;
        else if(mes == 4 || mes == 6 || mes == 9 || mes == 11)
        dias = 30;
        else if(mes == 2){
            if((ano%4==0) && (ano%100 != 0 || ano%400 == 0)){
                dias = 29;
                }
            else{
                dias = 28;
                }
            }
             
        switch(mes){
        case 1: System.out.printf("O mês de Janeiro do ano %d tem %d dias",ano,dias);break;
        case 2: System.out.printf("O mês de Fevereiro do ano %d tem %d dias",ano,dias);break;
        case 3: System.out.printf("O mês de Março do ano %d tem %d dias",ano,dias);break;
        case 4: System.out.printf("O mês de Abril do ano %d tem %d dias",ano,dias);break;
        case 5: System.out.printf("O mês de Maio do ano %d tem %d dias",ano,dias);break;
        case 6: System.out.printf("O mês de Junho do ano %d tem %d dias",ano,dias);break;
        case 7: System.out.printf("O mês de Julho do ano %d tem %d dias",ano,dias);break;
        case 8: System.out.printf("O mês de Agosto do ano %d tem %d dias",ano,dias);break;
        case 9: System.out.printf("O mês de Setembro do ano %d tem %d dias",ano,dias);break;
        case 10: System.out.printf("O mês de Outubro do ano %d tem %d dias",ano,dias);break;
        case 11: System.out.printf("O mês de Novembro do ano %d tem %d dias",ano,dias);break;
        case 12: System.out.printf("O mês de Dezembro do ano %d tem %d dias",ano,dias);break;
        }
    }
}