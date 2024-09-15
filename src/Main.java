import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        Cliente joao = new PessoaFisica("João", "Av. Antonio Carlos, 6627",
                                        new Date(), "111.111.111-11", 36, 'm');

        Cliente matheus = new PessoaFisica("Matheus", "Av. Antonio Carlos, 6627",
                new Date(), "111.111.111-11", 17, 'm');

        Cliente alex = new PessoaFisica("Alex", "Av. Antonio Carlos, 6627",
                new Date(), "111.111.111-11", 36, 'm');


        Conta contaJoao = new ContaCorrente(1234, joao, 100, 50);

        Conta contaMatheus = new ContaCorrente(5678, matheus, 1000, 1500);


        //Questão 1:

        //Aqui o saque excede o saldo da conta
        try {
            contaJoao.sacar(110);

        }catch(ValorInvalidoException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }catch(ValorNegativoException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }catch(SemLimiteException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }

        //Aqui o saque é negativo
        try {
            contaJoao.sacar(-100);
        }catch(ValorInvalidoException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }catch(ValorNegativoException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }catch(SemLimiteException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }

        //Aqui o saque excede o limite da conta
        try {
            contaJoao.sacar(60);
        }catch(ValorInvalidoException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }catch(ValorNegativoException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }catch(SemLimiteException e){
            System.out.println("Erro ao sacar: "+ e.getMessage());
        }

        //Questão 2:

        try {
            Conta contaAlex = new ContaCorrente(9101112, alex, 500, -1000);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
}